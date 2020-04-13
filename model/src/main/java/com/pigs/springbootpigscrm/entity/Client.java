package com.pigs.springbootpigscrm.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * @author PIGS
 * @since 2020-04-03
 * 客户模型
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@ApiModel("客户模型")
public class Client {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "客户id",required = true)
    private Integer id;

    @ApiModelProperty(value = "客户名称",required = true)
    private String clientName;

    @ApiModelProperty(value = "客户性别",required = true)
    private Integer sex;

    @ApiModelProperty(value = "客户状态",required = true)
    private Integer status;

    @ApiModelProperty(value = "客户加入时间",required = true)
    private String createTime;

    @ApiModelProperty(value = "客户密码",required = true)
    private String userPassword;

    @ApiModelProperty(value = "盐",required = true)
    private String salt;

    @ApiModelProperty(value = "是否正式客户",required = true)
    private Integer isOrders;

    @ApiModelProperty(value = "客户手机号码",required = true)
    private String phoneNumber;

    @ApiModelProperty(value = "客户办公地址",required = true)
    private String address;

    /**
     * 员工 模型
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "员工模型",required = true)
    private Employee employee;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", sex=" + sex +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", salt='" + salt + '\'' +
                ", isOrders=" + isOrders +
                ", phoneNumber=" + phoneNumber +
                ", address=" + address +
                ", employee=" + employee +
                '}';
    }
}
