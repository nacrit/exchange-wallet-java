package com.nacrt.exchange.wallet.crypto.security.service.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class WalletUtilTest {

    @Test
    void generateMnemonic() {
        Assertions.assertEquals(12, WalletUtil.generateMnemonic().size());
        Assertions.assertEquals(12, WalletUtil.generateMnemonic(12).size());
        Assertions.assertEquals(15, WalletUtil.generateMnemonic(15).size());
        Assertions.assertEquals(18, WalletUtil.generateMnemonic(18).size());
        Assertions.assertEquals(21, WalletUtil.generateMnemonic(21).size());
        Assertions.assertEquals(24, WalletUtil.generateMnemonic(24).size());
    }

    @Test
    void generateAddress() {
        for (int i = 0; i < 5; i++) {
            String address = WalletUtil.generateAddress(Arrays.asList("purpose","act","shallow","scorpion","resemble","denial","capable","desert","convince","empower","column","goddess"), i);
            Assertions.assertNotNull(address);
        }
        //seed = 0379cab169defd586d9313baa86b72654c4817d443535cee5dbf1befef15f6c63e49d5b3c66ac430b53d253eae47710c4b705234a725dab18e3e43803dc8bb5a
        //-------------------------------------------------------------
        //dkPriK = L23FA51C4rcNgoEq9VoKsV63xs4Tgif7Eygh2wCYQLnAx8mFtnNY
        //dkAddress =1KeQKrSbJdkhxrANJoaKswNQmLybYVYwof
        //dkPubK = 0339806d877818bea34c01db290868c2c5212d8150fb1a326ce9564da4f1d89a4a
        //dkPath = m/44H/0H/0H/0/0
        //-------------------------------------------------------------
        //15:14:37.039 [main] INFO org.bitcoinj.crypto.MnemonicCode -- PBKDF2 took 10 ms
        //-------------------------------------------------------------
        //dkPriK = L545CrGfhTGiGmoV63vKvUjZdjbMsZErxfN8A2Axk6kd3t9y6dfv
        //dkAddress =156ZdXD57mm9ushW48txM5fModFDwvhUcE
        //dkPubK = 02bf4a0c8ef42d8a7603b08084c6381d0914fdd07ab20754472c17d027bd42542e
        //dkPath = m/44H/0H/0H/0/1
        //-------------------------------------------------------------
        //15:14:37.043 [main] INFO org.bitcoinj.crypto.MnemonicCode -- PBKDF2 took 2 ms
        //-------------------------------------------------------------
        //dkPriK = L5G327zjec4RWvAWLpiafbEWFzYDw5dyBjRor6ZWHKK3ezK9bZpe
        //dkAddress =13iSC6HbNDVZ9hYSNTnpG24djHSCAF6yh9
        //dkPubK = 02b3fda55d8e34d1e287bce69721f0e20a584e9f09af6fb801ee5d2ad74bd3c601
        //dkPath = m/44H/0H/0H/0/2
        //-------------------------------------------------------------
        //15:14:37.048 [main] INFO org.bitcoinj.crypto.MnemonicCode -- PBKDF2 took 2 ms
        //-------------------------------------------------------------
        //dkPriK = L3fy3Xcv9DGFQmtFLQYQFvi6c8PdkACoBJYdgtpu6ffro4fuX9oE
        //dkAddress =1LoqdPaqnVuSTaYbxqxFoaQnPoo9cWBxzo
        //dkPubK = 03e6e780a3a07ec5edee97ab1e144f3bf9f3f538cab8add7b4805212e615a58517
        //dkPath = m/44H/0H/0H/0/3
        //-------------------------------------------------------------
        //15:14:37.052 [main] INFO org.bitcoinj.crypto.MnemonicCode -- PBKDF2 took 2 ms
        //-------------------------------------------------------------
        //dkPriK = L3UxLeTAitaeeHiwcq5g8efpsWR8Jw8FxxFgRVQZS8Ries4pFe4R
        //dkAddress =1Pr6UabztubWJs3a5HxxCgg3zr8AL4UFZm
        //dkPubK = 03ec0d48ad1a0b50ed29dc384e3f5666eb3dc2fa4ea5e716f74b227c16c506fb91
        //dkPath = m/44H/0H/0H/0/4
        //-------------------------------------------------------------


        // === 批量生成结果 (路径: M/44H/0H/0H/0) ===
        //
        //--- 钱包 #1 ---
        //私钥 (WIF): Ky7SLx6rQMJRMWdV1h8g1vWLj9ano8fLfqhnZVr7NKAnQEM5V7Wf
        //地址     : 19yVactF14xzKrEDV6FnxDgbVQwDpGZU5v
        //路径     : M/44H/0H/0H/0/0
        //
        //--- 钱包 #2 ---
        //私钥 (WIF): L22qaXUVwcWk51CjShqLW2FTBnRWsimist85MLGDJ1TpaFSHDSau
        //地址     : 13E7XsuS6EV3xiGYx6c663CcGNyGGzUyEc
        //路径     : m/44H/0H/0H/0/1
        //
        //--- 钱包 #3 ---
        //私钥 (WIF): KzSUah1FkBqyn7EUjraVWnCtYd9oDZjr77Ne5sHAdmbpzhnDyY3Y
        //地址     : 1JfiPytJGsJgonZK7zUg7159muHYiyR4rQ
        //路径     : m/44H/0H/0H/0/2
        //
        //--- 钱包 #4 ---
        //私钥 (WIF): L57Jbw12TWDTDbfdewugMw5JtCTy4tsYxjQDs3zQiBYaHmoYg1LZ
        //地址     : 1KjpJaepnjTV22xsAPjCyRYVbrJzuWAhm5
        //路径     : m/44H/0H/0H/0/3
        //
        //--- 钱包 #5 ---
        //私钥 (WIF): L5n7Vcy7KWrWpkXr5SqStyyNdHmpSFQ6TqDTUq7kAjm8SqtZ2oxQ
        //地址     : 1P8HAV26ovkypBpVCVjCr1oySDbvxT3NFt
        //路径     : m/44H/0H/0H/0/4


    }

}