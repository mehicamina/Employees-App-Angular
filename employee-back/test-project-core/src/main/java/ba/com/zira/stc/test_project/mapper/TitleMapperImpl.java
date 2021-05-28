package ba.com.zira.stc.test_project.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import ba.com.zira.stc.test_project.api.model.Title;
import ba.com.zira.stc.test_project.dao.model.TitleEntity;

@Component
public class TitleMapperImpl implements TitleMapper {
	
	@Override
     public TitleEntity dtoToEntity(Title title) {
		 if(title== null) {
			 return null;
		 }
		 TitleEntity titleEntity = new TitleEntity();
		 
		 titleEntity.setCode(title.getCode());
		 titleEntity.setCreated(title.getCreated());
		 titleEntity.setCreatedBy(title.getCreatedBy());
		 titleEntity.setDescription(title.getDescription());
		 titleEntity.setModified(title.getModified());
		 titleEntity.setModifiedBy(title.getModifiedBy());
		 titleEntity.setName(title.getName());
		 titleEntity.setShortName(title.getShortName());
		 titleEntity.setStatus(title.getStatus());
		 return titleEntity;
		 
	 }
	
	 @Override
    public Title entityToDto(TitleEntity titleEntity){
		 if(titleEntity== null) {
			 return null;
		 }
		 Title title = new Title();
		 
		 title.setCode(titleEntity.getCode());
		 title.setCreated(titleEntity.getCreated());
		 title.setCreatedBy(titleEntity.getCreatedBy());
		 title.setDescription(titleEntity.getDescription());
		 title.setModified(titleEntity.getModified());
		 title.setModifiedBy(titleEntity.getModifiedBy());
		 title.setName(titleEntity.getName());
		 title.setShortName(titleEntity.getShortName());
		 title.setStatus(titleEntity.getStatus());
		 return title;
	 }
	
	 @Override
	    public void updateDto(TitleEntity titleEntity, @MappingTarget Title title) {
		 if(titleEntity==null) {
			 return;
		 }
		 title.setCode(titleEntity.getCode());
		 title.setCreated(titleEntity.getCreated());
		 title.setCreatedBy(titleEntity.getCreatedBy());
		 title.setDescription(titleEntity.getDescription());
		 title.setModified(titleEntity.getModified());
		 title.setModifiedBy(titleEntity.getModifiedBy());
		 title.setName(titleEntity.getName());
		 title.setShortName(titleEntity.getShortName());
		 title.setStatus(titleEntity.getStatus());
	 }
	 
	 @Override
	    public void updateEntity(Title title, @MappingTarget TitleEntity titleEntity){
		 if ( title == null ) {
	            return;
	        }
		 titleEntity.setCode(title.getCode());
		 titleEntity.setCreated(title.getCreated());
		 titleEntity.setCreatedBy(title.getCreatedBy());
		 titleEntity.setDescription(title.getDescription());
		 titleEntity.setModified(title.getModified());
		 titleEntity.setModifiedBy(title.getModifiedBy());
		 titleEntity.setName(title.getName());
		 titleEntity.setShortName(title.getShortName());
		 titleEntity.setStatus(title.getStatus());
	 }
	 @Override
	 public List<TitleEntity> dtosToEntities(List<Title> title){
		 if ( title == null ) {
	            return null;
	        }
		 
		 List<TitleEntity> list = new ArrayList<TitleEntity>( title.size() );
		 for ( Title title1 : title ) {
	            list.add( dtoToEntity( title1 ) );
	        }

	        return list;
	 }
	 @Override
	 public List<Title> entitiesToDtos(List<TitleEntity> titleEntity){
		 if ( titleEntity == null ) {
	            return null;
	        }
		 
		 List<Title> list = new ArrayList<Title>( titleEntity.size() );
		 for ( TitleEntity titleEntity1 : titleEntity ) {
	            list.add( entityToDto( titleEntity1 ) );
	        }

	        return list;
	 }
}
