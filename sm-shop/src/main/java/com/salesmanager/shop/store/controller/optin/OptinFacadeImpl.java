package com.salesmanager.shop.store.controller.optin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.system.optin.OptinService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.system.optin.Optin;
import com.salesmanager.shop.mapper.optin.PersistableOptinMapper;
import com.salesmanager.shop.mapper.optin.ReadableOptinMapper;
import com.salesmanager.shop.model.system.PersistableOptin;
import com.salesmanager.shop.model.system.ReadableOptin;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;

@Service
public class OptinFacadeImpl implements OptinFacade {

	@Autowired
	private OptinService optinService;

	@Autowired
	private ReadableOptinMapper readableOptinConverter;
	@Autowired
	private PersistableOptinMapper persistableOptinConverter;

	@Override
	public ReadableOptin create(PersistableOptin persistableOptin, MerchantStore merchantStore, Language language) {
		Optin optinEntity = persistableOptinConverter.convert(persistableOptin, merchantStore, language);
		Optin savedOptinEntity = createOptin(optinEntity);
		return readableOptinConverter.convert(savedOptinEntity, merchantStore, language);
	}

	private Optin createOptin(Optin optinEntity) {
		try {
			optinService.create(optinEntity);
			return optinEntity;
		} catch (ServiceException e) {
			throw new ServiceRuntimeException(e);
		}

	}
}
