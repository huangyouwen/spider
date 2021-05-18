package com.easystar.entity;

import java.util.Date;

public class ImageConfig {
    private Long id;
    private String name;
    private String cnName;
    private Date createDate;
    private String index_path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIndex_path() {
        return index_path;
    }

    public void setIndex_path(String index_path) {
        this.index_path = index_path;
    }
}
