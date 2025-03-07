package com.easemob.im.server.api.push.nickname;

import com.easemob.im.server.api.Context;
import reactor.core.publisher.Mono;

public class UpdateUserNickname {

    private Context context;

    public UpdateUserNickname(Context context) {
        this.context = context;
    }

    public Mono<Void> update(String username, String nickname) {
        return this.context.getHttpClient()
                .flatMap(httpClient -> httpClient.put()
                        .uri(String.format("/users/%s", username))
                        .send(Mono.create(sink -> sink.success(this.context.getCodec()
                                .encode(new UpdateUserNicknameRequest(nickname)))))
                        .responseSingle(
                                (rsp, buf) -> {
                                    this.context.getErrorMapper().statusCode(rsp);
                                    return buf;
                                })
                        .doOnNext(buf -> this.context.getErrorMapper().checkError(buf))
                        .then());
    }

}
