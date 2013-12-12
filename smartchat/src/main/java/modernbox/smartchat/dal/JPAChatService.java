package modernbox.smartchat.dal;

import java.util.ArrayList;
import java.util.List;

import modernbox.smartchat.dal.OpenchatroomDAO;
import modernbox.smartchat.dal.OpenchatroomsmessageDAO;
import modernbox.smartchat.dal.model.Openchatroom;
import modernbox.smartchat.dal.model.Openchatroomsmessage;


public class JPAChatService implements ChatService {

	public void openChatRoom(String userName) {
		new OpenchatroomDAO().deleteByUsername(userName);

		Openchatroom ochr = new Openchatroom();
		ochr.setUserName(userName);
		ochr.setParticipantName(userName);
		ochr.setLeftTheChat(false);
		new OpenchatroomDAO().create(ochr);
	}

	public void joinTheChatRoom(String sPerformer, String sUserName) {
		new OpenchatroomDAO().deleteByUserNameAndParticipantName(sPerformer, sUserName);

		Openchatroom ochr = new Openchatroom();
		ochr.setUserName(sPerformer);
		ochr.setParticipantName(sUserName);
		ochr.setLeftTheChat(false);
		new OpenchatroomDAO().create(ochr);
	}

	public List<Openchatroom> getChatRoomActiveParticipants(String userName) {
		return new OpenchatroomDAO().readActiveParticipants(userName);
	}

	public List<Openchatroom> getChatRoomParticipants(String userName, int chatParticipantRowId) {
		return new OpenchatroomDAO().read(userName, chatParticipantRowId);
	}

	public void closeChatRoom(String userName) {
		new OpenchatroomDAO().deleteByUsername(userName);
		new OpenchatroomsmessageDAO().deleteByUserName(userName);
	}

	public void leaveChatRoom(String chatRoom, String userName) {
		new OpenchatroomDAO().deleteByUserNameAndParticipantName(chatRoom, userName);
		
		Openchatroom ochr = new Openchatroom();
		ochr.setUserName(chatRoom);
		ochr.setParticipantName(userName);
		ochr.setLeftTheChat(true);
		new OpenchatroomDAO().create(ochr);
	}

	public Long addMessageToTheChatRoom(String sPerformer, String sUserName, String sMessage) {
		Long epochTime = getEpochTime() - 60 * 1000;// current time minus 1 minute
		new OpenchatroomsmessageDAO().deleteByPerformer(sPerformer, epochTime);
		
		Openchatroomsmessage ochrm = new Openchatroomsmessage();
		ochrm.setUserName(sPerformer);
		ochrm.setParticipantName(sUserName);
		ochrm.setMessage(sMessage);
		ochrm.setCreatedEpochTime(getEpochTime());
		new OpenchatroomsmessageDAO().create(ochrm);
		
		return ochrm.getRowId();
	}

	public Long getEpochTime() {
		return System.currentTimeMillis();
	}

	public List<Openchatroomsmessage> getChatRoomMessages(String userName, Long chatMessageEpochTime) {
		return new OpenchatroomsmessageDAO().read(userName, chatMessageEpochTime);
	}

	public Long getChatRoomMessagesMaxRowID(String userName) {
		return new OpenchatroomsmessageDAO().getMaxRowID(userName);
	}

	public List<OpenChatRoomDTO> getOpenChatRooms() {
		List<OpenChatRoomDTO> openchatroomDTOs = new ArrayList<OpenChatRoomDTO>();
		List<Openchatroom> openchatrooms = new OpenchatroomDAO().readAll();
		int participantsCounter = 0;
		String currentPerformer = null;
		for (Openchatroom openchatroom : openchatrooms) {
			if (! openchatroom.getUserName().equals(currentPerformer)) {
				if (currentPerformer != null) {
					OpenChatRoomDTO openChatRoomDTO = new OpenChatRoomDTO();
					openChatRoomDTO.setPerformer(currentPerformer);
					openChatRoomDTO.setNumberOfParticipants(participantsCounter);
					openchatroomDTOs.add(openChatRoomDTO);
				}
				currentPerformer = openchatroom.getUserName();
				participantsCounter = 1;
			} else {
				participantsCounter++;
			}
		}
		if (currentPerformer != null) {
			OpenChatRoomDTO openChatRoomDTO = new OpenChatRoomDTO();
			openChatRoomDTO.setPerformer(currentPerformer);
			openChatRoomDTO.setNumberOfParticipants(participantsCounter);
			openchatroomDTOs.add(openChatRoomDTO);
		}
		return openchatroomDTOs;
	}

}
