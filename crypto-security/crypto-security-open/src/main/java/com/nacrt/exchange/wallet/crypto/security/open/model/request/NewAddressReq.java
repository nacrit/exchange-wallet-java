package com.nacrt.exchange.wallet.crypto.security.open.model.request;

import lombok.Data;

@Data
public class NewAddressReq {
    private String chain;
    private String childNumber;
}
