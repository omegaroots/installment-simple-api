package com.mendes.installmentapi.repository;

import com.mendes.installmentapi.model.Installment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {
}
