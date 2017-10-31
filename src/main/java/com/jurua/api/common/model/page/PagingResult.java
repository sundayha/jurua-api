package com.jurua.api.common.model.page;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * 分页结果
 */
public class PagingResult<T, E> implements Serializable {
    private static final long serialVersionUID = -4451791256461165445L;
    @ApiModelProperty(value = "分页查询，返回的结果集合")
    private List<T> list;
    @ApiModelProperty(value = "分页查询，返回的分页查询条件")
    private PagingInfo<E> page;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PagingInfo<E> getPage() {
        return page;
    }

    public void setPage(PagingInfo<E> page) {
        this.page = page;
    }
}
