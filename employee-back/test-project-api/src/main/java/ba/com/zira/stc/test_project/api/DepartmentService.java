package ba.com.zira.stc.test_project.api;

import ba.com.zira.commons.exception.ApiException;
import ba.com.zira.commons.message.request.EntityRequest;
import ba.com.zira.commons.message.request.Request;
import ba.com.zira.commons.message.response.PagedPayloadResponse;
import ba.com.zira.commons.message.response.PayloadResponse;
import ba.com.zira.commons.model.response.ResponseCode;
import ba.com.zira.stc.test_project.api.model.Department;

public interface DepartmentService {
	/**
	 * Create new {@link Department}. <br>
	 * Method validates the request before inserting into database.
	 * 
	 * @param request {@link EntityRequest} for {@link Department}
	 * @return {@link PayloadResponse} holding created {@link Department}.
	 * @throws ApiException If there was a problem during API invocation then.
	 *                      {@link ApiException} will be generated/returned with
	 *                      corresponding error message and {@link ResponseCode}.
	 */
	PayloadResponse<Department> create(final EntityRequest<Department> request) throws ApiException;

	/**
	 * Update existing {@link Department}. <br>
	 * Method validates if Employee exists and if the request is valid update
	 * database.
	 * 
	 * @param request {@link EntityRequest} for {@link Department}
	 * @return {@link PayloadResponse} holding created {@link Department}.
	 * @throws ApiException If there was a problem during API invocation then.
	 *                      {@link ApiException} will be generated/returned with
	 *                      corresponding error message and {@link ResponseCode}.
	 */
	PayloadResponse<Department> update(final EntityRequest<Department> request) throws ApiException;

	/**
	 * Delete {@link Department} from the database. <br>
	 * If {@link Department} with the given Id does not exist a validation exception
	 * will be thrown.
	 * 
	 * @param request {@link EntityRequest} for Employee Id.
	 * @throws ApiException If there was a problem during API invocation then
	 *                      {@link ApiException} will be generated/returned with
	 *                      corresponding error message and {@link ResponseCode}.
	 */
	void delete(EntityRequest<String> request) throws ApiException;

	/**
	 * 
	 * @param request {@link Request} containing pagination and sorting information.
	 * @return {@link PagedPayloadResponse} for {@link Department}.
	 * @throws ApiException If there was a problem during API invocation then.
	 *                      {@link ApiException} will be generated/returned with
	 *                      corresponding error message and {@link ResponseCode}.
	 */
	PagedPayloadResponse<Department> find(final Request request) throws ApiException;

	/**
	 * Retrieve {@link Department} by Id.
	 * 
	 * @param request {@link EntityRequest} containing the unique identifier.
	 * @return {@link PayloadResponse} for {@link Department}.
	 * @throws ApiException If there was a problem during API invocation then.
	 *                      {@link ApiException} will be generated/returned with
	 *                      corresponding error message and {@link ResponseCode}.
	 */
	PayloadResponse<Department> findByCode(EntityRequest<String> request) throws ApiException;
}
