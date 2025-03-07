package com.easemob.im.server.api.group.create;

import com.easemob.im.server.api.Context;
import com.easemob.im.server.exception.EMUnknownException;
import reactor.core.publisher.Mono;

import java.util.List;

public class CreateGroup {

    private Context context;

    public CreateGroup(Context context) {
        this.context = context;
    }

    public Mono<String> publicGroup(String owner, String groupName, String description,
            List<String> members, int maxMembers, boolean needApproveToJoin) {
        return this.context.getHttpClient()
                .flatMap(httpClient -> httpClient.post()
                        .uri("/chatgroups")
                        .send(Mono.create(sink -> sink.success(this.context.getCodec()
                                .encode(new CreateGroupRequest(groupName, description, true, owner,
                                        members, maxMembers, false, needApproveToJoin)))))
                        .responseSingle(
                                (rsp, buf) -> {
                                    this.context.getErrorMapper().statusCode(rsp);
                                    return buf;
                                })
                        .doOnNext(buf -> this.context.getErrorMapper().checkError(buf)))
                .map(buf -> this.context.getCodec().decode(buf, CreateGroupResponse.class))
                .handle((rsp, sink) -> {
                    String groupId = rsp.getGroupId();
                    if (groupId == null) {
                        sink.error(new EMUnknownException("groupId is null"));
                    }
                    sink.next(groupId);
                });
    }

    public Mono<String> publicGroup(String owner, String groupName, String description,
            List<String> members, int maxMembers, boolean needApproveToJoin, String custom) {
        return this.context.getHttpClient()
                .flatMap(httpClient -> httpClient.post()
                        .uri("/chatgroups")
                        .send(Mono.create(sink -> sink.success(this.context.getCodec()
                                .encode(new CreateGroupRequest(groupName, description, true, owner,
                                        members, maxMembers, false, needApproveToJoin, custom)))))
                        .responseSingle(
                                (rsp, buf) -> {
                                    this.context.getErrorMapper().statusCode(rsp);
                                    return buf;
                                })
                        .doOnNext(buf -> this.context.getErrorMapper().checkError(buf)))
                .map(buf -> this.context.getCodec().decode(buf, CreateGroupResponse.class))
                .handle((rsp, sink) -> {
                    String groupId = rsp.getGroupId();
                    if (groupId == null) {
                        sink.error(new EMUnknownException("groupId is null"));
                    }
                    sink.next(groupId);
                });
    }

    public Mono<String> publicGroup(String owner, String groupName, String description,
            List<String> members, int maxMembers, boolean needApproveToJoin, String custom, boolean needVerify) {
        return this.context.getHttpClient()
                .flatMap(httpClient -> httpClient.post()
                        .uri("/chatgroups")
                        .send(Mono.create(sink -> sink.success(this.context.getCodec()
                                .encode(new CreateGroupRequest(groupName, description, true, owner,
                                        members, maxMembers, false, needApproveToJoin, custom, needVerify)))))
                        .responseSingle(
                                (rsp, buf) -> {
                                    this.context.getErrorMapper().statusCode(rsp);
                                    return buf;
                                })
                        .doOnNext(buf -> this.context.getErrorMapper().checkError(buf)))
                .map(buf -> this.context.getCodec().decode(buf, CreateGroupResponse.class))
                .handle((rsp, sink) -> {
                    String groupId = rsp.getGroupId();
                    if (groupId == null) {
                        sink.error(new EMUnknownException("groupId is null"));
                    }
                    sink.next(groupId);
                });
    }

    public Mono<String> privateGroup(String owner, String groupName, String description,
            List<String> members, int maxMembers, boolean canMemberInvite) {
        return this.context.getHttpClient()
                .flatMap(httpClient -> httpClient.post()
                        .uri("/chatgroups")
                        .send(Mono.create(sink -> sink.success(this.context.getCodec()
                                .encode(new CreateGroupRequest(groupName, description, false, owner,
                                        members, maxMembers, canMemberInvite, !canMemberInvite)))))
                        .responseSingle(
                                (rsp, buf) -> {
                                    this.context.getErrorMapper().statusCode(rsp);
                                    return buf;
                                })
                        .doOnNext(buf -> this.context.getErrorMapper().checkError(buf)))
                .map(buf -> this.context.getCodec().decode(buf, CreateGroupResponse.class))
                .handle((rsp, sink) -> {
                    String groupId = rsp.getGroupId();
                    if (groupId == null) {
                        sink.error(new EMUnknownException("groupId is null"));
                    }
                    sink.next(groupId);
                });
    }

    public Mono<String> privateGroup(String owner, String groupName, String description,
            List<String> members, int maxMembers, boolean canMemberInvite, String custom) {
        return this.context.getHttpClient()
                .flatMap(httpClient -> httpClient.post()
                        .uri("/chatgroups")
                        .send(Mono.create(sink -> sink.success(this.context.getCodec()
                                .encode(new CreateGroupRequest(groupName, description, false, owner,
                                        members, maxMembers, canMemberInvite, !canMemberInvite, custom)))))
                        .responseSingle(
                                (rsp, buf) -> {
                                    this.context.getErrorMapper().statusCode(rsp);
                                    return buf;
                                })
                        .doOnNext(buf -> this.context.getErrorMapper().checkError(buf)))
                .map(buf -> this.context.getCodec().decode(buf, CreateGroupResponse.class))
                .handle((rsp, sink) -> {
                    String groupId = rsp.getGroupId();
                    if (groupId == null) {
                        sink.error(new EMUnknownException("groupId is null"));
                    }
                    sink.next(groupId);
                });
    }

    public Mono<String> privateGroup(String owner, String groupName, String description,
            List<String> members, int maxMembers, boolean canMemberInvite, boolean needInviteConfirm, boolean needApproveToJoin, String custom) {
        return this.context.getHttpClient()
                .flatMap(httpClient -> httpClient.post()
                        .uri("/chatgroups")
                        .send(Mono.create(sink -> sink.success(this.context.getCodec()
                                .encode(new CreateGroupRequest(groupName, description, false, owner,
                                        members, maxMembers, canMemberInvite, needInviteConfirm, needApproveToJoin, custom)))))
                        .responseSingle(
                                (rsp, buf) -> {
                                    this.context.getErrorMapper().statusCode(rsp);
                                    return buf;
                                })
                        .doOnNext(buf -> this.context.getErrorMapper().checkError(buf)))
                .map(buf -> this.context.getCodec().decode(buf, CreateGroupResponse.class))
                .handle((rsp, sink) -> {
                    String groupId = rsp.getGroupId();
                    if (groupId == null) {
                        sink.error(new EMUnknownException("groupId is null"));
                    }
                    sink.next(groupId);
                });
    }

    public Mono<String> privateGroup(String owner, String groupName, String description,
            List<String> members, int maxMembers, boolean canMemberInvite, boolean needInviteConfirm,
            boolean needApproveToJoin, String custom, boolean needVerify) {
        return this.context.getHttpClient()
                .flatMap(httpClient -> httpClient.post()
                        .uri("/chatgroups")
                        .send(Mono.create(sink -> sink.success(this.context.getCodec()
                                .encode(new CreateGroupRequest(groupName, description, false, owner,
                                        members, maxMembers, canMemberInvite, needInviteConfirm, needApproveToJoin, custom, needVerify)))))
                        .responseSingle(
                                (rsp, buf) -> {
                                    this.context.getErrorMapper().statusCode(rsp);
                                    return buf;
                                })
                        .doOnNext(buf -> this.context.getErrorMapper().checkError(buf)))
                .map(buf -> this.context.getCodec().decode(buf, CreateGroupResponse.class))
                .handle((rsp, sink) -> {
                    String groupId = rsp.getGroupId();
                    if (groupId == null) {
                        sink.error(new EMUnknownException("groupId is null"));
                    }
                    sink.next(groupId);
                });
    }
}
