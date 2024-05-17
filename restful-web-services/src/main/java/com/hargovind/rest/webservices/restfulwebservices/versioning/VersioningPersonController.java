package com.hargovind.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	//URI Versioning
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	//RequestParam Versioning
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonRequestParameter() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getSecondVersionOfPersonRequestParameter() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	//Headers Versioning
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonHeaders() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path = "/person/headerr", headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonHeaders() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	//Media type Versioning(Content Negotiation)
	@GetMapping(path = "/person/accept", produces = "application/vnc.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeaders() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path = "/person/accept", produces = "application/vnc.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeaders() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}
