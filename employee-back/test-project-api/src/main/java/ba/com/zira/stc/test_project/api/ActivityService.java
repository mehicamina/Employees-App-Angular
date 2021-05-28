package ba.com.zira.stc.test_project.api;

import ba.com.zira.commons.exception.ApiException;
import ba.com.zira.commons.message.request.EntityRequest;
import ba.com.zira.commons.message.request.Request;
import ba.com.zira.commons.message.response.PagedPayloadResponse;
import ba.com.zira.commons.message.response.PayloadResponse;
import ba.com.zira.commons.model.response.ResponseCode;
import ba.com.zira.stc.test_project.api.model.Activity;
import ba.com.zira.stc.test_project.api.model.Employee;

public interface ActivityService {
	/**
     * Create new {@link Activity}. <br>
     * Method validates the request before inserting into database.
     * 
     * @param request
     *            {@link EntityRequest} for {@link Activity}
     * @return {@link PayloadResponse} holding created {@link Activity}.
     * @throws ApiException
     *             If there was a problem during API invocation then.
     *             {@link ApiException} will be generated/returned with
     *             corresponding error message and {@link ResponseCode}.
     */
    PayloadResponse<Activity> create(final EntityRequest<Activity> request) throws ApiException;

    /**
     * Update existing {@link Activity}. <br>
     * Method validates if Employee exists and if the request is valid update
     * database.
     * 
     * @param request
     *            {@link EntityRequest} for {@link Activity}
     * @return {@link PayloadResponse} holding created {@link Activity}.
     * @throws ApiException
     *             If there was a problem during API invocation then.
     *             {@link ApiException} will be generated/returned with
     *             corresponding error message and {@link ResponseCode}.
     */
    PayloadResponse<Activity> update(final EntityRequest<Activity> request) throws ApiException;

    /**
     * Delete {@link Activity} from the database. <br>
     * If {@link Activity} with the given Id does not exist a validation
     * exception will be thrown.
     * 
     * @param request
     *            {@link EntityRequest} for Employee Id.
     * @throws ApiException
     *             If there was a problem during API invocation then
     *             {@link ApiException} will be generated/returned with
     *             corresponding error message and {@link ResponseCode}.
     */
    void delete(EntityRequest<String> request) throws ApiException;

    /**
     * Retrieve All {@link Activity}s from the database.
     * 
     * @param request
     *            {@link Request} containing pagination and sorting information.
     * @return {@link PagedPayloadResponse} for {@link Activity}.
     * @throws ApiException
     *             If there was a problem during API invocation then.
     *             {@link ApiException} will be generated/returned with
     *             corresponding error message and {@link ResponseCode}.
     */
    PagedPayloadResponse<Activity> find(final Request request) throws ApiException;

    /**
     * Retrieve {@link Activity} by Id.
     * 
     * @param request
     *            {@link EntityRequest} containing the unique identifier.
     * @return {@link PayloadResponse} for {@link Activity}.
     * @throws ApiException
     *             If there was a problem during API invocation then.
     *             {@link ApiException} will be generated/returned with
     *             corresponding error message and {@link ResponseCode}.
     */
    PayloadResponse<Activity> findByCode(EntityRequest<String> request) throws ApiException;
}
