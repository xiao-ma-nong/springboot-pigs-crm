package com.pigs.springbootpigscrm.service;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Department;
import com.pigs.springbootpigscrm.entity.Role;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PIGS
 * @version 1.0
 * @date 2020/3/27 10:02
 * @effect :
 */
@SpringBootTest
public class IRoleServiceTest {

    @Autowired
    private IRoleService roleService;

    private Logger logger = LoggerFactory.getLogger(IRoleServiceTest.class);

    @Test
    public void queryRoleList() {
        Page<Role> iPage = new Page<Role>(1, 2);
        Map<String, Object> map = new HashMap<String, Object>();

        IPage<Role> roleIPage = roleService.queryRoleList(iPage, map);

        List<Role> roleList = iPage.getRecords();
        for (Role role : roleList) {
            logger.info("Role={}", role);
        }
    }

    @Test
    public void queryRole(){
        List list = roleService.list();
        System.out.println(list);

    }

}