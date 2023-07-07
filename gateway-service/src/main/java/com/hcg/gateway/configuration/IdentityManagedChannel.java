package com.hcg.gateway.configuration;

import com.hcg.gateway.qualifiers.IdentityChannelQualifier;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Setter
@Getter
@Configuration
@Slf4j
public class IdentityManagedChannel {
    @Value("${managed-grpc-channel.identity-service.host}")
    private String host;

    @Value("${managed-grpc-channel.identity-service.port}")
    private int port;
    @Bean
    @IdentityChannelQualifier
    public ManagedChannel identityChannel() {
        log.info("Init grpc channel at: {}:{}", host, port);
        return ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
    }
}
