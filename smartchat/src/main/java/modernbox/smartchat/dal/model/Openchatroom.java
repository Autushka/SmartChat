package modernbox.smartchat.dal.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the openchatrooms database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Openchatroom.Get", query = "SELECT ochr FROM Openchatroom ochr WHERE ochr.userName = :u AND ochr.rowId >= :r"),
	@NamedQuery(name = "Openchatroom.GetActive", query = "SELECT ochr FROM Openchatroom ochr WHERE ochr.userName = :u AND ochr.leftTheChat = FALSE"),
	@NamedQuery(name = "Openchatroom.GetAllActive", query = "SELECT ochr FROM Openchatroom ochr WHERE ochr.leftTheChat = FALSE ORDER BY ochr.userName ASC"),
	@NamedQuery(name = "Openchatroom.GetByUser", query = "SELECT o FROM Openchatroom o WHERE o.userName = :u"),
	@NamedQuery(name = "Openchatroom.GetByUserAndParticipant", query = "SELECT o FROM Openchatroom o WHERE o.userName = :u AND o.participantName = :p")})
public class Openchatroom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long rowId;

	private boolean leftTheChat;

	@Column(length = 15)
	private String participantName;

	@Column(length = 15)
	private String userName;

	public Openchatroom() {
	}

	public Long getRowId() {
		return this.rowId;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	public boolean getLeftTheChat() {
		return this.leftTheChat;
	}

	public void setLeftTheChat(boolean leftTheChat) {
		this.leftTheChat = leftTheChat;
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