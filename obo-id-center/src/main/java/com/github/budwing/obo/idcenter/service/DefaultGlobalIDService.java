package com.github.budwing.obo.idcenter.service;

import com.github.budwing.obo.idcenter.dto.IDScopeDTO;
import com.github.budwing.obo.idcenter.entity.GlobalID;
import com.github.budwing.obo.idcenter.repository.GlobalIDRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class DefaultGlobalIDService implements GlobalIDService {
    @Autowired
    private GlobalIDRepository idRepository;

    @Transactional
    @Override
    public IDScopeDTO getNextIDScope(Integer id) {
        Optional<GlobalID> optional = idRepository.findById(id);
        if (!optional.isPresent()) return null;

        GlobalID globalID = optional.get();
        if (globalID.needRestore()) {
            log.warn("ID in {} reach its upper limit {}, will be restored to 1.",
                    globalID.getBucket(), globalID.getUpperLimit());
            // An event should be published here, so that the client can renew its algorithm in the new round.
            globalID.restore();
        }
        IDScopeDTO dto = new IDScopeDTO();
        dto.setStart(globalID.getValue());
        dto.setEnd(globalID.getValue() + globalID.getStep() - 1);
        globalID.setValue(globalID.getValue() + globalID.getStep());

        log.debug("Global ID scope is {}.", dto);

        return dto;
    }
}
