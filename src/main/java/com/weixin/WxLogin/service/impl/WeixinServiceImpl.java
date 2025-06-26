package com.weixin.WxLogin.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixin.Utils.JWTUtils;
import com.weixin.Utils.TokenBlack;
import com.weixin.WxLogin.entity.WXAuth;
import com.weixin.WxLogin.entity.Weixin;
import com.weixin.WxLogin.entity.WxUserInfo;
import com.weixin.WxLogin.mapper.WeixinMapper;
import com.weixin.WxLogin.service.IWeixinService;
import com.weixin.commons.RedisKey;
import com.weixin.commons.Result;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
public class WeixinServiceImpl extends ServiceImpl<WeixinMapper, Weixin> implements IWeixinService {


    @Value("${weixin.appid}")
    private String appid;

    @Value("${weixin.secret}")
    private String secret;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    WxService wxService;

    @Autowired
    WeixinMapper weixinMapper;

    @Value("${upload.ImgPath}")
    private String imagePath;//上传地址

    @Value("${upload.reflexFolder}")
    private String reflexFolder;//重定向地址

    @Value("${upload.avatarUrl}")
    private String avatarUrl;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    TokenBlack tokenBlack;
    @Autowired
    JWTUtils jwtUtils;
    @Override
    public String getSessionId(String code) {

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
        String replaceUrl = url.replace("{0}", appid).replace("{1}", secret).replace("{2}", code);
        String res = HttpUtil.get(replaceUrl);
        log.info("发送链接后获得的数据{}",res);
        String s = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(RedisKey.WX_SESSION_ID + s, res);
        return s;
    }


    @Override
    public Result authLogin(WXAuth wxAuth) {
        System.out.println(wxAuth);
        try {
            String wxRes = wxService.wxDecrypt(wxAuth.getEncryptedData(), wxAuth.getSessionId(), wxAuth.getIv());
            log.info("用户信息："+wxRes);
            WxUserInfo wxUserInfo = JSON.parseObject(wxRes,WxUserInfo.class);

            //这里是做业务操作的，需要查询数据库，看这个用户信息是否存在，存在直接返回登录态，

            System.out.println(wxUserInfo);
            String rjson=redisTemplate.opsForValue().get(RedisKey.WX_SESSION_ID +wxAuth.getSessionId());

            JSONObject jsonObject = new JSONObject(rjson);
            String openid = jsonObject.getString("openid");
            Weixin weixin=new Weixin();
            if (weixinMapper.selectByOpenid(openid) == null){
                System.out.println("用户信息不存在");
                String secretKey=UUID.randomUUID().toString().replace("-","");
                weixin.setOpenId(openid);
                weixin.setNickName(wxUserInfo.getNickName());
                weixin.setAvatarUrl(wxUserInfo.getAvatarUrl());
                weixin.setGender(wxUserInfo.getGender());
                weixin.setCity(wxUserInfo.getCity());
                weixin.setProvince(wxUserInfo.getProvince());
                weixin.setCountry(wxUserInfo.getCountry());
                weixin.setIsAuth(1);
                weixin.setState("true");
                weixin.setSecretKey(secretKey);
                weixinMapper.insert(weixin);
                String token = JWTUtils.createToken(openid);
                wxUserInfo.setOpenId(openid);
                wxUserInfo.setToken(token);
                wxUserInfo.setSecretKey(secretKey);
                String rKey=RedisKey.TOKEN+openid;
                System.out.println(rKey);
                redisTemplate.opsForValue().set(rKey,token);
                return Result.SUCCESS(wxUserInfo);
            }
            else {
               String state=weixinMapper.selectByOpenid(openid).getState();
               if (state.equals("true")){
                   String token = JWTUtils.createToken(openid);
                   String secretKey = weixinMapper.selectSecretKey(openid);
                   Weixin weixinNew=weixinMapper.selectByOpenid(openid);
                   System.out.println(weixinNew);
                   wxUserInfo.setAvatarUrl(weixinNew.getAvatarUrl());
                   wxUserInfo.setNickName(weixinNew.getNickName());
                   wxUserInfo.setToken(token);
                   wxUserInfo.setOpenId(openid);
                   wxUserInfo.setSecretKey(secretKey);
                   wxUserInfo.setBafaKey(weixinNew.getBafaKey());
                   String rKey=RedisKey.TOKEN+openid;
                   redisTemplate.opsForValue().set(rKey,token);
                   return Result.SUCCESS(wxUserInfo);
               }
               else {
                   return Result.FAIL("用户已被限制登录");
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.FAIL();
    }
    @Override
    public Result editUserInfo(WxUserInfo wxUserInfo) {
        System.out.println(wxUserInfo);
        UpdateWrapper<Weixin> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("open_id", wxUserInfo.getOpenId());
        updateWrapper.set("avatar_url",wxUserInfo.getAvatarUrl());
        updateWrapper.set("nick_name",wxUserInfo.getNickName());
        updateWrapper.set("bafa_key",wxUserInfo.getBafaKey());
        int affectedRows = weixinMapper.update(null, updateWrapper);
        if (affectedRows > 0) {
            return Result.SUCCESS("数据更新成功！");
        } else {
            return Result.FAIL("数据更新失败！");
        }
    }
    @Override
    public Result getUserAll() {
        List<Weixin> weixinList =weixinMapper.selectList(null);
        Collections.reverse(weixinList);
        return Result.SUCCESS(weixinList);
    }

    @Override
    public Result editPermissions(Weixin weixin) {
        UpdateWrapper<Weixin> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("open_id", weixin.getOpenId());
        int affectedRows = weixinMapper.update(weixin, updateWrapper);
        if (affectedRows > 0) {
            String rKey=RedisKey.TOKEN+weixin.getOpenId();
            String token =redisTemplate.opsForValue().get(rKey);
            tokenBlack.addBlacklist(token);
            return Result.SUCCESS("数据更新成功！");
        } else {
            return Result.FAIL("数据更新失败！");
        }
    }

    @Override
    public Result uploadImg(MultipartFile imgFile){
        if (imgFile != null && !imgFile.isEmpty()) {
            //获取文件名
            String filename = imgFile.getOriginalFilename(); //图片名
            String[] split = new String[0];
            if (filename != null) {
                split = filename.split("\\.");
            }
            //只接受jpg、png、jpeg格式图片文件，其它格式的文件可按需添加判断，主要是为了防止上传恶意文件，加强安全性
            if ("jpg".equalsIgnoreCase(split[1]) || "png".equalsIgnoreCase(split[1]) || "jpeg".equalsIgnoreCase(split[1])) {
                //图片重命名加后缀
                String photoName = java.util.UUID.randomUUID().toString().replace("-", "") + "." + split[1];
                File destFile = new File(imagePath+avatarUrl + File.separator + photoName);
                //判断是否存在, 不存在就创建
                if (!destFile.getParentFile().exists()) {
                    destFile.getParentFile().mkdirs();
                }
                try {
                    imgFile.transferTo(destFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //获取协议、服务器IP、端口号、工程路径
                String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
//                String basePath = request.getScheme() + "://" + "172.29.35.235" + ":" + request.getServerPort() + request.getContextPath();
                String httpUrl = basePath + reflexFolder+avatarUrl + photoName;
                return Result.SUCCESS(httpUrl);
            }
        }
        return Result.FAIL("操作失败");
    }
}