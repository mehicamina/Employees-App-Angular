
package ba.com.zira.stc.test_project.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

import ba.com.zira.stc.test_project.api.model.Department;
import ba.com.zira.stc.test_project.dao.model.DepartmentEntity;

@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2019-07-10T09:11:17+0200", comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_211 (Oracle Corporation)")
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

	@Override
	public DepartmentEntity dtoToEntity(Department department) {
		if (department == null) {
			return null;
		}

		DepartmentEntity departmentEntity = new DepartmentEntity();

		departmentEntity.setDescription(department.getDescription());
		departmentEntity.setShortName(department.getShortName());
		departmentEntity.setCreated(department.getCreated());
		departmentEntity.setCreatedBy(department.getCreatedBy());
		departmentEntity.setCode(department.getCode());
		departmentEntity.setName(department.getName());
		departmentEntity.setModified(department.getModified());
		departmentEntity.setModifiedBy(department.getModifiedBy());
		departmentEntity.setStatus(department.getStatus());

		return departmentEntity;
	}

	@Override
	public Department entityToDto(DepartmentEntity departmentEntity) {
		if (departmentEntity == null) {
			return null;
		}

		Department department = new Department();

		department.setDescription(departmentEntity.getDescription());
		department.setShortName(departmentEntity.getShortName());
		department.setCreated(departmentEntity.getCreated());
		department.setCreatedBy(departmentEntity.getCreatedBy());
		department.setCode(departmentEntity.getCode());
		department.setName(departmentEntity.getName());
		department.setModified(departmentEntity.getModified());
		department.setModifiedBy(departmentEntity.getModifiedBy());
		department.setStatus(departmentEntity.getStatus());

		return department;
	}

	@Override
	public void updateDto(DepartmentEntity departmentEntity, Department department) {
		if (departmentEntity == null) {
			return;
		}

		department.setDescription(departmentEntity.getDescription());
		department.setShortName(departmentEntity.getShortName());
		department.setCreated(departmentEntity.getCreated());
		department.setCreatedBy(departmentEntity.getCreatedBy());
		department.setCode(departmentEntity.getCode());
		department.setName(departmentEntity.getName());
		department.setModified(departmentEntity.getModified());
		department.setModifiedBy(departmentEntity.getModifiedBy());
		department.setStatus(departmentEntity.getStatus());
	}

	@Override
	public void updateEntity(Department department, DepartmentEntity departmentEntity) {
		if (department == null) {
			return;
		}

		departmentEntity.setDescription(department.getDescription());
		departmentEntity.setShortName(department.getShortName());
		departmentEntity.setCreated(department.getCreated());
		departmentEntity.setCreatedBy(department.getCreatedBy());
		departmentEntity.setCode(department.getCode());
		departmentEntity.setName(department.getName());
		departmentEntity.setModified(department.getModified());
		departmentEntity.setModifiedBy(department.getModifiedBy());

		departmentEntity.setStatus(department.getStatus());
	}

	@Override
	public List<DepartmentEntity> dtosToEntities(List<Department> department) {
		if (department == null) {
			return null;
		}

		List<DepartmentEntity> list = new ArrayList<DepartmentEntity>(department.size());
		for (Department department1 : department) {
			list.add(dtoToEntity(department1));
		}

		return list;
	}

	@Override
	public List<Department> entitiesToDtos(List<DepartmentEntity> departmentEntity) {
		if (departmentEntity == null) {
			return null;
		}

		List<Department> list = new ArrayList<Department>(departmentEntity.size());
		for (DepartmentEntity departmentEntity1 : departmentEntity) {
			list.add(entityToDto(departmentEntity1));
		}

		return list;
	}
}
