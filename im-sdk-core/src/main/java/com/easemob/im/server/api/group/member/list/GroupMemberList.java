package com.easemob.im.server.api.group.member.list;

import com.easemob.im.server.api.Context;
import com.easemob.im.server.api.DefaultErrorMapper;
import com.easemob.im.server.api.ErrorMapper;
import com.easemob.im.server.model.EMPage;
import io.netty.handler.codec.http.QueryStringEncoder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GroupMemberList {

    private Context context;

    public GroupMemberList(Context context) {
        this.context = context;
    }

    public Flux<String> all(String groupId, int limit, String sort) {
        return next(groupId, limit, null, sort)
                .expand(rsp -> rsp.getCursor() == null ?
                        Mono.empty() :
                        next(groupId, limit, rsp.getCursor(), sort))
                .concatMapIterable(EMPage::getValues);
    }

    public Mono<EMPage<String>> next(String groupId, int limit, String cursor, String sort) {
        final String uriPath = String.format("/chatgroups/%s/users", groupId);
        QueryStringEncoder encoder = new QueryStringEncoder(uriPath);
        encoder.addParam("version", "v3");
        encoder.addParam("limit", String.valueOf(limit));
        if (cursor != null) {
            encoder.addParam("cursor", cursor);
        }
        if (sort != null) {
            encoder.addParam("sort", sort);
        }
        String uriString = encoder.toString();
        return this.context.getHttpClient()
                .flatMap(httpClient -> httpClient.get()
                        .uri(uriString)
                        .responseSingle(
                                (rsp, buf) -> Mono.zip(Mono.just(rsp), buf)))
                .map(tuple2 -> {
                    ErrorMapper mapper = new DefaultErrorMapper();
                    mapper.statusCode(tuple2.getT1());
                    mapper.checkError(tuple2.getT2());

                    return tuple2.getT2();
                })
                .map(buf -> this.context.getCodec().decode(buf, GroupMemberListResponse.class))
                .map(GroupMemberListResponse::toEMPage);
    }
}
