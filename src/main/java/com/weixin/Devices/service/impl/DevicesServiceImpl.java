package com.weixin.Devices.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.weixin.Devices.entity.Devices;
import com.weixin.Devices.entity.DevicesCtrl;
import com.weixin.Devices.entity.DevicesRes;
import com.weixin.Devices.mapper.DevicesMapper;
import com.weixin.Devices.service.IDevicesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixin.Record.entity.Record;
import com.weixin.Record.mapper.RecordMapper;
import com.weixin.commons.Crypt;
import com.weixin.commons.Result;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.TextMessage;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-03
 */
@Service
public class DevicesServiceImpl extends ServiceImpl<DevicesMapper, Devices> implements IDevicesService {
    @Autowired
    private DevicesMapper devicesMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private RecordMapper recordMapper;
    @Override
    public Result getDevicesList(String openId,String room){
        if (room.equals("全屋")){
            List<Devices> devicesList=devicesMapper.selectByOpenId(openId);
            return Result.SUCCESS(devicesList);
        }
        else {
            List<Devices> devicesList=devicesMapper.selectByRoom(openId,room);
            return Result.SUCCESS(devicesList);
        }
    }
    @Override
    public Result getDevicesById(String openId,String deId){
        Devices devices= devicesMapper.selectByDeId(openId,deId);
        return Result.SUCCESS(devices);
    }
    @Override
    public Result addDevicesList(Devices devices){
        String deId= UUID.randomUUID().toString().replace("-","");
        devices.setDeId(deId);
        devices.setState("false");
        devices.setTopic(deId+devices.getCode());
        devicesMapper.insert(devices);
        return Result.SUCCESS("添加成功");
    }
    @Override
    public Result editDevicesList(Devices devices){
        UpdateWrapper<Devices> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("de_id", devices.getDeId()).eq("author",devices.getAuthor());
        int affectedRows = devicesMapper.update(devices, updateWrapper);
        if (affectedRows > 0) {
            return Result.SUCCESS("数据更新成功");
        } else {
            return Result.FAIL("数据更新失败");
        }
    }
    @Override
    public Result deleteDevicesList(Devices devices){
        String openId=devices.getAuthor();
        String deId=devices.getDeId();
        devicesMapper.deleteByRoom(openId,deId);
        return Result.SUCCESS("删除成功");
    }
    @Override
    public Result getBaFaDevices(String openId){
        List<Devices> devicesList = devicesMapper.selectBafaAll(openId);
        return Result.SUCCESS(devicesList);
    }
    @Override
    public Result getBaFaDevisesList(String openId,String room){
        if (room.equals("全屋")){
            List<Devices> devicesList=devicesMapper.selectByBafa(openId);
            return Result.SUCCESS(devicesList);
        }
        else {
            List<Devices> devicesList=devicesMapper.selectBafaByRoom(openId,room);
            return Result.SUCCESS(devicesList);
        }
    }
    @Override
    public Result ctrlDevice(DevicesCtrl devicesCtrl) {
        String operate = Crypt.deCrypt(devicesCtrl.getOperate());
        String topic = devicesCtrl.getTopic();
        Destination destination = new ActiveMQTopic(topic);
        try {
            jmsTemplate.convertAndSend(destination, operate);
            // 如果没有抛出异常，则表示消息发送成功
            System.out.println("消息发送成功");
            Record record=new Record();
            record.setRId(UUID.randomUUID().toString().replace("-",""));
            record.setDeviceName(devicesCtrl.getDeName());
            record.setDeviceType(devicesCtrl.getDeType());
            record.setDeviceRoom(devicesCtrl.getDeRoom());
            record.setAuthor(devicesCtrl.getAuthor());
            record.setOperate(operate);
            recordMapper.insert(record);
            return Result.SUCCESS();
        } catch (JmsException e) {
            // 发送异常，表示消息发送失败
            System.out.println("消息发送失败: " + e.getMessage());
            return Result.FAIL();
        }
    }

    //后台
    @Override
    public Result getDeviceAll(){
        List<DevicesRes> devicesList =devicesMapper.selectAll();
        Collections.reverse(devicesList);
        return Result.SUCCESS(devicesList);
    }

    @Override
    public Result deleteDevice(Devices devices){
        String deId=devices.getDeId();
        devicesMapper.deleteDevice(deId);
        return Result.SUCCESS("删除成功");
    }
    @Override
    public Result deleteDevices(List<Devices> devicesList){
        for(Devices devices:devicesList){
            devicesMapper.deleteDevice(devices.getDeId());
        }
        return Result.SUCCESS("删除成功");
    }
    @Override
    public Result testZC() {
        String topic = "info_control";
        Destination destination = new ActiveMQTopic(topic);
        String msg ="{ \"device_id\": \"info\",\"temperature\": 20.3, \"humidity\": 40,\"light\": 60,\"smoke\": \"正常\",\"secretKey\": \"93dcb6d31ba04a2d88fe113ccea012a3\"}";
        try {
            jmsTemplate.convertAndSend(destination, msg.getBytes(StandardCharsets.UTF_8));
            // 如果没有抛出异常，则表示消息发送成功
            System.out.println("消息发送成功");
            return Result.SUCCESS();
        } catch (JmsException e) {
            // 发送异常，表示消息发送失败
            System.out.println("消息发送失败: " + e.getMessage());
            return Result.FAIL();
        }
    }
    @Override
    public Result testYC() {
        String topic = "info_control";
        Destination destination = new ActiveMQTopic(topic);
        String msg ="{ \"device_id\": \"info\",\"temperature\": 20.3, \"humidity\": 40,\"light\": 60,\"smoke\": \"异常\",\"secretKey\": \"93dcb6d31ba04a2d88fe113ccea012a3\"}";
        try {
            jmsTemplate.convertAndSend(destination, msg.getBytes(StandardCharsets.UTF_8));
            // 如果没有抛出异常，则表示消息发送成功
            System.out.println("消息发送成功");
            return Result.SUCCESS();
        } catch (JmsException e) {
            // 发送异常，表示消息发送失败
            System.out.println("消息发送失败: " + e.getMessage());
            return Result.FAIL();
        }
    }
}
