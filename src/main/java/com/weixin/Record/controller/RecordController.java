package com.weixin.Record.controller;


import com.weixin.Devices.entity.Devices;
import com.weixin.Record.entity.Record;
import com.weixin.Record.service.IRecordService;
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
 * @since 2024-04-05
 */
@RestController
@RequestMapping("/Record")
@CrossOrigin
public class RecordController {
    //后台
    @Autowired
    private IRecordService iRecordService;
    @GetMapping("/getRecordAll")
    public Result getRecordAll(){
        Result result=iRecordService.getRecordAll();
        return result;
    }

    @PostMapping("/deleteRecordData")
    public Result deleteRecordData(@RequestBody Record record){
        Result result=iRecordService.deleteRecordData(record);
        return result;
    }

    @PostMapping("/deleteRecords")
    public Result deleteRecords(@RequestBody List<Record> recordList){
        Result result=iRecordService.deleteRecords(recordList);
        return result;
    }
}

