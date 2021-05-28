package ba.com.zira.stc.test_project.core.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ba.com.zira.commons.message.request.EntityRequest;
import ba.com.zira.commons.message.response.ValidationResponse;
import ba.com.zira.commons.model.response.ResponseCode;
import ba.com.zira.commons.validation.RequestValidator;
import ba.com.zira.stc.test_project.api.EmployeeService;
import ba.com.zira.stc.test_project.api.model.Employee;
import ba.com.zira.stc.test_project.dao.EmployeeDAO;

/**
 * EmployeeRequestValidationImpl is used for validation of
 * {@link EmployeeService} requests.<br>
 * i.e. database validation needed
 * 
 * @author zira
 *
 */
@Component("employeeRequestValidation")
public class EmployeeRequestValidation {

    @Autowired
    private RequestValidator requestValidator;

    @Autowired
    private EmployeeDAO employeeDAO;

    /**
     * Validates update Employee from {@link EmployeeService}.
     *
     * @param request
     *            the {@link EntityRequest} to validate
     * @param validationRuleMessage
     *            name of the validation rule that is going to be used for
     *            validating {@link Employee}
     * 
     * @return {@link ValidationResponse}
     */
    public ValidationResponse validateUpdateRequest(final EntityRequest<Employee> request, final String validationRuleMessage) {
        ValidationResponse validationResponse = requestValidator.validate(request, validationRuleMessage);
        if (validationResponse.getResponseCode() == ResponseCode.OK.getCode()) {
            final StringBuilder errorDescription = new StringBuilder();
            existsEmployee(request.getEntity().getId(), errorDescription);
            validationResponse = requestValidator.createResponse(request, errorDescription);
        }
        return validationResponse;
    }

    /**
     * Validates if Employee exists.
     *
     * @param request
     *            the {@link EntityRequest} to validate
     * @param validationRuleMessage
     *            name of the validation rule that is going to be used for
     *            validating {@link Long}
     * 
     * @return {@link ValidationResponse}
     */
    public ValidationResponse validateExistsEmployee(final EntityRequest<Long> request, final String validationRuleMessage) {
        ValidationResponse validationResponse = requestValidator.validate(request, validationRuleMessage);
        if (validationResponse.getResponseCode() == ResponseCode.OK.getCode()) {
            final StringBuilder errorDescription = new StringBuilder();
            existsEmployee(request.getEntity(), errorDescription);
            validationResponse = requestValidator.createResponse(request, errorDescription);
        }
        return validationResponse;
    }

    private void existsEmployee(final Long employeeId, final StringBuilder errorDescription) {
        if (!employeeDAO.existsByPK(employeeId)) {
            errorDescription.append("Employee with Id: ").append(employeeId).append(" does not exist!");
        }
    }

}
