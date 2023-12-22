package davis.christin.maintenancerequestserver.domain.maintenanceRequest.services;

import davis.christin.maintenancerequestserver.domain.core.exceptions.ResourceCreationException;
import davis.christin.maintenancerequestserver.domain.core.exceptions.ResourceNotFoundException;
import davis.christin.maintenancerequestserver.domain.maintenanceRequest.repos.MaintenanceRequestRepo;
import davis.christin.maintenancerequestserver.domain.maintenanceRequest.models.MaintenanceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//@Service annotation is often used to annotate classes that provide business services, perform business logic, or coordinate transactions.
@Service
public class MaintenanceRequestServiceImpl implements MaintenanceRequestService{
    private MaintenanceRequestRepo maintenanceRequestRepo;

    @Autowired
    public MaintenanceRequestServiceImpl(MaintenanceRequestRepo maintenanceRequestRepo) {
        this.maintenanceRequestRepo = maintenanceRequestRepo;
    }

    //this covers a situation if there is an error in the createMaintenanceRequest object. It will reject the request if an email is associated with an
    // existing maintenance request
    //*******Question: What if a person has multiple requests at different times and uses the same email address. How do they enter an additional request?******
    @Override
    public MaintenanceRequest create(MaintenanceRequest maintenanceRequest) throws ResourceCreationException {
        Optional<MaintenanceRequest> optional = maintenanceRequestRepo.findByEmail(maintenanceRequest.getEmail());
        if(optional.isPresent())
            throw new ResourceCreationException("Maintenance request with email exists: " + maintenanceRequest.getEmail());
        maintenanceRequest = maintenanceRequestRepo.save(maintenanceRequest);
        return maintenanceRequest;
    }

    //this method allows us to search for a request by the ID, and if no ID is found, it returns the ResourceNotFoundException
    @Override
    public MaintenanceRequest getById(Long id) throws ResourceNotFoundException {
        MaintenanceRequest maintenanceRequest = maintenanceRequestRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No Maintenance Request with id: " + id));
        return maintenanceRequest;
    }

    //this method allows us to search for a request by the email, and if no email is found, it returns the ResourceNotFoundException
    @Override
    public MaintenanceRequest getByEmail(String email) throws ResourceNotFoundException {
        MaintenanceRequest maintenanceRequest = maintenanceRequestRepo.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("No Maintenance Request with email: " + email));
        return maintenanceRequest;
    }

    //this method allows us to list all the maintenance requests
    @Override
    public List<MaintenanceRequest> getAll() {
        return maintenanceRequestRepo.findAll();
    }

    //this method allows us to update an existing request
    @Override
    public MaintenanceRequest update(Long id, MaintenanceRequest maintenanceRequestDetail) throws ResourceNotFoundException {
        MaintenanceRequest maintenanceRequest = getById(id);
        maintenanceRequest.setFirstName(maintenanceRequestDetail.getFirstName());
        maintenanceRequest.setLastName(maintenanceRequestDetail.getLastName());
        maintenanceRequest.setEmail(maintenanceRequestDetail.getEmail());
        maintenanceRequest.setAptNumber(maintenanceRequestDetail.getAptNumber());
        maintenanceRequest.setDescription(maintenanceRequestDetail.getDescription());
        maintenanceRequest = maintenanceRequestRepo.save(maintenanceRequest);
        return maintenanceRequest;
    }


    //this method allows us to delete an existing request
    @Override
    public void delete(Long id) {
        MaintenanceRequest maintenanceRequest = getById(id);
        maintenanceRequestRepo.delete(maintenanceRequest);
    }
}
