package com.jurua.api.user.controller;

import com.jurua.api.common.model.result.ResultApi;
import com.jurua.api.user.model.vo.UsersVO;
import com.jurua.api.user.service.IUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.HttpURLConnection;
import java.util.List;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/UserController")
@Api(value = "用户controller", description = "用户")
public class UserController {

    @Autowired
    private IUser iUser;

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
}
