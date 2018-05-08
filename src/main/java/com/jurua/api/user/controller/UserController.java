package com.jurua.api.user.controller;

import com.jurua.api.common.model.result.ResultApi;
import com.jurua.api.config.jwt.JwtTokenUtil;
import com.jurua.api.user.model.User;
import com.jurua.api.user.model.vo.UsersVO;
import com.jurua.api.user.service.IUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
@Controller
@RequestMapping(value = "/UserController")
@Api(value = "用户controller", description = "用户")
public class UserController {

    @Autowired
    private IUser iUser;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private HttpServletResponse httpServletResponse;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "查询所有用户的集合对象", notes = "查询所有用户的集合对象")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个ResultApi", response = ResultApi.class)
    @RequestMapping(value = "/findUsers", method = RequestMethod.POST)
    @ResponseBody
    ResultApi findUsers() throws Exception {
        try {
            ResultApi<List<UsersVO>> resultApi = new ResultApi<>();
            resultApi.setData(iUser.findUsers());
            return resultApi;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @ApiOperation(value = "登陆", notes = "登陆")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个ResultApi", response = ResultApi.class)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultApi login(@ApiParam("用户名密码") @RequestBody User user) throws Exception {
        try {
            String uuid = UUID.randomUUID().toString();
            jwtTokenUtil.generateToken(new HashMap<>(0), uuid, user, httpServletRequest, httpServletResponse);
            return new ResultApi();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @ApiOperation(value = "测试注销", notes = "测试注销")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个ResultApi", response = ResultApi.class)
    @RequestMapping(value = "/logOut", method = RequestMethod.POST)
    @ResponseBody
    public void logOut() {
    }
}
