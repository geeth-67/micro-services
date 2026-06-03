package com.gemtrading.gem_service.repository;

import com.gemtrading.gem_service.entity.GemStone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface GemStoneRepository extends JpaRepository<GemStone, Long> {

    Page<GemStone> findByActiveTrue(Pageable pageable);

    @Query("Select g FROM GemStone g WHERE g.pricePerCarat" +
            " BETWEEN :minPrice AND :maxPrice " +
            "AND g.caratWeight >= :minCarat AND g.stockQuantity > 0 AND g.active = true")
    Page<GemStone> searchInvestmentGradeGems(@Param("minPrice")BigDecimal minPrice,
                                             @Param("maxPrice") BigDecimal maxPrice,
                                             @Param("minCarat") BigDecimal minCarat,
                                             Pageable pageable);
}
