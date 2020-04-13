package com.pigs.springbootpigscrm.service;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Employee;
import com.pigs.springbootpigscrm.entity.vo.NameRolePermissionVo;
import com.pigs.springbootpigscrm.entity.vo.QueryEmployeeListVo;
import com.pigs.springbootpigscrm.mapper.EmployeeMapper;
import com.pigs.springbootpigscrm.util.ResultUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author PIGS
 * @version 1.0
 * @date 2020/3/23 18:45
 * @effect :
 */
@SpringBootTest
public class IEmployeeServiceTest {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private Employee employee;

    /**
     * 查询员工信息
     */
    @Test
    public void queryNameRolePermissionInfo() {
        List<Employee> nameRolePermissionVos = employeeService.queryEmployeeInfo("猪农aaaaaaaaaaa");
        for (Employee nameRolePermissionVo : nameRolePermissionVos) {
            System.out.println(nameRolePermissionVo);
        }
    }

    /**
     * 菜单
     */
    @Test
    public void queryNavs() {
        List<NameRolePermissionVo> nameRolePermissionVos = employeeService.queryNavs("猪农aaaaaaaaaaa");
        for (NameRolePermissionVo nameRolePermissionVo : nameRolePermissionVos) {
            System.out.println(nameRolePermissionVo);
        }
    }

    /**
     * 查询全部的员工 并分页
     */
    @Test
    public void queryEmployeeList() {
        Map<String, Object> map = new HashMap<String, Object>();

        Page<QueryEmployeeListVo> iPage = new Page<QueryEmployeeListVo>(1, 5);
        System.out.println(iPage);
        map.put("employeeId",1);
        IPage<QueryEmployeeListVo> mapIPage = employeeService.queryEmployeeList(iPage, map);

        List<QueryEmployeeListVo> queryEmployeeListVoList = iPage.getRecords();
        for (QueryEmployeeListVo queryEmployeeListVo : queryEmployeeListVoList) {
            System.out.println(queryEmployeeListVo);
        }

    }

    /**
     * 修改员工状态
     */
    @Test
    void updateEmployeeState() {
        employee.setId(1);
        employee.setState(0);

        int update = employeeMapper.updateById(employee);
        System.out.println(update);
    }

}