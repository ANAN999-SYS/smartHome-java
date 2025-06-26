package com.weixin.analyse.controller;

import com.weixin.Devices.mapper.DevicesMapper;
import com.weixin.Record.mapper.RecordMapper;
import com.weixin.WxLogin.mapper.WeixinMapper;
import com.weixin.analyse.entity.ActiveUser;
import com.weixin.analyse.entity.AnalyseCard;
import com.weixin.analyse.entity.DeviceLine;
import com.weixin.commons.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/Analyse")
public class AnalyseController {

    @Autowired
    private WeixinMapper weixinMapper;
    @Autowired
    private DevicesMapper devicesMapper;
    @Autowired
    private RecordMapper recordMapper;


    @GetMapping("/getAnalyseCard")
    public Result getAnalyseCard() {
        AnalyseCard analyseCard = new AnalyseCard();
        analyseCard.setUserNum(weixinMapper.selectCount(null));
        analyseCard.setDevicesNum(devicesMapper.selectCount(null));
        String today = LocalDateTime.now().toString().substring(0, 10) + "%";
        analyseCard.setDirectivesNum(recordMapper.selectByTime(today).size());
        return Result.SUCCESS(analyseCard);
    }

    @GetMapping("/getDevicePie")
    public Result getDevicePie() {
        List<Map<String, Integer>> list = devicesMapper.selectTypeCount();
        return Result.SUCCESS(list);
    }

    @GetMapping("/getActiveUser")
    public Result getActiveUser() {
        List<Map<String, Integer>> activeList = recordMapper.selectActiveUser();
        List<Map<String, Integer>> inActiveList = recordMapper.selectInactiveUser();
        Integer sum = weixinMapper.selectCount(null);
        ActiveUser activeUser = new ActiveUser();
        activeUser.setActiveNum(activeList.size());
        activeUser.setInActiveNum(inActiveList.size());
        activeUser.setSum(sum);
        return Result.SUCCESS(activeUser);
    }

    @GetMapping("/getTimePeriod")
    public Result getTimePeriod() {
        List<Map<String, Integer>> timePeriod = recordMapper.selectTimePeriod();
        return Result.SUCCESS(timePeriod);
    }

    @GetMapping("/getDeviceLine")
    public Result getDeviceLine() {
        List<String> xData = new ArrayList<>();
        List<Map<String, List<Integer>>> yData = new ArrayList<>();
        List<DeviceLine> data;
        List<Integer> LightData = new ArrayList<>();
        List<Integer> curtainData = new ArrayList<>();
        List<Integer> windData = new ArrayList<>();
        List<Integer> receptacleData = new ArrayList<>();
        List<Integer> lockData = new ArrayList<>();
        List<Integer> airConditioningData = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            String day = LocalDateTime.now().minusDays(4 - i).toString().substring(0, 10);
            xData.add(day);
            data = recordMapper.selectDeviceLine(day);
            if (data.size() == 0) {
                LightData.add(0);
                curtainData.add(0);
                windData.add(0);
                receptacleData.add(0);
                lockData.add(0);
                airConditioningData.add(0);
            } else {
                System.out.println("have");
                for (DeviceLine deviceLine : data) {
                    String deviceType = deviceLine.getDeviceType();
                    switch (deviceType) {
                        case "light":
                            LightData.add(deviceLine.getCount());
                            break;
                        case "curtain":
                            curtainData.add(deviceLine.getCount());
                            break;
                        case "wind":
                            windData.add(deviceLine.getCount());
                            break;
                        case "receptacle":
                            receptacleData.add(deviceLine.getCount());
                            break;
                        case "lock":
                            lockData.add(deviceLine.getCount());
                            break;
                        case "airConditioning":
                            airConditioningData.add(deviceLine.getCount());
                            break;
                    }
                }
                if (lockData.size() == i) {
                    lockData.add(0);
                }
                if (LightData.size() == i) {
                    LightData.add(0);
                }
                if (windData.size() == i) {
                    windData.add(0);
                }
                if (curtainData.size() == i) {
                    curtainData.add(0);
                }
                if (receptacleData.size() == i) {
                    receptacleData.add(0);
                }
                if (airConditioningData.size() == i) {
                    airConditioningData.add(0);
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("light", LightData);
        map.put("curtain", curtainData);
        map.put("wind", windData);
        map.put("receptacle", receptacleData);
        map.put("lock", lockData);
        map.put("airConditioning", airConditioningData);
        map.put("xData", xData);
        return Result.SUCCESS(map);
    }
}
