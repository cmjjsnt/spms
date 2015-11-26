package com.huihuan.jerrys.shiro.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huihuan.jerrys.shiro.entity.App;
import com.huihuan.jerrys.shiro.server.dao.AppDao;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service

public class AppServiceImpl implements AppService {

    @Autowired
    private AppDao appDao;

    public App createApp(App app) {
        return appDao.createApp(app);
    }

    public App updateApp(App app) {
        return appDao.updateApp(app);
    }

    public void deleteApp(Long appId) {
        appDao.deleteApp(appId);
    }

    @Override
    public App findOne(Long appId) {
        return appDao.findOne(appId);
    }

    @Override
    public List<App> findAll() {
        return appDao.findAll();
    }

    @Override
    public Long findAppIdByAppKey(String appKey) {
        return appDao.findAppIdByAppKey(appKey);
    }

	@Override
	public App getAppByAppKey(String appKey) {
	return 	appDao.getAppByAppKey(appKey);
	}
    
    
}
