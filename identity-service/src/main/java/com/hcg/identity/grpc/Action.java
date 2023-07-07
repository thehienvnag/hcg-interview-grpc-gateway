package com.hcg.identity.grpc;

import com.hcg.identity.dto.ActionResultDTO;
import org.springframework.lang.NonNull;

import javax.validation.Valid;

public interface Action<K, V> {
    ActionResultDTO<V> proceedInternal(@Valid K message);
}
