package com.pigs.springbootpigscrm.controller;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Department;
import com.pigs.springbootpigscrm.entity.ResultFormat;
import com.pigs.springbootpigscrm.entity.ResultFormatPaging;
import com.pigs.springbootpigscrm.entity.Role;
import com.pigs.springbootpigscrm.mapper.RoleMapper;
import com.pigs.springbootpigscrm.service.IRoleService;
import com.pigs.springbootpigscrm.util.ResultPagingUtil;
import com.pigs.springbootpigscrm.util.ResultUtil;
import com.pigs.springbootpigscrm.util.TimeUtitl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author PIGS
 * @since 2020-03-23
 * <p>
 * 角色控制器
 */
@RestController
@RequestMapping("/role/")
@Api(value = "/role", description = "角色前端控制器")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private RoleMapper roleMapper;


    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    /**
     * 查询角色列表 并分页
     */
    @GetMapping("queryRoleList")
    @ApiOperation(value = "查询角色信息列表")
    public ResultFormatPaging queryDepartmentName(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, Map<String, Object> map) {

        if (page != null && limit != null) {

            Page<Role> iPage = new Page<Role>(page, limit);

            IPage<Role> mapIPage = roleService.queryRoleList(iPage, map);

            List<Role> roleList = iPage.getRecords();
            for (Role role : roleList) {
                logger.info("role={}", role);
            }

            return ResultPagingUtil.pagingSuccess(0, (int) iPage.getTotal(), roleList);
        }

        return ResultPagingUtil.pagingError(500, 0, "查询失败");
    }

    /**
     * 修改角色状态
     */
    @PutMapping("updateRoleState")
    @ApiOperation(value = "查询角色状态")
    public ResultFormat updateRoleState(Integer roleState, Integer roleId) {
        Role role = new Role();
        logger.info("employeeState,employeeId={}", roleState + " -- " + roleId);
        if (roleState != null && roleId != null) {
            role.setId(roleId);
            role.setState(roleState);
            boolean update = roleService.updateById(role);
            logger.info("update={}", update);
            return ResultUtil.success(update);
        }
        return ResultUtil.error(100, "修改失败");
    }


    /**
     * 添加角色
     */
    @ApiOperation(value = "添加角色")
    @PostMapping("saveRole")
    public ResultFormat saveRole(@RequestBody Role role) {

        AbstractWrapper wrapper = new QueryWrapper<Role>();

        logger.info("role={}", role);

        if (role.getState() != null && role.getRole() != null) {
            role.setUpdateTime(TimeUtitl.dateTime());

            wrapper.eq("role", role.getRole());

            List list = roleService.list(wrapper);
            logger.info("list= {}", list);

            if (list.size() > 0) {
                return ResultUtil.error(100, "角色已经存在..");
            }

            boolean flag = roleService.save(role);
            logger.info("save={}", flag);
            if (flag) {
                return ResultUtil.success(flag);
            }
        }

        return ResultUtil.error(100, "添加失败");
    }


    /**
     * 修改角色信息
     */
    @PutMapping("updateRole")
    @ApiOperation(value = "查询角色信息")
    public ResultFormat updateRole(@RequestBody Role role) {

        logger.info("department={}", role);

        if (role.getId() != null) {
            AbstractWrapper wrapper = new QueryWrapper<Department>();

            Role role1 = roleMapper.selectById(role.getId());
            logger.info("list= {}", role1);

            if (role1.getRole().equals(role.getRole())) {
                return ResultUtil.error(100, "角色已经存在");
            }

            boolean flag = roleService.updateById(role);

            if (flag) {
                return ResultUtil.success(flag);
            }

        }


        return ResultUtil.error(100, "修改失败");
    }


    /**
     * 查询角色 role 回显数据
     *
     * @return
     */
    @GetMapping("queryRole")
    @ApiOperation(value = "查询角色信息")
    public ResultFormat queryRole() {
        AbstractWrapper wrapper = new QueryWrapper<Role>();
        wrapper.eq("state",0);
        List list = roleService.list(wrapper);

        for (Object o : list) {
            logger.info("role= {}", o);
        }


        return ResultUtil.success(list);

    }


}
