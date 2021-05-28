package ba.com.zira.stc.test_project.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CalendarResponse implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String activityCode;
	private LocalDateTime validFrom;
	private LocalDateTime validTo;
	private String firstName;
	private String lastName;

	public LocalDateTime getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDateTime validFrom) {
		this.validFrom = validFrom;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public LocalDateTime getValidTo() {
		return validTo;
	}

	public void setValidTo(LocalDateTime validTo) {
		this.validTo = validTo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
