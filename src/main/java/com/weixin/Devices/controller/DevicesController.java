package com.weixin.Devices.controller;


import com.weixin.Devices.entity.Devices;
import com.weixin.Devices.entity.DevicesCtrl;
import com.weixin.Devices.service.IDevicesService;
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
 * @since 2024-04-03
 */
@CrossOrigin
@RestController
@RequestMapping("/Devices")
public class DevicesController {
    @Autowired
    private IDevicesService iDevicesService;

    @GetMapping("/getDevicesList")
    public Result getDevicesList(@RequestHeader("Authorization") String token, @RequestParam String room){
        String openId= JWTUtils.validateToken(token);
        Result result=iDevicesService.getDevicesList(openId,room);
        return result;
    }
    @GetMapping("/getBaFaDevices")
    public Result getBaFaDevices(@RequestHeader("Authorization") String token){
        String openId= JWTUtils.validateToken(token);
        Result result=iDevicesService.getBaFaDevices(openId);
        return result;
    }
    @GetMapping("/getDevicesById")
    public Result getDevicesById(@RequestHeader("Authorization") String token, @RequestParam String deId){
        String openId= JWTUtils.validateToken(token);
        Result result=iDevicesService.getDevicesById(openId,deId);
        return result;
    }
    @PostMapping("/addDevicesList")
    public Result addDevicesList(@RequestBody Devices devices){
        Result result=iDevicesService.addDevicesList(devices);
        return result;
    }
    @PostMapping("/editDevicesList")
    public Result editDevicesList(@RequestBody Devices devices){
        Result result=iDevicesService.editDevicesList(devices);
        return result;
    }
    @PostMapping("/deleteDevicesList")
    public Result deleteDevicesList(@RequestBody Devices devices){
        Result result=iDevicesService.deleteDevicesList(devices);
        return result;
    }
    @PostMapping("/ctrlDevice")
    public Result ctrlDevice(@RequestBody DevicesCtrl devicesCtrl){
        System.out.println(devicesCtrl);
        Result result=iDevicesService.ctrlDevice(devicesCtrl);
        return result;
    }
    @GetMapping("/getBaFaDevisesList")
    public Result getBaFaDevisesList(@RequestHeader("Authorization") String token, @RequestParam String room){
        String openId= JWTUtils.validateToken(token);
        Result result=iDevicesService.getBaFaDevisesList(openId,room);
        return result;
    }
    //后台

    @GetMapping("/getDevicesAll")
    public Result getDevicesAll(){
        Result result=iDevicesService.getDeviceAll();
        return result;
    }

    @PostMapping("/deleteDevice")
    public Result deleteDevice(@RequestBody Devices devices){
        Result result=iDevicesService.deleteDevice(devices);
        return result;
    }
    @PostMapping("/deleteDevices")
    public Result deleteDevices(@RequestBody List<Devices> devicesList){
        Result result=iDevicesService.deleteDevices(devicesList);
        return result;
    }
    @PostMapping("/testZC")
    public Result testZC(){
        Result result=iDevicesService.testZC();
        return result;
    }
    @PostMapping("/testYC")
    public Result testYc(){
        Result result=iDevicesService.testYC();
        return result;
    }
}

