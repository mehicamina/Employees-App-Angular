package ba.com.zira.stc.test_project.core.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ba.com.zira.commons.message.request.EntityRequest;
import ba.com.zira.commons.message.response.ValidationResponse;
import ba.com.zira.commons.model.response.ResponseCode;
import ba.com.zira.commons.validation.RequestValidator;
import ba.com.zira.stc.test_project.api.EmployeeProjectService;
import ba.com.zira.stc.test_project.api.model.EmployeeProject;
import ba.com.zira.stc.test_project.dao.EmployeeProjectDAO;

/**
 * EmployeeProjectRequestValidationImpl is used for validation of
 * {@link EmployeeProjectService} requests.<br>
 * i.e. database validation needed
 * 
 * @author zira
 *
 */
@Component("employeeProjectRequestValidation")
public class EmployeeProjectRequestValidation {

    @Autowired
    private RequestValidator requestValidator;

    @Autowired
    private EmployeeProjectDAO employeeProjectDAO;

    /**
     * Validates update EmployeeProject from {@link EmployeeProjectService}.
     *
     * @param request
     *            the {@link EntityRequest} to validate
     * @param validationRuleMessage
     *            name of the validation rule that is going to be used for
     *            validating {@link EmployeeProject}
     * 
     * @return {@link ValidationResponse}
     */
    public ValidationResponse validateUpdateRequest(final EntityRequest<EmployeeProject> request, final String validationRuleMessage) {
        ValidationResponse validationResponse = requestValidator.validate(request, validationRuleMessage);
        if (validationResponse.getResponseCode() == ResponseCode.OK.getCode()) {
            final StringBuilder errorDescription = new StringBuilder();
            existsEmployeeProject(request.getEntity().getId(), errorDescription);
            validationResponse = requestValidator.createResponse(request, errorDescription);
        }
        return validationResponse;
    }

    /**
     * Validates if EmployeeProject exists.
     *
     * @param request
     *            the {@link EntityRequest} to validate
     * @param validationRuleMessage
     *            name of the validation rule that is going to be used for
     *            validating {@link Long}
     * 
     * @return {@link ValidationResponse}
     */
    public ValidationResponse validateExistsEmployeeProject(final EntityRequest<Long> request, final String validationRuleMessage) {
        ValidationResponse validationResponse = requestValidator.validate(request, validationRuleMessage);
        if (validationResponse.getResponseCode() == ResponseCode.OK.getCode()) {
            final StringBuilder errorDescription = new StringBuilder();
            existsEmployeeProject(request.getEntity(), errorDescription);
            validationResponse = requestValidator.createResponse(request, errorDescription);
        }
        return validationResponse;
    }

    private void existsEmployeeProject(final Long employeeProjectId, final StringBuilder errorDescription) {
        if (!employeeProjectDAO.existsByPK(employeeProjectId)) {
            errorDescription.append("EmployeeProject with Id: ").append(employeeProjectId).append(" does not exist!");
        }
    }

}
