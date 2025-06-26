package com.weixin.Record.service.impl;

import com.weixin.Record.entity.Record;
import com.weixin.Record.entity.RecordRes;
import com.weixin.Record.mapper.RecordMapper;
import com.weixin.Record.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixin.Room.entity.Room;
import com.weixin.commons.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-05
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {
    //后台
    @Autowired
    private RecordMapper recordMapper;
    @Override
    public Result getRecordAll(){
        List<RecordRes> recordList =recordMapper.selectAll();
        Collections.reverse(recordList);
        return Result.SUCCESS(recordList);
    }
    @Override
    public Result deleteRecordData(Record record){
        String rId=record.getRId();
        recordMapper.deleteByRid(rId);
        return Result.SUCCESS("删除成功");
    }
    @Override
    public Result deleteRecords(List<Record> recordList){
        for(Record record:recordList){
            recordMapper.deleteByRid(record.getRId());
        }
        return Result.SUCCESS("删除成功");
    }
}
