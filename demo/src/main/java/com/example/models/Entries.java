package com.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Entries {

	@JsonProperty(value = "API")
	private String api;
	
	@JsonProperty(value = "Description")
	private String description;
	
	@JsonProperty(value = "Auth")
	private String auth;
	
	@JsonProperty(value = "HTTPS")
	private boolean https;
	
	@JsonProperty(value = "Cors")
	private String cors;
	
	@JsonProperty(value = "Link")
	private String link;
	
	@JsonProperty(value = "Category")
	private String category;
	
}
