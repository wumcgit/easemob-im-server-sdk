package com.easemob.im.server.api.room;

import com.easemob.im.server.api.AbstractIT;
import com.easemob.im.server.api.util.Utilities;
import com.easemob.im.server.model.EMBlock;
import com.easemob.im.server.model.EMRoom;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RoomIT extends AbstractIT {

    RoomIT() {
        super();
    }

    @Test
    void testRoomCreate() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomCreateWithNeedVerify() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200, "custom", Boolean.TRUE)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomGet() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        EMRoom room = assertDoesNotThrow(() -> this.service.room().getRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertNotNull(room);
        assertEquals(2, room.members().size());
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomUpdate() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200, "custom")
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room()
                .updateRoom(roomId, settings -> settings.withMaxMembers(100).withCustom("custom"))
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomListAll() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().listRoomsAll().blockFirst(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomListRooms() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().listRooms(1, null).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomUserJoinedList() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().listRoomsUserJoined(randomOwnerUsername)
                .blockFirst(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomMembersAll() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername1 = Utilities.randomUserName();
        String randomMemberUsername2 = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername1);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername1, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername2, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().addRoomMember(roomId,randomMemberUsername2)
                .block(Utilities.IT_TIMEOUT));
        String firstJoinMember =assertDoesNotThrow(() -> this.service.room().listRoomMembersAll(roomId,"asc")
                .blockFirst(Utilities.IT_TIMEOUT));
        assertEquals(randomMemberUsername1, firstJoinMember);
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername1)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername2)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomMembersAllWithOwner() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername1 = Utilities.randomUserName();
        String randomMemberUsername2 = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername1);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername1, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername2, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().addRoomMember(roomId,randomMemberUsername2)
                .block(Utilities.IT_TIMEOUT));
        List<Map<String, String>> roomMembers = assertDoesNotThrow(() -> this.service.room().listRoomMembersAllWithOwner(roomId).collectList()
                .block(Utilities.IT_TIMEOUT));
        assertNotNull(roomMembers);
        assertEquals(3, roomMembers.size());
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername1)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername2)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomMembers() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().listRoomMembers(roomId, 1, null)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomMembersWithOwner() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        List<Map<String, String>> roomMembers = assertDoesNotThrow(() -> this.service.room().listRoomMembersWithOwner(roomId, 1, 10)
                .block(Utilities.IT_TIMEOUT));
        assertNotNull(roomMembers);
        assertEquals(2, roomMembers.size());
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomAddMember() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().addRoomMember(roomId, randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomRemoveMember() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().removeRoomMember(roomId, randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomAdminsAll() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().promoteRoomAdmin(roomId, randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().listRoomAdminsAll(roomId)
                .blockFirst(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomPromoteAdmin() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().promoteRoomAdmin(roomId, randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomDemoteAdmin() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().promoteRoomAdmin(roomId, randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().demoteRoomAdmin(roomId, randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomSuperAdminsAll() {
        String randomUsername = Utilities.randomUserName();
        String randomUsername1 = Utilities.randomUserName();
        String randomUsername2 = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();
        assertDoesNotThrow(() -> this.service.user().create(randomUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomUsername1, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomUsername2, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().promoteRoomSuperAdmin(randomUsername)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().promoteRoomSuperAdmin(randomUsername1)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().promoteRoomSuperAdmin(randomUsername2)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().listRoomSuperAdminsAll()
                .blockFirst(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomUsername1).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomUsername2).block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomPromoteSuperAdmin() {
        String randomUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();
        assertDoesNotThrow(() -> this.service.user().create(randomUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().promoteRoomSuperAdmin(randomUsername)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomUsername).block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomDemoteSuperAdmin() {
        String randomUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();
        assertDoesNotThrow(() -> this.service.user().create(randomUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().demoteRoomSuperAdmin(randomUsername)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomUsername).block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomDestroy() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomUsersBlockedSendMsg() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername1 = Utilities.randomUserName();
        String randomMemberUsername2 = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername1);
        members.add(randomMemberUsername2);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername1, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername2, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.block()
                .blockUserSendMsgToRoom(members, roomId, Duration.ofMillis(6000))
                .block(Utilities.IT_TIMEOUT));
        List<EMBlock> blocks = assertDoesNotThrow(() -> this.service.block().listUsersBlockedSendMsgToRoom(roomId)
                .collectList()
                .block(Utilities.IT_TIMEOUT));
        assertEquals(blocks.size(), 2);
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername1)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername2)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomBlockUserSendMsg() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.block()
                .blockUserSendMsgToRoom(randomMemberUsername, roomId, Duration.ofMillis(30000))
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().removeRoomMember(roomId, randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().addRoomMember(roomId, randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
        EMBlock block = assertDoesNotThrow(
                () -> this.service.block().listUsersBlockedSendMsgToRoom(roomId)
                        .blockFirst(Utilities.IT_TIMEOUT));
        if (!block.getUsername().equals(randomMemberUsername)) {
            throw new RuntimeException(
                    String.format("%s does not exist in %s room mute list", randomMemberUsername,
                            roomId));
        }
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomUnblockUserSendMsg() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.block()
                .blockUserSendMsgToRoom(randomMemberUsername, roomId, Duration.ofMillis(6000))
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.block().unblockUserSendMsgToRoom(randomMemberUsername, roomId)
                        .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomUnblockUsersSendMsg() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername1 = Utilities.randomUserName();
        String randomMemberUsername2 = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername1);
        members.add(randomMemberUsername2);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername1, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername2, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.block()
                .blockUserSendMsgToRoom(members, roomId, Duration.ofMillis(6000))
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.block().unblockUserSendMsgToRoom(members, roomId)
                        .block(Utilities.IT_TIMEOUT));

        List<EMBlock> blocks = assertDoesNotThrow(
                () -> this.service.block().listUsersBlockedSendMsgToRoom(roomId).collectList()
                        .block(Utilities.IT_TIMEOUT));
        assertEquals(blocks.size(), 0);
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername1)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername2)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomAssign(){
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));

        assertDoesNotThrow(()->this.service.room().assignRoom(roomId, randomMemberUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

    @Test
    void testRoomGetAnnouncement() {

        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.block()
                .blockAllUserSendMsgToRoom(roomId)
                .block(Utilities.IT_TIMEOUT));

        EMRoom blockRoom = assertDoesNotThrow(
                () -> this.service.room().getRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertNotNull(blockRoom);
        assertEquals(true, blockRoom.mute());

        assertDoesNotThrow(
                () -> this.service.block().unblockAllUserSendMsgToRoom(roomId)
                        .block(Utilities.IT_TIMEOUT));

        EMRoom unblockRoom = assertDoesNotThrow(
                () -> this.service.room().getRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertNotNull(unblockRoom);
        assertEquals(false, unblockRoom.mute());

        assertDoesNotThrow(() -> this.service.room().assignRoom(roomId, randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.room().getRoomAnnouncement(roomId)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));

    }

    @Test
    void testRoomUpdateAnnouncement() {
        String randomOwnerUsername = Utilities.randomUserName();
        String randomPassword = Utilities.randomPassword();

        String randomMemberUsername = Utilities.randomUserName();
        List<String> members = new ArrayList<>();
        members.add(randomMemberUsername);
        assertDoesNotThrow(() -> this.service.user().create(randomOwnerUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().create(randomMemberUsername, randomPassword)
                .block(Utilities.IT_TIMEOUT));
        String roomId = assertDoesNotThrow(() -> this.service.room()
                .createRoom("chat room", "room description", randomOwnerUsername, members, 200)
                .block(Utilities.IT_TIMEOUT));

        String updateAnnouncement = "update announcement";

        assertDoesNotThrow(
                () -> this.service.room().updateRoomAnnouncement(roomId, updateAnnouncement)
                        .block(Utilities.IT_TIMEOUT));
        String newAnnouncement = assertDoesNotThrow(
                () -> this.service.room().getRoomAnnouncement(roomId)
                        .block(Utilities.IT_TIMEOUT));
        if (!updateAnnouncement.equals(newAnnouncement)) {
            throw new RuntimeException(String.format("update %s group announcement fail", roomId));
        }
        assertDoesNotThrow(
                () -> this.service.room().destroyRoom(roomId).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(
                () -> this.service.user().delete(randomOwnerUsername).block(Utilities.IT_TIMEOUT));
        assertDoesNotThrow(() -> this.service.user().delete(randomMemberUsername)
                .block(Utilities.IT_TIMEOUT));
    }

}
