package com.bridgelabz.onlinecoadingcafe.admin.service;

import java.io.IOException;

import com.bridgelabz.onlinecoadingcafe.admin.model.Program;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProgramService {
	public Mono<Program> sendCode(Program program);

	public Mono<Program> getCode(String id);
	public String runProgram(Program program) throws IOException, InterruptedException;
	
}
