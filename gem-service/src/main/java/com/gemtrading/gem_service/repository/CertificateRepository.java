package com.gemtrading.gem_service.repository;

import com.gemtrading.gem_service.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    Optional<Certificate> findByGemStoneId(Long gemStoneId);

    boolean existsByGemStoneId(Long gemStoneId);

    boolean existsByCertificateNumber(String certificateNumber);

}
