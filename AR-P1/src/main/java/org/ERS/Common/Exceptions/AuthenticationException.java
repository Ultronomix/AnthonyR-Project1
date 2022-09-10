package org.ERS.Common.Exceptions;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("Could not find a user account with the provided credentials!");
    }
}
