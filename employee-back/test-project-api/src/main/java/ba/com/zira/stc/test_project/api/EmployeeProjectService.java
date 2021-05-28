package ba.com.zira.stc.test_project.api;

import java.time.LocalDateTime;

import ba.com.zira.commons.exception.ApiException;
import ba.com.zira.commons.message.request.EntityRequest;
import ba.com.zira.commons.message.request.Request;
import ba.com.zira.commons.message.response.PagedPayloadResponse;
import ba.com.zira.commons.message.response.PayloadResponse;
import ba.com.zira.commons.model.response.ResponseCode;
import ba.com.zira.stc.test_project.api.model.CalendarResponse;
import ba.com.zira.stc.test_project.api.model.EmployeeProject;
import ba.com.zira.stc.test_project.api.model.EmployeeProjectTree;

/**
 * Methods used to manipulate EmployeeProject data. <br>
 * List of APIs implemented in this class with links:
 * <ul>
 * <li>{@link #create}</li>
 * <li>{@link #update}</li>
 * <li>{@link #delete}</li>
 * <li>{@link #find}</li>
 * <li>{@link #findById}</li>
 * </ul>
 * 
 * @author zira
 *
 */
public interface EmployeeProjectService {

	/**
	 * Create new {@link EmployeeProject}. <br>
	 * Method validates the request before inserting into database.
	 * 
	 * @param request {@link EntityRequest} for {@link EmployeeProject}
	 * @return {@link PayloadResponse} holding created {@link EmployeeProject}.
	 * @throws ApiException If there was a problem during API invocation then.
	 *                      {@link ApiException} will be generated/returned with
	 *                      corresponding error message and {@link ResponseCode}.
	 */
	PayloadResponse<EmployeeProject> create(final EntityRequest<EmployeeProject> request) throws ApiException;

	/**
	 * Update existing {@link EmployeeProject}. <br>
	 * Method validates if EmployeeProject exists and if the request is valid update
	 * database.
	 * 
	 * @param request {@link EntityRequest} for {@link EmployeeProject}
	 * @return {@link PayloadResponse} holding created {@link EmployeeProject}.
	 * @throws ApiException If there was a problem during API invocation then.
	 *                      {@link ApiException} will be generated/returned with
	 *                      corresponding error message and {@link ResponseCode}.
	 */
	PayloadResponse<EmployeeProject> update(final EntityRequest<EmployeeProject> request) throws ApiException;

	/**
	 * Delete {@link EmployeeProject} from the database. <br>
	 * If {@link EmployeeProject} with the given Id does not exist a validation
	 * exception will be thrown.
	 * 
	 * @param request {@link EntityRequest} for EmployeeProject Id.
	 * @throws ApiException If there was a problem during API invocation then
	 *                      {@link ApiException} will be generated/returned with
	 *                      corresponding error message and {@link ResponseCode}.
	 */
	void delete(EntityRequest<Long> request) throws ApiException;

	/**
	 * Retrieve All {@link EmployeeProject}s from the database.
	 * 
	 * @param request {@link Request} containing pagination and sorting information.
	 * @return {@link PagedPayloadResponse} for {@link EmployeeProject}.
	 * @throws ApiException If there was a problem during API invocation then.
	 *                      {@link ApiException} will be generated/returned with
	 *                      corresponding error message and {@link ResponseCode}.
	 */
	PagedPayloadResponse<EmployeeProject> find(final Request request) throws ApiException;

	/**
	 * Retrieve {@link EmployeeProject} by Id.
	 * 
	 * @param request {@link EntityRequest} containing the unique identifier.
	 * @return {@link PayloadResponse} for {@link EmployeeProject}.
	 * @throws ApiException If there was a problem during API invocation then.
	 *                      {@link ApiException} will be generated/returned with
	 *                      corresponding error message and {@link ResponseCode}.
	 */
	PayloadResponse<EmployeeProject> findById(EntityRequest<Long> request) throws ApiException;

	PagedPayloadResponse<String> getWorkStatuses() throws ApiException;

	PagedPayloadResponse<EmployeeProjectTree> searchByActivityCode(EntityRequest<String> request) throws ApiException;

	PagedPayloadResponse<CalendarResponse> searchByActivityCodeandDate(final EntityRequest<String> activitCode,
			final EntityRequest<LocalDateTime> date) throws ApiException;
}
