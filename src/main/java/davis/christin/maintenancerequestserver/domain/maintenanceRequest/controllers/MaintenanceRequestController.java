package davis.christin.maintenancerequestserver.domain.maintenanceRequest.controllers;
//this whole file is our CRUD method.
import davis.christin.maintenancerequestserver.domain.maintenanceRequest.models.MaintenanceRequest;
import davis.christin.maintenancerequestserver.domain.maintenanceRequest.services.MaintenanceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//@RestController is a specialized version of controller, used to define RESTful web services. Combines @Controller and @ResponseBody
@RestController
//@CrosOrigin supports secure CrossOrigin requests and data transfers between browsers and servers
@CrossOrigin
//@RequestMapping -- Maps HTTP requests to handler methods in a controller.
//Can be applied at the class level and/or method level. It has various attributes to match by URL, HTTP method, request parameters, headers, and media types
@RequestMapping("/api/v1/maintenance-request")
public class MaintenanceRequestController {
    //used to utilize the interface MaintenanceRequestService so we can use the methods in that file
    private MaintenanceRequestService maintenanceRequestService;
    //@Autowired -- Marks a constructor, field, or setter method to automatically inject dependencies. Reduces the need for explicit bean wiring.
    @Autowired
    public MaintenanceRequestController(MaintenanceRequestService maintenanceRequestService) {
            this.maintenanceRequestService = maintenanceRequestService;
    }
    //@GetMapping -- maps the HTTP GET request to specific handler methods in a controller.
    @GetMapping
    //to set the body, status, and headers of an HTTP response using ResponseEntity
    public ResponseEntity<List<MaintenanceRequest>> getAll(){
        List<MaintenanceRequest> maintenanceRequests = maintenanceRequestService.getAll();
        //This line of code is telling the computer to send back a response saying that everything is okay
        // and include a list of maintenance requests.
        return new ResponseEntity<>(maintenanceRequests, HttpStatus.OK);
    }

    //@PostMapping -- maps the HTTP POST request to specific handler methods in a controller.
    @PostMapping
    //@RequestBody maps the HTTP REQUEST body to a transfer or domain object, enabling automatic deserialization
    // (which is the process of turning it from computer language to human-readable language)
    public ResponseEntity<MaintenanceRequest> create(@RequestBody MaintenanceRequest maintenanceRequest){
        maintenanceRequest = maintenanceRequestService.create(maintenanceRequest);
        //HttpStatus.CREATED is a 201 code that indicates the server was successfully processed the request, the new resource has been created and is now ready for interaction
        return new ResponseEntity<>(maintenanceRequest, HttpStatus.CREATED);
    }

    //@GetMapping("{id}") indicates that the annotated method should handle GET requests where the URL path includes a variable placeholder {id}.
    // This variable placeholder is typically a path variable, and the value specified in the URL will be passed to the corresponding method parameter.
    @GetMapping("{id}")
    //the @PathVariable annotation can be used to handle template variables in the request URI mapping, and set them as method parameters.
    public ResponseEntity<MaintenanceRequest> getById(@PathVariable("id") Long id){
        MaintenanceRequest maintenanceRequest = maintenanceRequestService.getById(id);
        return new ResponseEntity<>(maintenanceRequest, HttpStatus.OK);
    }

    //lookup is used to find email, not dynamic because this word does not change. You would type ?email= the email address to use this method.
    @GetMapping("lookup")
    public ResponseEntity<MaintenanceRequest> getByEmail(@RequestParam String email){
        MaintenanceRequest maintenanceRequest = maintenanceRequestService.getByEmail(email);
        return new ResponseEntity<>(maintenanceRequest, HttpStatus.OK);
    }

    // The PUT method is used to update a resource or create a new resource if it doesn't exist at a specified URI (Uniform Resource Identifier).
    @PutMapping("{id}")
    public ResponseEntity<MaintenanceRequest> update(@PathVariable("id") Long id, @RequestBody MaintenanceRequest maintenanceRequestDetail){
        maintenanceRequestDetail = maintenanceRequestService.update(id, maintenanceRequestDetail);
        //HttpStatus.ACCEPTED represents the HTTP status code 202 Accepted
        return new ResponseEntity<>(maintenanceRequestDetail, HttpStatus.ACCEPTED);
    }

    //@DeleteMapping annotation is used to map HTTP DELETE requests to a specific handler method. The DELETE method is
    // used to request the removal of a resource identified by a given URI.
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        maintenanceRequestService.delete(id);
        //HttpStatus.NO_CONTENT represents the HTTP status code 204 No Content
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
