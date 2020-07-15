package com.njupt.system.controller;

import com.njupt.common.annotation.Log;
import com.njupt.common.controller.BaseController;
import com.njupt.common.exception.GlobalException;
import com.njupt.common.utils.QueryPage;
import com.njupt.common.utils.R;
import com.njupt.system.entity.SysLoginLog;
import com.njupt.system.service.LoginLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/loginlog")
@Api(value = "LoginLogController", tags = {"登录日志管理接口"})
public class LoginLogController extends BaseController {

    @Autowired
    private LoginLogService loginLogService;

    @GetMapping("/list")
    public R findByPage(SysLoginLog sysLoginLog, QueryPage queryPage) {
        return new R<>(super.getData(loginLogService.list(sysLoginLog, queryPage)));
    }

    @Log("删除登录日志")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        try {
            loginLogService.delete(id);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }
}
