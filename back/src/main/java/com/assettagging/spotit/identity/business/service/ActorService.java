package com.assettagging.spotit.identity.business.service;

import com.assettagging.spotit.identity.domain.model.DexActor;

import java.util.List;

public interface ActorService {
    List<DexActor> findAllActors();
}
