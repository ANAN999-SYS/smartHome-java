package com.weixin.WxLogin.controller;


import com.weixin.WxLogin.entity.Weixin;
import com.weixin.WxLogin.entity.WxUserInfo;
import com.weixin.WxLogin.service.IWeixinService;
import com.weixin.commons.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/weixin")
public class WeixinController {

    @Autowired
    IWeixinService weixinService;

    @PostMapping("/editUserInfo")
    public Result editUserInfo(@RequestBody WxUserInfo wxUserInfo) {
        Result result = weixinService.editUserInfo(wxUserInfo);
        return result;
    }
    @PostMapping("/uploadImg")
    public Result uploadImg(@RequestParam(value = "imgFile") MultipartFile imgFile){
        Result result=weixinService.uploadImg(imgFile);
        return result;
    }
    @GetMapping("/getUserAll")
    public Result getUserAll(){
        Result result=weixinService.getUserAll();
        return  result;
    }
    @GetMapping("/userTest")
    public Result userTest(){
        return  Result.SUCCESS();
    }
    @PostMapping("/editPermissions")
    public Result editPermissions(@RequestBody Weixin weixin) {
        Result result = weixinService.editPermissions(weixin);
        return result;
    }


}

