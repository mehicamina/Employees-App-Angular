package ba.com.zira.stc.test_project.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

import ba.com.zira.stc.test_project.api.model.Activity;
import ba.com.zira.stc.test_project.dao.model.ActivityEntity;

@Generated(
	    value = "org.mapstruct.ap.MappingProcessor",
	    date = "2019-07-10T09:11:17+0200",
	    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_211 (Oracle Corporation)"
	)
@Component
public class ActivityMapperImpl implements ActivityMapper{

	@Override
    public ActivityEntity dtoToEntity(Activity activity) {
        if ( activity == null ) {
            return null;
        }

        ActivityEntity activityEntity = new ActivityEntity();

        activityEntity.setType( activity.getType() );
        activityEntity.setDescription( activity.getDescription() );
        activityEntity.setShortName( activity.getShortName() );
        activityEntity.setCreated( activity.getCreated() );
        activityEntity.setCreatedBy( activity.getCreatedBy() );
        activityEntity.setCode( activity.getCode() );
        activityEntity.setName( activity.getName() );
        activityEntity.setModified( activity.getModified() );
        activityEntity.setModifiedBy( activity.getModifiedBy() );
        activityEntity.setStatus(activity.getStatus());

        return activityEntity;
    }
	
	@Override
    public Activity entityToDto(ActivityEntity activityEntity) {
        if ( activityEntity == null ) {
            return null;
        }

        Activity activity = new Activity();

        activity.setType( activityEntity.getType() );
        activity.setDescription( activityEntity.getDescription() );
        activity.setShortName( activityEntity.getShortName() );
        activity.setCreated( activityEntity.getCreated() );
        activity.setCreatedBy( activityEntity.getCreatedBy() );
        activity.setCode( activityEntity.getCode() );
        activity.setName( activityEntity.getName() );
        activity.setModified( activityEntity.getModified() );
        activity.setModifiedBy( activityEntity.getModifiedBy() );
        activity.setStatus(activityEntity.getStatus());

        return activity;
    }
	
	@Override
    public void updateDto(ActivityEntity activityEntity, Activity activity) {
        if ( activityEntity == null ) {
            return;
        }

        activity.setType( activityEntity.getType() );
        activity.setDescription( activityEntity.getDescription() );
        activity.setShortName( activityEntity.getShortName() );
        activity.setCreated( activityEntity.getCreated() );
        activity.setCreatedBy( activityEntity.getCreatedBy() );
        activity.setCode( activityEntity.getCode() );
        activity.setName( activityEntity.getName() );
        activity.setModified( activityEntity.getModified() );
        activity.setModifiedBy( activityEntity.getModifiedBy() );
        activity.setStatus(activityEntity.getStatus());
    }
	
	@Override
    public void updateEntity(Activity activity, ActivityEntity activityEntity) {
        if ( activity == null ) {
            return;
        }

        activityEntity.setType( activity.getType() );
        activityEntity.setDescription( activity.getDescription() );
        activityEntity.setShortName( activity.getShortName() );
        activityEntity.setCreated( activity.getCreated() );
        activityEntity.setCreatedBy( activity.getCreatedBy() );
        activityEntity.setCode( activity.getCode() );
        activityEntity.setName( activity.getName() );
        activityEntity.setModified( activity.getModified() );
        activityEntity.setModifiedBy( activity.getModifiedBy() );

        activityEntity.setStatus(activity.getStatus());
    }
	
	@Override
    public List<ActivityEntity> dtosToEntities(List<Activity> activity) {
        if ( activity == null ) {
            return null;
        }

        List<ActivityEntity> list = new ArrayList<ActivityEntity>( activity.size() );
        for ( Activity activity1 : activity ) {
            list.add( dtoToEntity( activity1 ) );
        }

        return list;
    }
	
	@Override
    public List<Activity> entitiesToDtos(List<ActivityEntity> activityEntity) {
        if ( activityEntity == null ) {
            return null;
        }

        List<Activity> list = new ArrayList<Activity>( activityEntity.size() );
        for ( ActivityEntity activityEntity1 : activityEntity ) {
            list.add( entityToDto( activityEntity1 ) );
        }

        return list;
    }
}
