package ba.com.zira.stc.test_project.core.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ba.com.zira.commons.exception.ApiException;
import ba.com.zira.commons.message.request.EntityRequest;
import ba.com.zira.commons.message.request.Request;
import ba.com.zira.commons.message.response.PagedPayloadResponse;
import ba.com.zira.commons.message.response.PayloadResponse;
import ba.com.zira.commons.model.PagedData;
import ba.com.zira.commons.model.enums.Status;
import ba.com.zira.commons.model.response.ResponseCode;
import ba.com.zira.commons.validation.RequestValidator;
import ba.com.zira.stc.test_project.api.DepartmentService;
import ba.com.zira.stc.test_project.api.model.Department;
import ba.com.zira.stc.test_project.core.validation.DepartmentRequestValidation;
import ba.com.zira.stc.test_project.dao.DepartmentDAO;
import ba.com.zira.stc.test_project.dao.model.DepartmentEntity;
import ba.com.zira.stc.test_project.mapper.DepartmentMapper;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private RequestValidator requestValidator;

	@Autowired
	private DepartmentRequestValidation departmentRequestValidator;

	@Autowired
	private DepartmentDAO departmentDAO;

	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public PayloadResponse<Department> create(final EntityRequest<Department> request) throws ApiException {
		requestValidator.validate(request, "createDepartment");

		final LocalDateTime date = LocalDateTime.now();

		final Department department = request.getEntity();

		final DepartmentEntity departmentEntity = departmentMapper.dtoToEntity(department);

		departmentEntity.setStatus(Status.ACTIVE.getValue());
		departmentEntity.setCreated(date);
		//departmentEntity.setCreatedBy(request.getUser().getUserId());
		departmentDAO.persist(departmentEntity);

		departmentMapper.updateDto(departmentEntity, department);

		return new PayloadResponse<>(request, ResponseCode.OK, department);
	}

	@Override
	public PayloadResponse<Department> update(final EntityRequest<Department> request) throws ApiException {
		departmentRequestValidator.validateUpdateRequest(request, "updateDepartment");

		final LocalDateTime date = LocalDateTime.now();

		final Department department = request.getEntity();

		final DepartmentEntity departmentEntity = departmentDAO.findByPK(department.getCode());
		departmentMapper.updateEntity(department, departmentEntity);

		departmentEntity.setModified(date);
		//departmentEntity.setModifiedBy(request.getUser().getUserId());
		departmentDAO.merge(departmentEntity);

		departmentMapper.updateDto(departmentEntity, department);

		return new PayloadResponse<>(request, ResponseCode.OK, department);
	}

	@Override
	public void delete(final EntityRequest<String> request) throws ApiException {
		departmentRequestValidator.validateExistsDepartment(request, "validateCode");

		departmentDAO.removeByPK(request.getEntity());
	}

	@Override
	public PagedPayloadResponse<Department> find(final Request request) throws ApiException {
		requestValidator.validate(request);

		final PagedData<DepartmentEntity> departmentEntity = departmentDAO.findAll(request.getFilter());

		final List<Department> activities = departmentMapper.entitiesToDtos(departmentEntity.getRecords());

		return new PagedPayloadResponse<>(request, ResponseCode.OK, activities.size(), 1, 1, activities.size(),
				activities);
	}

	@Override
	public PayloadResponse<Department> findByCode(final EntityRequest<String> request) throws ApiException {
		requestValidator.validate(request, "validateCode");

		final DepartmentEntity departmentEntity = departmentDAO.findByPK(request.getEntity());

		final Department department = departmentMapper.entityToDto(departmentEntity);

		return new PayloadResponse<>(request, ResponseCode.OK, department);
	}
}
