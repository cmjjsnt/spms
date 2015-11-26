package com.huihuan.jerrys.shiro.server.service;

import java.util.List;
import java.util.Set;

import com.huihuan.jerrys.shiro.entity.Resource;

/**
 * 
 * 不多讲自己看
 * 
 * @author jerrys
 * @Date 2015-09-25
 * @version 1.0.1
 * @since
 */
public interface ResourceService {

	public Resource createResource(Resource resource);

	public Resource updateResource(Resource resource);

	public void deleteResource(Long resourceId);

	Resource findOne(Long resourceId);

	List<Resource> findAll();

	/**
	 * 得到资源对应的权限字符串
	 * 
	 * @param resourceIds
	 * @return
	 */
	Set<String> findPermissions(Set<Long> resourceIds);

	/**
	 * 根据用户权限得到菜单
	 * 
	 * @param permissions
	 * @return
	 */
	List<Resource> findMenus(Set<String> permissions);

	/**
	 * 根据Appkey和权限获取应用菜单
	 * 
	 * @param permissions
	 * @return
	 */
	public List<Resource> findMenusByApp(String appKey, Set<String> permissions);

	public List<Resource> findMenusByParentId(Long pid);

	public List<Resource> findResourcesByAppKeyAndIds(String appId, List<Long>  ids);

}
