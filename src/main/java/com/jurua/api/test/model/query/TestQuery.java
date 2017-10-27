package com.jurua.api.test.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 创建人：张博
 */
@ApiModel("测试表查询条件")
public class TestQuery implements Serializable {

    @ApiModelProperty("用户id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
