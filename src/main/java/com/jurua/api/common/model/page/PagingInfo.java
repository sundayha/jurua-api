package com.jurua.api.common.model.page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * 分页信息
 */
@JsonIgnoreProperties(value = {"offset"})
public class PagingInfo<E> implements Serializable {
    private static final long serialVersionUID = 699898315263998732L;
    @ApiModelProperty(value = "分页查询，当前要查询的页码")
    private Integer current = 1;
    @ApiModelProperty(value = "分页查询，当前要每页的页数")
    private Integer pageSize = 10;
    @ApiModelProperty(value = "分页查询，查询结果的总条数")
    private Long total = 0L;
    @ApiModelProperty(value = "分页查询，查询结果的总页数")
    private Integer totalPages = 0;
    @ApiModelProperty(value = "分页查询，查询条件所需封装的model")
    private E query;
    @ApiModelProperty(value = "需要排序的字段")
    private String sortField;
    @ApiModelProperty(value = "需要排序的方式")
    private String sortOrder;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public E getQuery() {
        return query;
    }

    public void setQuery(E query) {
        this.query = query;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
