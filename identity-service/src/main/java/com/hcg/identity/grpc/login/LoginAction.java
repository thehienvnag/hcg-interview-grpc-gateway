package com.hcg.identity.grpc.login;

import com.hcg.identity.grpc.Action;
import com.hcg.identity.grpc.service.LoginReply;
import com.hcg.identity.message.ValidatedLoginMessage;

public interface LoginAction extends Action<ValidatedLoginMessage, LoginReply> {
}
