package com.hcg.identity.grpc.register;

import com.hcg.identity.grpc.Action;
import com.hcg.identity.grpc.service.RegisterReply;
import com.hcg.identity.message.ValidatedRegisterMessage;

public interface RegisterAction extends Action<ValidatedRegisterMessage, RegisterReply> {
}
