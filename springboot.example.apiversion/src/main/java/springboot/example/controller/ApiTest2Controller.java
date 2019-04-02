package springboot.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.example.annotation.ApiVersion;

/**
 * 版本控制验证2
 * @author lich
 *
 */
@RestController
@ApiVersion(2)
@RequestMapping("/{version}/hello")
public class ApiTest2Controller {
	
	@RequestMapping("/world")
	@ResponseBody
	@ApiVersion(3)
    public String helloWorld3(){
        System.out.println("版本是3的接口");
        return "hello,world .version is 3";
    }
	
	@RequestMapping("/world")
	@ResponseBody
    public String helloWorld(){
		System.out.println("版本是2的接口");
        return "hello,world .version is 2";
    }

}
