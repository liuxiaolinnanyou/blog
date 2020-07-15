package com.njupt.system.controller;

import com.njupt.common.annotation.Log;
import com.njupt.common.controller.BaseController;
import com.njupt.common.exception.GlobalException;
import com.njupt.common.utils.QueryPage;
import com.njupt.common.utils.R;
import com.njupt.system.entity.SysArticle;
import com.njupt.system.entity.SysCategory;
import com.njupt.system.service.ArticleService;
import com.njupt.system.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/category")
@Api(value = "CategoryController", tags = {"分类管理接口"})
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/findAll")
    public R<List<SysCategory>> findAll() {
        return new R<>(categoryService.list(new QueryWrapper<>()));
    }

    /**
     * 查询所有的分类（包含对应的文章数量），结构：
     * [{分类名称，数量},{},....]
     *
     * @return
     */
    @GetMapping("/findArticleCountForCategory")
    public R<Map<String, Object>> findArticleCountForCategory() {
        Map<String, Object> map = new HashMap<>();
        List<SysCategory> sysCategoryList = categoryService.list(new QueryWrapper<>());
        for (SysCategory sysCategory : sysCategoryList) {
            List<SysArticle> sysArticleList = articleService.findByCategory(sysCategory.getName());
            map.put(sysCategory.getName(), sysArticleList.size());
        }
        return new R<>(map);
    }

    @GetMapping("/list")
    public R<Map<String, Object>> list(SysCategory sysCategory, QueryPage queryPage) {
        return new R<>(super.getData(categoryService.list(sysCategory, queryPage)));
    }

    @GetMapping("/{id}")
    public R<SysCategory> findById(@PathVariable("id") Long id) {
        return new R<>(categoryService.getById(id));
    }

    @PostMapping
    @Log("新增分类")
    public R save(@RequestBody SysCategory sysCategory) {
        try {
            categoryService.add(sysCategory);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping
    @Log("更新分类")
    public R update(@RequestBody SysCategory sysCategory) {
        try {
            categoryService.update(sysCategory);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Log("删除分类")
    public R delete(@PathVariable Long id) {
        try {
            categoryService.delete(id);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }
}
