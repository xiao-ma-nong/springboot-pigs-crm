package com.pigs.springbootpigscrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author PIGS
 * @version 1.0
 * @date 2020/3/23 15:10
 * @effect :
 * 公共 模型
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@ApiModel("基类")
public class CommonalityEntity implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id",required = true)
    private Integer id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",required = true)
    private String createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间",required = true)
    private String updateTime;


    /**
     * 状态
     */
    @ApiModelProperty(value = "状态",required = true)
    private Integer state;



}
