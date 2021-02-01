package com.revature.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true", methods = { RequestMethod.GET })
public class PingController {

	@GetMapping
	public ResponseEntity<String> ping(HttpSession session){
		return ResponseEntity.ok().build();
	}
}
