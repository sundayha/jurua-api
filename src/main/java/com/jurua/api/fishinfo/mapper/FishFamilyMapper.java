package com.jurua.api.fishinfo.mapper;

import com.jurua.api.config.mybatis.Mapper;
import com.jurua.api.fishinfo.model.FishFamily;
@Mapper
public interface FishFamilyMapper {
    int deleteByPrimaryKey(Long fFId);

    int insert(FishFamily record);

    int insertSelective(FishFamily record);

    FishFamily selectByPrimaryKey(Long fFId);

    int updateByPrimaryKeySelective(FishFamily record);

    int updateByPrimaryKey(FishFamily record);
}