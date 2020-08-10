package com.advantage.accountsoap.services;

import com.advantage.accountsoap.config.AccountConfiguration;
import com.advantage.accountsoap.dto.account.GetAccountConfigurationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountConfigurationService {
    @Autowired
    @Qualifier("accountConfiguration")
    public AccountConfiguration accountConfiguration;

    @Transactional(readOnly = true)
    public GetAccountConfigurationResponse getAllConfigurationParameters() {
        GetAccountConfigurationResponse getAccountConfigurationResponse = new GetAccountConfigurationResponse();
        getAccountConfigurationResponse = accountConfiguration.getAllConfigurationParameters();
        return getAccountConfigurationResponse;
    }

}
