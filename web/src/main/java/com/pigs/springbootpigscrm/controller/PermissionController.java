package com.pigs.springbootpigscrm.controller;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.*;
import com.pigs.springbootpigscrm.mapper.PermissionMapper;
import com.pigs.springbootpigscrm.service.IPermissionRoleRefService;
import com.pigs.springbootpigscrm.service.IPermissionService;
import com.pigs.springbootpigscrm.util.ResultPagingUtil;
import com.pigs.springbootpigscrm.util.ResultUtil;
import com.pigs.springbootpigscrm.util.TimeUtitl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限前端控制器
 * </p>
 *
 * @author PIGS
 * @since 2020-03-23
 */
@RestController
@RequestMapping("/permission/")
@Api(value = "/permission", description = "权限前端控制器")
public class PermissionController {


    private Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private PermissionMapper permissionMapper;


    @Autowired
    private IPermissionRoleRefService permissionRoleRefService;

    /**
     * 查询 权限列表 并分页
     */
    @GetMapping("queryPermissionList")
    @ApiOperation(value = "查询权限信息列表")
    public ResultFormatPaging queryPermissionList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, Map<String, Object> map) {

        if (page != null && limit != null) {

            Page<Permission> iPage = new Page<Permission>(page, limit);

            IPage<Permission> mapIPage = permissionService.queryPermissionList(iPage, map);

            List<Permission> permissionList = iPage.getRecords();
            for (Permission permission : permissionList) {
                logger.info("permission={}", permission);
            }
            return ResultPagingUtil.pagingSuccess(0, (int) iPage.getTotal(), permissionList);
        }
        return ResultPagingUtil.pagingError(500, 0, "查询失败");
    }


    /**
     * 添加 权限
     */
    @PostMapping("savePermission")
    @ApiOperation(value = "添加权限")
    public ResultFormat savePermission(@RequestBody HashMap<String, Object> hashMap) {

        logger.info("permission={}", hashMap);

        if (hashMap.get("state") != null && hashMap.get("permission") != null && hashMap.get("name") != null && hashMap.get("url") != null && hashMap.get("roleId") != null) {

            AbstractWrapper wrapper = new QueryWrapper<Permission>();
            wrapper.eq("url", hashMap.get("url"));

            Permission permissionList = permissionMapper.selectOne(wrapper);

            logger.info("permissionList= {}", permissionList);

            if (permissionList != null) {
                if (permissionList.getUrl() != null) {
                    return ResultUtil.error(100, "url已经存在..");
                }

            }

            AbstractWrapper wrapperTwo = new QueryWrapper<Permission>();

            wrapperTwo.eq("permission", hashMap.get("permission"));

            Permission permissionTwo = permissionMapper.selectOne(wrapperTwo);
            if (permissionTwo != null) {
                if (permissionTwo.getPermission() != null) {
                    return ResultUtil.error(100, "权限已经存在..");
                }
            }

            Permission permission = new Permission();

            permission.setUpdateTime(TimeUtitl.dateTime());
            permission.setName((String) hashMap.get("name"));

            String state = (String) hashMap.get("state");
            permission.setState(Integer.valueOf(state));
            permission.setUrl((String) hashMap.get("url"));
            permission.setPermission((String) hashMap.get("permission"));

            boolean permissionFlag = permissionService.save(permission);

            PermissionRoleRef permissionRoleRef = new PermissionRoleRef();
            permissionRoleRef.setPermissionId(permission.getId());
            permissionRoleRef.setUpdateTime(TimeUtitl.dateTime());
            permissionRoleRef.setState(0);

            String roleId = (String) hashMap.get("roleId");
            permissionRoleRef.setRoleId(Integer.valueOf(roleId));

            boolean permissionRoleRefFlag = permissionRoleRefService.save(permissionRoleRef);

            logger.info("permissionFlag={}", permissionFlag);
            logger.info("permissionRoleRefFlag={}", permissionRoleRefFlag);

            if (permissionFlag && permissionRoleRefFlag) {
                return ResultUtil.success(permissionFlag);
            }
        }

        return ResultUtil.error(100, "添加失败");
    }


    /**
     * 修改权限状态
     */
    @PutMapping("updatePermissionState")
    @ApiOperation(value = "修改权限状态")
    public ResultFormat updatePermissionState(Integer permissionState, Integer permissionId) {
        Permission permission = new Permission();
        logger.info("permissionState,permissionId={}", permissionState + " -- " + permissionId);
        if (permissionState != null && permissionId != null) {
            permission.setId(permissionId);
            permission.setState(permissionState);
            boolean update = permissionService.updateById(permission);
            logger.info("updatePermissionState={}", update);
            return ResultUtil.success(update);
        }
        return ResultUtil.error(100, "修改失败");
    }

    /**
     * 修改权限信息
     */
    @PutMapping("updatePermission")
    @ApiOperation(value = "修改权限信息")
    public ResultFormat updatePermission(@RequestBody HashMap<String, Object> hashMap) {

        logger.info("permission={}", hashMap);

        if (hashMap.get("id") != null && hashMap.get("state") != null && hashMap.get("permission") != null && hashMap.get("name") != null && hashMap.get("url") != null && hashMap.get("roleId") != null) {

            AbstractWrapper wrapper = new QueryWrapper<Permission>();
            wrapper.eq("url", hashMap.get("url"));

            Permission permissionList = permissionMapper.selectOne(wrapper);

            logger.info("permissionList= {}", permissionList);

            if (permissionList != null) {

                if (hashMap.get("url").equals(permissionList.getUrl()) || permissionList.getUrl() == null) {

                    AbstractWrapper wrapperTwo = new QueryWrapper<Permission>();

                    wrapperTwo.eq("permission", hashMap.get("permission"));
                    Permission permissionTwo = permissionMapper.selectOne(wrapperTwo);

                    if (permissionTwo != null) {

                        if (hashMap.get("permission").equals(permissionTwo.getPermission()) || permissionTwo.getPermission() == null) {

                            Permission permission = new Permission();

                            permission.setUpdateTime(TimeUtitl.dateTime());
                            permission.setName((String) hashMap.get("name"));

                            String state = (String) hashMap.get("state");
                            permission.setState(Integer.valueOf(state));
                            permission.setUrl((String) hashMap.get("url"));
                            permission.setPermission((String) hashMap.get("permission"));
                            String id = (String) hashMap.get("id");

                            AbstractWrapper wrapperOne = new QueryWrapper<Permission>();

                            wrapperOne.eq("id", Integer.parseInt(id));


                            boolean permissionFlag = permissionService.update(permission, wrapperOne);

                            PermissionRoleRef permissionRoleRef = new PermissionRoleRef();

                            permissionRoleRef.setUpdateTime(TimeUtitl.dateTime());

                            String roleId = (String) hashMap.get("roleId");
                            permissionRoleRef.setRoleId(Integer.valueOf(roleId));

                            AbstractWrapper wrapperThree = new QueryWrapper<Permission>();

                            wrapperThree.eq("permission_id", Integer.parseInt(id));

                            boolean permissionRoleRefFlag = permissionRoleRefService.update(permissionRoleRef, wrapperThree);

                            logger.info("permissionFlag={}", permissionFlag);
                            logger.info("permissionRoleRefFlag={}", permissionRoleRefFlag);

                            if (permissionFlag && permissionRoleRefFlag) {
                                return ResultUtil.success(permissionFlag);
                            }


                        }
                        if (permissionTwo.getPermission() != null) {
                            return ResultUtil.error(100, "权限已经存在..");
                        }

                    }


                }
                if (permissionList.getUrl() != null) {

                    return ResultUtil.error(100, "url已经存在..");
                }

            }


        }

        return ResultUtil.error(100, "修改失败");
    }

}
