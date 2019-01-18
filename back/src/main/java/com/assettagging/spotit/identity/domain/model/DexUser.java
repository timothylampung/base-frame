package com.assettagging.spotit.identity.domain.model;

/**
 * @author canang technologies
 */
public interface DexUser extends DexPrincipal {

    String DEFAULT_PASSWORD = "abc123";

    String getUsername();

    void setUsername(String username);

    String getRealName();

    void setRealName(String firstName);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    DexActor getActor();

    void setActor(DexActor actor);

}
