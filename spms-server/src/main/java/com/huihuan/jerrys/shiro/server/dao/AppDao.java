package com.huihuan.jerrys.shiro.server.dao;

import java.util.List;

import com.huihuan.jerrys.shiro.entity.App;

/**
 * 
 * 应用管理
 * 
 * @author jerrys
 * @Date 2015-09-25
 * @version 1.0.1
 * @since
 */
public interface AppDao {

	public App createApp(App app);

	public App updateApp(App app);

	public void deleteApp(Long appId);

	public App findOne(Long appId);

	public List<App> findAll();

	Long findAppIdByAppKey(String appKey);
	
	public App getAppByAppKey(String appkey);
}
