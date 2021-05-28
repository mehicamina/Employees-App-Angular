package ba.com.zira.stc.test_project.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import ba.com.zira.stc.test_project.api.model.Activity;
import ba.com.zira.stc.test_project.dao.model.ActivityEntity;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

	ActivityEntity dtoToEntity(Activity activity);

	@InheritInverseConfiguration(name = "dtoToEntity")
	Activity entityToDto(ActivityEntity activityEntity);

	@InheritConfiguration
	void updateDto(ActivityEntity activityEntity, @MappingTarget Activity activity);

	@InheritConfiguration
	void updateEntity(Activity activity, @MappingTarget ActivityEntity activityEntity);

	List<ActivityEntity> dtosToEntities(List<Activity> activity);

	List<Activity> entitiesToDtos(List<ActivityEntity> activityEntity);
}
