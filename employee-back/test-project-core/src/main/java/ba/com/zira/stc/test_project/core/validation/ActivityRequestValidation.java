package ba.com.zira.stc.test_project.core.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ba.com.zira.commons.message.request.EntityRequest;
import ba.com.zira.commons.message.response.ValidationResponse;
import ba.com.zira.commons.model.response.ResponseCode;
import ba.com.zira.commons.validation.RequestValidator;
import ba.com.zira.stc.test_project.api.model.Activity;
import ba.com.zira.stc.test_project.dao.ActivityDAO;

/**
 * ActivityRequestValidation is used for validation of
 * {@link ActivityService} requests.<br>
 * i.e. database validation needed
 * 
 * @author zira
 *
 */
@Component("activityRequestValidation")
public class ActivityRequestValidation {

	 @Autowired
	    private RequestValidator requestValidator;

	    @Autowired
	    private ActivityDAO activityDAO;
	    
	    /**
	     * Validates update Activity from {@link ActivityService}.
	     *
	     * @param request
	     *            the {@link EntityRequest} to validate
	     * @param validationRuleMessage
	     *            name of the validation rule that is going to be used for
	     *            validating {@link Activity}
	     * 
	     * @return {@link ValidationResponse}
	     */
	    public ValidationResponse validateUpdateRequest(final EntityRequest<Activity> request, final String validationRuleMessage) {
	        ValidationResponse validationResponse = requestValidator.validate(request, validationRuleMessage);
	        if (validationResponse.getResponseCode() == ResponseCode.OK.getCode()) {
	            final StringBuilder errorDescription = new StringBuilder();
	            existsActivity(request.getEntity().getCode(), errorDescription);
	            validationResponse = requestValidator.createResponse(request, errorDescription);
	        }
	        return validationResponse;
	    }

	    /**
	     * Validates if Activity exists.
	     *
	     * @param request
	     *            the {@link EntityRequest} to validate
	     * @param validationRuleMessage
	     *            name of the validation rule that is going to be used for
	     *            validating {@link String}
	     * 
	     * @return {@link ValidationResponse}
	     */
	    public ValidationResponse validateExistsActivity(final EntityRequest<String> request, final String validationRuleMessage) {
	        ValidationResponse validationResponse = requestValidator.validate(request, validationRuleMessage);
	        if (validationResponse.getResponseCode() == ResponseCode.OK.getCode()) {
	            final StringBuilder errorDescription = new StringBuilder();
	            existsActivity(request.getEntity(), errorDescription);
	            validationResponse = requestValidator.createResponse(request, errorDescription);
	        }
	        return validationResponse;
	    }

	    private void existsActivity(final String activityCode, final StringBuilder errorDescription) {
	        if (!activityDAO.existsByPK(activityCode)) {
	            errorDescription.append("Activity with Code: ").append(activityCode).append(" does not exist!");
	        }
	    }
}
