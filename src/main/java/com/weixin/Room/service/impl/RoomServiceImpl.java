package com.weixin.Room.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.weixin.Devices.entity.Devices;
import com.weixin.Devices.mapper.DevicesMapper;
import com.weixin.Room.entity.Room;
import com.weixin.Room.entity.RoomRes;
import com.weixin.Room.mapper.RoomMapper;
import com.weixin.Room.service.IRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixin.Scenario.entity.Operate;
import com.weixin.Scenario.entity.Scenario;
import com.weixin.commons.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-04
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private DevicesMapper devicesMapper;
    @Override
    public Result getRoomList(String openId){
        List<Room> roomList=roomMapper.selectByOpenId(openId);
        return Result.SUCCESS(roomList);
    }
    @Override
    public Result addRoomList(Room room){
        String roomId= UUID.randomUUID().toString().replace("-","");
        room.setRoomId(roomId);
        roomMapper.insert(room);
        return Result.SUCCESS("添加成功");
    }
    @Override
    public Result editRoomList(Room room){
        UpdateWrapper<Room> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("room_id", room.getRoomId()).eq("author",room.getAuthor());
        int affectedRows = roomMapper.update(room, updateWrapper);
        if (affectedRows > 0) {
            return Result.SUCCESS("数据更新成功");
        } else {
            return Result.FAIL("数据更新失败");
        }
    }
    @Override
    public Result deleteRoomList(Room room){
        String roomId=room.getRoomId();
        String openId=room.getAuthor();
        roomMapper.deleteByRoomId(roomId,openId);
        List<Devices> devicesList=devicesMapper.selectByRoom(openId,room.getName());
        for (Devices devices : devicesList) {
            devices.setRoomName("全屋");
            UpdateWrapper<Devices> devicesUpdateWrapper= new UpdateWrapper<>();
            devicesUpdateWrapper.eq("de_id", devices.getDeId()).eq("author",devices.getAuthor());
            devicesMapper.update(devices,devicesUpdateWrapper);
        }
        return Result.SUCCESS("删除成功");
    }
    //后台
    @Override
    public Result getRoomAll(){
        List<RoomRes> scenarioList =roomMapper.selectAll();
        return Result.SUCCESS(scenarioList);
    }
    @Override
    public Result deleteRoomData(Room room){
        String roomId=room.getRoomId();
        roomMapper.deleteRoom(roomId);
        return Result.SUCCESS("删除成功");
    }
    @Override
    public Result deleteRooms(List<Room> roomList){
        for(Room room:roomList){
            roomMapper.deleteRoom(room.getRoomId());
        }
        return Result.SUCCESS("删除成功");
    }
}
