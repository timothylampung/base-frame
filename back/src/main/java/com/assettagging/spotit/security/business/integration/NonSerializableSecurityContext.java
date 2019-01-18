package com.assettagging.spotit.security.business.integration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;

/**
 */
public class NonSerializableSecurityContext implements SecurityContext {

    private transient Authentication authentication;

    public boolean equals(Object obj) {
        if (obj instanceof SecurityContextImpl) {
            SecurityContextImpl test = (SecurityContextImpl) obj;

            if ((this.getAuthentication() == null) && (test.getAuthentication() == null)) {
                return true;
            }

            if ((this.getAuthentication() != null) && (test.getAuthentication() != null)
                    && this.getAuthentication().equals(test.getAuthentication())) {
                return true;
            }
        }
        return false;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public int hashCode() {
        if (this.authentication == null) {
            return -1;
        } else {
            return this.authentication.hashCode();
        }
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (this.authentication == null) {
            sb.append(": Null authentication");
        } else {
            sb.append(": Authentication: ").append(this.authentication);
        }
        return sb.toString();
    }
}
