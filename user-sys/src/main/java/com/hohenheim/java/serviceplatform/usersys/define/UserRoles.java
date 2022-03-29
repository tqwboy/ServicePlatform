package com.hohenheim.java.serviceplatform.usersys;

/**
 * @author Hohenheim
 * @date 2020/8/9
 * @description
 */
public enum UserRoles {
    /* 角色配置 */
    ROLE_ADMIN(1, "系统管理员"),
    ROLE_MANAGER(2, "管理员"),
    ROLE_USER(100, "普通用户");

    UserRoles(long roleId, String roleDesc) {
        this.roleId = roleId;
        this.roleDesc = roleDesc;
    }

    private long roleId;

    private String roleDesc;

    public long getRoleId() {
        return roleId;
    }

    public String getRoleDesc() {
        return roleDesc;
    }
}
