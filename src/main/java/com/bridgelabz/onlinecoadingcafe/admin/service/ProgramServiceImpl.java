package com.bridgelabz.onlinecoadingcafe.admin.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.onlinecoadingcafe.admin.model.Program;
import com.bridgelabz.onlinecoadingcafe.admin.repository.IOnlineCodeCafe;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class ProgramServiceImpl implements IProgramService {
	private  Logger logger = LogManager.getLogger(ProgramServiceImpl.class);	
	@Autowired
	private IOnlineCodeCafe onlineCodeCafe;
	
	@Override
	public Mono<Program> sendCode(Program program) {

		Mono<Program> monoProgram=onlineCodeCafe.save(program);
		return monoProgram;
	}

	@Override
	public Mono<Program> getCode(String id) {
		Mono<Program> monoProgram=onlineCodeCafe.findById(id);
		return monoProgram ;
		
	}
	
	@SuppressWarnings("null")
	public String pythonJavaCompile(File javaFile) throws IOException {
		Process process = Runtime.getRuntime().exec("python JavaCompile.py "+javaFile);
		/*BufferedReader stdInput = new BufferedReader(new 
                InputStreamReader(process.getInputStream()));*/

           BufferedReader stdError = new BufferedReader(new 
                InputStreamReader(process.getErrorStream()));
       
           String errors=null;
           // read any errors from the attempted command
           logger.debug("Here is the standard error of the command (if any):\n");
           while ((errors = stdError.readLine()) != null) {
               logger.error("Errors are : \n",errors);
           }
          /* if(errors.isEmpty()) {
        	   return "compilation sucess";
           }*/
		return errors;
	}
	
	@Override
	public String runProgram(Program program) throws IOException {
		
		BufferedWriter bufferedWriter = null;
		FileWriter fileWriter = null;
		File file=null;

		try {
			
			file=new File("/home/bridgeit/Project/OnlineCoadingCafe/src/main/resources/python/"+program.getTitle()+".java");
			
			if(file.createNewFile()) {
				
				logger.debug("File is created ");
			
			} else {
				
				logger.debug("File is already exist ");

			}
			
			if(!file.exists()) {
				
				logger.error("file is not exist ");
				return null;
			
			}
			
			if(!file.canWrite()) {
				
				logger.error("File can't have  write permission");
				return null;
			
			}
			
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(program.getCode());
			
			logger.debug(" file write operation completed");
			String status=pythonJavaCompile(file);
			if(status!=null) {
				return status;
			}
			String output=pythonJavaRun(file);
		 return output;
		} finally {

				if (bufferedWriter != null) {
					
					bufferedWriter.close();

				}

				if (fileWriter != null) {
					
					fileWriter.close();

				}
		}
	}

	private String pythonJavaRun(File javaFile) throws IOException {
		Process process = Runtime.getRuntime().exec("python JavaExec.py "+javaFile);
		BufferedReader stdOutput = new BufferedReader(new 
                InputStreamReader(process.getInputStream()));

           BufferedReader stdError = new BufferedReader(new 
                InputStreamReader(process.getErrorStream()));
       
           String output=null;
           // read any output from the attempted command
           logger.debug("Here is the standard output of the command (if any):\n");
           while ((output = stdOutput.readLine()) != null) {
               logger.error("output are : \n",output);
           }
           String errors=null;
           // read any errors from the attempted command
           logger.debug("Here is the standard error of the command (if any):\n");
           while ((errors = stdError.readLine()) != null) {
               logger.error("Errors are : \n",errors);
           }
           /*if(!errors.isEmpty()) {
        	   return errors;
           }
           if(output.isEmpty()) {
        	   return output;
           }*/
           return output+errors;	
		
	}

}
