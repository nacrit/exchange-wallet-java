package com.nacrt.exchange.wallet.crypto.security.service.util;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.base.LegacyAddress;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.crypto.*;
import org.bitcoinj.params.MainNetParams;

import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class WalletUtil {

    private static final NetworkParameters MAIN_NET = MainNetParams.get();
    // 设置路径：m/44'/0'/0'/0
    private static final ImmutableList<ChildNumber> M440000 = ImmutableList.of(
            new ChildNumber(44, true), new ChildNumber(0, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);


    public static String generateAddress(List<String> mnemonic, int childNumber) {
        byte[] seed = MnemonicCode.toSeed(mnemonic, "");

//        System.out.println("seed = " + Hex.toHexString(seed));
        // 生成主密钥
        DeterministicKey masterKey = HDKeyDerivation.createMasterPrivateKey(seed);
//        System.out.println("masterKey = " + masterKey.getPrivateKeyAsWiF(MAIN_NET));
//        System.out.println("masterAddress =" + LegacyAddress.fromKey(MAIN_NET, masterKey));
//        System.out.println("masterKey.getPath() = " + masterKey.getPathAsString()); // 打印默认path
//        return LegacyAddress.fromKey(MAIN_NET, masterKey).toBase58();
        return LegacyAddress.fromKey(MAIN_NET, derivedAddresses(masterKey, childNumber)).toBase58();
    }

    // hd派生子私钥地址
    private static DeterministicKey derivedAddresses(DeterministicKey masterKey, int childNumber) {
        System.out.println("-------------------------------------------------------------");
        // 路径：m/44'/0'/0'/0/xx
        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(masterKey);
        DeterministicKey dk = deterministicHierarchy.deriveChild(M440000, false, true, new ChildNumber(childNumber));

        System.out.println("dkPriK = " + dk.getPrivateKeyAsWiF(MAIN_NET));
        System.out.println("dkAddress =" + LegacyAddress.fromKey(MAIN_NET, dk));
        System.out.println("dkPubK = " + dk.getPublicKeyAsHex());
        System.out.println("dkPath = " + dk.getPathAsString());
        System.out.println("-------------------------------------------------------------");
        return dk;
    }

    /**
     * 默认无参生成12位助记词
     */
    public static List<String> generateMnemonic() {
        return generateMnemonic(12);
    }

    /**
     * 生成助记词 <br/>
     * #### **生成流程**： <br/>
     * 1. **生成熵**：创建128/160/192/224/256位随机熵（如128位→12个词），单词数=`3*熵位数/32`。 <br/>
     * 2. **计算校验和**：取熵的SHA256哈希的前几位（熵长度/32），如128位熵校验和=128/32=4位。 <br/>
     * 3. **组合为助记词**：`(熵+校验和)` 每11位分割 → 映射为词库中的单词（如`11101010110`→"apple"）。 <br/>
     * 4. **生成种子**：`PBKDF2(助记词, salt="mnemonic"+密码, 2048次哈希, 输出512位种子)`。 <br/>
     * 5. **创建钱包**：种子按BIP32生成主密钥，按BIP44路径派生地址。 <br/>
     * > `随机熵(128位) → BIP39 → 助记词(12个单词) + 密码 → 种子(512位) → BIP32主密钥 → 按BIP44路径派生 → 地址(如 m/44'/0'/0'/0/0)` <br/>
     */
    public static List<String> generateMnemonic(int mnemonicLength) {
//        return Arrays.asList("purpose","act","shallow","scorpion","resemble","denial","capable","desert","convince","empower","column","goddess");
        // 熵位数: 随机数2进制长度
        // 校验和: 熵位数 / 32
        // 单词总数: 11位 2进制最大值
        // 助记词2进制长度：熵位数 + 熵位数 / 32
        // 单词数 = 助记词2进制长度 / 11 = (熵位数 + 熵位数 / 32) / 11 = 33 * 熵位数 / 32 / 11 = 3 * 熵位数 / 32
        // 单词数 = 3 * 熵位数 / 32
        // mnemonicLength = 3 * byteLen * 8 / 32 = 3 * byteLen / 4
        // byteLen = mnemonicLength * 4 / 3
        if (mnemonicLength % 3 != 0 && mnemonicLength >= 12 && mnemonicLength <= 24) {
            throw new IllegalArgumentException("mnemonic length must be multiple of 3 and mnemonic length must be between 12 and 24");
        }
        int byteLen = mnemonicLength * 4 / 3;
        byte[] entropy = new byte[byteLen];
        new SecureRandom().nextBytes(entropy);
        ThreadLocalRandom.current().nextBytes(entropy);
        //熵转化为助记词
        try {
            return new MnemonicCode().toMnemonic(entropy);
        } catch (Exception e) {
            log.warn("Generate Mnemonic Error ..", e);
            throw new RuntimeException(e);
        }
    }

}
