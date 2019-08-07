package com.salesmanager.test.utils;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.currency.CurrencyService;
import com.salesmanager.core.business.utils.CacheUtils;
import com.salesmanager.core.model.common.Address;
import com.salesmanager.core.model.reference.currency.Currency;
import com.salesmanager.core.modules.utils.Encryption;
import com.salesmanager.core.modules.utils.GeoLocation;
import com.salesmanager.test.configuration.ConfigurationTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { ConfigurationTest.class })
@Ignore
public class UtilsTestCase {

	@Autowired
	private CountryService countryService;

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private Encryption encryption;

	@Autowired
	private CacheUtils cache;

	@Autowired
	private GeoLocation geoLoaction;

	// @Test
	@Ignore
	public void testCache() throws Exception {

		@SuppressWarnings("rawtypes")
		List countries = countryService.list();

		// CacheUtils cache = CacheUtils.getInstance();
		cache.putInCache(countries, "COUNTRIES");

		@SuppressWarnings("rawtypes")
		List objects = (List) cache.getFromCache("COUNTRIES");

		Assert.assertNotNull(objects);

	}

	// @Test
	@Ignore
	public void testCurrency() throws Exception {

		Currency currency = currencyService.getByCode("BGN");

		java.util.Currency c = currency.getCurrency();

		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
		numberFormat.setCurrency(c);

		System.out.println("Done");

	}

	@Test
	public void testGeoLocation() throws Exception {

		Address address = geoLoaction.getAddress("96.21.132.0");
		if (address != null) {
			System.out.println(address.getCountry());
		}

	}

}
