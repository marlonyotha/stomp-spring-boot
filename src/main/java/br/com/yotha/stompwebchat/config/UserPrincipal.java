package br.com.yotha.stompwebchat.config;

import java.security.Principal;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserPrincipal implements Principal {

    @Override
    public String getName() {
        final String randomId = UUID.randomUUID().toString();
        log.info("User with ID '{}' opened the page", randomId);
        return randomId;
    }

}
