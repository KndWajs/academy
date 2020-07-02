package rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.UUID;

@RestController
public class ControllerWithExceptionHandler {

    @GetMapping("/error")
    public void throwSomeError(){
        throw new NullPointerException("some error");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private MyError throwError(RuntimeException e, WebRequest request) {
        UUID uuid = UUID.randomUUID();

        return new MyError(request.getContextPath(), e.getLocalizedMessage(), "667", "localhost", uuid);
    }
}
