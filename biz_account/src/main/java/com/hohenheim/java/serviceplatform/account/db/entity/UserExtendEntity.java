package com.hohenheim.java.serviceplatform.account.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户拓展信息
 * </p>
 *
 * @author Hohenheim
 * @since 2022-03-30
 */
@Data
@TableName("user_extend")
public class UserExtendEntity implements Serializable {

    private static final long serialVersionUID = 4621341131233853016L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

    /**
     * 真实名称
     */
    @TableField("real_name")
    private String realName;

    @TableField("birthday")
    private LocalDateTime birthday;

    /**
     * 身份证号码
     */
    @TableField("id_card")
    private String idCard;

    /**
     * 国家
     */
    @TableField("country")
    private String country;

    /**
     * 省/自治区
     */
    @TableField("province")
    private String province;

    /**
     * 城市
     */
    @TableField("city")
    private String city;

    /**
     * 区/县
     */
    @TableField("area")
    private String area;

    /**
     * 详细地址
     */
    @TableField("address")
    private String address;

    /**
     * 邮编
     */
    @TableField("postcode")
    private String postcode;
}
