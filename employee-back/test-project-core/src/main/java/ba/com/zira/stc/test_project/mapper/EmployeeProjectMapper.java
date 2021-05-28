package ba.com.zira.stc.test_project.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import ba.com.zira.stc.test_project.api.model.EmployeeProject;
import ba.com.zira.stc.test_project.dao.model.EmployeeProjectEntity;

@Mapper(componentModel = "spring")
public interface EmployeeProjectMapper {

    @Mapping(source = "employeeId", target = "employee.id")
    EmployeeProjectEntity dtoToEntity(EmployeeProject employeeProject);

    @InheritInverseConfiguration(name = "dtoToEntity")
    EmployeeProject entityToDto(EmployeeProjectEntity employeeProjectEntity);

    @InheritConfiguration
    void updateDto(EmployeeProjectEntity employeeProjectEntity, @MappingTarget EmployeeProject employeeProject);

    @InheritConfiguration
    void updateEntity(EmployeeProject employeeProject, @MappingTarget EmployeeProjectEntity employeeProjectEntity);

    List<EmployeeProjectEntity> dtosToEntities(List<EmployeeProject> employeeProject);

    List<EmployeeProject> entitiesToDtos(List<EmployeeProjectEntity> employeeProjectEntity);
}