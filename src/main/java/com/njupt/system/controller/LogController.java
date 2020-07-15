package com.njupt.system.controller;

import com.njupt.common.annotation.Log;
import com.njupt.common.controller.BaseController;
import com.njupt.common.exception.GlobalException;
import com.njupt.common.utils.QueryPage;
import com.njupt.common.utils.R;
import com.njupt.system.entity.SysLog;
import com.njupt.system.service.LogService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/log")
@Api(value = "LogController", tags = {"日志管理接口"})
public class LogController extends BaseController {

    @Autowired
    private LogService logService;

    @GetMapping("/list")
    public R findByPage(SysLog log, QueryPage queryPage) {
        return new R<>(super.getData(logService.list(log, queryPage)));
    }

    @Log("删除系统日志")
    @DeleteMapping("/{id}")
    @RequiresPermissions("log:delete")
    public R delete(@PathVariable Long id) {
        try {
            logService.delete(id);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }
}
