package modernbox.smartchat.dal;

import java.util.List;

import modernbox.smartchat.dal.model.Openchatroom;
import modernbox.smartchat.dal.model.Openchatroomsmessage;


public interface ChatService {

	public void openChatRoom(String userName);

	public void joinTheChatRoom(String sPerformer, String sUserName);

	public List<Openchatroom> getChatRoomActiveParticipants(String userName);

	public List<Openchatroom> getChatRoomParticipants(String userName, int chatParticipantRowId);

	public void closeChatRoom(String userName);

	public void leaveChatRoom(String chatRoom, String userName);

	public Long addMessageToTheChatRoom(String sPerformer, String sUserName, String sMessage);

	public Long getEpochTime();

	public List<Openchatroomsmessage> getChatRoomMessages(String userName, Long chatMessageEpochTime);

	public Long getChatRoomMessagesMaxRowID(String userName);

	public List<OpenChatRoomDTO> getOpenChatRooms();

}
