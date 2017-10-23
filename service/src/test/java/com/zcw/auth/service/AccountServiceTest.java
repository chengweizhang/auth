package com.zcw.auth.service;

import com.zcw.auth.dao.entity.Account;
import com.zcw.auth.dao.entity.AccountRoleR;
import com.zcw.auth.dao.entity.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by zhangchengwei on 09/10/2017.
 */
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AccountServiceTest extends BaseTest {
    @Autowired
    private AccountService accountService;

    @Autowired
    private GeneralServiceImpl generalService;

    @Test
    public void curdTest() throws Exception {
        Account account = new Account();
        account.setName("zhangsan").setMobile("mobile").setEmail("abc@cc.com");

        accountService.insert(account);

        Role role = new Role();
        role.setName("zhangsan2");
        role.setType(Role.RoleType.ADMINISTRATOR);

        generalService.saveObj(role);

        AccountRoleR accountRoleR = new AccountRoleR();
        accountRoleR.setRole(role);
        accountRoleR.setAccount(account);
        generalService.saveObj(accountRoleR);

        Account account1 = accountService.getById(account.getId());
        System.out.println(account1.getName());
    }

    @Test
    public void deleteTest() throws Exception {
        String id = "a372a1ed-e6d1-49bf-9ba1-8fdf764e3d4e";
        accountService.deleteById(id);
    }
}
