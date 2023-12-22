package davis.christin.maintenancerequestserver.domain.core.exceptions;

//http status is an enum class that allows us to retrieve http status codes. Some codes consist of 202, 400 (for bad requests), etc.
import org.springframework.http.HttpStatus;
//used to import the ResponseStatus annotation
import org.springframework.web.bind.annotation.ResponseStatus;
//used to specify the response status of a controller method. Two interchangeable arguments for code and value.
//HttpStatus.conflict is a 409 code, means conflict
@ResponseStatus(value = HttpStatus.CONFLICT)
//Runtime Exception is an unchecked exception is something that has gone wrong with the program and is unrecoverable
public class ResourceCreationException extends RuntimeException{
    public ResourceCreationException(String message){
        super(message);
    }
}