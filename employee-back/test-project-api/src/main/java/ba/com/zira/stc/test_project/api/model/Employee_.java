package ba.com.zira.stc.test_project.api.model;

import java.sql.Timestamp;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Employee.class)
public class Employee_ {
    public static volatile SingularAttribute<Employee, Long> id;
    public static volatile SingularAttribute<Employee, String> activityCode;
    public static volatile SingularAttribute<Employee, Timestamp> birtDate;
    public static volatile SingularAttribute<Employee, Timestamp> created;
    public static volatile SingularAttribute<Employee, String> createdBy;
    public static volatile SingularAttribute<Employee, String> departmentCode;
    public static volatile SingularAttribute<Employee, String> email;
    public static volatile SingularAttribute<Employee, String> firstName;
    public static volatile SingularAttribute<Employee, String> gender;
    public static volatile SingularAttribute<Employee, Timestamp> hireDate;
    public static volatile SingularAttribute<Employee, String> jobTitleCode;
    public static volatile SingularAttribute<Employee, String> lastName;
    public static volatile SingularAttribute<Employee, Timestamp> modified;
    public static volatile SingularAttribute<Employee, String> modifiedBy;
    public static volatile SingularAttribute<Employee, String> phoneNumber;
}
