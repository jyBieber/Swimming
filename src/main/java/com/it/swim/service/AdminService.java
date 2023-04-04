package com.it.swim.service;

import com.it.swim.dto.AdminExecution;
import com.it.swim.dto.ImageHolder;
import com.it.swim.entity.Admin;

public interface AdminService {
    /*
     * @description: 通过管理员ID获取指定管理员信息
     * @param adminId
     * @return Admin
     */
    Admin getAdminById(long adminId);

    /*
     * @description: 修改管理员信息
     * @param admin
     * @param imageHolder
     * @return  AdminExecution
     */
    AdminExecution modifyAdmin(Admin admin, ImageHolder imageHolder);
}
