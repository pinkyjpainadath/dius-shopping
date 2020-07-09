package com.dius.shopping.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.management.openmbean.InvalidKeyException;

import com.dius.shopping.rules.BulkDiscountRule;
import com.dius.shopping.rules.BuyXGetYRule;
import com.dius.shopping.rules.FreeItemRule;
import com.dius.shopping.rules.IPricingRule;

public class PricingRuleMapping {

	/**
	 * Pricing rule loaded from properties file.
	 */
	public static Set<IPricingRule>  getPriceRuleMapping() {
		Set<IPricingRule> rules =  new HashSet<IPricingRule>();

		InputStream inputStream = null;
		try {
			Properties prop = new Properties();
			String propFileName = "discountConfig.properties";

			inputStream = CheckOutService.class.getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "'is not found");
			}

			for (Object rule : prop.keySet()) {
				String mapping=prop.getProperty(rule.toString());
				if(mapping!=null && mapping!="" ) {

					String[] mapArray = mapping.split(",");
					switch (mapArray[0]) {
					case "ByXGetYRule":
						if(mapArray.length!=3) {
							throw new InvalidKeyException();
						}else {
							rules.add(new BuyXGetYRule(rule.toString(),Integer.parseInt(mapArray[1]),Integer.parseInt(mapArray[2])));
						}
						break;
					case "BulkDiscountRule":
						if(mapArray.length!=3) {
							throw new InvalidKeyException();
						}else {
							rules.add(new BulkDiscountRule(rule.toString(),Integer.parseInt(mapArray[1]),new BigDecimal(mapArray[2])));
						}
						break;
					case "FreeItemRule":
						if(mapArray.length!=2) {
							throw new InvalidKeyException();
						}else {
							rules.add(new FreeItemRule(rule.toString(),mapArray[1]));
						}
						break;
					default:
						break;
					}

				}
			}

		} catch (Exception e) {
			System.out.println("Exception: Invalid input in pricing rule");
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rules;
	}


}
