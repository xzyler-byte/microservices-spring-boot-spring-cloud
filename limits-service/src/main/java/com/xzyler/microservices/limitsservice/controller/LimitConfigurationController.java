package com.xzyler.microservices.limitsservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xzyler.microservices.limitsservice.bean.LimitConfiguration;
import com.xzyler.microservices.limitsservice.config.Configuration;


@RestController
public class LimitConfigurationController {

	private Configuration configuration;

	public LimitConfigurationController(Configuration configuration) {
		this.configuration = configuration;
	}
	@RequestMapping("/limits")
	public LimitConfiguration retrieveLimitConfiguration() {
		System.out.println(configuration);
		LimitConfiguration limitConfiguration=new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
		return limitConfiguration;
	}

}
