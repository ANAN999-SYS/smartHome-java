package com.weixin.Room.controller;


import com.weixin.Devices.entity.Devices;
import com.weixin.Room.entity.Room;
import com.weixin.Room.service.IRoomService;
import com.weixin.Utils.JWTUtils;
import com.weixin.commons.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-04
 */
@CrossOrigin
@RestController
@RequestMapping("/Room")
public class RoomController {
    @Autowired
    private IRoomService iRoomService;
    @GetMapping("/getRoomList")
    public Result getRoomList(@RequestHeader("Authorization") String token){
        String openId= JWTUtils.validateToken(token);
        Result result=iRoomService.getRoomList(openId);
        return result;
    }
    @PostMapping ("/addRoomList")
    public Result addRoomList(@RequestBody Room room){
        System.out.println(room);
        Result result=iRoomService.addRoomList(room);
        return result;
    }
    @PostMapping ("/editRoomList")
    public Result editRoomList(@RequestBody Room room){
        Result result=iRoomService.editRoomList(room);
        return result;
    }
    @PostMapping("/deleteRoomList")
    public Result deleteRoomList(@RequestBody Room room){
        Result result=iRoomService.deleteRoomList(room);
        return result;
    }

    //后台

    @GetMapping("/getRoomAll")
    public Result getRoomAll(){
        Result result=iRoomService.getRoomAll();
        return result;
    }

    @PostMapping("/deleteRoomData")
    public Result deleteRoomData(@RequestBody Room room){
        Result result=iRoomService.deleteRoomData(room);
        return result;
    }

    @PostMapping("/deleteRooms")
    public Result deleteRooms(@RequestBody List<Room> roomList){
        Result result=iRoomService.deleteRooms(roomList);
        return result;
    }
}

