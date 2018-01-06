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
	
	/*@SuppressWarnings("null")
	public String pythonJavaCompile(File javaFile) throws IOException {
		Process process = Runtime.getRuntime().exec("python JavaCompile.py "+javaFile);
		 String errors = null;
		try( BufferedReader stdError = new BufferedReader(new 
                InputStreamReader(process.getErrorStream()));) {
			
		
		BufferedReader stdInput = new BufferedReader(new 
                InputStreamReader(process.getInputStream()));

          
       
          
           // read any errors from the attempted command
           logger.debug("Here is the standard error of the command (if any):\n");
           while ((errors = stdError.readLine()) != null) {
               logger.debug("Errors are : \n",errors);
           }
           if(errors.isEmpty()) {
        	   return "compilation sucess";
           }

		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return errors;
	}*/
	
	@SuppressWarnings("null")
	public String pythonJavaCompile(File javaFile) throws IOException, InterruptedException {
		ProcessBuilder processBuilder=new ProcessBuilder("python","/home/bridgeit/Project/OnlineCoadingCafe/src/main/resources/python/JavaCompile.py",""+javaFile);
		processBuilder.redirectErrorStream(true);
		logger.debug("inside java compile code");

		Process process = processBuilder.start();  

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String data;
		while ((data = bufferedReader.readLine()) != null) {
		    logger.debug(data);
		}
		process.waitFor();

		bufferedReader.close();
		return data;
	}
	
	@Override
	public String runProgram(Program program) throws IOException, InterruptedException {
		
		BufferedWriter bufferedWriter = null;
		FileWriter fileWriter = null;
		File file=null;

		try {
			
			file=new File("/home/bridgeit/Project/OnlineCoadingCafe/src/main/resources/python/"+program.getTitle()+".java");
			
			if(file.createNewFile()) {
				
				logger.debug("@@@@@@@@@@@File is created ");
			
			} else {
				
				logger.debug("@@@@@@@@@@@@@@@@File is already exist ");

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

	/*private String pythonJavaRun(File javaFile) throws IOException {
		Process process = Runtime.getRuntime().exec("python JavaExec.py "+javaFile);
		  String output = null;
		try(BufferedReader stdOutput = new BufferedReader(new 
                InputStreamReader(process.getInputStream()));

           BufferedReader stdError = new BufferedReader(new 
                InputStreamReader(process.getErrorStream()));) {
       
         
           // read any output from the attempted command
           logger.debug("Here is the standard output of the command (if any):\n");
           while ((output = stdOutput.readLine()) != null) {
               logger.error("output are : \n",output);
           }
           String errors;
           // read any errors from the attempted command
           logger.debug("Here is the standard error of the command (if any):\n");
           while ((errors = stdError.readLine()) != null) {
               logger.debug("Errors are : \n",errors);
           }
           if(!errors.isEmpty()) {
        	   return errors;
           }
           if(output.isEmpty()) {
        	   return output;
           }
		} catch(Exception ex) {
			ex.printStackTrace();
		}
           return output;	
		
	}*/
	private String pythonJavaRun(File javaFile) throws IOException, InterruptedException {
		ProcessBuilder processBuilder=new ProcessBuilder("python","/home/bridgeit/Project/OnlineCoadingCafe/src/main/resources/python/JavaExec.py",""+javaFile);
		processBuilder.redirectErrorStream(true);
		logger.debug("inside java run code");
		Process process = processBuilder.start();  

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String data;
		String dataT="";
		while ((data = bufferedReader.readLine()) != null) {
			logger.debug(dataT=dataT+data);
		}
		process.waitFor();
		logger.debug(dataT);

		bufferedReader.close();

		return dataT;
	}

}
