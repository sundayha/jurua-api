package com.jurua.api.common.model.page;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/8/18.
 */

public class PagingResult<T> implements Serializable {
    private static final long serialVersionUID = -4451791256461165445L;
    @ApiModelProperty(value = "分页查询，返回的结果集合")
    private List<T> list;//数据
    @ApiModelProperty(value = "分页查询，返回的分页查询条件")
    private PagingInfo<T> page;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PagingInfo<T> getPage() {
        return page;
    }

    public void setPage(PagingInfo<T> page) {
        this.page = page;
    }
}
