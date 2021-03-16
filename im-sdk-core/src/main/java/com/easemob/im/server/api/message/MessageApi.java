package com.easemob.im.server.api.message;

import com.easemob.im.server.api.Context;
import com.easemob.im.server.api.message.missed.MessageMissed;
import com.easemob.im.server.api.message.send.SendMessage;
import com.easemob.im.server.api.message.send.SendMessageResponse;
import com.easemob.im.server.api.message.status.MessageStatus;
import com.easemob.im.server.model.EMKeyValue;
import com.easemob.im.server.model.EMMessage;
import com.easemob.im.server.model.EMMessageStatus;
import reactor.core.publisher.Mono;

import java.util.Set;

public class MessageApi {

    private Context context;

    private MessageMissed missed;

    private SendMessage sendMessage;

    public MessageApi(Context context) {
        this.context = context;
        this.missed = new MessageMissed(context);
        this.sendMessage = new SendMessage(context);
    }

    public MessageMissed missed() {
        return this.missed;
    }

    public Mono<EMMessageStatus> isMessageDeliveredToUser(String messageId, String toUser) {
        return MessageStatus.isMessageDeliveredToUser(this.context, messageId, toUser);
    }

    /**
     * A fluent fashion API to send message.
     *
     * You can send a text message to a user like this:
     * <pre>{@code
     * EMService service;
     * service.message().send()
     *                  .fromUser("alice").toUser("rabbit")
     *                  .text(msg -> msg.text("hello"))
     *                  .extension(exts -> exts.add(EMKeyValue.of("timeout", 1)))
     *                  .send()
     *                  .block(Duration.ofSeconds(3));
     * }</pre>
     *
     * There's also a raw version.
     * @return A {@code SendMessage} as send spec builder.
     */
    public SendMessage send() {
        return this.sendMessage;
    }

    /**
     * A low level API to send message.
     *
     * @param from the sender's username
     * @param toType the receiver's type. could be one of `users`, `chatgroups`, `chatrooms`
     * @param tos the receiver's ids
     * @param message the message to send
     * @param extensions the extension to send
     * @return A {@code Mono} which emits {@code SendMessageResponse}.
     */
    public Mono<SendMessageResponse> send(String from, String toType, Set<String> tos, EMMessage message, Set<EMKeyValue> extensions) {
        return this.sendMessage.send(from, toType, tos, message, extensions);
    }
}
