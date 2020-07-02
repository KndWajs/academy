package info.wajs.academy.rest.validation;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ControllerWithValidation {

    @PostMapping("/do-with-answer")
    public MyObjectWithValidation doSomePostWithAnswer(@RequestBody @Valid MyObjectWithValidation input){

        return input;
    }
}
