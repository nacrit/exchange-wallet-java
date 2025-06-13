package com.nacrt.exchange.wallet.crypto.security.dao.repository;

import com.nacrt.exchange.wallet.crypto.security.dao.model.KeyManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeyManagementRepository extends JpaRepository<KeyManagement, Long> {
    
    Optional<KeyManagement> findByAddressAndChainIdAndIsActiveTrue(String address, String chainId);
    
    List<KeyManagement> findByChainIdAndIsActiveTrue(String chainId);
    
    @Query("SELECT k FROM KeyManagement k WHERE k.masterKey.id = ?1 AND k.isActive = true")
    List<KeyManagement> findActiveKeysByMasterKeyId(Long masterKeyId);
    
    @Query("SELECT k FROM KeyManagement k WHERE k.derivationPath = ?1 AND k.chainId = ?2 AND k.isActive = true")
    Optional<KeyManagement> findActiveKeyByPathAndChainId(String derivationPath, String chainId);
    
    boolean existsByAddressAndChainIdAndIsActiveTrue(String address, String chainId);
    
    @Query("SELECT COUNT(k) FROM KeyManagement k WHERE k.chainId = ?1 AND k.isActive = true")
    long countActiveKeysByChainId(String chainId);
} 