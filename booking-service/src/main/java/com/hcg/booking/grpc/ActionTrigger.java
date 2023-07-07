package com.hcg.booking.grpc;

import com.hcg.booking.dto.ActionResultDTO;
import com.hcg.booking.exceptions.InputErrorConst;
import com.hcg.booking.exceptions.InputErrorDTO;
import com.hcg.booking.utils.Utils;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.el.PropertyNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ActionTrigger<K, V> {

    public ActionTrigger<K, V> init(Action<K, V> action) {
        this.action = action;
        return this;
    }

    private Action<K, V> action;

    @Autowired
    private Validator validator;

    public void proceed(K message, StreamObserver<V> observer) {

        if (action == null) {
            throw new PropertyNotFoundException("The [action] parameter is not configured properly.");
        }

        var errors = validator.validate(message);
        if (errors.size() > 0) {
            Metadata metadata = getInvalidArgumentMetaData(errors);
            observer.onError(Status.INVALID_ARGUMENT.asRuntimeException(metadata));
            return;
        }

        ActionResultDTO<V> result;
        try {
            result = action.proceedInternal(message);
        } catch (Throwable ex) {
            result = new ActionResultDTO<>(Status.INTERNAL
                    .withDescription(ex.getMessage())
                    .withCause(ex.getCause()), null);
        }

        Status status = result.getStatus();
        if (status.isOk()) {
            observer.onNext(result.getResult());
            observer.onCompleted();
        } else {
            observer.onError(status.asRuntimeException());
        }
    }

    @NonNull
    private Metadata getInvalidArgumentMetaData(Set<ConstraintViolation<K>> errors) {
        Metadata metadata = new Metadata();

        List<InputErrorDTO> errorList = new ArrayList<>();
        errors.forEach(ex -> {
            errorList.add(new InputErrorDTO(ex.getPropertyPath().toString(), ex.getMessage()));
        });

        metadata.put(InputErrorConst.KEY, Utils.toJson(errorList));
        return metadata;
    }
}
