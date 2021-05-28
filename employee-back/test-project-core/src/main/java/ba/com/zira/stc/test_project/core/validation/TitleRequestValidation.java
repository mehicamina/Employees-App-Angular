package ba.com.zira.stc.test_project.core.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ba.com.zira.commons.message.request.EntityRequest;
import ba.com.zira.commons.message.response.ValidationResponse;
import ba.com.zira.commons.model.response.ResponseCode;
import ba.com.zira.commons.validation.RequestValidator;
import ba.com.zira.stc.test_project.api.model.Title;
import ba.com.zira.stc.test_project.dao.TitleDAO;

@Component("titleRequestValidation")
public class TitleRequestValidation {
	@Autowired
    private RequestValidator requestValidator;

    @Autowired
    private TitleDAO titleDAO;
    
    public ValidationResponse validateUpdateRequest(final EntityRequest<Title> request, final String validationRuleMessage) {
        ValidationResponse validationResponse = requestValidator.validate(request, validationRuleMessage);
        if (validationResponse.getResponseCode() == ResponseCode.OK.getCode()) {
            final StringBuilder errorDescription = new StringBuilder();
            existsTitle(request.getEntity().getCode(), errorDescription);
            validationResponse = requestValidator.createResponse(request, errorDescription);
        }
        return validationResponse;
    }
    
    public ValidationResponse validateExistsTitle(final EntityRequest<String> request, final String validationRuleMessage) {
        ValidationResponse validationResponse = requestValidator.validate(request, validationRuleMessage);
        if (validationResponse.getResponseCode() == ResponseCode.OK.getCode()) {
            final StringBuilder errorDescription = new StringBuilder();
            existsTitle(request.getEntity(), errorDescription);
            validationResponse = requestValidator.createResponse(request, errorDescription);
        }
        return validationResponse;
    }

    private void existsTitle(final String tileCode, final StringBuilder errorDescription) {
        if (!titleDAO.existsByPK(tileCode)) {
            errorDescription.append("Title with code: ").append(tileCode).append(" does not exist!");
        }
    }
}
