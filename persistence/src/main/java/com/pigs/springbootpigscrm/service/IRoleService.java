package com.pigs.springbootpigscrm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author PIGS
 * @since 2020-03-23
 * 角色 service 接口
 */
public interface IRoleService extends IService<Role> {

    /**
     * 查询 全部员工 并分页
     *
     * @param map
     * @param page
     * @return
     */
    IPage<Role> queryRoleList(Page page, Map<String, Object> map);


}
