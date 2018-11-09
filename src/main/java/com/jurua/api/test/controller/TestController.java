package com.jurua.api.test.controller;

import com.jurua.api.common.constants.StatusCode;
import com.jurua.api.common.model.page.PagingInfo;
import com.jurua.api.common.model.page.PagingResult;
import com.jurua.api.common.model.result.ResultApi;
import com.jurua.api.test.model.Test;
import com.jurua.api.test.model.query.TestQuery;
import com.jurua.api.test.service.ITestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;


/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 */
@Controller
@RequestMapping(value = "/jurua/TestController")
@Api(value = "测试controller", description = "测试测试")
public class TestController {

    private ITestService iTestService;

    public TestController(ITestService iTestService) {
        this.iTestService = iTestService;
    }

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

    @ApiOperation(value = "测试缓存插入", notes = "测试缓存插入")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个ResultApi", response = ResultApi.class)
    @RequestMapping(value = "/cacheData/{key}", method = RequestMethod.GET)
    @ResponseBody
    public ResultApi cacheData(@PathVariable String key) throws Exception {
        ResultApi<String> resultApi = new ResultApi<>();
        try {
            //resultApi.setData(iTestService.findData(key));
            resultApi.setData(iTestService.findDataAnnotation(key));
            return resultApi;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @ApiOperation(value = "测试缓存插入1", notes = "测试缓存插入1")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个ResultApi", response = ResultApi.class)
    @RequestMapping(value = "/cacheData1/{key}", method = RequestMethod.GET)
    @ResponseBody
    public ResultApi cacheData1(@PathVariable String key) throws Exception {
        ResultApi<String> resultApi = new ResultApi<>();
        try {
            resultApi.setData(iTestService.findDataAnnotation1(key));
            return resultApi;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @ApiOperation(value = "测试缓存删除", notes = "测试缓存删除")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个ResultApi", response = ResultApi.class)
    @RequestMapping(value = "/delCacheData/{key}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delCacheData(@PathVariable String key) throws Exception {
        return iTestService.delDataAnnotation(key);
    }

    @ApiOperation(value = "测试缓存更新", notes = "测试缓存更新")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个ResultApi", response = ResultApi.class)
    @RequestMapping(value = "/updateCacheData/{key}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateCacheData(@PathVariable String key) throws Exception {
        return iTestService.updateDataAnnotation(key);
    }

    @ApiOperation(value = "测试 redissonTopic", notes = "测试 redissonTopic")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个ResultApi", response = ResultApi.class)
    @RequestMapping(value = "/redissonTopicT", method = RequestMethod.POST)
    @ResponseBody
    public String redissonTopicT() throws Exception {
        return iTestService.redissonTopicT();
    }

    @ApiOperation(value = "测试 tAsync", notes = "测试 tAsync")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "无返回值")
    @RequestMapping(value = "/tAsync", method = RequestMethod.POST)
    @ResponseBody
    public String tAsync() throws Exception {
        return iTestService.tAsync();
    }
}
