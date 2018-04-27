package com.jurua.api.fishinfo.service;

import com.jurua.api.config.exception.service.FishLibServiceException;
import com.jurua.api.fishinfo.model.FishLib;
import com.jurua.api.fishinfo.model.vo.FishLibsVO;

import java.util.List;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
public interface IFishLibService {

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2018/4/23 下午9:51
     * @param fishLib 鱼种库Do
     * @apiNote 插入鱼种类数据
     * @throws FishLibServiceException 插入鱼种类数据异常
     */
    void insertFishLib(FishLib fishLib) throws FishLibServiceException;

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2018/4/26 下午3:03
     * @apiNote 查询所有鱼种的集合对象给下拉框用
     * @return List<FishLibsVO>
     * @throws FishLibServiceException 查询所有鱼种的集合对象给下拉框用异常
     */
    List<FishLibsVO> findFishLibs() throws FishLibServiceException;
}
