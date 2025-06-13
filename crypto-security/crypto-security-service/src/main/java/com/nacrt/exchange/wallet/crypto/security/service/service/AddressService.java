package com.nacrt.exchange.wallet.crypto.security.service.service;

import com.nacrt.exchange.wallet.crypto.security.open.model.request.NewAddressReq;
import com.nacrt.exchange.wallet.crypto.security.open.model.response.NewAddressRes;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressService {

    public NewAddressRes newAddress(NewAddressReq body) {
        return new NewAddressRes(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }
}
