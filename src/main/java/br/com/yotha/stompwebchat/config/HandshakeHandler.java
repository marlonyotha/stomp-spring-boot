package br.com.yotha.stompwebchat.config;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import com.sun.security.auth.UserPrincipal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
            Map<String, Object> attributes) {

        final String randomId = UUID.randomUUID().toString();
        log.info("User with ID '{}' opened the page", randomId);

        return new UserPrincipal(randomId);
    }

}
