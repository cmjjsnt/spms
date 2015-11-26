package com.huihuan.jerrys.shiro.client;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import com.huihuan.jerrys.shiro.client.remote.RemoteServiceInterface;

import java.io.Serializable;

/**
 * 对会话的Session进行维护<br/>
 * <p>通过暴露rpcClient端的 RemoteService接口来实现,统一在此处进行维护本地是不维护session</p>
 * 
 * @author jerrys
 * @Date 2015-09-25
 * @version 1.0.1
 * @since
 */
public class ClientSessionDAO extends CachingSessionDAO {

	
	private RemoteServiceInterface remoteService;
	private String appKey;

	public void setRemoteService(RemoteServiceInterface remoteService) {
		this.remoteService = remoteService;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	@Override
	protected void doDelete(Session session) {
		
		remoteService.deleteSession(appKey, session);
	}

	@Override
	protected void doUpdate(Session session) {
		remoteService.updateSession(appKey, session);
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = remoteService.createSession(session);
		assignSessionId(session, sessionId);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		return remoteService.getSession(appKey, sessionId);
	}
}
