package com.jurua.api.crowdshare.controller;

import com.jurua.api.common.model.page.PagingInfo;
import com.jurua.api.common.model.page.PagingResult;
import com.jurua.api.common.model.result.ResultApi;
import com.jurua.api.common.utils.http.HttpUtil;
import com.jurua.api.crowdshare.model.FishBubble;
import com.jurua.api.crowdshare.model.query.FishFriendsSellFishTableQuery;
import com.jurua.api.crowdshare.model.vo.FishFriendsSellFishTableVO;
import com.jurua.api.crowdshare.service.ICrowdShareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;
import java.time.LocalDate;

import static com.jurua.api.common.constants.ServiceConstants.CROWD_SHARE_EXPORT_SELL_FISH_EXCEL_FILE_NAME;
import static com.jurua.api.common.constants.ServiceConstants.CROWD_SHARE_EXPORT_SELL_FISH_EXCEL_SUFFIX_NAME;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
@Controller
@RequestMapping(value = "/CrowdShareServiceController")
@Api(value = "群共享controller", description = "群共享")
public class CrowdShareServiceController {

    private ICrowdShareService iCrowdShareService;

    public CrowdShareServiceController(ICrowdShareService iCrowdShareService) {
        this.iCrowdShareService = iCrowdShareService;
    }

    @ApiOperation(value = "导出渔友出鱼excel", notes = "导出渔友出鱼excel")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个ResultApi", response = ResultApi.class)
    @RequestMapping(value = "/exportFishFriendsSellFishExcel", method = RequestMethod.GET)
    @ResponseBody
    ResultApi exportFishfriendsSellFishExcel(HttpServletResponse httpServletResponse) throws Exception {
        try {
            ResultApi<String> resultApi = new ResultApi<>();
            Workbook workbook = iCrowdShareService.exportFishFriendsSellFishExcel();
            String fileName = CROWD_SHARE_EXPORT_SELL_FISH_EXCEL_FILE_NAME.concat(LocalDate.now().toString()).concat(CROWD_SHARE_EXPORT_SELL_FISH_EXCEL_SUFFIX_NAME);
            ServletOutputStream servletOutputStream = HttpUtil.getDownloadServletOutputStream(httpServletResponse, fileName);
            workbook.write(servletOutputStream);
            servletOutputStream.flush();
            servletOutputStream.close();
            return resultApi;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @ApiOperation(value = "向鱼泡表插入渔友出鱼数据", notes = "向鱼泡表插入渔友出鱼数据")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个ResultApi", response = ResultApi.class)
    @RequestMapping(value = "/insertFishBubble", method = RequestMethod.POST)
    @ResponseBody
    ResultApi insertFishBubble(@RequestBody FishBubble fishBubble) throws Exception {
        try {
            ResultApi resultApi = new ResultApi();
            iCrowdShareService.insertFishBubble(fishBubble);
            return resultApi;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @ApiOperation(value = "渔友出鱼列表", notes = "渔友出鱼列表")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个ResultApi", response = ResultApi.class)
    @RequestMapping(value = "/findFishFriendsSellFishTable", method = RequestMethod.POST)
    @ResponseBody
    ResultApi findFishFriendsSellFishTable(@RequestBody PagingInfo<FishFriendsSellFishTableQuery> pagingInfo) throws Exception {
        try {
            ResultApi<PagingResult<FishFriendsSellFishTableVO, FishFriendsSellFishTableQuery>> resultApi = new ResultApi<>();
            resultApi.setData(iCrowdShareService.findFishFriendsSellFishTable(pagingInfo));
            return resultApi;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @ApiOperation(value = "修改渔友出鱼数据", notes = "修改渔友出鱼数据")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "成功返回，一个ResultApi", response = ResultApi.class)
    @RequestMapping(value = "/updateFishFriendsSellFish", method = RequestMethod.PUT)
    @ResponseBody
    ResultApi updateFishFriendsSellFish(@RequestBody FishBubble fishBubble) throws Exception {
        try {
            ResultApi resultApi = new ResultApi();
            iCrowdShareService.updateFishFriendsSellFish(fishBubble);
            return resultApi;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
