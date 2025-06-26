package com.weixin.Devices.service;

import com.weixin.Devices.entity.Devices;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weixin.Devices.entity.DevicesCtrl;
import com.weixin.Feedback.entity.UserFeedback;
import com.weixin.commons.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-03
 */
public interface IDevicesService extends IService<Devices> {
    Result getDevicesList(String openId, String room);
    Result getDevicesById(String openId,String deId);
    Result addDevicesList(Devices devices);
    Result editDevicesList(Devices devices);

    Result deleteDevicesList(Devices devices);

    Result ctrlDevice(DevicesCtrl devicesCtrl);
    Result getBaFaDevices(String openId);
    Result getBaFaDevisesList(String openId,String room);

    //后台
    Result getDeviceAll();
    Result deleteDevice(Devices devices);

    Result deleteDevices(List<Devices> devicesList);

    Result testZC();
    Result testYC();


}
