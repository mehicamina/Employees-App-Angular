
package ba.com.zira.stc.test_project.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import ba.com.zira.stc.test_project.api.model.Department;
import ba.com.zira.stc.test_project.dao.model.DepartmentEntity;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

	DepartmentEntity dtoToEntity(Department department);

	@InheritInverseConfiguration(name = "dtoToEntity")
	Department entityToDto(DepartmentEntity departmentEntity);

	@InheritConfiguration
	void updateDto(DepartmentEntity departmentEntity, @MappingTarget Department department);

	@InheritConfiguration
	void updateEntity(Department department, @MappingTarget DepartmentEntity departmentEntity);

	List<DepartmentEntity> dtosToEntities(List<Department> department);

	List<Department> entitiesToDtos(List<DepartmentEntity> departmentEntity);
}
