package com.pigs.springbootpigscrm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author PIGS
 * @since 2020-03-23
 */
public interface IPermissionService extends IService<Permission> {

    /**
     * 查询全部的权限并 分页
     * 携带多个条件查询
     *
     * @param page
     * @param map
     * @return
     */
    IPage<Permission> queryPermissionList(Page page, Map<String, Object> map);

}
