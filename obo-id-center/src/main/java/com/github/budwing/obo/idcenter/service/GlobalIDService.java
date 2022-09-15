package com.github.budwing.obo.idcenter.service;

import com.github.budwing.obo.idcenter.dto.IDScopeDTO;

public interface GlobalIDService {
    IDScopeDTO getNextIDScope(Integer id);
}
