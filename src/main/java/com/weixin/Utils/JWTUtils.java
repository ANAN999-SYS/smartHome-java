package com.weixin.Utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@ConfigurationProperties(prefix = "jwt")
@Slf4j
public class JWTUtils {
    //定义token返回头部
    @Value("${jwt.header}")
    public static String header;

    @Value("${jwt.tokenPrefix}")
    //token前缀
    public static String tokenPrefix;

    //签名密钥
    @Value("${jwt.secret}")
    public static String secret ;

    //有效期
    @Value("${jwt.expireTime}")
    public static long expireTime;



    public void setHeader(String header) {
        JWTUtils.header = header;
    }

    public void setTokenPrefix(String tokenPrefix) {
        JWTUtils.tokenPrefix = tokenPrefix;
    }

    public void setSecret(String secret) {
        JWTUtils.secret = secret;
    }

    public void setExpireTime(long expireTime) {
        JWTUtils.expireTime = expireTime;
    }

    /**
     * 创建TOKEN
     *
     * @param sub
     * @return
     */
    public static String createToken(String sub) {
        return tokenPrefix + JWT.create()
                .withSubject(sub)
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                .sign(Algorithm.HMAC512(secret));
    }


    /**
     * 验证token
     *
     * @param token
     */
    public static String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token.replace(tokenPrefix, ""))
                    .getSubject();
        } catch (TokenExpiredException e) {
            System.out.println("token已过期");
            return "";
        } catch (Exception e) {
            System.out.println("token验证失败");
            return "";
        }
    }
    /**
     * 添加至黑名单
     *
     * @param token
     */


    /**
     * 检查token是否需要更新
     *
     * @param token ·
     * @return
     */
    public static boolean isNeedUpdate(String token) {
        //获取token过期时间
        Date expiresAt = null;
        try {
            expiresAt = JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token.replace(tokenPrefix, ""))
                    .getExpiresAt();
        } catch (TokenExpiredException e) {
            return true;
        } catch (Exception e) {
            System.out.println("token验证失败");
            return false;
        }
        //如果剩余过期时间少于过期时常的一般时 需要更新
        return (expiresAt.getTime() - System.currentTimeMillis()) < (expireTime >> 1);
    }
    public static  Date getIssuedAt(String token) {
        //获取token过期时间
        Date issuedAt = null;
        try {
            issuedAt = JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token.replace(tokenPrefix, ""))
                    .getIssuedAt();
        } catch (Exception e) {
            System.out.println("解析token失败: " + e.getMessage());
        }
        return issuedAt;
    }
}