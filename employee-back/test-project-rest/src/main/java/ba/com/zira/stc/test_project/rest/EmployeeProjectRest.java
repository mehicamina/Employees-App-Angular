package ba.com.zira.stc.test_project.rest;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
import ba.com.zira.stc.test_project.api.EmployeeProjectService;
import ba.com.zira.stc.test_project.api.model.CalendarResponse;
import ba.com.zira.stc.test_project.api.model.EmployeeProject;
import ba.com.zira.stc.test_project.api.model.EmployeeProjectTree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "employeeproject")
@RestController
@RequestMapping(value = "/employeeproject")
@CrossOrigin
public class EmployeeProjectRest {

	@Autowired
	private EmployeeProjectService employeeProjectService;

	@ApiOperation(value = "Create EmployeeProject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.POST)
	public PayloadResponse<EmployeeProject> create(@RequestBody final EntityRequest<EmployeeProject> request)
			throws ApiException {
		return employeeProjectService.create(request);
	}

	@ApiOperation(value = "Update EmployeeProject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.PUT)
	public PayloadResponse<EmployeeProject> update(@RequestBody final EntityRequest<EmployeeProject> request)
			throws ApiException {
		return employeeProjectService.update(request);
	}

	@ApiOperation(value = "Delete EmployeeProject by Id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void delete(@PathVariable final Long id) throws ApiException {
		final EntityRequest<Long> request = new EntityRequest<>();
		request.setEntity(id);

		employeeProjectService.delete(request);
	}

	@ApiOperation(value = "find EmployeeProject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET)
	public PagedPayloadResponse<EmployeeProject> find(@RequestParam(required = false) final String filter,
			@RequestParam(required = false) final String sorting,
			@RequestParam(required = false) final String pagination) throws ApiException {
		final Request request = new Request();
		request.setFilterExpression(filter);
		request.setSorting(sorting);
		request.setPagination(pagination);
		return employeeProjectService.find(request);
	}

	@ApiOperation(value = "Get EmployeeProject by Id.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public PayloadResponse<EmployeeProject> findById(@PathVariable final Long id) throws ApiException {
		final EntityRequest<Long> request = new EntityRequest<>();
		request.setEntity(id);

		return employeeProjectService.findById(request);
	}

	@ApiOperation(value = "Get work statuses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET, value = "/work_statuses")
	public PagedPayloadResponse<String> getWorkStatuses() throws ApiException {
		return employeeProjectService.getWorkStatuses();
	}

	@ApiOperation(value = "Datas for tree diagram by Activity Code", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET, value = "/tree")
	public PagedPayloadResponse<EmployeeProjectTree> searchByActivityCode(
			@RequestParam(value = "activityCode", required = true) final String activityCode) throws ApiException {
		EntityRequest<String> parametars = new EntityRequest<String>();
		parametars.setEntity(activityCode);
		return employeeProjectService.searchByActivityCode(parametars);

	}

	@ApiOperation(value = "Get avibility by activity code and date", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET, value = "/getAvailibility")
	public PagedPayloadResponse<CalendarResponse> searchByActivityCodeandDate(
			@RequestParam(value = "activityCode", required = true) final String activityCode,
			@RequestParam(value = "date", required = true) @DateTimeFormat(iso = ISO.DATE_TIME) final LocalDateTime date)
			throws ApiException {

		final EntityRequest<String> reqActCode = new EntityRequest<>();
		final EntityRequest<LocalDateTime> requestDate = new EntityRequest<>();
		reqActCode.setEntity(activityCode);
		requestDate.setEntity(date);
		return employeeProjectService.searchByActivityCodeandDate(reqActCode, requestDate);
	}
}
