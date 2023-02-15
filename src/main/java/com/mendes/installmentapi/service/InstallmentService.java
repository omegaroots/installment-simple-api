package com.mendes.installmentapi.service;

import com.mendes.installmentapi.model.Installment;
import com.mendes.installmentapi.repository.InstallmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstallmentService {

    private InstallmentRepository repository;

    public InstallmentService(InstallmentRepository repository) {
        this.repository = repository;
    }

     public Installment retrieveInstallment(Long id) {
         return repository.findById(id).orElseThrow(ResourceNotFoundException::new);

     }

     public Installment create(Installment installment) {
        return repository.save(installment);
     }
}
