package com.jurua.api.test.controller;

import com.jurua.api.common.constants.StatusCode;
import com.jurua.api.common.model.page.PagingInfo;
import com.jurua.api.common.model.page.PagingResult;
import com.jurua.api.common.model.result.ResultApi;
import com.jurua.api.config.jwt.JwtTokenUtil;
import com.jurua.api.test.model.Test;
import com.jurua.api.test.model.query.TestQuery;
import com.jurua.api.test.service.ITestService;
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


/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 */
@Controller
@RequestMapping(value = "/jurua/TestController")
@Api(value = "测试controller", description = "测试测试")
public class TestController {

    @Autowired
    private ITestService iTestService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private HttpServletResponse httpServletResponse;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "测试用", notes = "真的是测试用")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个string", response = ResultApi.class)
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public ResultApi test() throws Exception {
        try {
            ResultApi<String> stringResultApi = new ResultApi<>();
            stringResultApi.setData(iTestService.test());
            //stringResultApi.setMessage(StatusCode.TEST.getMessage());
            //stringResultApi.setStatusCode(StatusCode.TEST.getCode());
            return stringResultApi;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @ApiOperation(value = "测试插入", notes = "测试插入")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个string", response = ResultApi.class)
    @RequestMapping(value = "/insertTest", method = RequestMethod.POST)
    @ResponseBody
    public ResultApi insertTest(@ApiParam("test对象") @RequestBody Test test) throws Exception {
        try {
            ResultApi<Integer> resultApi = new ResultApi<>();
            resultApi.setData(iTestService.insertTest(test));
            resultApi.setStatusCode(StatusCode.COMMON_OK.getCode());
            resultApi.setMessage(StatusCode.COMMON_OK.getMessage());
            return resultApi;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @ApiOperation(value = "测试分页", notes = "测试分页")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个ResultApi", response = PagingInfo.class)
    @RequestMapping(value = "/findTestById", method = RequestMethod.POST)
    @ResponseBody
    public ResultApi findTestById(@ApiParam("查询条件") @RequestBody PagingInfo<TestQuery> pagingInfo) throws Exception {
        try {
            String s = (String) redisTemplate.opsForValue().get(String.valueOf(httpServletRequest.getAttribute("uuid")));
            System.out.println(s);
            ResultApi<PagingResult<Test, TestQuery>> resultApi = new ResultApi<>();
            resultApi.setData(iTestService.findTestById(pagingInfo));
            resultApi.setMessage(StatusCode.COMMON_OK.getMessage());
            resultApi.setStatusCode(StatusCode.COMMON_OK.getCode());
            return resultApi;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //@ApiOperation(value = "测试登陆", notes = "测试登陆")
    //@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个ResultApi", response = ResultApi.class)
    //@RequestMapping(value = "/login", method = RequestMethod.POST)
    //@ResponseBody
    //public ResultApi login(@ApiParam("用户名密码") @RequestBody User user) throws Exception {
    //    try {
    //        String uuid = UUID.randomUUID().toString();
    //        jwtTokenUtil.generateToken(new HashMap<>(0), uuid, httpServletRequest, httpServletResponse);
    //        return new ResultApi();
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //        throw e;
    //    }
    //}

    @ApiOperation(value = "测试注销", notes = "测试注销")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个ResultApi", response = ResultApi.class)
    @RequestMapping(value = "/logOut", method = RequestMethod.POST)
    @ResponseBody
    public void logOut() {
    }
}
