package ba.com.zira.stc.test_project.core.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ba.com.zira.commons.exception.ApiException;
import ba.com.zira.commons.message.request.EntityRequest;
import ba.com.zira.commons.message.request.Request;
import ba.com.zira.commons.message.response.PagedPayloadResponse;
import ba.com.zira.commons.message.response.PayloadResponse;
import ba.com.zira.commons.model.PagedData;
import ba.com.zira.commons.model.enums.Status;
import ba.com.zira.commons.model.response.ResponseCode;
import ba.com.zira.commons.validation.RequestValidator;
import ba.com.zira.stc.test_project.api.EmployeeProjectService;
import ba.com.zira.stc.test_project.api.model.CalendarResponse;
import ba.com.zira.stc.test_project.api.model.Employee;
import ba.com.zira.stc.test_project.api.model.EmployeeProject;
import ba.com.zira.stc.test_project.api.model.EmployeeProjectTree;
import ba.com.zira.stc.test_project.core.validation.EmployeeProjectRequestValidation;
import ba.com.zira.stc.test_project.dao.EmployeeProjectDAO;
import ba.com.zira.stc.test_project.dao.model.EmployeeEntity;
import ba.com.zira.stc.test_project.dao.model.EmployeeProjectEntity;
import ba.com.zira.stc.test_project.mapper.EmployeeMapper;
import ba.com.zira.stc.test_project.mapper.EmployeeProjectMapper;

@Service
public class EmployeeProjectServiceImpl implements EmployeeProjectService {
	@Autowired
	private EmployeeMapper employeetMapper;
	@Autowired
	private RequestValidator requestValidator;

	@Autowired
	private EmployeeProjectRequestValidation employeeProjectRequestValidator;

	@Autowired
	private EmployeeProjectDAO employeeProjectDAO;
	@Autowired
	private EmployeeProjectMapper employeeProjectMapper;

	@Override
	public PayloadResponse<EmployeeProject> create(final EntityRequest<EmployeeProject> request) throws ApiException {
		requestValidator.validate(request, "createEmployeeProject");

		final LocalDateTime date = LocalDateTime.now();

		final EmployeeProject employeeProject = request.getEntity();

		final EmployeeProjectEntity employeeProjectEntity = employeeProjectMapper.dtoToEntity(employeeProject);

		employeeProjectEntity.setStatus(Status.ACTIVE.getValue());
		employeeProjectEntity.setCreated(date);
		//employeeProjectEntity.setCreatedBy(request.getUser().getUserId());
		employeeProjectDAO.persist(employeeProjectEntity);

		employeeProjectMapper.updateDto(employeeProjectEntity, employeeProject);

		return new PayloadResponse<>(request, ResponseCode.OK, employeeProject);
	}

	@Override
	public PayloadResponse<EmployeeProject> update(final EntityRequest<EmployeeProject> request) throws ApiException {
		employeeProjectRequestValidator.validateUpdateRequest(request, "updateEmployeeProject");

		final LocalDateTime date = LocalDateTime.now();

		final EmployeeProject employeeProject = request.getEntity();

		final EmployeeProjectEntity employeeProjectEntity = employeeProjectDAO.findByPK(employeeProject.getId());
		employeeProjectMapper.updateEntity(employeeProject, employeeProjectEntity);

		employeeProjectEntity.setModified(date);
		//employeeProjectEntity.setModifiedBy(request.getUser().getUserId());
		employeeProjectDAO.merge(employeeProjectEntity);

		employeeProjectMapper.updateDto(employeeProjectEntity, employeeProject);

		return new PayloadResponse<>(request, ResponseCode.OK, employeeProject);
	}

	@Override
	public void delete(final EntityRequest<Long> request) throws ApiException {
		employeeProjectRequestValidator.validateExistsEmployeeProject(request, "validateId");

		employeeProjectDAO.removeByPK(request.getEntity());
	}

	@Override
	public PagedPayloadResponse<EmployeeProject> find(final Request request) throws ApiException {
		requestValidator.validate(request);

		final PagedData<EmployeeProjectEntity> employeeProjectEntities = employeeProjectDAO
				.findAll(request.getFilter());

		final List<EmployeeProject> employeeProjects = employeeProjectMapper
				.entitiesToDtos(employeeProjectEntities.getRecords());

		return new PagedPayloadResponse<>(request, ResponseCode.OK, employeeProjects.size(), 1, 1,
				employeeProjects.size(), employeeProjects);
	}

	@Override
	public PayloadResponse<EmployeeProject> findById(final EntityRequest<Long> request) throws ApiException {
		requestValidator.validate(request, "validateId");

		final EmployeeProjectEntity employeeProjectEntity = employeeProjectDAO.findByPK(request.getEntity());

		final EmployeeProject employeeProject = employeeProjectMapper.entityToDto(employeeProjectEntity);

		return new PayloadResponse<>(request, ResponseCode.OK, employeeProject);
	}

	@Override
	public PagedPayloadResponse<String> getWorkStatuses() throws ApiException {
		List<String> rezultat = employeeProjectDAO.getWorkStatuses();
		System.out.println(rezultat.size());
		return new PagedPayloadResponse<>(null, ResponseCode.OK, rezultat.size(), 1, 1, rezultat.size(), rezultat);

	}

	@Override
	public PagedPayloadResponse<EmployeeProjectTree> searchByActivityCode(final EntityRequest<String> request)
			throws ApiException {
		List<EmployeeEntity> rez = employeeProjectDAO.searchByActivityCode(request.getEntity());
		List<EmployeeProjectTree> rezultat = new ArrayList<EmployeeProjectTree>();
		List<Employee> convertovaniRez = employeetMapper.entitiesToDtos(rez);
		Random r = new Random ();
		for (int i = 0; i < convertovaniRez.size(); i++) {
			EmployeeProjectTree empt = new EmployeeProjectTree();
			empt.setKey(String.valueOf(i + 1));
			empt.setId(String.valueOf(convertovaniRez.get(i).getId()));
			empt.setName(convertovaniRez.get(i).getFirstName() + " " + convertovaniRez.get(i).getLastName());
			empt.setTitle(convertovaniRez.get(i).getJobTitleCode());
			
			if (convertovaniRez.get(i).getGender().equals("M")) {
				
				empt.setSource("https://randomuser.me/api/portraits/men/"+String.valueOf(r.nextInt(100)+".jpg"));
			} else {
				empt.setSource("https://randomuser.me/api/portraits/women/"+String.valueOf(r.nextInt(100)+".jpg"));
			}
			rezultat.add(empt);
		}
		setParents(rezultat);
		setFullJobTitles(rezultat, employeeProjectDAO.getFullJobTitles());
		return new PagedPayloadResponse<>(null, ResponseCode.OK, rezultat.size(), 1, 1, rezultat.size(), rezultat);
	}

	private void setParents(List<EmployeeProjectTree> lista) {
		Map<String, String> mapaParents = new HashedMap();
		for (int i = 0; i < lista.size(); i++) {
			mapaParents.put(lista.get(i).getTitle(), lista.get(i).getKey());
		}

		for (int k = 0; k < lista.size(); k++) {
			if (lista.get(k).getTitle().equals("PRO_OWN")) {
				lista.get(k).setParent(mapaParents.get("PRO_MAN"));
			} else if (lista.get(k).getTitle().equals("Q_A_L") || lista.get(k).getTitle().equals("T_L")) {
				lista.get(k).setParent(mapaParents.get("PRO_OWN"));
			} else if (lista.get(k).getTitle().equals("PRO") || lista.get(k).getTitle().equals("JUN")) {
				lista.get(k).setParent(mapaParents.get("T_L"));
			} else if (lista.get(k).getTitle().equals("Q_A")) {
				lista.get(k).setParent(mapaParents.get("Q_A_L"));
			}
		}
	}

	private void setFullJobTitles(List<EmployeeProjectTree> lista, Map<String, String> mapa) {
		for (int k = 0; k < lista.size(); k++) {
			lista.get(k).setTitle(mapa.get(lista.get(k).getTitle()));
		}
	}

	@Override
	public PagedPayloadResponse<CalendarResponse> searchByActivityCodeandDate(final EntityRequest<String> activitCode,
			final EntityRequest<LocalDateTime> date) throws ApiException {

		List<EmployeeProjectEntity> listaProjekata = employeeProjectDAO.searchByActivityCode(activitCode.getEntity(),
				date.getEntity());

		List<CalendarResponse> listakalendara = new ArrayList<CalendarResponse>();
		for (int i = 0; i < listaProjekata.size(); i++) {
			CalendarResponse cr = new CalendarResponse();
			cr.setActivityCode(listaProjekata.get(i).getActivityCode());
			cr.setValidFrom(listaProjekata.get(i).getValidFrom());
			cr.setValidTo(listaProjekata.get(i).getValidTo());
			cr.setFirstName(listaProjekata.get(i).getEmployee().getFirstName());
			cr.setLastName(listaProjekata.get(i).getEmployee().getLastName());
			listakalendara.add(cr);

		}

		return new PagedPayloadResponse<>(null, ResponseCode.OK, listakalendara.size(), 1, 1, listakalendara.size(),
				listakalendara);
	}
}
