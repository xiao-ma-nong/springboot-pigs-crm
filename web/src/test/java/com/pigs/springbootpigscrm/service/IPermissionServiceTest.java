package com.pigs.springbootpigscrm.service;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Department;
import com.pigs.springbootpigscrm.entity.Permission;
import com.pigs.springbootpigscrm.mapper.PermissionMapper;
import com.pigs.springbootpigscrm.util.ResultUtil;
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
 * @date 2020/3/27 22:54
 * @effect :
 */
@SpringBootTest
public class IPermissionServiceTest {

    private Logger logger = LoggerFactory.getLogger(IPermissionServiceTest.class);

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     *
     */
    @Test
    public void queryPermission() {

        Page<Permission> iPage = new Page<Permission>(1, 20);
        Map<String, Object> map = new HashMap<String, Object>();
        //map.put("departmentName","门");

        IPage<Permission> mapIPage = permissionService.queryPermissionList(iPage, map);

        List<Permission> records = iPage.getRecords();
        for (Permission permission : records) {
            logger.info("permission= {}", permission);
        }
    }

    @Test
    void queryPermissionInfo() {
        AbstractWrapper wrapper = new QueryWrapper<Permission>();

        wrapper.eq("url", "saveEmployeetList1");
        wrapper.or();
        wrapper.eq("permission", "employee:save");

        Permission permissionList = permissionMapper.selectOne(wrapper);

        logger.info("permissionList={}", permissionList);

        if (permissionList != null) {

            if (permissionList.getUrl() != null) {
                System.out.println("url已经存在..");
                return;
            }

            if (permissionList.getPermission() != null) {
                System.out.println("权限已经存在..");
            }
        }

    }

}