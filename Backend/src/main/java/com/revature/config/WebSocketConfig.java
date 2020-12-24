package com.revature.config;

import com.revature.controllers.GameSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private static final String GAME_ENDPOINT = "/gameaction";

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(getGameSocketHandler(), GAME_ENDPOINT).setAllowedOrigins("http://localhost:4200");
    }

    @Bean
    public WebSocketHandler getGameSocketHandler(){
        return new GameSocketHandler();
    }
}
