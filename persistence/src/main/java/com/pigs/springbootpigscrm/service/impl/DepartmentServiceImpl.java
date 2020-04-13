package com.pigs.springbootpigscrm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Department;
import com.pigs.springbootpigscrm.mapper.DepartmentMapper;
import com.pigs.springbootpigscrm.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author PIGS
 * @since 2020-03-23
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<Department> queryEmployeeList(Page page, Map<String, Object> map) {
        IPage<Department> departmentIPage = departmentMapper.queryEmployeeList(page, map);

        if (departmentIPage != null) {

            return departmentIPage;
        }
        return null;
    }
}
