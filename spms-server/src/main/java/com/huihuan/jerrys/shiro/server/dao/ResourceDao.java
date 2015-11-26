package com.huihuan.jerrys.shiro.server.dao;

import java.util.List;

import com.huihuan.jerrys.shiro.entity.Resource;

/**
 * 
 * 资源管理
 * 
 * @author jerrys
 * @Date 2015-09-25
 * @version 1.0.1
 * @since
 */
public interface ResourceDao {

	public Resource createResource(Resource resource);

	public Resource updateResource(Resource resource);

	public void deleteResource(Long resourceId);

	Resource findOne(Long resourceId);

	List<Resource> findAll();

	/**
	 * findAllResourceByAppKey
	 * 
	 * @param appKey
	 * @return
	 */
	public List<Resource> findAllResourceByAppId(Long appId);

	/**
	 * 获取属于app应用的指定资源列表
	 * 
	 * @param appId
	 * @param ids
	 * @return
	 */
	public List<Resource> findResourcesByIdsAndAppId(Long appId, List<Long> ids);
}
