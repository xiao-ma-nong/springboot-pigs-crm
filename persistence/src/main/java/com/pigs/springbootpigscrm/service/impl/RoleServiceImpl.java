package com.pigs.springbootpigscrm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Role;
import com.pigs.springbootpigscrm.mapper.RoleMapper;
import com.pigs.springbootpigscrm.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author PIGS
 * @since 2020-03-23
 * 角色 service 实现类
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询 角色 列表 分页
     * @param page
     * @param map
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<Role> queryRoleList(Page page, Map<String, Object> map) {
        IPage<Role> roleIPage = roleMapper.queryRoleList(page, map);
        if (roleIPage != null) {
            return roleIPage;
        }
        return null;
    }
}
