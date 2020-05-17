/**
 * 
 */
package com.pri.microservices.rest.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coder
 *
 */

@RestController
public class HelloWorldController {

	@GetMapping(path="/helloworld")
	public String getString() {
		return "Hellow WOrld!";
	}
	
	@GetMapping(path="/helloworld-bean")
	public HWBean getStringBean() {
		return new HWBean("Hellow from Bean!!");
	}
	
	@GetMapping(path="/helloworld-path/{input}")
	public HWBean getStringPathV(@PathVariable String input) {
		return new HWBean("Hellow with user passed Input, "+input);
	}
}
