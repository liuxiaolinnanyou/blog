package com.njupt.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@TableName("tb_article_category")
public class ArticleCategory implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @NotNull
    @TableField("article_id")
    private Long articleId;

    @NotNull
    @TableField("category_id")
    private Long categoryId;

    public ArticleCategory() {
    }

    public ArticleCategory(Long articleId, Long categoryId) {
        this.articleId = articleId;
        this.categoryId = categoryId;
    }
}
