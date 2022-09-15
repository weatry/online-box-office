package com.github.budwing.obo.idcenter.controller;

import com.github.budwing.obo.idcenter.dto.IDScopeDTO;
import com.github.budwing.obo.idcenter.service.GlobalIDService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/obo")
@Slf4j
public class GlobalIDController {
    @Autowired
    private GlobalIDService idService;

    @Operation(summary = "Get ID numbers", description = "Get a range of id numbers based its primary key")
    @GetMapping(value = "/id/{id}", produces = "application/json")
    public ResponseEntity getNext(@PathVariable Integer id) {
        IDScopeDTO dto = idService.getNextIDScope(id);
        if (dto==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

}
