package com.user.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "WashPacks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WashPacks {
	 	@Id
	    String id;
	    String name;
	    int cost;
	    String description;

}
