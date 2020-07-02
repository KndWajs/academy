package info.wajs.academy.rest.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class SampleController {

    private Logger logger = LoggerFactory.getLogger(SampleController.class);

    @PostMapping("/do")
    public void doSomePost(@RequestBody MyObject input){
        logger.info("do some post - " + input);
    }

    @PostMapping("/do-with-answer")
    public MyObject doSomePostWithAnswer(@RequestBody MyObject input){

        return input;
    }

    @GetMapping("/error")
    public void throwSomeError(){
        throw new NullPointerException();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private MyError throwError(RuntimeException e, WebRequest request) {
        UUID uuid = UUID.randomUUID();

        return new MyError(request.getContextPath(), e.getLocalizedMessage(), "667", "localhost", uuid);
    }
}
