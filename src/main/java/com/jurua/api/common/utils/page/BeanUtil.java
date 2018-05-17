package com.jurua.api.common.utils.page;

import com.github.pagehelper.Page;
import com.jurua.api.common.model.page.PagingInfo;
import com.jurua.api.common.model.page.PagingResult;

import java.util.List;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * 整合分页结果
 */
public class BeanUtil {
    public static <T, E> PagingResult <T, E> toPagedResultMybatis(List<T> data, PagingInfo<E> pageInfo) {
        PagingResult<T, E> pageResult = new PagingResult<>();
        if (data instanceof Page) {
            Page<T> page = (Page<T>) data;
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
