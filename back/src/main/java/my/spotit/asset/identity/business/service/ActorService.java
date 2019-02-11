package my.spotit.asset.identity.business.service;

import my.spotit.asset.identity.domain.model.DexActor;

import java.util.List;

public interface ActorService {
    List<DexActor> findAllActors();
}
