package com.weixin.Room.service;

import com.weixin.Room.entity.Room;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weixin.Scenario.entity.Scenario;
import com.weixin.commons.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-04
 */
public interface IRoomService extends IService<Room> {
    Result getRoomList(String openId);
    Result addRoomList(Room room);
    Result deleteRoomList(Room room);
    Result editRoomList(Room room);
    //后台
    Result getRoomAll();
    Result deleteRoomData(Room room);
    Result deleteRooms(List<Room> roomList);
}
