package davis.christin.maintenancerequestserver.domain.core.exceptions;

//http status is an enum class that allows us to retrieve http status codes. Some codes consist of 202, 400 (for bad requests), etc.
import org.springframework.http.HttpStatus;
//used to import the ResponseStatus annotation
import org.springframework.web.bind.annotation.ResponseStatus;
//used to specify the response status of a controller method. Two interchangeable arguments for code and value.
//HttpStatus.NOT_FOUND is a 404 code, means not found
@ResponseStatus(value = HttpStatus.NOT_FOUND)
//Runtime Exception is an unchecked exception is something that has gone wrong with the program and is unrecoverable
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
