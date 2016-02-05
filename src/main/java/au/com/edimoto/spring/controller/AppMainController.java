package au.com.edimoto.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppMainController {

	@RequestMapping (value = "/")
	public String main(){
		return "index";
	}
}
