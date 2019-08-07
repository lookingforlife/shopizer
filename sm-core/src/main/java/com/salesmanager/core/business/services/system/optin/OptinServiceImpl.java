package com.salesmanager.core.business.services.system.optin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.system.OptinRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.system.optin.Optin;
import com.salesmanager.core.model.system.optin.OptinType;

@Service
public class OptinServiceImpl extends SalesManagerEntityServiceImpl<Long, Optin> implements OptinService {

	private OptinRepository optinRepository;

	@Autowired
	public OptinServiceImpl(OptinRepository optinRepository) {
		super(optinRepository);
		this.optinRepository = optinRepository;
	}

	@Override
	public Optin getOptinByCode(MerchantStore store, String code) throws ServiceException {
		return optinRepository.findByMerchantAndCode(store.getId(), code);
	}

	@Override
	public Optin getOptinByMerchantAndType(MerchantStore store, OptinType type) throws ServiceException {
		return optinRepository.findByMerchantAndType(store.getId(), type);
	}

}
