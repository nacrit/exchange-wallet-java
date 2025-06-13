package com.nacrt.exchange.wallet.crypto.security.service.service;

import com.nacrt.exchange.wallet.crypto.security.dao.model.KeyManagement;
import com.nacrt.exchange.wallet.crypto.security.dao.model.MasterKey;
import com.nacrt.exchange.wallet.crypto.security.dao.repository.KeyManagementRepository;
import com.nacrt.exchange.wallet.crypto.security.dao.repository.MasterKeyRepository;
import com.nacrt.exchange.wallet.crypto.security.open.model.request.NewAddressReq;
import com.nacrt.exchange.wallet.crypto.security.open.model.response.NewAddressRes;
import com.nacrt.exchange.wallet.crypto.security.service.util.WalletUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.web3j.utils.Strings;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressService {
    private final KeyManagementRepository keyManagementRepository;
    private final MasterKeyRepository masterKeyRepository;

    public NewAddressRes newAddress(NewAddressReq body) {
        String chain = body.getChain();
        MasterKey masterKey = masterKeyRepository.findByChainIdAndIsActiveTrue(chain).orElse(null);
        if (masterKey == null) {
            masterKey = new MasterKey();
            masterKey.setChainId(chain);
            String mnemonic = Strings.join(WalletUtil.generateMnemonic(12), " ");
            masterKey.setEncryptedMnemonic(mnemonic);
            masterKey.setEncryptedMasterPrivateKey(mnemonic);
            masterKey.setCreatedBy("nacrt");
            masterKey.setIsActive(true);
            masterKeyRepository.save(masterKey);
        }
        String mnemonicStr = masterKey.getEncryptedMnemonic();
        List<String> mnemonic = Arrays.stream(mnemonicStr.split(" ")).toList();
        String address = WalletUtil.generateAddress(mnemonic, Integer.parseInt(body.getChildNumber()));
        KeyManagement keyManagement = new KeyManagement();
        keyManagement.setAddress(address);
        keyManagement.setPublicKey(address);
        keyManagement.setChainId(chain);
        keyManagement.setEncryptedPrivateKey(mnemonicStr);
        keyManagement.setMasterKey(masterKey);
        keyManagement.setCreatedBy("nacrt");
        keyManagement.setUpdatedBy("nacrt");
        keyManagement.setIsActive(true);
        keyManagement.setDerivationPath(body.getChildNumber());
        keyManagementRepository.save(keyManagement);
        return new NewAddressRes(address, address + body.getChain());
    }
}
