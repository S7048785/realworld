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
 * 文章评论表
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("comment")
@Schema(name="Comment对象", description="文章评论表")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(name = "评论内容")
    private String body;

    @Schema(name = "评论者id")
    private Integer userId;

    @Schema(name = "文章id")
    private Integer articleId;

    @Schema(name = "评论时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @Schema(name = "标记i删除状态")
    private Integer isDel;


}
