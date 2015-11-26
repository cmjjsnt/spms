package com.huihuan.jerrys.shiro.server.service;

import java.util.List;

import com.huihuan.jerrys.shiro.entity.App;

/**
 * 
 * 不多讲自己看
 * 
 * @author jerrys
 * @Date 2015-09-25
 * @version 1.0.1
 * @since
 */
public interface AppService {


    public App createApp(App app);
    public App updateApp(App app);
    public void deleteApp(Long appId);

    public App findOne(Long appId);
    public List<App> findAll();

    /**
     * 根据appKey查找AppId
     * @param appKey
     * @return
     */
    public Long findAppIdByAppKey(String appKey);
    
    /**
     * 
     * @param appKey
     * @return
     */
	public App getAppByAppKey(String appKey);
}
