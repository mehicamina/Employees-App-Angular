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
import ba.com.zira.stc.test_project.api.EmployeeService;
import ba.com.zira.stc.test_project.api.model.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "employee")
@RestController
@RequestMapping(value = "/employee")
@CrossOrigin
public class EmployeeRest {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "Create Employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.POST)
    public PayloadResponse<Employee> create(@RequestBody final EntityRequest<Employee> request) throws ApiException {
        return employeeService.create(request);
    }

    @ApiOperation(value = "Update Employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.PUT)
    public PayloadResponse<Employee> update(@RequestBody final EntityRequest<Employee> request) throws ApiException {
        return employeeService.update(request);
    }

    @ApiOperation(value = "Delete Employee by Id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable final Long id) throws ApiException {
        final EntityRequest<Long> request = new EntityRequest<>();
        request.setEntity(id);

        employeeService.delete(request);
    }

    @ApiOperation(value = "find Employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.GET)
    public PagedPayloadResponse<Employee> find(@RequestParam(required = false) final String filter,
            @RequestParam(required = false) final String sorting, @RequestParam(required = false) final String pagination)
            throws ApiException {
        final Request request = new Request();
        request.setFilterExpression(filter);
        request.setSorting(sorting);
        request.setPagination(pagination);
        return employeeService.find(request);
    }

    @ApiOperation(value = "Get Employee by Id.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public PayloadResponse<Employee> findById(@PathVariable final Long id) throws ApiException {
        final EntityRequest<Long> request = new EntityRequest<>();
        request.setEntity(id);

        return employeeService.findById(request);
    }
    
    @ApiOperation(value = "search Employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public PagedPayloadResponse<Employee> search(@RequestParam(value = "first_name",required = false) final String first_name,@RequestParam(value = "last_name",required = false) final String last_name
    		,@RequestParam(value = "phone_number",required = false) final String phone_number
    		,@RequestParam(value = "hire_date",required = false)  @DateTimeFormat(iso = ISO.DATE_TIME) final LocalDateTime hire_date
    		,@RequestParam(value = "job_title_code",required = false) final String job_title_code)
            throws ApiException {
    	
    	System.out.println("!!!!!!!!!!!!!!!!"+hire_date);
    	 final EntityRequest<String> requestName = new EntityRequest<>();
    	 final EntityRequest<String> requestSurname = new EntityRequest<>();
    	 final EntityRequest<String> requestNumber = new EntityRequest<>();
    	 final EntityRequest<LocalDateTime> requestHireDate = new EntityRequest<>();
    	 final EntityRequest<String> requestJobTitle = new EntityRequest<>();
    	 requestName.setEntity(first_name);
    	 requestSurname.setEntity(last_name);
    	 requestNumber.setEntity(phone_number);
    	 requestHireDate.setEntity(hire_date);
    	 requestJobTitle.setEntity(job_title_code);
        return employeeService.search(requestName,requestSurname,requestNumber,requestHireDate,requestJobTitle);
    }
    
}
