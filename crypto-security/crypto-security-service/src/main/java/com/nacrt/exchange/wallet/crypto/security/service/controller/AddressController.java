package com.nacrt.exchange.wallet.crypto.security.service.controller;

import com.nacrt.exchange.wallet.crypto.security.open.api.AddressApi;
import com.nacrt.exchange.wallet.crypto.security.open.model.request.NewAddressReq;
import com.nacrt.exchange.wallet.crypto.security.open.model.response.NewAddressRes;
import com.nacrt.exchange.wallet.crypto.security.service.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AddressController implements AddressApi {

    private final AddressService addressService;

    @Override
    public NewAddressRes newAddress(NewAddressReq body) {
        return addressService.newAddress(body);
    }
}
