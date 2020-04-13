package com.pigs.springbootpigscrm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pigs.springbootpigscrm.entity.vo.NameRolePermissionVo;
import com.pigs.springbootpigscrm.entity.vo.QueryEmployeeListVo;
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
public interface IEmployeeService extends IService<Employee> {

    /**
     * employee.name,employee.salt,role.role,permission.permission,permission.url,department.name
     * 查询 员工名称 盐 角色 权限 url 部门名称 信息
     *
     * @param employeeName
     * @return
     */
    public List<Employee> queryEmployeeInfo(String employeeName);

    /**
     * permission.url,department.icon,department.name
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
    IPage<QueryEmployeeListVo> queryEmployeeList(Page page, @Param("map") Map<String, Object> map);



}
