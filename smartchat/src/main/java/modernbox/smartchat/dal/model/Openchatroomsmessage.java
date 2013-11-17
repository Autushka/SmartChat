package modernbox.smartchat.dal.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the openchatroomsmessages database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Openchatroomsmessage.Get", query = "SELECT o FROM Openchatroomsmessage o WHERE o.userName = :u AND o.createdEpochTime > :r"),
	@NamedQuery(name = "Openchatroomsmessage.GetByUser", query = "SELECT o FROM Openchatroomsmessage o WHERE o.userName = :u"),
	@NamedQuery(name = "Openchatroomsmessage.GetByUserAndTime", query = "SELECT o FROM Openchatroomsmessage o WHERE o.userName = :u AND o.createdEpochTime < :t"),
	@NamedQuery(name = "Openchatroomsmessage.GetMaxRowId", query = "SELECT MAX( o.rowId ) FROM Openchatroomsmessage o WHERE o.userName = :u")})
public class Openchatroomsmessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long rowId;

	private Long createdEpochTime;

	@Lob
	private String message;

	@Column(length = 15)
	private String participantName;

	@Column(length = 15)
	private String userName;

	public Openchatroomsmessage() {
	}

	public Long getRowId() {
		return this.rowId;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	public Long getCreatedEpochTime() {
		return this.createdEpochTime;
	}

	public void setCreatedEpochTime(Long createdEpochTime) {
		this.createdEpochTime = createdEpochTime;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getParticipantName() {
		return this.participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}