package com.hohenheim.java.serviceplatform.account.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 第三方平台信息表
 * </p>
 *
 * @author Hohenheim
 * @since 2022-09-25
 */
@Getter
@Setter
@TableName("platforms")
public class PlatformsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 平台名称
     */
    @TableField("platform_name")
    private String platformName;

    /**
     * 创建人ID，如果是0，代表初始化的数据
     */
    @TableField("creator")
    private Long creator;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;


}
