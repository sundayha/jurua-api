package com.jurua.api.fishinfo.service.impl;

import com.jurua.api.config.exception.service.FishLibServiceException;
import com.jurua.api.fishinfo.mapper.FishLibMapper;
import com.jurua.api.fishinfo.model.FishLib;
import com.jurua.api.fishinfo.model.vo.FishLibsVO;
import com.jurua.api.fishinfo.service.IFishLibService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
@Service
public class FishLibServiceImpl implements IFishLibService {

    private FishLibMapper fishLibMapper;

    public FishLibServiceImpl(FishLibMapper fishLibMapper) {
        this.fishLibMapper = fishLibMapper;
    }

    @Override
    public void insertFishLib(FishLib fishLib) throws FishLibServiceException {
        try {
            fishLibMapper.insertSelective(fishLib);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FishLibServiceException("插入鱼种类数据异常", e);
        }
    }

    @Override
    public List<FishLibsVO> findFishLibs() throws FishLibServiceException {
        try {
            return fishLibMapper.findFishLibs();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FishLibServiceException("查询所有鱼种的集合对象给下拉框用异常", e);
        }
    }
}
