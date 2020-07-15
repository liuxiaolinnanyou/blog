package com.njupt.system.mapper;

import com.njupt.system.entity.SysArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface ArticleMapper extends BaseMapper<SysArticle> {

    List<String> findArchivesDates();

    List<SysArticle> findArchivesByDate(String date);
}
