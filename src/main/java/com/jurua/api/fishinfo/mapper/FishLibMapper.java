package com.jurua.api.fishinfo.mapper;

import com.jurua.api.config.mybatis.Mapper;
import com.jurua.api.fishinfo.model.FishLib;
import com.jurua.api.fishinfo.model.vo.FishLibsVO;

import java.util.List;

@Mapper
public interface FishLibMapper {
    int deleteByPrimaryKey(Long fishId);

    int insert(FishLib record);

    int insertSelective(FishLib record);

    FishLib selectByPrimaryKey(Long fishId);

    int updateByPrimaryKeySelective(FishLib record);

    int updateByPrimaryKey(FishLib record);

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2018/4/26 下午3:03
     * @apiNote 查询所有鱼种的集合对象给下拉框用
     * @return List<FishLibsVO>
     */
    List<FishLibsVO> findFishLibs();
}