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
import ba.com.zira.stc.test_project.api.ActivityService;
import ba.com.zira.stc.test_project.api.model.Activity;
import ba.com.zira.stc.test_project.api.model.Employee;
import ba.com.zira.stc.test_project.core.validation.ActivityRequestValidation;
import ba.com.zira.stc.test_project.dao.ActivityDAO;
import ba.com.zira.stc.test_project.dao.model.ActivityEntity;
import ba.com.zira.stc.test_project.mapper.ActivityMapper;

@Service
public class ActivityServiceImpl implements ActivityService{
	
	@Autowired
    private RequestValidator requestValidator;
	
	@Autowired
    private ActivityRequestValidation activityRequestValidator;

    @Autowired
    private ActivityDAO activityDAO;
    
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public PayloadResponse<Activity> create(final EntityRequest<Activity> request) throws ApiException {
        requestValidator.validate(request, "createActivity");

        final LocalDateTime date = LocalDateTime.now();

        final Activity activity = request.getEntity();

        final ActivityEntity activityEntity = activityMapper.dtoToEntity(activity);

        activityEntity.setStatus(Status.ACTIVE.getValue());
        activityEntity.setCreated(date);
       // activityEntity.setCreatedBy(request.getUser().getUserId());
        activityDAO.persist(activityEntity);

        activityMapper.updateDto(activityEntity, activity);

        return new PayloadResponse<>(request, ResponseCode.OK, activity);
    }

    @Override
    public PayloadResponse<Activity> update(final EntityRequest<Activity> request) throws ApiException {
    	activityRequestValidator.validateUpdateRequest(request, "updateEmployee");

        final LocalDateTime date = LocalDateTime.now();

        final Activity activity = request.getEntity();

        final ActivityEntity activityEntity = activityDAO.findByPK(activity.getCode());
        activityMapper.updateEntity(activity, activityEntity);

        activityEntity.setModified(date);
       // activityEntity.setModifiedBy(request.getUser().getUserId());
        activityDAO.merge(activityEntity);

        activityMapper.updateDto(activityEntity, activity);

        return new PayloadResponse<>(request, ResponseCode.OK, activity);
    }

    @Override
    public void delete(final EntityRequest<String> request) throws ApiException {
    	activityRequestValidator.validateExistsActivity(request, "validateCode");

    	activityDAO.removeByPK(request.getEntity());
    }

    @Override
    public PagedPayloadResponse<Activity> find(final Request request) throws ApiException {
        requestValidator.validate(request);

        final PagedData<ActivityEntity> activityEntities = activityDAO.findAll(request.getFilter());

        final List<Activity> activities = activityMapper.entitiesToDtos(activityEntities.getRecords());

        return new PagedPayloadResponse<>(request, ResponseCode.OK, activities.size(), 1, 1, activities.size(), activities);
    }

    @Override
    public PayloadResponse<Activity> findByCode(final EntityRequest<String> request) throws ApiException {
        requestValidator.validate(request, "validateCode");

        final ActivityEntity activityEntity = activityDAO.findByPK(request.getEntity());

        final Activity activity = activityMapper.entityToDto(activityEntity);

        return new PayloadResponse<>(request, ResponseCode.OK, activity);
    }
}
