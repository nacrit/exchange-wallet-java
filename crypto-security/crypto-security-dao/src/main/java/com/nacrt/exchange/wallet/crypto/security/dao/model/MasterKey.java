package com.nacrt.exchange.wallet.crypto.security.dao.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "master_key")
public class MasterKey {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String chainId;  // 链标识，如 ETH, BTC, TRX 等

    @Column(nullable = false, columnDefinition = "TEXT")
    private String encryptedMnemonic;  // 加密后的助记词

    @Column(nullable = false, columnDefinition = "TEXT")
    private String encryptedMasterPrivateKey;  // 加密后的主私钥

    @Column(nullable = false)
    private String createdBy;  // 创建人

    @Column(length = 512)
    private String description;  // 描述信息

    @Column(nullable = false)
    private Boolean isActive;  // 是否激活

    @CreationTimestamp
    private LocalDateTime createTime;
} 