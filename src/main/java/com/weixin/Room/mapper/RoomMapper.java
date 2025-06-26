package com.weixin.Room.mapper;

import com.weixin.Room.entity.Room;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weixin.Room.entity.RoomRes;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-04
 */
@Mapper
@Resource
public interface RoomMapper extends BaseMapper<Room> {

    @Select("Select * from room where author = #{openId}")
    List<Room> selectByOpenId(String openId);

    @Delete("delete from room where room_id=#{roomId} and author=#{openId}")
    void deleteByRoomId(String roomId,String openId);

    @Delete("delete from room where room_id=#{roomId}")
    void deleteRoom(String roomId);

    @Select("SELECT \n" +
            "    r.*,\n" +
            "    u.nick_name\n" +
            "FROM \n" +
            "    `room` r\n" +
            "JOIN \n" +
            "    (\n" +
            "        SELECT \n" +
            "            open_id,\n" +
            "            nick_name\n" +
            "        FROM \n" +
            "            `user`\n" +
            "    ) AS u ON r.author = u.open_id;\n")
    List<RoomRes> selectAll();
}
