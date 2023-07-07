package com.hcg.identity.grpc.get_user_details;

import com.hcg.identity.grpc.Action;
import com.hcg.identity.grpc.service.GetUserDetailsReply;
import com.hcg.identity.message.ValidatedGetUserDetailsMessage;

public interface GetUserDetailsAction extends Action<ValidatedGetUserDetailsMessage, GetUserDetailsReply> {
}
