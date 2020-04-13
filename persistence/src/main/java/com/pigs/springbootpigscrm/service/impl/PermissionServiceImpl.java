package com.pigs.springbootpigscrm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Permission;
import com.pigs.springbootpigscrm.mapper.PermissionMapper;
import com.pigs.springbootpigscrm.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 查询全部的权限并 分页
     * 携带多个条件查询
     *
     * @param page
     * @param map
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public IPage<Permission> queryPermissionList(Page page, Map<String, Object> map) {

        IPage<Permission> permissionIPage = permissionMapper.queryPermissionList(page, map);
        if (permissionIPage != null) {
            return permissionIPage;
        }

        return null;
    }
}
