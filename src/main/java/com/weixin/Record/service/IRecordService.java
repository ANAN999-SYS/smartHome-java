package com.weixin.Record.service;

import com.weixin.Record.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weixin.commons.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-05
 */
public interface IRecordService extends IService<Record> {
    Result getRecordAll();

    Result deleteRecordData(Record record);

    Result deleteRecords(List<Record> recordList);
}
