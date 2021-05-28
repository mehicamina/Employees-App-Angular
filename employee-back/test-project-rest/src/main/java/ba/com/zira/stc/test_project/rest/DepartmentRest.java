package ba.com.zira.stc.test_project.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ba.com.zira.commons.exception.ApiException;
import ba.com.zira.commons.message.request.EntityRequest;
import ba.com.zira.commons.message.request.Request;
import ba.com.zira.commons.message.response.PagedPayloadResponse;
import ba.com.zira.commons.message.response.PayloadResponse;
import ba.com.zira.stc.test_project.api.DepartmentService;
import ba.com.zira.stc.test_project.api.model.Department;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "department")
@RestController
@RequestMapping(value = "/department")
@CrossOrigin
public class DepartmentRest {

	@Autowired
	private DepartmentService departmentService;

	@ApiOperation(value = "Create Department", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.POST)
	public PayloadResponse<Department> create(@RequestBody final EntityRequest<Department> request)
			throws ApiException {
		return departmentService.create(request);
	}

	@ApiOperation(value = "Update Department", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.PUT)
	public PayloadResponse<Department> update(@RequestBody final EntityRequest<Department> request)
			throws ApiException {
		return departmentService.update(request);
	}

	@ApiOperation(value = "Delete Department by Code", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.DELETE, value = "{code}")
	public void delete(@PathVariable final String code) throws ApiException {
		final EntityRequest<String> request = new EntityRequest<>();
		request.setEntity(code);

		departmentService.delete(request);
	}

	@ApiOperation(value = "find Department", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET)
	public PagedPayloadResponse<Department> find(@RequestParam(required = false) final String filter,
			@RequestParam(required = false) final String sorting,
			@RequestParam(required = false) final String pagination) throws ApiException {
		final Request request = new Request();
		request.setFilterExpression(filter);
		request.setSorting(sorting);
		request.setPagination(pagination);
		return departmentService.find(request);
	}

	@ApiOperation(value = "Get Department by Code", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET, value = "{code}")
	public PayloadResponse<Department> findByCode(@PathVariable final String code) throws ApiException {
		final EntityRequest<String> request = new EntityRequest<>();
		request.setEntity(code);

		return departmentService.findByCode(request);
	}
}
