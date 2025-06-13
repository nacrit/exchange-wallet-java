package com.nacrt.exchange.wallet.crypto.security.dao.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "key_management")
public class KeyManagement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "master_key_id", nullable = false)
    private MasterKey masterKey;  // 关联主密钥

    @Column(nullable = false, length = 128)
    private String derivationPath;  // 派生路径

    @Column(nullable = false, columnDefinition = "TEXT")
    private String encryptedPrivateKey;  // 加密后的私钥

    @Column(nullable = false, length = 128)
    private String address;  // 钱包地址

    @Column(nullable = false, columnDefinition = "TEXT")
    private String publicKey;  // 公钥

    @Column(nullable = false, length = 32)
    private String chainId;  // 链标识

    @Column(nullable = false)
    private String createdBy;  // 创建人

    @Column(nullable = false)
    private String updatedBy;  // 更新人

    @Column(length = 512)
    private String description;  // 描述信息

    @Column(nullable = false)
    private Boolean isActive;  // 是否激活

    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;
}