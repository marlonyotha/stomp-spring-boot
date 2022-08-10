package br.com.yotha.stompwebchat.model;

import java.nio.file.attribute.UserPrincipal;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomUserPrincipal implements UserPrincipal {

    private final String name;

    @Override
    public String getName() {
        return name;
    }

}
