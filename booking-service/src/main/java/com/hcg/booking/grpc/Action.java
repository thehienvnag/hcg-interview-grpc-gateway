package com.hcg.booking.grpc;


import com.hcg.booking.dto.ActionResultDTO;

public interface Action<K, V> {
    ActionResultDTO<V> proceedInternal(K message);
}
