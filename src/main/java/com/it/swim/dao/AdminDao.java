package com.it.swim.dao;

import com.it.swim.entity.Admin;

/*
 * @description: 管理员实体类dao层接口
 */
public interface AdminDao {
    /*
     * @description: 通过adminId查询指定管理员信息
     * @param adminId
     * @return Admin
     */
    Admin queryAdminById(long adminId);

    /*
     * @description: 修改管理员信息
     * @param admin
     * @return int
     */
    int modifyAdmin(Admin admin);
}
