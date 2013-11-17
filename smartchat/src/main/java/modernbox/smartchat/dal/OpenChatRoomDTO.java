package modernbox.smartchat.dal;

public class OpenChatRoomDTO {

	private String performer;
	private String performerAvatarURL;
	private Integer numberOfParticipants; 
	
	public String getPerformer() {
		return performer;
	}
	
	public void setPerformer(String performer) {
		this.performer = performer;
	}
	
	public String getPerformerAvatarURL() {
		return performerAvatarURL;
	}

	public void setPerformerAvatarURL(String performerAvatarURL) {
		this.performerAvatarURL = performerAvatarURL;
	}

	public Integer getNumberOfParticipants() {
		return numberOfParticipants;
	}
	
	public void setNumberOfParticipants(Integer numberOfParticipants) {
		this.numberOfParticipants = numberOfParticipants;
	}

}
