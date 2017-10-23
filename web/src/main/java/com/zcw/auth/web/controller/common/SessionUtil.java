package com.zcw.auth.web.controller.common;

import com.zcw.auth.dao.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class SessionUtil {

    private static final Logger log = LoggerFactory.getLogger(SessionUtil.class);

    public static Account getCurrentUser(HttpServletRequest request) {
        return null;
    }

    public static void clearLoginInfor(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
