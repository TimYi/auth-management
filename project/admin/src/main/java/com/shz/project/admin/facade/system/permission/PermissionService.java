package com.shz.project.admin.facade.system.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shz.foundation.persistence.springdata.PageableBuilder;
import com.shz.foundation.persistence.springdata.PagedList;
import com.shz.foundation.service.PagingService;
import com.shz.project.admin.facade.system.permission.field.FieldPermissionService;
import com.shz.project.admin.facade.system.permission.object.ObjectPermissionService;
import com.shz.project.admin.facade.system.permission.operation.OperatePermissionService;
import com.shz.project.domain.system.permission.Permission;
import com.shz.project.domain.system.permission.PermissionRepository;

@Service
@Transactional
public class PermissionService implements PagingService<PermissionVo, PermissionInputArgs, String> {

	@Autowired
	private FieldPermissionService fieldService;
	@Autowired
	private OperatePermissionService operateService;
	@Autowired
	private ObjectPermissionService objectService;
	@Autowired
	private PermissionConverter converter;
	@Autowired
	private PermissionRepository repository;
	
	@SuppressWarnings("rawtypes")
	protected PagingService getService(PermissionInputArgs args) {
		switch (args.getPermissionType()) {
		case FIELD:
			return fieldService;
		case OPERATE:
			return operateService;
		case OBJECT:
			return objectService;
		default:
			throw new IllegalArgumentException("权限类型不能为空");
		}
	}
	
	@Override
	public PermissionVo add(PermissionInputArgs args) {
		@SuppressWarnings("unchecked")
		PagingService<PermissionVo, PermissionInputArgs, String> service=getService(args);
		return service.add(args);
	}

	@Override
	public PermissionVo update(PermissionInputArgs args) {
		@SuppressWarnings("unchecked")
		PagingService<PermissionVo, PermissionInputArgs, String> service=getService(args);
		return service.update(args);
	}

	@Override
	public PermissionVo findOne(String id) {
		Permission permission=repository.findOne(id);
		return converter.convert(permission);
	}

	@Override
	public boolean exists(String id) {
		return repository.exists(id);
	}

	@Override
	public List<PermissionVo> findAll() {
		return converter.convertList(repository.findAll());
	}

	@Override
	public List<PermissionVo> findAll(Iterable<String> ids) {
		return converter.convertList(repository.findAll(ids));
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void delete(String id) {
		repository.delete(id);
	}

	@Override
	public void delete(Iterable<String> ids) {
		for (String id : ids) {
			delete(id);
		}
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public PagedList<PermissionVo> findPage(int page, int size) {
		Pageable pageable=PageableBuilder.build(page, size);
		Page<Permission> permissions=repository.findAll(pageable);
		Page<PermissionVo> result=permissions.map(converter);
		return new PagedList<>(result);
	}

}
