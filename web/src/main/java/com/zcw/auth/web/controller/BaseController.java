package com.zcw.auth.web.controller;

import com.zcw.auth.dao.common.Page;
import com.zcw.auth.web.controller.common.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;


public abstract class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);
    protected static final String PAGE = "page";
    protected static final String PAGE_SIZE = "pageSize";


    protected static Page getPage(Map<String, String> params) {
        return new Page(getInteger(params, PAGE), getInteger(params, PAGE_SIZE));
    }


    protected static Integer getPageNum(Map<String, String> params) {
        return getInteger(params, PAGE);
    }

    protected static Integer getPageSize(Map<String, String> params) {
        return getInteger(params, PAGE_SIZE);
    }

    public static Integer getInteger(Map<String, String> params, String key) {
        String keyS = params.get(key);
        if (StringUtils.isBlank(keyS)) {
            return null;
        } else {
            return Integer.parseInt(keyS);
        }
    }


    public static ResponseEntity<Result> getResult(Object data) {
        return getResult(new Result(data));
    }

    public static ResponseEntity<Result> getResult(Result result) {
        ResponseEntity<Result> responseEntity;
        if (result == null) {
            //默认返回成功
            result = new Result(true);
        }
        if (result.isSuccess()) {
            responseEntity = ResponseEntity.ok().body(result);
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
        return responseEntity;
    }
}
