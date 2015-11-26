package com.huihuan.jerrys.shiro.entity;

import java.io.Serializable;

/**
 * 
 * 不多讲自己看
 * 
 * @author jerrys
 * @Date 2015-09-25
 * @version 1.0.1
 * @since
 */
public class Resource implements Serializable {
	private Long id; // 编号
	private String name; // 资源名称
	private ResourceType type = ResourceType.menu; // 资源类型
	private String url; // 资源路径
	private String permission; // 权限字符串
	private Long parentId; // 父编号
	private String parentIds; // 父编号列表
	private Boolean available = Boolean.FALSE;
	private Long scope;// 菜单所属应用

	public static enum ResourceType {
		app("应用"), module("模块"), menu("菜单"), button("按钮");

		private final String info;

		private ResourceType(String info) {
			this.info = info;
		}

		public String getInfo() {
			return info;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ResourceType getType() {
		return type;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Long getScope() {
		return scope;
	}

	public void setScope(Long scope) {
		this.scope = scope;
	}

	public boolean isRootNode() {
		return parentId == 0;
	}

	public String makeSelfAsParentIds() {
		return getParentIds() + getId() + "/";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Resource resource = (Resource) o;

		if (id != null ? !id.equals(resource.id) : resource.id != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}


}
