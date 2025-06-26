package com.weixin.adminLogin.service;

import com.weixin.adminLogin.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weixin.commons.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-09
 */
public interface IAdminService extends IService<Admin> {

    Result adminLogin(Admin admin);
    Result uploadImg(MultipartFile imgFile);
    Result addAdmin(Admin admin);

    Result deleteAdmin(Admin admin);

    Result deleteAdmins(List<Admin> adminList);
    Result permissionsAdmins(Admin admin);
    Result getAdminALl();
}
