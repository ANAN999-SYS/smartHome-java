package com.weixin.adminLogin.controller;

import com.weixin.adminLogin.entity.Admin;
import com.weixin.adminLogin.service.IAdminService;
import com.weixin.commons.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-09
 */
@RestController
@CrossOrigin
@RequestMapping("/adminLogin")
public class AdminController {

    @Autowired
    private IAdminService iAdminService;
    @PostMapping("/Login")
    public Result adminLogin(@RequestBody Admin admin){
        Result result = iAdminService.adminLogin(admin);
        return result;
    }
    @PostMapping("/uploadImg")
    public Result uploadImg(@RequestParam(value = "imgFile") MultipartFile imgFile){
        Result result=iAdminService.uploadImg(imgFile);
        return result;
    }
    @GetMapping("/getAdminALl")
    public Result getAdminALl(){
        Result result = iAdminService.getAdminALl();
        return result;
    }
    @PostMapping("/addAdmin")
    public Result addAdmin(@RequestBody Admin admin){
        Result result = iAdminService.addAdmin(admin);
        return result;
    }
    @PostMapping("/deleteAdmin")
    public Result deleteAdmin(@RequestBody Admin admin){
        Result result = iAdminService.deleteAdmin(admin);
        return result;
    }
    @PostMapping("/deleteAdmins")
    public Result deleteAdmins(@RequestBody List<Admin> adminList){
        Result result = iAdminService.deleteAdmins(adminList);
        return result;
    }
    @PostMapping("/permissionsAdmins")
    public Result permissionsAdmins(@RequestBody Admin admin){
        Result result = iAdminService.permissionsAdmins(admin);
        return result;
    }

    @GetMapping("/test")
    public String test(){
        return "ok";
    }
}

