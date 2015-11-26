package com.huihuan.jerrys.shiro.server.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.huihuan.jerrys.shiro.entity.App;
import com.huihuan.jerrys.shiro.entity.Resource;
import com.huihuan.jerrys.shiro.server.dao.AppDao;
import com.huihuan.jerrys.shiro.server.dao.AuthorizationDao;
import com.huihuan.jerrys.shiro.server.dao.ResourceDao;

/**
 * 
 * 不多讲自己看
 * 
 * @author jerrys
 * @Date 2015-09-25
 * @version 1.0.1
 * @since
 */
@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private AppDao appDao;
	@Autowired
	private AuthorizationService authorizationService;

	@Override
	public Resource createResource(Resource resource) {
		return resourceDao.createResource(resource);
	}

	@Override
	public Resource updateResource(Resource resource) {
		return resourceDao.updateResource(resource);
	}

	@Override
	public void deleteResource(Long resourceId) {
		resourceDao.deleteResource(resourceId);
	}

	@Override
	public Resource findOne(Long resourceId) {
		return resourceDao.findOne(resourceId);
	}

	@Override
	public List<Resource> findAll() {
		return resourceDao.findAll();
	}

	@Override
	public Set<String> findPermissions(Set<Long> resourceIds) {
		Set<String> permissions = new HashSet<String>();
		for (Long resourceId : resourceIds) {
			Resource resource = findOne(resourceId);
			if (resource != null && !StringUtils.isEmpty(resource.getPermission())) {
				permissions.add(resource.getPermission());
			}
		}
		return permissions;
	}

	@Override
	public List<Resource> findMenus(Set<String> permissions) {

		List<Resource> allResources = findAll();
		List<Resource> menus = new ArrayList<Resource>();
		for (Resource resource : allResources) {
			if (resource.isRootNode()) {
				continue;
			}
			if ((resource.getType() != Resource.ResourceType.menu)
					&& (resource.getType() != Resource.ResourceType.module)) {
				continue;
			}
			if (!hasPermission(permissions, resource)) {
				continue;
			}
			menus.add(resource);
		}
		return menus;
	}

	private boolean hasPermission(Set<String> permissions, Resource resource) {
		if (StringUtils.isEmpty(resource.getPermission())) {
			return true;
		}
		for (String permission : permissions) {
			WildcardPermission p1 = new WildcardPermission(permission);
			WildcardPermission p2 = new WildcardPermission(resource.getPermission());
			if (p1.implies(p2) || p2.implies(p1)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Resource> findMenusByParentId(Long pid) {

		return null;
	}

	@Override
	public List<Resource> findMenusByApp(String appKey, Set<String> permissions) {
		App app = appDao.getAppByAppKey(appKey);

		if (app == null)
			return null;
		List<Resource> resourceList = resourceDao.findAllResourceByAppId(app.getId());
		if (resourceList == null)
			return null;
		System.out.println("----------------------------------------app  number  : " + resourceList.size()
				+ "--------------------------------------------");
		List<Resource> menus = new ArrayList<Resource>();
		for (Resource resource : resourceList) {
			if (resource.isRootNode()) {
				continue;
			}
			if ((resource.getType() != Resource.ResourceType.menu)
					&& (resource.getType() != Resource.ResourceType.module)) {
				continue;
			}
			if (!hasPermission(permissions, resource)) {
				continue;
			}
			menus.add(resource);
		}
		return menus;
	}
	@Override
	public List<Resource> findResourcesByAppKeyAndIds(String appKey, List<Long> ids) {
		App app = appDao.getAppByAppKey(appKey);
		return resourceDao.findResourcesByIdsAndAppId(app.getId(), ids);
	}

}
