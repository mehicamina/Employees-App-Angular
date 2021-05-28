package ba.com.zira.stc.test_project.mapper;

import ba.com.zira.stc.test_project.api.model.Employee;
import ba.com.zira.stc.test_project.dao.model.EmployeeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-10T09:11:17+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_211 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeEntity dtoToEntity(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setId( employee.getId() );
        employeeEntity.setActivityCode( employee.getActivityCode() );
        employeeEntity.setBirtDate( employee.getBirtDate() );
        employeeEntity.setCreated( employee.getCreated() );
        employeeEntity.setCreatedBy( employee.getCreatedBy() );
        employeeEntity.setDepartmentCode( employee.getDepartmentCode() );
        employeeEntity.setEmail( employee.getEmail() );
        employeeEntity.setFirstName( employee.getFirstName() );
        employeeEntity.setGender( employee.getGender() );
        employeeEntity.setHireDate( employee.getHireDate() );
        employeeEntity.setJobTitleCode( employee.getJobTitleCode() );
        employeeEntity.setLastName( employee.getLastName() );
        employeeEntity.setModified( employee.getModified() );
        employeeEntity.setModifiedBy( employee.getModifiedBy() );
        employeeEntity.setPhoneNumber( employee.getPhoneNumber() );
        employeeEntity.setStatus(employee.getStatus());

        return employeeEntity;
    }

    @Override
    public Employee entityToDto(EmployeeEntity employeeEntity) {
        if ( employeeEntity == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeEntity.getId() );
        employee.setActivityCode( employeeEntity.getActivityCode() );
        employee.setBirtDate( employeeEntity.getBirtDate() );
        employee.setCreated( employeeEntity.getCreated() );
        employee.setCreatedBy( employeeEntity.getCreatedBy() );
        employee.setDepartmentCode( employeeEntity.getDepartmentCode() );
        employee.setEmail( employeeEntity.getEmail() );
        employee.setFirstName( employeeEntity.getFirstName() );
        employee.setGender( employeeEntity.getGender() );
        employee.setHireDate( employeeEntity.getHireDate() );
        employee.setJobTitleCode( employeeEntity.getJobTitleCode() );
        employee.setLastName( employeeEntity.getLastName() );
        employee.setModified( employeeEntity.getModified() );
        employee.setModifiedBy( employeeEntity.getModifiedBy() );
        employee.setPhoneNumber( employeeEntity.getPhoneNumber() );
        employee.setStatus(employeeEntity.getStatus());

        return employee;
    }

    @Override
    public void updateDto(EmployeeEntity employeeEntity, Employee employee) {
        if ( employeeEntity == null ) {
            return;
        }

        employee.setId( employeeEntity.getId() );
        employee.setActivityCode( employeeEntity.getActivityCode() );
        employee.setBirtDate( employeeEntity.getBirtDate() );
        employee.setCreated( employeeEntity.getCreated() );
        employee.setCreatedBy( employeeEntity.getCreatedBy() );
        employee.setDepartmentCode( employeeEntity.getDepartmentCode() );
        employee.setEmail( employeeEntity.getEmail() );
        employee.setFirstName( employeeEntity.getFirstName() );
        employee.setGender( employeeEntity.getGender() );
        employee.setHireDate( employeeEntity.getHireDate() );
        employee.setJobTitleCode( employeeEntity.getJobTitleCode() );
        employee.setLastName( employeeEntity.getLastName() );
        employee.setModified( employeeEntity.getModified() );
        employee.setModifiedBy( employeeEntity.getModifiedBy() );
        employee.setPhoneNumber( employeeEntity.getPhoneNumber() );

        employee.setStatus(employeeEntity.getStatus());
    }

    @Override
    public void updateEntity(Employee employee, EmployeeEntity employeeEntity) {
        if ( employee == null ) {
            return;
        }

        employeeEntity.setId( employee.getId() );
        employeeEntity.setActivityCode( employee.getActivityCode() );
        employeeEntity.setBirtDate( employee.getBirtDate() );
        employeeEntity.setCreated( employee.getCreated() );
        employeeEntity.setCreatedBy( employee.getCreatedBy() );
        employeeEntity.setDepartmentCode( employee.getDepartmentCode() );
        employeeEntity.setEmail( employee.getEmail() );
        employeeEntity.setFirstName( employee.getFirstName() );
        employeeEntity.setGender( employee.getGender() );
        employeeEntity.setHireDate( employee.getHireDate() );
        employeeEntity.setJobTitleCode( employee.getJobTitleCode() );
        employeeEntity.setLastName( employee.getLastName() );
        employeeEntity.setModified( employee.getModified() );
        employeeEntity.setModifiedBy( employee.getModifiedBy() );
        employeeEntity.setPhoneNumber( employee.getPhoneNumber() );

        employeeEntity.setStatus(employee.getStatus());
    }

    @Override
    public List<EmployeeEntity> dtosToEntities(List<Employee> employee) {
        if ( employee == null ) {
            return null;
        }

        List<EmployeeEntity> list = new ArrayList<EmployeeEntity>( employee.size() );
        for ( Employee employee1 : employee ) {
            list.add( dtoToEntity( employee1 ) );
        }

        return list;
    }

    @Override
    public List<Employee> entitiesToDtos(List<EmployeeEntity> employeeEntity) {
        if ( employeeEntity == null ) {
            return null;
        }

        List<Employee> list = new ArrayList<Employee>( employeeEntity.size() );
        for ( EmployeeEntity employeeEntity1 : employeeEntity ) {
            list.add( entityToDto( employeeEntity1 ) );
        }

        return list;
    }
}
