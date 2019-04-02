package springboot.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.example.annotation.ApiVersion;

/**
 * 版本控制验证1
 * @author lich
 *
 */
@RestController
@ApiVersion(1)
@RequestMapping("/{version}/hello")
public class ApiTest1Controller {
	
	@RequestMapping("/world")
	@ResponseBody
    public String helloWorld(){
        System.out.println("版本是1的接口");
        return "hello,world .version is 1";
    }

}
