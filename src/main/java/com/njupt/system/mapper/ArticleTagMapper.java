package com.njupt.system.mapper;

import com.njupt.system.entity.SysArticle;
import com.njupt.system.entity.ArticleTag;
import com.njupt.system.entity.SysTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    List<SysTag> findByArticleId(long articleId);

    List<SysArticle> findByTagName(String name);
}
