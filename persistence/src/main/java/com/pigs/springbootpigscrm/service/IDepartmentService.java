package com.pigs.springbootpigscrm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pigs.springbootpigscrm.entity.vo.QueryEmployeeListVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author PIGS
 * @since 2020-03-23
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 查询 全部 部门 并分页
     * @param map
     * @param page
     * @return
     */
    IPage<Department> queryEmployeeList(Page page,  Map<String, Object> map);


}
