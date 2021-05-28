package ba.com.zira.stc.test_project.api;

import ba.com.zira.commons.exception.ApiException;
import ba.com.zira.commons.message.request.EntityRequest;
import ba.com.zira.commons.message.request.Request;
import ba.com.zira.commons.message.response.PagedPayloadResponse;
import ba.com.zira.commons.message.response.PayloadResponse;
import ba.com.zira.stc.test_project.api.model.Title;

public interface TitleService {
	PayloadResponse<Title> create(final EntityRequest<Title> request) throws ApiException;
	PayloadResponse<Title> update(final EntityRequest<Title> request) throws ApiException;
	void delete(EntityRequest<String> request) throws ApiException;
	PagedPayloadResponse<Title> find(final Request request) throws ApiException;
	PayloadResponse<Title> findById(EntityRequest<String> request) throws ApiException;
}
