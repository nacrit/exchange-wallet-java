package com.nacrt.exchange.wallet.crypto.security.dao.repository;

import com.nacrt.exchange.wallet.crypto.security.dao.model.SignatureOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SignatureOperationRepository extends JpaRepository<SignatureOperation, Long> {

    Page<SignatureOperation> findByKeyManagementId(Long keyManagementId, Pageable pageable);

    @Query("SELECT s FROM SignatureOperation s WHERE s.keyManagement.chainId = ?1 AND s.createTime BETWEEN ?2 AND ?3")
    List<SignatureOperation> findByChainIdAndTimeRange(String chainId, LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT s FROM SignatureOperation s WHERE s.keyManagement.address = ?1 AND s.operationType = ?2 AND s.success = true")
    List<SignatureOperation> findSuccessfulOperationsByAddressAndType(String address, String operationType);

    @Query("SELECT COUNT(s) FROM SignatureOperation s WHERE s.keyManagement.chainId = ?1 AND s.success = true AND s.createTime >= ?2")
    long countSuccessfulOperationsByChainIdAndTime(String chainId, LocalDateTime startTime);

    @Query("SELECT s FROM SignatureOperation s WHERE s.operator = ?1 AND s.createTime >= ?2 ORDER BY s.createTime DESC")
    List<SignatureOperation> findRecentOperationsByOperator(String operator, LocalDateTime startTime);
} 