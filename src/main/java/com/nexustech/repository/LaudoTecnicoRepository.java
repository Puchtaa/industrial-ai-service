package com.nexustech.repository;

import com.nexustech.entity.LaudoTecnicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LaudoTecnicoRepository
        extends JpaRepository<LaudoTecnicoEntity, UUID> {

    List<LaudoTecnicoEntity>
    findAllByOrderByDataCriacaoDesc();

    List<LaudoTecnicoEntity>
    findByGravidadeIgnoreCaseOrderByDataCriacaoDesc(
            String gravidade
    );
}