package davis.christin.maintenancerequestserver.domain.maintenanceRequest.services;
//This interface lists the methods that are implemented in the MaintenanceRequestController
import java.util.List;
import davis.christin.maintenancerequestserver.domain.core.exceptions.ResourceCreationException;
import davis.christin.maintenancerequestserver.domain.core.exceptions.ResourceNotFoundException;
import davis.christin.maintenancerequestserver.domain.maintenanceRequest.models.MaintenanceRequest;

public interface MaintenanceRequestService {

    MaintenanceRequest create(MaintenanceRequest maintenanceRequest) throws ResourceCreationException;
    MaintenanceRequest getById(Long id) throws ResourceNotFoundException;
    MaintenanceRequest getByEmail(String email) throws ResourceNotFoundException;
    List<MaintenanceRequest> getAll();
    MaintenanceRequest update(Long id, MaintenanceRequest maintenanceRequestDetail) throws ResourceNotFoundException;
    void delete(Long id);
}