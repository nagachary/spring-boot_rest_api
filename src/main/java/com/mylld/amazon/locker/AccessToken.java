package com.mylld.amazon.locker;

import java.time.Instant;

/* This is the class to provide access code, expiration date and valid compartment for deposited package*/
public class AccessToken {

    private final String accessCode;
    private final Instant expirationTime;
    private final Compartment compartment;

    public AccessToken(String accessCode, Instant expirationTime, Compartment compartment) {
        this.accessCode = accessCode;
        this.expirationTime = expirationTime;
        this.compartment = compartment;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public Compartment getCompartment() {
        return compartment;
    }

    /**
     * It validates the expiration date and identifies the compartment whose expiration date is still valid.
     * @return valid compartment
     */
    public Compartment getCompartmentIfValid() {
        if (Instant.now().isBefore(expirationTime)) {
            return compartment;
        } else
            return null;
    }
}
