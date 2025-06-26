package com.weixin.Record.mapper;

import com.weixin.Record.entity.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weixin.Record.entity.RecordRes;
import com.weixin.analyse.entity.DeviceLine;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-05
 */
@Resource
@Mapper
public interface RecordMapper extends BaseMapper<Record> {
    @Delete("Delete from record where r_id=#{rId}")
    void deleteByRid(String rId);

    @Select("SELECT * FROM `record` WHERE create_time LIKE #{time}")
    List<Record> selectByTime(String time);

    //活跃
    @Select("SELECT author AS user,COUNT(*) AS count FROM `record` GROUP BY author HAVING count >= 10")
    List<Map<String,Integer>> selectActiveUser();
    //不活跃
    @Select("SELECT author AS user,COUNT(*) AS count FROM `record` GROUP BY author HAVING count < 10")
    List<Map<String,Integer>> selectInactiveUser();
    @Select("SELECT   \n" +
            "    CASE   \n" +
            "        WHEN HOUR(create_time) BETWEEN 0 AND 3 THEN '00:00 - 03:59'  \n" +
            "        WHEN HOUR(create_time) BETWEEN 4 AND 7 THEN '04:00 - 07:59'  \n" +
            "        WHEN HOUR(create_time) BETWEEN 8 AND 11 THEN '08:00 - 11:59'  \n" +
            "        WHEN HOUR(create_time) BETWEEN 12 AND 15 THEN '12:00 - 15:59'  \n" +
            "        WHEN HOUR(create_time) BETWEEN 16 AND 19 THEN '16:00 - 19:59'  \n" +
            "        WHEN HOUR(create_time) BETWEEN 20 AND 23 THEN '20:00 - 23:59'  \n" +
            "    END AS timePeriod,  \n" +
            "    COUNT(*) AS count\n" +
            "FROM record  \n" +
            "GROUP BY timePeriod  \n" +
            "ORDER BY timePeriod")
    List<Map<String,Integer>> selectTimePeriod();

    @Select("SELECT device_type AS deviceType,COUNT(*) AS count FROM record WHERE DATE(create_time) = #{day} GROUP BY device_type")
    List<DeviceLine> selectDeviceLine(String day);

    @Select("SELECT \n" +
            "    r.*,\n" +
            "    u.nick_name\n" +
            "FROM \n" +
            "    `record` r\n" +
            "JOIN \n" +
            "    (\n" +
            "        SELECT \n" +
            "            open_id,\n" +
            "            nick_name\n" +
            "        FROM \n" +
            "            `user`\n" +
            "    ) AS u ON r.author = u.open_id;\n")
    List<RecordRes> selectAll();
}
