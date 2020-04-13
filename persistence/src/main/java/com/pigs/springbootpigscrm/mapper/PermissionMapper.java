package com.pigs.springbootpigscrm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author PIGS
 * @since 2020-03-23
 *
 * 权限 mapper 接口
 *
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 查询全部的权限并 分页
     * 携带多个条件查询
     * @param page
     * @param map
     * @return
     */
    IPage<Permission> queryPermissionList(Page page, @Param("map") Map<String, Object> map);

}
