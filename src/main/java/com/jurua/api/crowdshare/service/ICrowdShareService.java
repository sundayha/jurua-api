package com.jurua.api.crowdshare.service;

import com.jurua.api.common.model.page.PagingInfo;
import com.jurua.api.common.model.page.PagingResult;
import com.jurua.api.config.exception.service.CrowdShareServiceException;
import com.jurua.api.crowdshare.model.FishBubble;
import com.jurua.api.crowdshare.model.query.FishFriendsSellFishTableQuery;
import com.jurua.api.crowdshare.model.vo.FishFriendsSellFishTableVO;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
public interface ICrowdShareService {

    String loadExcel();

    String editExcel();

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2018/4/23 上午11:53
     * @apiNote 导出渔友出鱼excel
     * @return Workbook
     * @throws CrowdShareServiceException 导出渔友出鱼excel异常
     */
    Workbook exportFishFriendsSellFishExcel() throws CrowdShareServiceException;

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2018/4/24 上午9:42
     * @param fishBubble 鱼泡表Do
     * @apiNote 向鱼泡表插入渔友出鱼数据
     * @throws CrowdShareServiceException 插入鱼泡数据异常，新增渔友出鱼数据异常
     */
    void insertFishBubble(FishBubble fishBubble) throws CrowdShareServiceException;

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2018/4/25 下午3:26
     * @param pagingInfo 分页信息
     * @apiNote 渔友出鱼列表
     * @return PagingResult<FishFriendsSellFishTableVO, FishFriendsSellFishTableQuery>
     * @throws CrowdShareServiceException 渔友出鱼列表异常
     */
    PagingResult<FishFriendsSellFishTableVO, FishFriendsSellFishTableQuery> findFishFriendsSellFishTable(PagingInfo<FishFriendsSellFishTableQuery> pagingInfo) throws CrowdShareServiceException;

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2018/4/27 下午2:33
     * @param fishBubble 鱼泡表Do
     * @apiNote 修改渔友出鱼数据
     * @throws CrowdShareServiceException 修改渔友出鱼数据异常
     */
    void updateFishFriendsSellFish(FishBubble fishBubble) throws CrowdShareServiceException;
}
