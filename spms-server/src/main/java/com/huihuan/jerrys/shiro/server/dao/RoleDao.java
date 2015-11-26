package com.huihuan.jerrys.shiro.server.dao;

import java.util.List;

import com.huihuan.jerrys.shiro.entity.Role;

/**
 * 角色管理
 * 
 * @author jerrys
 * @Date 2015-09-25
 * @version 1.0.1
 * @since
 */
public interface RoleDao {

	public Role createRole(Role role);

	public Role updateRole(Role role);

	public void deleteRole(Long roleId);

	public Role findOne(Long roleId);

	public List<Role> findAll();

	public List<Role> findRoleByIds(Long[] ids);
}
