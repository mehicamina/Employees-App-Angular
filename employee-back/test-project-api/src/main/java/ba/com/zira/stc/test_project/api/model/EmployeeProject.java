package ba.com.zira.stc.test_project.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeProject implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String activityCode;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime modified;
    private String modifiedBy;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;
    private Long employeeId;

}