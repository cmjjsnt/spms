package com.huihuan.jerrys.shiro.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.huihuan.jerrys.shiro.entity.Authorization;
import com.mysql.jdbc.Statement;

/**
 * 
 * 不多讲自己看
 * 
 * @author jerrys
 * @Date 2015-09-25
 * @version 1.0.1
 * @since
 */
@Repository
public class AuthorizationDaoImpl implements AuthorizationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Authorization createAuthorization(final Authorization authorization) {

		final String sql = "insert into sys_user_app_roles(user_id, app_id, role_id) values(?,?,?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				int count = 1;
				psst.setLong(count++, authorization.getUserId());
				psst.setLong(count++, authorization.getAppId());
				psst.setLong(count++, authorization.getRoleId());
				return psst;
			}
		}, keyHolder);
		authorization.setId(keyHolder.getKey().longValue());
		return authorization;
	}

	@Override
	public Authorization updateAuthorization(Authorization authorization) {
		final String sql = "update sys_user_app_roles set user_id=?, app_id=?, role_id=? where id=?";
		jdbcTemplate.update(sql, authorization.getUserId(), authorization.getAppId(), authorization.getRoleId(),
				authorization.getId());
		return authorization;
	}

	public void deleteAuthorization(Long authorizationId) {
		final String sql = "delete from sys_user_app_roles where id=?";
		jdbcTemplate.update(sql, authorizationId);
	}

	@Override
	public Authorization findOne(Long authorizationId) {
		final String sql = "select id, user_id, app_id, role_id as roleId from sys_user_app_roles where id=?";
		List<Authorization> authorizationList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<Authorization>(Authorization.class), authorizationId);
		if (authorizationList.size() == 0) {
			return null;
		}
		return authorizationList.get(0);
	}

	@Override
	public List<Authorization> findAll() {
		final String sql = "select id, user_id, app_id, role_id as roleId from sys_user_app_roles";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Authorization.class));
	}

	@Override
	public Authorization findByAppUser(Long appId, Long userId) {
		final String sql = "select id, user_id, app_id, role_id as roleId from sys_user_app_roles where app_id=? and user_id=?";
		List<Authorization> authorizationList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<Authorization>(Authorization.class), appId, userId);
		System.out.println(authorizationList.size());
		if (authorizationList.size() == 0) {
			return null;
		}
		return authorizationList.get(0);
	}
}
