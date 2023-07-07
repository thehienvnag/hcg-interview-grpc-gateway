package com.hcg.booking.configuration;

import com.hcg.booking.qualifiers.IdentityChannelQualifier;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Setter
@Getter
@Configuration
public class IdentityManagedChannel {

    @Value("${managed-grpc-channel.identity-service.host}")
    private String host;

    @Value("${managed-grpc-channel.identity-service.port}")
    private int port;
    @Bean
    @IdentityChannelQualifier
    public ManagedChannel identityChannel() {
        return ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
    }
}
