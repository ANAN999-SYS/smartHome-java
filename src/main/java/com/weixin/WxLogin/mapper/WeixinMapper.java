package com.weixin.WxLogin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weixin.WxLogin.entity.Weixin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;



@Mapper
@Repository
public interface WeixinMapper extends BaseMapper<Weixin> {
    @Select("SELECT * FROM user WHERE open_id = #{openid}")
    Weixin selectByOpenid(String openId);

    @Select("SELECT secret_key FROM user WHERE open_id = #{openid}")
    String selectSecretKey(String openId);

    @Select("Select open_id from user where secret_key=#{secretKey}")
    String selectOpenId(String secretKey);

}
