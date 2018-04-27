package com.jurua.api.crowdshare.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.github.pagehelper.PageHelper;
import com.jurua.api.common.model.page.PagingInfo;
import com.jurua.api.common.model.page.PagingResult;
import com.jurua.api.common.utils.BeanUtil;
import com.jurua.api.config.exception.service.CrowdShareServiceException;
import com.jurua.api.crowdshare.mapper.FishBubbleMapper;
import com.jurua.api.crowdshare.model.FishBubble;
import com.jurua.api.crowdshare.model.query.FishFriendsSellFishTableQuery;
import com.jurua.api.crowdshare.model.vo.ExportFishFriendsSellFishExcelVo;
import com.jurua.api.crowdshare.model.vo.FishFriendsSellFishTableVO;
import com.jurua.api.crowdshare.service.ICrowdShareService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jurua.api.common.constants.ServiceConstants.CROWD_SHARE_EXPORT_SELL_FISH_EXCEL_SHEET_NAME;
import static com.jurua.api.common.constants.ServiceConstants.CROWD_SHARE_EXPORT_SELL_FISH_EXCEL_TITLE;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
@Service
public class CrowdShareServiceImpl implements ICrowdShareService {

    @Autowired
    private FishBubbleMapper fishBubbleMapper;

    @Override
    public String loadExcel() {
        return null;
    }

    @Override
    public String editExcel() {
        return null;
    }

    @Override
    public Workbook exportFishFriendsSellFishExcel() throws CrowdShareServiceException {
        try {
            List<ExportFishFriendsSellFishExcelVo> list = fishBubbleMapper.exportFishFriendsSellFishExcel();
            ExportParams exportParams = new ExportParams(CROWD_SHARE_EXPORT_SELL_FISH_EXCEL_TITLE, CROWD_SHARE_EXPORT_SELL_FISH_EXCEL_SHEET_NAME);
            exportParams.setStyle(ExcelExportStylerImpl.class);
            return ExcelExportUtil.exportExcel(exportParams, ExportFishFriendsSellFishExcelVo.class, list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CrowdShareServiceException("导出渔友出鱼excel异常", e);
        }
    }

    @Override
    public void insertFishBubble(FishBubble fishBubble) throws CrowdShareServiceException {
        try {
            fishBubbleMapper.insertSelective(fishBubble);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CrowdShareServiceException("插入鱼泡数据异常，新增渔友出鱼数据异常", e);
        }
    }

    @Override
    public PagingResult<FishFriendsSellFishTableVO, FishFriendsSellFishTableQuery> findFishFriendsSellFishTable(PagingInfo<FishFriendsSellFishTableQuery> pagingInfo) throws CrowdShareServiceException {
        try {
            PagingResult<FishFriendsSellFishTableVO, FishFriendsSellFishTableQuery> pagingResult;
            PageHelper.startPage(pagingInfo.getCurrent(), pagingInfo.getPageSize());
            pagingResult = BeanUtil.toPagedResultMybatis(fishBubbleMapper.findFishFriendsSellFishTable(pagingInfo), pagingInfo);
            return pagingResult;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CrowdShareServiceException("渔友出鱼列表异常", e);
        }
    }

    @Override
    public void updateFishFriendsSellFish(FishBubble fishBubble) throws CrowdShareServiceException {
        try {
            fishBubbleMapper.updateByPrimaryKeySelective(fishBubble);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CrowdShareServiceException("修改渔友出鱼数据异常", e);
        }
    }
}
