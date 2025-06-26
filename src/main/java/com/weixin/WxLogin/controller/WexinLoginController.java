package com.weixin.WxLogin.controller;

import com.weixin.WxLogin.entity.WXAuth;
import com.weixin.WxLogin.service.IWeixinService;
import com.weixin.commons.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/weixinLogin")
public class WexinLoginController {

    @Autowired
    IWeixinService weixinService;

    @GetMapping("/sessionId/{code}")
    public String getSessionId(@PathVariable("code") String code){
        System.out.println(code);
        return  weixinService.getSessionId(code);
    }

    @PostMapping("/authLogin")
    public Result authLogin(@RequestBody WXAuth wxAuth) {

        Result result = weixinService.authLogin(wxAuth);
        log.info("{}",result);
        return result;
    }
}
