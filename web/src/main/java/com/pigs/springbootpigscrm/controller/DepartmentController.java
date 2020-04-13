package com.pigs.springbootpigscrm.controller;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Department;
import com.pigs.springbootpigscrm.entity.ResultFormat;
import com.pigs.springbootpigscrm.entity.ResultFormatPaging;
import com.pigs.springbootpigscrm.entity.vo.QueryEmployeeListVo;
import com.pigs.springbootpigscrm.mapper.DepartmentMapper;
import com.pigs.springbootpigscrm.service.IDepartmentService;
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

/**
 * <p>
 * 部门前端控制器
 * </p>
 *
 * @author PIGS
 * @since 2020-03-23
 */
@RestController
@Api(value = "/department", description = "部门前端控制器")
@RequestMapping("/department/")
public class DepartmentController {
    private Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private DepartmentMapper departmentMapper;


    /**
     * 查询 部门 并分页
     */
    @GetMapping("queryDepartmentList")
    @ApiOperation(value = "查询部门列表")
    public ResultFormatPaging queryDepartmentList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, Map<String, Object> map) {

        if (page != null && limit != null) {

            Page<Department> iPage = new Page<Department>(page, limit);

            IPage<Department> mapIPage = departmentService.queryEmployeeList(iPage, map);

            List<Department> departments = iPage.getRecords();
            for (Department department : departments) {
                logger.info("department={}", department);
            }

            return ResultPagingUtil.pagingSuccess(0, (int) iPage.getTotal(), departments);
        }

        return ResultPagingUtil.pagingError(500, 0, "查询失败");
    }

    /**
     * 通过名字查询部门信息回显
     */
    @GetMapping("queryDepartmentName")
    @ApiOperation(value = "通过名字查询部门信息")
    public ResultFormat queryDepartmentName() {

        AbstractWrapper wrapper = new QueryWrapper();

        wrapper.eq("state", 0);
        List list = departmentService.list(wrapper);

        return ResultUtil.success(list);
    }

    /**
     * 修改 部门 状态
     */
    @PutMapping("updateDepartmentState")
    @ApiOperation(value = "修改部门状态")
    public ResultFormat updateDepartmentState(Integer departmentState, Integer departmentId) {
        Department department = new Department();
        logger.info("employeeState,employeeId={}", departmentState + " -- " + departmentId);

        if (departmentState != null && departmentId != null) {
            department.setId(departmentId);
            department.setState(departmentState);
            boolean update = departmentService.updateById(department);
            logger.info("update={}", update);
            return ResultUtil.success(update);
        }
        return ResultUtil.error(100, "修改失败");
    }

    /**
     * 修改 部门信息
     */
    @ApiOperation(value = "修改部门信息")
    @PutMapping("updateDepartment")
    public ResultFormat updateDepartment(@RequestBody Department department) {

        logger.info("department={}", department);

        if (department.getId() != null) {
            AbstractWrapper wrapper = new QueryWrapper<Department>();


            Department department1 = departmentMapper.selectById(department.getId());
            logger.info("list= {}", department1);

            if (department.getDepartmentName().equals(department1.getDepartmentName())) {
                return ResultUtil.error(100, "部门已经存在");
            }

            boolean flag = departmentService.updateById(department);

            if (flag) {
                return ResultUtil.success(flag);
            }

        }


        return ResultUtil.error(100, "修改失败");
    }

    /**
     *  添加部门
     */
    @PostMapping("saveDepartment")
    @ApiOperation(value = "添加部门")
    public ResultFormat saveDepartment(@RequestBody Department department) {

        logger.info("department={}", department);

        if (department.getState() != null && department.getDepartmentName() != null) {
            department.setUpdateTime(TimeUtitl.dateTime());
            AbstractWrapper wrapper = new QueryWrapper<Department>();
            wrapper.eq("department_name", department.getDepartmentName());
            List list = departmentService.list(wrapper);
            logger.info("list= {}", list);

            if (list.size() > 0) {
                return ResultUtil.error(100, "部门已经存在..");
            }

            Integer save = departmentMapper.insert(department);
            logger.info("save={}", save);
            if (save > 0) {
                return ResultUtil.success(save);
            }
        }

        return ResultUtil.error(100, "添加失败");
    }


}
