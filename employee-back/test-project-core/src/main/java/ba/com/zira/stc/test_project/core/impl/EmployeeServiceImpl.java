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
import ba.com.zira.stc.test_project.api.EmployeeService;
import ba.com.zira.stc.test_project.api.model.Employee;
import ba.com.zira.stc.test_project.core.validation.EmployeeRequestValidation;
import ba.com.zira.stc.test_project.dao.EmployeeDAO;
import ba.com.zira.stc.test_project.dao.model.EmployeeEntity;
import ba.com.zira.stc.test_project.mapper.EmployeeMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private RequestValidator requestValidator;

    @Autowired
    private EmployeeRequestValidation employeeRequestValidator;

    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public PayloadResponse<Employee> create(final EntityRequest<Employee> request) throws ApiException {
        requestValidator.validate(request, "createEmployee");

        final LocalDateTime date = LocalDateTime.now();

        final Employee employee = request.getEntity();

        final EmployeeEntity employeeEntity = employeeMapper.dtoToEntity(employee);

        employeeEntity.setStatus(Status.ACTIVE.getValue());
        employeeEntity.setCreated(date);
        //employeeEntity.setCreatedBy(request.getUser().getUserId());
        employeeDAO.persist(employeeEntity);

        employeeMapper.updateDto(employeeEntity, employee);

        return new PayloadResponse<>(request, ResponseCode.OK, employee);
    }

    @Override
    public PayloadResponse<Employee> update(final EntityRequest<Employee> request) throws ApiException {
        employeeRequestValidator.validateUpdateRequest(request, "updateEmployee");

        final LocalDateTime date = LocalDateTime.now();

        final Employee employee = request.getEntity();

        final EmployeeEntity employeeEntity = employeeDAO.findByPK(employee.getId());
        employeeMapper.updateEntity(employee, employeeEntity);

        employeeEntity.setModified(date);
        //employeeEntity.setModifiedBy(request.getUser().getUserId());
        employeeDAO.merge(employeeEntity);

        employeeMapper.updateDto(employeeEntity, employee);

        return new PayloadResponse<>(request, ResponseCode.OK, employee);
    }

    @Override
    public void delete(final EntityRequest<Long> request) throws ApiException {
        employeeRequestValidator.validateExistsEmployee(request, "validateId");

        employeeDAO.removeByPK(request.getEntity());
    }

    @Override
    public PagedPayloadResponse<Employee> find(final Request request) throws ApiException {
        requestValidator.validate(request);

        final PagedData<EmployeeEntity> employeeEntities = employeeDAO.findAll(request.getFilter());

        final List<Employee> employees = employeeMapper.entitiesToDtos(employeeEntities.getRecords());

        return new PagedPayloadResponse<>(request, ResponseCode.OK, employees.size(), 1, 1, employees.size(), employees);
    }

    @Override
    public PayloadResponse<Employee> findById(final EntityRequest<Long> request) throws ApiException {
        requestValidator.validate(request, "validateId");

        final EmployeeEntity employeeEntity = employeeDAO.findByPK(request.getEntity());

        final Employee employee = employeeMapper.entityToDto(employeeEntity);

        return new PayloadResponse<>(request, ResponseCode.OK, employee);
    }
    
    @Override
    public PagedPayloadResponse<Employee> search(final EntityRequest<String> requestName, final EntityRequest<String> requestSurname,final EntityRequest<String> requestNumber,final EntityRequest<LocalDateTime> requestHireDate,final EntityRequest<String> requestJobTitle)
    		throws ApiException {
        final List<EmployeeEntity> employeeEntities = employeeDAO.search(requestName.getEntity(),requestSurname.getEntity(),requestNumber.getEntity(),requestHireDate.getEntity(),requestJobTitle.getEntity());

        final List<Employee> employees = employeeMapper.entitiesToDtos(employeeEntities);

        return new PagedPayloadResponse<>(requestName, ResponseCode.OK, employees.size(), 1, 1, employees.size(), employees);
    }

}
