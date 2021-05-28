package ba.com.zira.stc.test_project.api.model;

import java.sql.Timestamp;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EmployeeProject.class)
public class EmployeeProject_ {
    public static volatile SingularAttribute<EmployeeProject, Long> id;
    public static volatile SingularAttribute<EmployeeProject, String> activityCode;
    public static volatile SingularAttribute<EmployeeProject, Timestamp> created;
    public static volatile SingularAttribute<EmployeeProject, String> createdBy;
    public static volatile SingularAttribute<EmployeeProject, Timestamp> modified;
    public static volatile SingularAttribute<EmployeeProject, String> modifiedBy;
    public static volatile SingularAttribute<EmployeeProject, Timestamp> validFrom;
    public static volatile SingularAttribute<EmployeeProject, Timestamp> validTo;
    public static volatile SingularAttribute<EmployeeProject, Employee> employee;
}
