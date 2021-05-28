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
import ba.com.zira.stc.test_project.api.TitleService;
import ba.com.zira.stc.test_project.api.model.Title;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "title")
@RestController
@RequestMapping(value = "/title")
@CrossOrigin
public class TitleRest {
	@Autowired
    private TitleService titleService;
	
	@ApiOperation(value = "Create Title", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.POST)
    public PayloadResponse<Title> create(@RequestBody final EntityRequest<Title> request) throws ApiException {
        return titleService.create(request);
    }
	 @ApiOperation(value = "Update Title", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(method = RequestMethod.PUT)
	    public PayloadResponse<Title> update(@RequestBody final EntityRequest<Title> request) throws ApiException {
	        return titleService.update(request);
	    }
	 
	 @ApiOperation(value = "Delete Title by Code", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(method = RequestMethod.DELETE, value = "{code}")
	    public void delete(@PathVariable final String code) throws ApiException {
	        final EntityRequest<String> request = new EntityRequest<>();
	        request.setEntity(code);

	        titleService.delete(request);
	    }
	 
	 @ApiOperation(value = "find Title", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(method = RequestMethod.GET)
	    public PagedPayloadResponse<Title> find(@RequestParam(required = false) final String filter,
	            @RequestParam(required = false) final String sorting, @RequestParam(required = false) final String pagination)
	            throws ApiException {
	        final Request request = new Request();
	        request.setFilterExpression(filter);
	        request.setSorting(sorting);
	        request.setPagination(pagination);
	        return titleService.find(request);
	    }
	 
	 @ApiOperation(value = "Get Title by Code.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    @RequestMapping(method = RequestMethod.GET, value = "{code}")
	    public PayloadResponse<Title> findByCode(@PathVariable final String code) throws ApiException {
	        final EntityRequest<String> request = new EntityRequest<>();
	        request.setEntity(code);

	        return titleService.findById(request);
	    }
}
