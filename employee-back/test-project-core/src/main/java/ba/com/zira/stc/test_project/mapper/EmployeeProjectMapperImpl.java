package ba.com.zira.stc.test_project.mapper;

import ba.com.zira.stc.test_project.api.model.EmployeeProject;
import ba.com.zira.stc.test_project.dao.model.EmployeeEntity;
import ba.com.zira.stc.test_project.dao.model.EmployeeProjectEntity;
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
public class EmployeeProjectMapperImpl implements EmployeeProjectMapper {

    @Override
    public EmployeeProjectEntity dtoToEntity(EmployeeProject employeeProject) {
        if ( employeeProject == null ) {
            return null;
        }

        EmployeeProjectEntity employeeProjectEntity = new EmployeeProjectEntity();

        employeeProjectEntity.setEmployee( employeeProjectToEmployeeEntity( employeeProject ) );
        employeeProjectEntity.setId( employeeProject.getId() );
        employeeProjectEntity.setActivityCode( employeeProject.getActivityCode() );
        employeeProjectEntity.setCreated( employeeProject.getCreated() );
        employeeProjectEntity.setCreatedBy( employeeProject.getCreatedBy() );
        employeeProjectEntity.setModified( employeeProject.getModified() );
        employeeProjectEntity.setModifiedBy( employeeProject.getModifiedBy() );
        employeeProjectEntity.setValidFrom( employeeProject.getValidFrom() );
        employeeProjectEntity.setValidTo( employeeProject.getValidTo() );

        return employeeProjectEntity;
    }

    @Override
    public EmployeeProject entityToDto(EmployeeProjectEntity employeeProjectEntity) {
        if ( employeeProjectEntity == null ) {
            return null;
        }

        EmployeeProject employeeProject = new EmployeeProject();

        Long id = employeeProjectEntityEmployeeId( employeeProjectEntity );
        if ( id != null ) {
            employeeProject.setEmployeeId( id );
        }
        employeeProject.setId( employeeProjectEntity.getId() );
        employeeProject.setActivityCode( employeeProjectEntity.getActivityCode() );
        employeeProject.setCreated( employeeProjectEntity.getCreated() );
        employeeProject.setCreatedBy( employeeProjectEntity.getCreatedBy() );
        employeeProject.setModified( employeeProjectEntity.getModified() );
        employeeProject.setModifiedBy( employeeProjectEntity.getModifiedBy() );
        employeeProject.setValidFrom( employeeProjectEntity.getValidFrom() );
        employeeProject.setValidTo( employeeProjectEntity.getValidTo() );

        return employeeProject;
    }

    @Override
    public void updateDto(EmployeeProjectEntity employeeProjectEntity, EmployeeProject employeeProject) {
        if ( employeeProjectEntity == null ) {
            return;
        }

        Long id = employeeProjectEntityEmployeeId( employeeProjectEntity );
        if ( id != null ) {
            employeeProject.setEmployeeId( id );
        }
        employeeProject.setId( employeeProjectEntity.getId() );
        employeeProject.setActivityCode( employeeProjectEntity.getActivityCode() );
        employeeProject.setCreated( employeeProjectEntity.getCreated() );
        employeeProject.setCreatedBy( employeeProjectEntity.getCreatedBy() );
        employeeProject.setModified( employeeProjectEntity.getModified() );
        employeeProject.setModifiedBy( employeeProjectEntity.getModifiedBy() );
        employeeProject.setValidFrom( employeeProjectEntity.getValidFrom() );
        employeeProject.setValidTo( employeeProjectEntity.getValidTo() );
    }

    @Override
    public void updateEntity(EmployeeProject employeeProject, EmployeeProjectEntity employeeProjectEntity) {
        if ( employeeProject == null ) {
            return;
        }

        if ( employeeProjectEntity.getEmployee() == null ) {
            employeeProjectEntity.setEmployee( new EmployeeEntity() );
        }
        employeeProjectToEmployeeEntity1( employeeProject, employeeProjectEntity.getEmployee() );
        employeeProjectEntity.setId( employeeProject.getId() );
        employeeProjectEntity.setActivityCode( employeeProject.getActivityCode() );
        employeeProjectEntity.setCreated( employeeProject.getCreated() );
        employeeProjectEntity.setCreatedBy( employeeProject.getCreatedBy() );
        employeeProjectEntity.setModified( employeeProject.getModified() );
        employeeProjectEntity.setModifiedBy( employeeProject.getModifiedBy() );
        employeeProjectEntity.setValidFrom( employeeProject.getValidFrom() );
        employeeProjectEntity.setValidTo( employeeProject.getValidTo() );
    }

    @Override
    public List<EmployeeProjectEntity> dtosToEntities(List<EmployeeProject> employeeProject) {
        if ( employeeProject == null ) {
            return null;
        }

        List<EmployeeProjectEntity> list = new ArrayList<EmployeeProjectEntity>( employeeProject.size() );
        for ( EmployeeProject employeeProject1 : employeeProject ) {
            list.add( dtoToEntity( employeeProject1 ) );
        }

        return list;
    }

    @Override
    public List<EmployeeProject> entitiesToDtos(List<EmployeeProjectEntity> employeeProjectEntity) {
        if ( employeeProjectEntity == null ) {
            return null;
        }

        List<EmployeeProject> list = new ArrayList<EmployeeProject>( employeeProjectEntity.size() );
        for ( EmployeeProjectEntity employeeProjectEntity1 : employeeProjectEntity ) {
            list.add( entityToDto( employeeProjectEntity1 ) );
        }

        return list;
    }

    protected EmployeeEntity employeeProjectToEmployeeEntity(EmployeeProject employeeProject) {
        if ( employeeProject == null ) {
            return null;
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setId( employeeProject.getEmployeeId() );

        return employeeEntity;
    }

    private Long employeeProjectEntityEmployeeId(EmployeeProjectEntity employeeProjectEntity) {
        if ( employeeProjectEntity == null ) {
            return null;
        }
        EmployeeEntity employee = employeeProjectEntity.getEmployee();
        if ( employee == null ) {
            return null;
        }
        Long id = employee.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected void employeeProjectToEmployeeEntity1(EmployeeProject employeeProject, EmployeeEntity mappingTarget) {
        if ( employeeProject == null ) {
            return;
        }

        mappingTarget.setId( employeeProject.getEmployeeId() );
    }
}
