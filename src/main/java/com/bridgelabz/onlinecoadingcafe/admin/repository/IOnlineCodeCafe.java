package com.bridgelabz.onlinecoadingcafe.admin.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bridgelabz.onlinecoadingcafe.admin.model.Program;

public interface IOnlineCodeCafe extends ReactiveMongoRepository<Program, String> {

}
