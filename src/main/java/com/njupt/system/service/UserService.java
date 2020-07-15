package com.njupt.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.njupt.system.entity.SysUser;


public interface UserService extends IService<SysUser> {
    /**
     * 根据Name查询用户数据
     *
     * @param username
     * @return
     */
    SysUser findByName(String username);

    /**
     * 新增
     *
     * @param sysUser
     */
    void add(SysUser sysUser);

    /**
     * 更新
     *
     * @param sysUser
     */
    void update(SysUser sysUser);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);
}
