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
import ba.com.zira.stc.test_project.api.ActivityService;
import ba.com.zira.stc.test_project.api.model.Activity;
import ba.com.zira.stc.test_project.api.model.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "activity")
@RestController
@RequestMapping(value = "/activity")
@CrossOrigin
public class ActivityRest {

	@Autowired
    private ActivityService activityService;
	
	@ApiOperation(value = "Create Activity", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.POST)
    public PayloadResponse<Activity> create(@RequestBody final EntityRequest<Activity> request) throws ApiException {
        return activityService.create(request);
    }
	
	@ApiOperation(value = "Update Activity", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.PUT)
    public PayloadResponse<Activity> update(@RequestBody final EntityRequest<Activity> request) throws ApiException {
        return activityService.update(request);
    }
	
	@ApiOperation(value = "Delete Activity by Code", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.DELETE, value = "{code}")
    public void delete(@PathVariable final String code) throws ApiException {
        final EntityRequest<String> request = new EntityRequest<>();
        request.setEntity(code);

        activityService.delete(request);
    }

    @ApiOperation(value = "find Activity", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.GET)
    public PagedPayloadResponse<Activity> find(@RequestParam(required = false) final String filter,
            @RequestParam(required = false) final String sorting, @RequestParam(required = false) final String pagination)
            throws ApiException {
        final Request request = new Request();
        request.setFilterExpression(filter);
        request.setSorting(sorting);
        request.setPagination(pagination);
        return activityService.find(request);
    }

    @ApiOperation(value = "Get Activity by Code", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.GET, value = "{code}")
    public PayloadResponse<Activity> findByCode(@PathVariable final String code) throws ApiException {
        final EntityRequest<String> request = new EntityRequest<>();
        request.setEntity(code);

        return activityService.findByCode(request);
    }
}
