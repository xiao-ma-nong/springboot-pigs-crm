package com.pigs.springbootpigscrm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Y.yang
 * @since 2020-03-23
 * 部门 模型
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@TableName("department")
@ApiModel("部门模型")
public class Department extends CommonalityEntity {

    /**
     * 名称
     */
    @ApiModelProperty(value = "部门名称",required = true)
    private String departmentName;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + super.getId() +
                ",departmentName=" + departmentName +
                ",createTime=" + super.getCreateTime() +
                ",state=" + super.getState() +
                ",updateTime=" + super.getUpdateTime() +
                '}';
    }
}
