package com.weixin.Scenario.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.weixin.Devices.entity.Devices;
import com.weixin.Record.entity.Record;
import com.weixin.Record.mapper.RecordMapper;
import com.weixin.Scenario.entity.Operate;
import com.weixin.Scenario.entity.Scenario;
import com.weixin.Scenario.entity.ScenarioRes;
import com.weixin.Scenario.mapper.ScenarioMapper;
import com.weixin.Scenario.service.IScenarioService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixin.commons.Result;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-05
 */
@Service
public class ScenarioServiceImpl extends ServiceImpl<ScenarioMapper, Scenario> implements IScenarioService {
    @Autowired
    private ScenarioMapper scenarioMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private RecordMapper recordMapper;
    @Override
    public Result getScenarioData(String openId){
        QueryWrapper<Scenario> queryWrapper = new QueryWrapper<Scenario>()
                .eq("author", openId);
        List<Scenario> scenarioList=scenarioMapper.selectList(queryWrapper);
        return Result.SUCCESS(scenarioList);
    }

    @Override
    public Result getScenarioBySid(String openId,String sId){
        QueryWrapper<Scenario> queryWrapper = new QueryWrapper<Scenario>()
                .eq("author", openId).eq("s_id",sId);
        Scenario scenario=scenarioMapper.selectOne(queryWrapper);
        return Result.SUCCESS(scenario);
    }
    @Override
    public Result addScenario(Scenario scenario){
        String sId= UUID.randomUUID().toString().replace("-","");
        scenario.setSId(sId);
        scenarioMapper.insert(scenario);
        return Result.SUCCESS("添加成功");
    }
    @Override
    public Result editScenario(Scenario scenario){
        UpdateWrapper<Scenario> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("s_id", scenario.getSId()).eq("author",scenario.getAuthor());
        int affectedRows = scenarioMapper.update(scenario, updateWrapper);
        if (affectedRows > 0) {
            return Result.SUCCESS("数据更新成功");
        } else {
            return Result.FAIL("数据更新失败");
        }
    }
    @Override
    public Result deleteScenario(Scenario scenario){
        String openId=scenario.getAuthor();
        String sId=scenario.getSId();
        scenarioMapper.deleteBySid(openId,sId);
        return Result.SUCCESS("删除成功");
    }

    @Override
    public Result ctrlScenario(Scenario scenario) {
        List<Operate> operateList = scenario.getOperate();
        String msg = "";
        Record record=new Record();
        for (Operate operate : operateList) {
            String topic = operate.getTopic();
            System.out.println(topic);
            String modeCtrl;
            if (operate.isOperate()) {
                modeCtrl = "on";
            } else {
                modeCtrl = "off";
            }
            Destination destination = new ActiveMQTopic(topic);
            try {
                jmsTemplate.convertAndSend(destination, modeCtrl);
                // 如果没有抛出异常，则表示消息发送成功
                record.setRId(UUID.randomUUID().toString().replace("-",""));
                record.setDeviceName(operate.getName());
                record.setDeviceType(operate.getType());
                record.setDeviceRoom(operate.getRoomName());
                record.setAuthor(operate.getAuthor());
                record.setOperate(modeCtrl);
                recordMapper.insert(record);
                System.out.println("消息发送成功");
                msg = "消息发送成功";
            } catch (JmsException e) {
                // 发送异常，表示消息发送失败
                System.out.println("消息发送失败");
                return Result.FAIL();
            }
        }
        return Result.SUCCESS(msg);
    }
    //后台
    @Override
    public Result getScenarioAll(){
        List<ScenarioRes> scenarioList =scenarioMapper.selectAll();
        Collections.reverse(scenarioList);
        return Result.SUCCESS(scenarioList);
    }
    @Override
    public Result deleteScenarioData(Scenario scenario){
        String sId=scenario.getSId();
        scenarioMapper.deleteById(sId);
        return Result.SUCCESS("删除成功");
    }
    @Override
    public Result deleteScenarios(List<Scenario> scenarioList){
        for(Scenario scenario:scenarioList){
            scenarioMapper.deleteById(scenario.getSId());
        }
        return Result.SUCCESS("删除成功");
    }
}
