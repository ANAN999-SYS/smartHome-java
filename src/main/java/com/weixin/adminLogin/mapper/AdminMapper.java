package com.weixin.adminLogin.mapper;

import com.weixin.adminLogin.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.annotation.Resource;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-09
 */
@Resource
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("SELECT * FROM admin WHERE uesr_name = #{uesrname} and password = #{password}")
    Admin adminLogin(String uesrname,String password);

    @Delete("Delete from admin where su_id=#{suId}")
    void deleteBySuId(String suId);
}
