package com.pigs.springbootpigscrm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pigs.springbootpigscrm.entity.Client;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <p>
 *  客户 Mapper 接口
 * </p>
 *
 * @author PIGS
 * @since 2020-04-03
 */
@Repository
public interface ClientMapper extends BaseMapper<Client> {

    /**
     * 查询 全部 客户 并分页 携带条件查询
     * @param map
     * @param page
     * @return
     */
    IPage<Client> queryClientList(Page page, @Param("map") Map<String, Object> map);


}
