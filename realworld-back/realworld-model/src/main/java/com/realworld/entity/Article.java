package com.realworld.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("article")
@NoArgsConstructor
@Schema(name="Article对象", description="文章表")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(name = "文章标题")
    private String title;

    @Schema(name = "文章简介")
    private String description;

    @Schema(name = "文章内容")
    private String body;

    @Schema(name = "作者id")
    private Integer authorId;

    @Schema(name = "点赞数量")
    private int likeCount;

    @Schema(name = "收藏数量")
    private int favoritesCount;

    @Schema(name = "评论数量")
    private int commentCount;

    @Schema(name = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @Schema(name = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @Schema(name = "标记删除状态")
    private int isDel;


}
