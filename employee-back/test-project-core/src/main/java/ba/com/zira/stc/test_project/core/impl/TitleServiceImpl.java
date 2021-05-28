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
import ba.com.zira.stc.test_project.api.TitleService;
import ba.com.zira.stc.test_project.api.model.Title;
import ba.com.zira.stc.test_project.core.validation.TitleRequestValidation;
import ba.com.zira.stc.test_project.dao.TitleDAO;
import ba.com.zira.stc.test_project.dao.model.TitleEntity;
import ba.com.zira.stc.test_project.mapper.TitleMapper;

@Service
public class TitleServiceImpl implements TitleService {
	 @Autowired
	    private RequestValidator requestValidator;
	 
	 @Autowired
	    private TitleRequestValidation titleRequestValidator;
	 
	 @Autowired
	    private TitleDAO titleDAO;
	 
	 @Autowired
	    private TitleMapper titleMapper;

	@Override
	public PayloadResponse<Title> create(EntityRequest<Title> request) throws ApiException {
		requestValidator.validate(request, "createTitle");
		final LocalDateTime date = LocalDateTime.now();

        final Title title = request.getEntity();
        final TitleEntity titleEntity = titleMapper.dtoToEntity(title);
        titleEntity.setStatus(Status.ACTIVE.getValue());
        titleEntity.setCreated(date);
        //titleEntity.setCreatedBy(request.getUser().getUserId());
        titleDAO.persist(titleEntity);

        titleMapper.updateDto(titleEntity, title);

        return new PayloadResponse<>(request, ResponseCode.OK, title);
	}

	@Override
	public PayloadResponse<Title> update(EntityRequest<Title> request) throws ApiException {
		 titleRequestValidator.validateUpdateRequest(request, "updateTitle");

	        final LocalDateTime date = LocalDateTime.now();

	        final Title title = request.getEntity();

	        final TitleEntity titleEntity = titleDAO.findByPK(title.getCode());
	        titleMapper.updateEntity(title, titleEntity);

	        titleEntity.setModified(date);
	        //titleEntity.setModifiedBy(request.getUser().getUserId());
	        titleDAO.merge(titleEntity);

	        titleMapper.updateDto(titleEntity, title);

	        return new PayloadResponse<>(request, ResponseCode.OK, title);
	}

	@Override
	public void delete(EntityRequest<String> request) throws ApiException {
		titleRequestValidator.validateExistsTitle(request, "validateId");
		titleDAO.removeByPK(request.getEntity());
		
	}

	@Override
	public PagedPayloadResponse<Title> find(Request request) throws ApiException {
		requestValidator.validate(request);

        final PagedData<TitleEntity> titleEntities = titleDAO.findAll(request.getFilter());

        final List<Title> title = titleMapper.entitiesToDtos(titleEntities.getRecords());

        return new PagedPayloadResponse<>(request, ResponseCode.OK, title.size(), 1, 1, title.size(), title);
	}

	@Override
	public PayloadResponse<Title> findById(EntityRequest<String> request) throws ApiException {
		 requestValidator.validate(request, "validateId");

	        final TitleEntity titleEntity = titleDAO.findByPK(request.getEntity());

	        final Title title = titleMapper.entityToDto(titleEntity);

	        return new PayloadResponse<>(request, ResponseCode.OK, title);
	}
}
