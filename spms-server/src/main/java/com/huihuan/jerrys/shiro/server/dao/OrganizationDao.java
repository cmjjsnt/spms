package com.huihuan.jerrys.shiro.server.dao;

import java.util.List;

import com.huihuan.jerrys.shiro.entity.Organization;

/**
 * 
 * 组织管理
 * 
 * @author jerrys
 * @Date 2015-09-25
 * @version 1.0.1
 * @since
 */
public interface OrganizationDao {

	public Organization createOrganization(Organization organization);

	public Organization updateOrganization(Organization organization);

	public void deleteOrganization(Long organizationId);

	Organization findOne(Long organizationId);

	List<Organization> findAll();

	List<Organization> findAllWithExclude(Organization excludeOraganization);

	void move(Organization source, Organization target);
}
