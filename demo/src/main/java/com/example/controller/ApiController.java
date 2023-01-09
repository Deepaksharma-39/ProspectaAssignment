package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.models.Entries;
import com.example.models.Result;
import com.example.models.SampleData;

@RestController
public class ApiController {

	
	@Autowired
	RestTemplate restTemplate;
	
	
	@GetMapping("/entries/{category}")
	public ResponseEntity<List<Result>> getSampleResults(@PathVariable("category") String category){
		
//		Retrieving data from the website
		SampleData data= restTemplate.getForObject("https://api.publicapis.org/entries", SampleData.class);
		
//		Retrieving list of entries
		List<Entries> entries= data.getEntries();
		
//		mapping the entries into the custom format
		List<Result> results= entries.stream().filter(e->e.getCategory().equalsIgnoreCase(category))
							  .map(a->new Result(a.getApi(), a.getDescription())).toList();
		
//		returning entries with proper response code
		return new ResponseEntity<>(results,HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping
	public ResponseEntity<Entries> postSampleData(@RequestBody Entries entry){
		
		SampleData sampleData= restTemplate.getForObject("https://api.publicapis.org/entries", SampleData.class);
		
//		Retrieving data from the website
		List<Entries> entries=sampleData.getEntries();
		
//		adding the user entry into the list
		entries.add(entry);
//		here we can persist the data in database with the help of Spring DATA JPA,
		
		return new ResponseEntity<>(entry,HttpStatus.ACCEPTED);
	}
}
