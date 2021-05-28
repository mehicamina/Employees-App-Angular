package ba.com.zira.stc.test_project.api;

import java.time.LocalDateTime;

import ba.com.zira.commons.exception.ApiException;
import ba.com.zira.commons.message.request.EntityRequest;
import ba.com.zira.commons.message.request.Request;
import ba.com.zira.commons.message.response.PagedPayloadResponse;
import ba.com.zira.commons.message.response.PayloadResponse;
import ba.com.zira.commons.model.response.ResponseCode;
import ba.com.zira.stc.test_project.api.model.Employee;

/**
 * Methods used to manipulate Employee data. <br>
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
public interface EmployeeService {

    /**
     * Create new {@link Employee}. <br>
     * Method validates the request before inserting into database.
     * 
     * @param request
     *            {@link EntityRequest} for {@link Employee}
     * @return {@link PayloadResponse} holding created {@link Employee}.
     * @throws ApiException
     *             If there was a problem during API invocation then.
     *             {@link ApiException} will be generated/returned with
     *             corresponding error message and {@link ResponseCode}.
     */
    PayloadResponse<Employee> create(final EntityRequest<Employee> request) throws ApiException;

    /**
     * Update existing {@link Employee}. <br>
     * Method validates if Employee exists and if the request is valid update
     * database.
     * 
     * @param request
     *            {@link EntityRequest} for {@link Employee}
     * @return {@link PayloadResponse} holding created {@link Employee}.
     * @throws ApiException
     *             If there was a problem during API invocation then.
     *             {@link ApiException} will be generated/returned with
     *             corresponding error message and {@link ResponseCode}.
     */
    PayloadResponse<Employee> update(final EntityRequest<Employee> request) throws ApiException;

    /**
     * Delete {@link Employee} from the database. <br>
     * If {@link Employee} with the given Id does not exist a validation
     * exception will be thrown.
     * 
     * @param request
     *            {@link EntityRequest} for Employee Id.
     * @throws ApiException
     *             If there was a problem during API invocation then
     *             {@link ApiException} will be generated/returned with
     *             corresponding error message and {@link ResponseCode}.
     */
    void delete(EntityRequest<Long> request) throws ApiException;

    /**
     * Retrieve All {@link Employee}s from the database.
     * 
     * @param request
     *            {@link Request} containing pagination and sorting information.
     * @return {@link PagedPayloadResponse} for {@link Employee}.
     * @throws ApiException
     *             If there was a problem during API invocation then.
     *             {@link ApiException} will be generated/returned with
     *             corresponding error message and {@link ResponseCode}.
     */
    PagedPayloadResponse<Employee> find(final Request request) throws ApiException;

    /**
     * Retrieve {@link Employee} by Id.
     * 
     * @param request
     *            {@link EntityRequest} containing the unique identifier.
     * @return {@link PayloadResponse} for {@link Employee}.
     * @throws ApiException
     *             If there was a problem during API invocation then.
     *             {@link ApiException} will be generated/returned with
     *             corresponding error message and {@link ResponseCode}.
     */
    PayloadResponse<Employee> findById(EntityRequest<Long> request) throws ApiException;

	PagedPayloadResponse<Employee> search(EntityRequest<String> requestName,EntityRequest<String> requestSurname,EntityRequest<String> requestNumber,EntityRequest<LocalDateTime> requestHireDate,EntityRequest<String> requestJobTitle) throws ApiException;
}
