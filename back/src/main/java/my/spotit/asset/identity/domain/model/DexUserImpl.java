package my.spotit.asset.identity.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author canang technologies
 */
@Entity(name = "DexUser")
@Table(name = "DEX_USER")
public class DexUserImpl extends DexPrincipalImpl implements DexUser {

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Column(name = "REAL_NAME", nullable = false)
    private String realName;

    @NotNull
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @OneToOne(targetEntity = DexActorImpl.class)
    @JoinColumn(name = "ACTOR_ID")
    private DexActor actor;

    public DexUserImpl() {
        setPrincipalType(DexPrincipalType.USER);
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public void setUsername(String username) {
        setName(username);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getRealName() {
        return realName;
    }

    @Override
    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public DexActor getActor() {
        return actor;
    }

    @Override
    public void setActor(DexActor actor) {
        this.actor = actor;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return DexUser.class;
    }

}
