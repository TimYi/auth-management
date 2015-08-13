package com.shz.project.admin.facade.system.department;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shz.foundation.mapping.service.DtoPagingService;
import com.shz.project.domain.system.department.Department;

@Service
@Transactional
public class DepartmentService extends DtoPagingService<Department, DepartmentDto, DepartmentInputArgs, String> {

}
