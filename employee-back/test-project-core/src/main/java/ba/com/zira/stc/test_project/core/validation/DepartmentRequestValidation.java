package ba.com.zira.stc.test_project.core.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ba.com.zira.commons.message.request.EntityRequest;
import ba.com.zira.commons.message.response.ValidationResponse;
import ba.com.zira.commons.model.response.ResponseCode;
import ba.com.zira.commons.validation.RequestValidator;
import ba.com.zira.stc.test_project.api.DepartmentService;
import ba.com.zira.stc.test_project.api.model.Department;
import ba.com.zira.stc.test_project.dao.DepartmentDAO;

/**
 * DepartmentRequestValidation is used for validation of
 * {@link DepartmentService} requests.<br>
 * i.e. database validation needed
 * 
 * @author zira
 *
 */
@Component("departmentRequestValidation")
public class DepartmentRequestValidation {

	@Autowired
	private RequestValidator requestValidator;

	@Autowired
	private DepartmentDAO departmentDAO;

	/**
	 * Validates update Department from {@link DepartmentService}.
	 *
	 * @param request               the {@link EntityRequest} to validate
	 * @param validationRuleMessage name of the validation rule that is going to be
	 *                              used for validating {@link Department}
	 * 
	 * @return {@link ValidationResponse}
	 */
	public ValidationResponse validateUpdateRequest(final EntityRequest<Department> request,
			final String validationRuleMessage) {
		ValidationResponse validationResponse = requestValidator.validate(request, validationRuleMessage);
		if (validationResponse.getResponseCode() == ResponseCode.OK.getCode()) {
			final StringBuilder errorDescription = new StringBuilder();
			existsDepartment(request.getEntity().getCode(), errorDescription);
			validationResponse = requestValidator.createResponse(request, errorDescription);
		}
		return validationResponse;
	}

	/**
	 * Validates if Department exists.
	 *
	 * @param request               the {@link EntityRequest} to validate
	 * @param validationRuleMessage name of the validation rule that is going to be
	 *                              used for validating {@link String}
	 * 
	 * @return {@link ValidationResponse}
	 */
	public ValidationResponse validateExistsDepartment(final EntityRequest<String> request,
			final String validationRuleMessage) {
		ValidationResponse validationResponse = requestValidator.validate(request, validationRuleMessage);
		if (validationResponse.getResponseCode() == ResponseCode.OK.getCode()) {
			final StringBuilder errorDescription = new StringBuilder();
			existsDepartment(request.getEntity(), errorDescription);
			validationResponse = requestValidator.createResponse(request, errorDescription);
		}
		return validationResponse;
	}

	private void existsDepartment(final String departmentCode, final StringBuilder errorDescription) {
		if (!departmentDAO.existsByPK(departmentCode)) {
			errorDescription.append("Department with Code: ").append(departmentCode).append(" does not exist!");
		}
	}
}
