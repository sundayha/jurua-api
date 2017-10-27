package com.jurua.api.common.utils;

import com.github.pagehelper.Page;
import com.jurua.api.common.model.page.PagingInfo;
import com.jurua.api.common.model.page.PagingResult;

import java.util.List;

public class BeanUtil {
    public static PagingResult toPagedResultMybatis(List data, PagingInfo pageInfo) {
        PagingResult pageResult = new PagingResult<>();
        if (data instanceof Page) {
            Page page = (Page) data;
            if (page.getPageNum() != 0) {
                pageInfo.setCurrent(page.getPageNum());
            }
            pageInfo.setPageSize(page.getPageSize());
            pageInfo.setTotal(page.getTotal());
            pageInfo.setTotalPages(page.getPages());
            pageResult.setList(page.getResult());
            pageResult.setPage(pageInfo);
        } else {
            pageInfo.setCurrent(1);
            pageInfo.setPageSize(data.size());
            pageInfo.setTotal((long) data.size());
            pageResult.setList(data);
            pageResult.setPage(pageInfo);
        }
        return pageResult;
    }
}
