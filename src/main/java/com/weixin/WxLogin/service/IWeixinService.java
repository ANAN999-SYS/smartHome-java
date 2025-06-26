package com.weixin.WxLogin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.weixin.WxLogin.entity.WXAuth;
import com.weixin.WxLogin.entity.Weixin;
import com.weixin.WxLogin.entity.WxUserInfo;
import com.weixin.adminLogin.entity.Admin;
import com.weixin.commons.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author crush
 * @since 2021-09-14
 */
public interface IWeixinService extends IService<Weixin> {

    String getSessionId(String code);

    Result authLogin(WXAuth wxAuth);
    Result editUserInfo(WxUserInfo wxUserInfo);

    Result getUserAll();
    Result editPermissions(Weixin weixin);

    Result uploadImg(MultipartFile imgFile);

}
