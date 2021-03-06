package com.cs401.storagevault.model.repository;

import com.cs401.storagevault.model.tables.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PricingRepository extends JpaRepository<Pricing, Integer> {

    @Query(value = "SELECT monthlyPrice FROM pricing WHERE customerType = :customerType", nativeQuery = true)
    double findByCustomerType(char customerType);

    @Query(value = "SELECT yearlyPrice FROM pricing WHERE customerType = :customerType", nativeQuery = true)
    double yearlySubscriptionPrice(char customerType);
}
