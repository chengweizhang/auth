package com.zcw.auth.web.controller.entity;

import java.util.List;

/**
 * Created by zhangchengwei on 2016/3/2.
 */
public class CommonC {
    private String entId;
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public CommonC setIds(List<String> ids) {
        this.ids = ids;
        return this;
    }

    public String getEntId() {
        return entId;
    }

    public CommonC setEntId(String entId) {
        this.entId = entId;
        return this;
    }
}
