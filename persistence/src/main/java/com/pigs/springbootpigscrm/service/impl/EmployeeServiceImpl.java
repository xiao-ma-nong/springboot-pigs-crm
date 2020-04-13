package com.pigs.springbootpigscrm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Employee;
import com.pigs.springbootpigscrm.entity.vo.NameRolePermissionVo;
import com.pigs.springbootpigscrm.entity.vo.QueryEmployeeListVo;
import com.pigs.springbootpigscrm.mapper.EmployeeMapper;
import com.pigs.springbootpigscrm.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 员工service 实现类
 *
 * @author PIGS
 * @since 2020-03-23
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 通过名字查询
     *
     * @param employeeName
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Employee> queryEmployeeInfo(String employeeName) {
        if (employeeName != null) {
            List<Employee> nameRolePermissionVo = employeeMapper.queryEmployeeInfo(employeeName);
            if (nameRolePermissionVo != null) {
                return nameRolePermissionVo;
            }

        }
        return null;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<NameRolePermissionVo> queryNavs(String employeeName) {
        if (employeeName != null) {
            List<NameRolePermissionVo> nameRolePermissionVo = employeeMapper.queryNavs(employeeName);
            if (nameRolePermissionVo != null) {
                return nameRolePermissionVo;
            }

        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<QueryEmployeeListVo> queryEmployeeList(Page page, Map<String, Object> map) {

        IPage<QueryEmployeeListVo> mapIPage = employeeMapper.queryEmployeeList(page, map);
        if (mapIPage != null) {

            return mapIPage;
        }


        return null;
    }
}
