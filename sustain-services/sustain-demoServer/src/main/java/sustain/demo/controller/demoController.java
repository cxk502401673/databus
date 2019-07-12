package sustain.demo.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {


    @PostMapping("demo")
    @ResponseBody
    public Object demo(){
        System.out.println("hello ");
        return "hello";
    }
}
