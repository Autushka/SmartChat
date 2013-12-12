package modernbox.smartchat.dal;

import java.util.List;

import modernbox.smartchat.dal.model.Openchatroom;
import modernbox.smartchat.dal.model.Openchatroomsmessage;


public class GoogleXMPPChatService implements ChatService {

	public void openChatRoom(String userName) {
		// TODO Implement
	}

	public void joinTheChatRoom(String sPerformer, String sUserName) {
		// TODO Implement
	}

	public List<Openchatroom> getChatRoomActiveParticipants(String userName) {
		// TODO Implement
		return null;
	}

	public List<Openchatroom> getChatRoomParticipants(String userName, int chatParticipantRowId) {
		// TODO Implement
		return null;
	}

	public void closeChatRoom(String userName) {
		// TODO Implement
	}

	public void leaveChatRoom(String chatRoom, String userName) {
		// TODO Implement
	}

	public Long addMessageToTheChatRoom(String sPerformer, String sUserName, String sMessage) {
		// TODO Implement
		return null;
	}

	public Long getEpochTime() {
		// TODO Implement
		return null;
	}

	public List<Openchatroomsmessage> getChatRoomMessages(String userName, Long chatMessageEpochTime) {
		// TODO Implement
		return null;
	}

	public Long getChatRoomMessagesMaxRowID(String userName) {
		// TODO Implement
		return null;
	}

	public List<OpenChatRoomDTO> getOpenChatRooms() {
		// TODO Implement
		return null;
	}

}
