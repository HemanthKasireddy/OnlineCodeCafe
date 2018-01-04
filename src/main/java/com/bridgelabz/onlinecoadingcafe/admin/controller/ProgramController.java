package com.bridgelabz.onlinecoadingcafe.admin.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.onlinecoadingcafe.admin.model.Program;
import com.bridgelabz.onlinecoadingcafe.admin.service.IProgramService;

import ch.qos.logback.core.net.SyslogOutputStream;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class ProgramController {
	@Autowired
	private IProgramService programService;
	
	@RequestMapping(value="/code",method=RequestMethod.POST)
	/*
	public Mono<Program> sendCode(@RequestParam() String code, 
			@RequestParam() String title,
			@RequestParam() String description
			) {
		Program program=new Program(code,description,title);
	}
	*/	
	public Mono<Program> sendCode(Program program) 
	{
		System.out.println("data of program"+program.getCode());
		System.out.println("Hello ");
		
		Mono<Program> monoProgram=programService.sendCode(program);
		return monoProgram;
	}
	@RequestMapping(value="/getCode/{id}",method=RequestMethod.GET)
	public Mono<Program> getCode(@PathVariable String id) {
		
		Mono<Program> monoProgram = programService.getCode(id);
		
		return monoProgram;
	
	}
	@RequestMapping(value="/runCode/{id}",method=RequestMethod.GET)
	public String runCode(@PathVariable String id) throws IOException {
		Mono<Program> monoProgram = programService.getCode(id);
		String status = programService.runProgram(monoProgram.block());
		
		return status;
	
	}
}
