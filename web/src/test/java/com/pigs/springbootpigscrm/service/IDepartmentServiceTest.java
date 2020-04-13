package com.pigs.springbootpigscrm.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Department;
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
 * @date 2020/3/24 17:00
 * @effect :
 */
@SpringBootTest
public class IDepartmentServiceTest {

    private Logger logger = LoggerFactory.getLogger(IDepartmentServiceTest.class);

    @Autowired
    private IDepartmentService departmentService;

    @Test
    void queryDepartmentName(){
        List<Department> list = departmentService.list();
        for (Department department : list) {
            System.out.println(department);
        }

    }

    @Test
    void queryEmployeeList(){
        Page<Department> iPage = new Page<Department>(1, 2);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("departmentName","门");

        IPage<Department> mapIPage = departmentService.queryEmployeeList(iPage, map);

        List<Department> records = iPage.getRecords();
        for (Department department : records) {
            logger.info("department={}", department);
        }
    }



}