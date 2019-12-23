package com.xzyler.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xzyler.microservices.currencyexchangeservice.bean.ExchangeValue;
import com.xzyler.microservices.currencyexchangeservice.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;
	
	@Autowired
	private Environment environment;


	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	@HystrixCommand(fallbackMethod = "fallBackMethodForCurrencyExchange")
	public ExchangeValue retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to) {
		ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		logger.info("{}", exchangeValue);
		return exchangeValue;
	}
	public ExchangeValue fallBackMethodForCurrencyExchange(@PathVariable("from") String from, @PathVariable("to") String to)
	{
		ExchangeValue exchangeValue=new ExchangeValue(10001L, from, to, 0, new Float(1));
		return exchangeValue;
	}
}
