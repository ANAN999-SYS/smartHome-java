package com.weixin.Feedback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.weixin.Devices.entity.Devices;
import com.weixin.Feedback.entity.ImgData;
import com.weixin.Feedback.entity.UserFeedback;
import com.weixin.Feedback.mapper.UserFeedbackMapper;
import com.weixin.Feedback.service.IUserFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weixin.Room.entity.Room;
import com.weixin.commons.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 纸凤孤凰
 * @since 2024-04-04
 */
@Service
public class UserFeedbackServiceImpl extends ServiceImpl<UserFeedbackMapper, UserFeedback> implements IUserFeedbackService {
    @Autowired
    private UserFeedbackMapper userFeedbackMapper;

    @Value("${upload.ImgPath}")
    private String imagePath;//上传地址

    @Value("${upload.reflexFolder}")
    private String reflexFolder;//重定向地址

    @Value("${upload.feedBackImg}")
    private String feedbackPath;
    @Autowired
    private HttpServletRequest request;
    @Override
    public Result feedback(UserFeedback userFeedback){
        userFeedback.setState("false");
        userFeedback.setFId(UUID.randomUUID().toString().replace("-",""));
        System.out.println(userFeedback);
        userFeedbackMapper.insert(userFeedback);
        return Result.SUCCESS("添加成功！");
    }

    @Override
    public Result getFeedback(String openId){
        QueryWrapper<UserFeedback> userFeedbackQueryWrapper=new QueryWrapper<UserFeedback>().eq("author",openId);
        List<UserFeedback> userFeedbacks=userFeedbackMapper.selectList(userFeedbackQueryWrapper);
        System.out.println(userFeedbacks);
        return Result.SUCCESS(userFeedbacks);
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
                String photoName = UUID.randomUUID().toString().replace("-", "") + "." + split[1];
                File destFile = new File(imagePath+feedbackPath + File.separator + photoName);
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
                String httpUrl = basePath + reflexFolder+feedbackPath + photoName;
                ImgData imgData=new ImgData();
                imgData.setName(photoName);
                imgData.setUrl(httpUrl);
                imgData.setType("image");
                return Result.SUCCESS(imgData);
            }
        }
        return Result.FAIL("操作失败");
    }
    //后台
    @Override
    public Result getFeedbackAll(){
        List<UserFeedback> scenarioList =userFeedbackMapper.selectList(null);
        Collections.reverse(scenarioList);
        return Result.SUCCESS(scenarioList);
    }
    @Override
    public Result deleteFeedbackData(UserFeedback userFeedback){
        String fId=userFeedback.getFId();
        userFeedbackMapper.deleteByFid(fId);
        return Result.SUCCESS("删除成功");
    }
    @Override
    public Result deleteFeedbacks(List<UserFeedback> userFeedbackList){
        for(UserFeedback userFeedback:userFeedbackList){
            userFeedbackMapper.deleteByFid(userFeedback.getFId());
        }
        return Result.SUCCESS("删除成功");
    }
    @Override
    public Result checkFeedbacks(UserFeedback userFeedback){
        userFeedback.setState("true");
        UpdateWrapper<UserFeedback> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("f_id", userFeedback.getFId());
        int affectedRows = userFeedbackMapper.update(userFeedback, updateWrapper);
        if (affectedRows > 0) {
            return Result.SUCCESS("数据更新成功");
        } else {
            return Result.FAIL("数据更新失败");
        }
    }
}
