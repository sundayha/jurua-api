package com.jurua.api.fishinfo.controller;

import com.jurua.api.common.model.result.ResultApi;
import com.jurua.api.fishinfo.model.FishLib;
import com.jurua.api.fishinfo.model.vo.FishLibsVO;
import com.jurua.api.fishinfo.service.IFishLibService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.HttpURLConnection;
import java.util.List;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
@Controller
@RequestMapping(value = "/fishInfo/FishLibController")
@Api(value = "鱼种类controller", description = "鱼种类")
public class FishLibController {

    private IFishLibService iFishLibService;

    public FishLibController(IFishLibService iFishLibService) {
        this.iFishLibService = iFishLibService;
    }

    @ApiOperation(value = "插入鱼种类数据", notes = "插入鱼种类数据")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个ResultApi", response = ResultApi.class)
    @RequestMapping(value = "/insertFishLib", method = RequestMethod.POST)
    @ResponseBody
    ResultApi insertFishLib(@RequestBody FishLib fishLib) throws Exception {
        try {
            ResultApi resultApi = new ResultApi();
            iFishLibService.insertFishLib(fishLib);
            return resultApi;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @ApiOperation(value = "查询所有鱼种的集合对象给下拉框用", notes = "查询所有鱼种的集合对象给下拉框用")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个ResultApi", response = ResultApi.class)
    @RequestMapping(value = "/findFishLibs", method = RequestMethod.POST)
    @ResponseBody
    ResultApi findFishLibs() throws Exception {
        try {
            ResultApi<List<FishLibsVO>> resultApi = new ResultApi<>();
            resultApi.setData(iFishLibService.findFishLibs());
            return resultApi;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
