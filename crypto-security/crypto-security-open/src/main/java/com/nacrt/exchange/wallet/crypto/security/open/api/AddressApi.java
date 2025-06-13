package com.nacrt.exchange.wallet.crypto.security.open.api;


import com.nacrt.exchange.wallet.crypto.security.open.model.request.NewAddressReq;
import com.nacrt.exchange.wallet.crypto.security.open.model.response.NewAddressRes;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "地址api", description = "地址相关api")
public interface AddressApi {
    @PostMapping("/address/new")
    NewAddressRes newAddress(@RequestBody NewAddressReq body);
}
