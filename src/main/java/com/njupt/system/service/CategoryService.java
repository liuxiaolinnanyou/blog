package com.njupt.system.service;

import com.njupt.common.utils.QueryPage;
import com.njupt.system.entity.SysCategory;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface CategoryService extends IService<SysCategory> {

    IPage<SysCategory> list(SysCategory sysCategory, QueryPage queryPage);

    void add(SysCategory sysCategory);

    void update(SysCategory sysCategory);

    void delete(Long id);

    /**
     * 根据文章ID查询其关联的分类数据
     *
     * @param id
     * @return
     */
    List<SysCategory> findByArticleId(Long id);

    SysCategory findByName(String name);
}
