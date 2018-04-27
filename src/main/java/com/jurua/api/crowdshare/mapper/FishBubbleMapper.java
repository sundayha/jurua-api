package com.jurua.api.crowdshare.mapper;

import com.jurua.api.common.model.page.PagingInfo;
import com.jurua.api.crowdshare.model.FishBubble;
import com.jurua.api.crowdshare.model.query.FishFriendsSellFishTableQuery;
import com.jurua.api.crowdshare.model.vo.ExportFishFriendsSellFishExcelVo;
import com.jurua.api.crowdshare.model.vo.FishFriendsSellFishTableVO;

import java.util.List;

public interface FishBubbleMapper {
    int deleteByPrimaryKey(Long fBId);

    int insert(FishBubble record);

    int insertSelective(FishBubble record);

    FishBubble selectByPrimaryKey(Long fBId);

    int updateByPrimaryKeySelective(FishBubble record);

    int updateByPrimaryKey(FishBubble record);

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2018/4/23 上午11:53
     * @apiNote 导出渔友出鱼excel
     * @return List<ExportFishFriendsSellFishExcelVo>
     */
    List<ExportFishFriendsSellFishExcelVo> exportFishFriendsSellFishExcel();

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2018/4/25 下午3:26
     * @param pagingInfo 分页信息
     * @apiNote 渔友出鱼列表
     * @return PagingResult<FishFriendsSellFishTableVO, FishFriendsSellFishTableQuery>
     */
    List<FishFriendsSellFishTableVO> findFishFriendsSellFishTable(PagingInfo<FishFriendsSellFishTableQuery> pagingInfo);
}