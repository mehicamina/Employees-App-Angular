package ba.com.zira.stc.test_project.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String activityCode;
    private LocalDateTime birtDate;
    private LocalDateTime created;
    private String createdBy;
    private String departmentCode;
    private String email;
    private String firstName;
    private String gender;
    private LocalDateTime hireDate;
    private String jobTitleCode;
    private String lastName;
    private LocalDateTime modified;
    private String modifiedBy;
    private String phoneNumber;
    private String status;

}