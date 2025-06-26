package com.weixin.Scenario.mapper;

import com.weixin.Devices.entity.Devices;
import com.weixin.Scenario.entity.Scenario;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weixin.Scenario.entity.ScenarioRes;
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
 * @since 2024-04-05
 */
@Resource
@Mapper
public interface ScenarioMapper extends BaseMapper<Scenario> {
    @Delete("Delete from scenario where author=#{openId} and s_id=#{sId}")
    void deleteBySid(String openId,String sId);

    @Delete("Delete from scenario where s_id=#{sId}")
    void deleteById(String sId);

    @Select("SELECT \n" +
            "    s.*,\n" +
            "    u.nick_name\n" +
            "FROM \n" +
            "    `scenario` s\n" +
            "JOIN \n" +
            "    (\n" +
            "        SELECT \n" +
            "            open_id,\n" +
            "            nick_name\n" +
            "        FROM \n" +
            "            `user`\n" +
            "    ) AS u ON s.author = u.open_id;\n")
    List<ScenarioRes> selectAll();
}
