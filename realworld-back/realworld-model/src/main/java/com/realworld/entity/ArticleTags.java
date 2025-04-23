package com.realworld.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章标签关系表
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("article_tags")
@Schema(name="ArticleTags对象", description="文章标签关系表")
public class ArticleTags implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(name = "文章id")
    @TableField(value = "article_id")
    private Integer articleId;

    @Schema(name = "标签id")
    @TableField(value = "tag_id")
    private Integer tagId;

    @Schema(name = "标记删除状态")
    @TableField(value = "is_del")
    private Integer isDel;
}
