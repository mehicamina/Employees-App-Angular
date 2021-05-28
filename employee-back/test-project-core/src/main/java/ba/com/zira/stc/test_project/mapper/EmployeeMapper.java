package ba.com.zira.stc.test_project.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import ba.com.zira.stc.test_project.api.model.Employee;
import ba.com.zira.stc.test_project.dao.model.EmployeeEntity;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeEntity dtoToEntity(Employee employee);

    @InheritInverseConfiguration(name = "dtoToEntity")
    Employee entityToDto(EmployeeEntity employeeEntity);

    @InheritConfiguration
    void updateDto(EmployeeEntity employeeEntity, @MappingTarget Employee employee);

    @InheritConfiguration
    void updateEntity(Employee employee, @MappingTarget EmployeeEntity employeeEntity);

    List<EmployeeEntity> dtosToEntities(List<Employee> employee);

    List<Employee> entitiesToDtos(List<EmployeeEntity> employeeEntity);
}