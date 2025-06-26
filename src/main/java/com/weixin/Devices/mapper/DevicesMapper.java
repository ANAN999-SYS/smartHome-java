package com.weixin.Devices.mapper;

import com.weixin.Devices.entity.Devices;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weixin.Devices.entity.DevicesRes;
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
 * @since 2024-04-03
 */
@Mapper
@Resource
public interface DevicesMapper extends BaseMapper<Devices> {
    @Select("Select * from devices where author=#{openId}")
    List<Devices> selectByOpenId(String openId);

    @Select("Select * from devices where author=#{openId} and room_name=#{room}")
    List<Devices> selectByRoom(String openId,String room);
    @Select("Select * from devices where author=#{openId} and de_id=#{deId}")
    Devices selectByDeId(String openId,String deId);
    @Delete("Delete from devices where author=#{openId} and de_id=#{deId}")
    void deleteByRoom(String openId,String deId);

    @Select("Select topic from devices where author=#{openId} and type=#{type}")
    List<String> selectByAuthor(String openId,String type);

    @Delete("Delete from devices where de_id=#{deId}")
    void deleteDevice(String deId);

    @Select("SELECT COUNT(*) AS value, type AS name FROM devices GROUP BY type;")
    List<Map<String,Integer>> selectTypeCount();

    @Select("SELECT \n" +
            "    d.*,\n" +
            "    u.nick_name\n" +
            "FROM \n" +
            "    `devices` d\n" +
            "JOIN \n" +
            "    (\n" +
            "        SELECT \n" +
            "            open_id,\n" +
            "            nick_name\n" +
            "        FROM \n" +
            "            `user`\n" +
            "    ) AS u ON d.author = u.open_id;\n")
    List<DevicesRes> selectAll();
    @Select("SELECT * FROM devices where state='true' and author=#{openId}")
    List<Devices> selectBafaAll(String openId);

    @Select("Select * from devices where author=#{openId} and state='false'")
    List<Devices> selectByBafa(String openId);

    @Select("Select * from devices where author=#{openId} and room_name=#{room} and state='false'")
    List<Devices> selectBafaByRoom(String openId,String room);

}
