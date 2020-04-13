package com.pigs.springbootpigscrm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author PIGS
 * @since 2020-03-23
 */
@Repository
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 查询 全部 部门 并分页
     * @param map
     * @param page
     * @return
     */
    IPage<Department> queryEmployeeList(Page page, @Param("map") Map<String, Object> map);


}
