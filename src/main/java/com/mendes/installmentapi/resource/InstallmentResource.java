package com.mendes.installmentapi.resource;

import com.mendes.installmentapi.model.Installment;
import com.mendes.installmentapi.service.InstallmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class InstallmentResource {

    private InstallmentService installmentService;

    @Autowired
    public InstallmentResource (InstallmentService installmentService){
        this.installmentService = installmentService;
    }

    @GetMapping(value = "/installment/{installmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Installment> retrieveCoursesForStudent(@PathVariable Long installmentId) {
        return ResponseEntity.ok(installmentService.retrieveInstallment(installmentId));
    }

    @PostMapping(value = "/installment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Installment> retrieveCoursesForStudent(@Valid @RequestBody Installment installment) {
        return new ResponseEntity<>(installmentService.create(installment), HttpStatus.CREATED);
    }
}
