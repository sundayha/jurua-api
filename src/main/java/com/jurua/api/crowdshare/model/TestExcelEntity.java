package com.jurua.api.crowdshare.model;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
public class TestExcelEntity {

    @Excel(name = "姓名")
    private String name;
    @Excel(name = "性别")
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
