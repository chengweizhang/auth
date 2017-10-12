package com.zcw.auth.dao.common;

import org.hibernate.criterion.Order;

/**
 * Created by zhangsan on 2015/9/18.
 */
public class Page {

    public static final Page MAX_PAGE = new Page(1, Integer.MAX_VALUE);

    public static final Page DEFAULT_PAGE = new Page(1, 10);

    public static final Order DEFAULT_ORDER = Order.desc("updateTime");

    private Integer page;

    private Integer pageSize;

    public Page(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize == null ? DEFAULT_PAGE.getPageSize() : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getFirstNumber() {
        if (page == null || pageSize == null) {
            return 0;
        }
        return page > 0 ? (page - 1) * pageSize : 0;
    }

    public static boolean isPageValid(Page page) {
        return page != null && page.getPage() != null && page.getPageSize() != null;
    }
}
