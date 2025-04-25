package com.realworld.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户关注关系表
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_follow")
@Schema(description="用户关注关系表")
public class UserFollow implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "关注ID")
    private Integer userId;

    @Schema(description = "被关注ID")
    private Integer followedUserId;

    @Schema(description = "关注时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createdAt;

    @Schema(description = "标记删除状态")
    private int isDel;


}
