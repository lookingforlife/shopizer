package com.salesmanager.shop.store.api.v1.marketplace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.marketplace.ReadableMarketPlace;
import com.salesmanager.shop.store.controller.marketplace.facade.MarketPlaceFacade;
import com.salesmanager.shop.store.controller.store.facade.StoreFacade;
import com.salesmanager.shop.utils.LanguageUtils;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
public class MarketPlaceApi {

	@Autowired
	private MarketPlaceFacade marketPlaceFacade;

	@Autowired
	private StoreFacade storeFacade;

	@Autowired
	private LanguageUtils languageUtils;

	/**
	 * Get a marketplace from storeCode returns market place details and merchant
	 * store
	 */
	@GetMapping("/private/marketplace/{store}")
	@ApiOperation(httpMethod = "GET", value = "Get market place meta-data", notes = "", produces = "application/json", response = ReadableMarketPlace.class)
	public ReadableMarketPlace marketPlace(@PathVariable String store,
			@RequestParam(value = "lang", required = false) String lang) {

		Language language = languageUtils.getServiceLanguage(lang);
		return marketPlaceFacade.get(store, language);
	}
}
