package com.zcw.auth.web.controller;

import com.zcw.auth.dao.AccountQuery;
import com.zcw.auth.dao.common.Page;
import com.zcw.auth.dao.entity.Account;
import com.zcw.auth.dao.entity.AccountRoleR;
import com.zcw.auth.dao.entity.Role;
import com.zcw.auth.service.AccountService;
import com.zcw.auth.service.GeneralServiceImpl;
import com.zcw.auth.web.controller.common.Result;
import com.zcw.auth.web.controller.common.SessionUtil;
import com.zcw.auth.web.controller.entity.CommonC;
import com.zcw.auth.web.controller.entity.LoginC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangsan on 2015/8/9.
 */
@RestController
@RequestMapping(path = "/account")
public class AccountController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);


    @Autowired
    private GeneralServiceImpl generalService;


    @Autowired
    private AccountService accountService;

    /**
     * 用户更新,只能改名字，密码，地址
     *
     * @return
     */
    @RequestMapping(path = "", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<Result> update(@RequestBody Account account, HttpServletRequest request) {
        accountService.saveOrUpdate(account);
        Result result = new Result(account);
        return getResult(result);
    }

    /**
     * 获取角色列表
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/role", method = RequestMethod.GET)
    public ResponseEntity<Result> addRole(HttpServletRequest request) {
        List<Role> roles = generalService.getRoles(null);
        Result result = new Result(roles);
        return getResult(result);
    }

    /**
     * 角色增改
     *
     * @param role
     * @param request
     * @return
     */
    @RequestMapping(path = "/role", method = RequestMethod.POST)
    public ResponseEntity<Result> addRole(@RequestBody Role role, HttpServletRequest request) {
        generalService.saveOrUpdateObj(role);
        Result result = new Result(role);
        return getResult(result);
    }

    /**
     * 分配角色
     *
     * @param commonC
     * @param accountId
     * @param request
     * @return
     */
    @RequestMapping(path = "/role/{accountId}", method = RequestMethod.POST)
    public ResponseEntity<Result> addRole(@RequestBody CommonC commonC, @PathVariable String accountId, HttpServletRequest request) {
        List<AccountRoleR> accountRoleRs = new ArrayList<>();
        for (String roleId : commonC.getIds()) {
            accountRoleRs.add(new AccountRoleR(new Account(accountId), roleId));
        }
        generalService.saveOrUpdateObj(accountRoleRs);
        return getResult(null);
    }

    /**
     * 删除分配角色
     *
     * @param commonC
     * @param accountId
     * @param request
     * @return
     */
    @RequestMapping(path = "/role/{accountId}", method = RequestMethod.DELETE)
    public ResponseEntity<Result> deleteRole(@RequestBody CommonC commonC, @PathVariable String accountId, HttpServletRequest request) {
        for (String roleId : commonC.getIds()) {
            generalService.deleteAccountRoleRByMixId(accountId, roleId);
        }
        return getResult(null);
    }

    /**
     * 用户登录
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Result> login(LoginC loginEntity, HttpServletRequest request, HttpServletResponse response) {

        return getResult(true);
    }

    /**
     * 分页获取用户列表
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Result> get(AccountQuery accountQuery, @RequestParam Map<String, String> params, HttpServletRequest request) {
        SessionUtil.getCurrentUser(request);
        Page page = getPage(params);
        List<Account> accounts = accountService.findByQuery(accountQuery, page);
        Long countByQuery = accountService.getCountByQuery(accountQuery);
        Result result = new Result(accounts);
        result.setTotal(countByQuery);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Result> getById(@PathVariable String id, HttpServletRequest request) {
        Account account = accountService.getById(id);
        return getResult(account);
    }

    /**
     * 用户登出
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ResponseEntity<Result> logout(HttpServletRequest request) {
        SessionUtil.clearLoginInfor(request);
        return getResult(null);
    }

    /**
     * 获取当前登录人信息
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public ResponseEntity<Result> getCurrent(HttpServletRequest request) {
        Account currentUser = SessionUtil.getCurrentUser(request);
        return getResult(currentUser);
    }
}
