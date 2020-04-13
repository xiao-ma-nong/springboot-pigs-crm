package com.pigs.springbootpigscrm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pigs.springbootpigscrm.entity.vo.NameRolePermissionVo;
import com.pigs.springbootpigscrm.entity.vo.QueryEmployeeListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author PIGS
 * @since 2020-03-23
 */
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 查询 员工名称 盐 角色 权限 url 信息
     *
     * @param employeeName
     * @return
     */
    List<Employee> queryEmployeeInfo(String employeeName);


    /**
     * 查询 菜单
     *
     * @param employeeName
     * @return
     */
    List<NameRolePermissionVo> queryNavs(String employeeName);

    /**
     * 查询 全部员工 并分页
     *
     * @param map
     * @param page
     * @return
     */
    IPage<QueryEmployeeListVo> queryEmployeeList(Page page,@Param("map") Map<String, Object> map);


}
