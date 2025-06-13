package com.nacrt.exchange.wallet.crypto.security.dao.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "signature_operation")
public class SignatureOperation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "key_management_id", nullable = false)
    private KeyManagement keyManagement;  // 关联密钥管理

    @Column(nullable = false, length = 64)
    private String operationType;  // 操作类型：SIGN_TRANSACTION, SIGN_MESSAGE 等

    @Column(nullable = false, columnDefinition = "TEXT")
    private String rawData;  // 原始数据

    @Column(columnDefinition = "TEXT")
    private String signature;  // 签名结果

    @Column(nullable = false)
    private Boolean success;  // 是否成功

    @Column(length = 512)
    private String errorMessage;  // 错误信息

    @Column(nullable = false)
    private String operator;  // 操作人

    @Column(nullable = false)
    private String clientIp;  // 客户端IP

    @Column(length = 512)
    private String remark;  // 备注

    @CreationTimestamp
    private LocalDateTime createTime;  // 创建时间
} 