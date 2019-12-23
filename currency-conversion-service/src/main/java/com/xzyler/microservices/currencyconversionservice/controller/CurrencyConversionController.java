package com.xzyler.microservices.currencyconversionservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xzyler.microservices.currencyconversionservice.bean.CurrencyConversion;
import com.xzyler.microservices.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/{quantity}")
	public CurrencyConversion convertCurrency(@PathVariable("from") String from, @PathVariable("to") String to,
			@PathVariable("quantity") Float quantity) {
		CurrencyConversion response = proxy.retrieveExchangeValue(from, to);
		Float localConversionMultiple = response.getConversionMultiple();
		Float calculatedAmount = localConversionMultiple * quantity;
		logger.info("{}", response);
		return new CurrencyConversion(response.getId(), from, to, response.getPort(), quantity,
				response.getConversionMultiple(), calculatedAmount);
	}
}
