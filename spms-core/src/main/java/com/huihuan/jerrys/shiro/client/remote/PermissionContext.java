package com.huihuan.jerrys.shiro.client.remote;

import java.io.Serializable;
import java.util.Set;

/**
 * 	角色和权限的上下文管理
 * @author jerrys
 * @Date 2015-09-24
 * @version 1.0.1
 * @since
 */
public class PermissionContext implements Serializable {
    private Set<String> roles;
    private Set<String> permissions;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }


    @Override
    public String toString() {
        return "PermissionContext{" +
                ", roles=" + roles +
                ", permissions=" + permissions +
                '}';
    }
}
