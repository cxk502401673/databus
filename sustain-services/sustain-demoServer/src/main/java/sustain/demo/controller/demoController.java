package sustain.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {


    @RequestMapping("demo")
    @ResponseBody
    public String demo(){
        return "hello";
    }
}