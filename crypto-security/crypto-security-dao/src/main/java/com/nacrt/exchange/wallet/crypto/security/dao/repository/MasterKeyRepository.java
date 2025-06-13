package com.nacrt.exchange.wallet.crypto.security.dao.repository;

import com.nacrt.exchange.wallet.crypto.security.dao.model.MasterKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MasterKeyRepository extends JpaRepository<MasterKey, Long> {
    
    Optional<MasterKey> findByChainIdAndIsActiveTrue(String chainId);
    
    List<MasterKey> findByIsActiveTrue();
    
    @Query("SELECT m FROM MasterKey m WHERE m.chainId = ?1 AND m.isActive = true")
    Optional<MasterKey> findActiveMasterKeyByChainId(String chainId);
    
    boolean existsByChainIdAndIsActiveTrue(String chainId);
} 