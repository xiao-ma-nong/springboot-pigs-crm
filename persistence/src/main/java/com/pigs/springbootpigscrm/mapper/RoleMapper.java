package com.pigs.springbootpigscrm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pigs.springbootpigscrm.entity.vo.QueryEmployeeListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author PIGS
 * @since 2020-03-23
 * 角色 mapper 接口
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询 全部员工 并分页
     *
     * @param map
     * @param page
     * @return
     */
    IPage<Role> queryRoleList(Page page, @Param("map") Map<String, Object> map);


}
