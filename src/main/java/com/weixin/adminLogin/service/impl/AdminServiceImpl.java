package com.weixin.adminLogin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.weixin.Utils.JWTUtils;
import com.weixin.Utils.TokenBlack;
import com.weixin.adminLogin.entity.Admin;
import com.weixin.adminLogin.entity.AdminInfo;
import com.weixin.adminLogin.mapper.AdminMapper;
import com.weixin.adminLogin.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixin.commons.RedisKey;
import com.weixin.commons.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-09
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Value("${upload.ImgPath}")
    private String imagePath;//上传地址

    @Value("${upload.reflexFolder}")
    private String reflexFolder;//重定向地址

    @Value("${upload.avatarUrl}")
    private String avatarUrl;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    TokenBlack tokenBlack;
    @Override
    public Result adminLogin(Admin admin){
        String username=admin.getUesrName();
        String password=admin.getPassword();
        Admin isAdmin = adminMapper.adminLogin(username,password);
        AdminInfo adminInfo=new AdminInfo();
        if (isAdmin!=null){
            if (isAdmin.getState().equals("true")){
                String token= JWTUtils.createToken(isAdmin.getSuId());
                adminInfo.setAdmin(isAdmin);
                adminInfo.setToken(token);
                redisTemplate.opsForValue().set(RedisKey.TOKEN+isAdmin.getSuId(),token);
                return Result.SUCCESS(adminInfo);
            }
            else {
                return Result.FAIL("用户已被限制登录！");
            }
        }
        else {
            return Result.FAIL("登录失败！");
        }
    }
    @Override
    public Result getAdminALl(){
        List<Admin> adminList=adminMapper.selectList(null);
        return Result.SUCCESS(adminList);
    }
    @Override
    public Result addAdmin(Admin admin){
        admin.setSuId(UUID.randomUUID().toString().replace("-",""));
        admin.setState("true");
        adminMapper.insert(admin);
        return Result.SUCCESS();
    }
    @Override
    public Result deleteAdmin(Admin admin){
        String suId=admin.getSuId();
        adminMapper.deleteBySuId(suId);
        return Result.SUCCESS();
    }
    @Override
    public Result deleteAdmins(List<Admin>adminList){
        for (Admin admin :adminList){
            adminMapper.deleteBySuId(admin.getSuId());
        }
        return Result.SUCCESS();
    }
    @Override
    public Result permissionsAdmins(Admin admin){
        UpdateWrapper<Admin> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("su_id", admin.getSuId());
        int affectedRows = adminMapper.update(admin, updateWrapper);
        if (affectedRows > 0) {
            String rKey=RedisKey.TOKEN+admin.getSuId();
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
