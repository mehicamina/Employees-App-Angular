package ba.com.zira.stc.test_project.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import ba.com.zira.stc.test_project.api.model.Title;
import ba.com.zira.stc.test_project.dao.model.TitleEntity;

@Mapper(componentModel = "spring")
public interface TitleMapper {
	TitleEntity dtoToEntity(Title title);
	
	@InheritInverseConfiguration(name = "dtoToEntity")
    Title entityToDto(TitleEntity titleEntity);
	
	 @InheritConfiguration
	    void updateDto(TitleEntity titleEntity, @MappingTarget Title title);
	 
	 @InheritConfiguration
	    void updateEntity(Title title, @MappingTarget TitleEntity titleEntity);
	 
	 List<TitleEntity> dtosToEntities(List<Title> title);
	 
	 List<Title> entitiesToDtos(List<TitleEntity> titleEntity);
}
