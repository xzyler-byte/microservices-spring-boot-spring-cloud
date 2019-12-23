package com.xzyler.microservices.currencyconversionservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyConversion {

	private Long id;
	private String from;
	private String to;
	private int port;
	private Float conversionMultiple;
	private Float quantity;
	private Float totalCalculatedAmount;
}
