package com.jurua.api.crowdshare.mapper;

import com.jurua.api.crowdshare.model.FishBubblePic;

public interface FishBubblePicMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FishBubblePic record);

    int insertSelective(FishBubblePic record);

    FishBubblePic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FishBubblePic record);

    int updateByPrimaryKey(FishBubblePic record);
}