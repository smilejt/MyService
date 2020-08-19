package cn.laoshengle.user.controller;

import cn.laoshengle.core.constant.CommonConstant;
import cn.laoshengle.core.entity.JsonResult;
import cn.laoshengle.core.entity.request.user.AddUserForm;
import cn.laoshengle.core.entity.request.user.QueryUserForm;
import cn.laoshengle.core.service.user.UserService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description: 用户jie'kou
 * @author: 龙逸
 * @createDate: 2020/08/18 18:07:20
 **/
@RestController
@RequestMapping(value = "userController")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    /**
     * 新增用户
     *
     * @param addUserForm 前端入参
     * @return 操作结果
     */
    @PostMapping("addUser")
    public JsonResult addUser(@RequestBody AddUserForm addUserForm) {

        logger.info("[UserController].[addUser]------> addUserForm = {}", JSON.toJSONString(addUserForm));

        //验证登录名
        if (StringUtils.isEmpty(addUserForm.getLoginName()) || StringUtils.isEmpty(addUserForm.getLoginName().trim())) {
            return JsonResult.buildFailMsg("登录名不能为空");
        } else if (!StringUtils.isEmpty(userService.getUserByLoginName(addUserForm.getLoginName()))) {
            return JsonResult.buildFailMsg("登录名已存在");
        } else {
            addUserForm.setLoginName(addUserForm.getLoginName().trim());
        }

        //验证用户名
        if (StringUtils.isEmpty(addUserForm.getUsername()) || StringUtils.isEmpty(addUserForm.getUsername().trim())) {
            return JsonResult.buildFailMsg("用户名不能为空");
        } else {
            addUserForm.setUsername(addUserForm.getUsername().trim());
        }

        //TODO 初始化密码并加密(仅限管理端新增用户)
        addUserForm.setPassword(CommonConstant.INITIAL_PASSWORD);

        //初始化用户状态(启动)
        addUserForm.setUserStatus(CommonConstant.USER_STATUS_ENABLE);

        //初始化创建时间
        addUserForm.setCreatTime(new Date());

        //调用数据处理层处理数据
        if (userService.addUser(addUserForm)) {
            return JsonResult.buildSuccess();
        } else {
            return JsonResult.buildFail();
        }
    }

    /**
     * 分页查询用户
     *
     * @param queryUserForm 前端查询参数
     * @return 查询结果
     */
    @PostMapping("getUserPage")
    public JsonResult getUserPage(@RequestBody QueryUserForm queryUserForm) {
        return JsonResult.buildSuccess().addObject(CommonConstant.RESULT_DATA, userService.getUserPage(queryUserForm));
    }
}
