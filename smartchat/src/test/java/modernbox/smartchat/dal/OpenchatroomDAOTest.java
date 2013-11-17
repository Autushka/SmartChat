package modernbox.smartchat.dal;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import modernbox.smartchat.dal.OpenchatroomDAO;
import modernbox.smartchat.dal.model.Openchatroom;

import org.junit.Before;
import org.junit.Test;


public class OpenchatroomDAOTest {
    
	@Before
    public void setUp() throws Exception {
		SetUp.setUp();
    }

	@Test
	public void createRead() {
		String userName = "userNameCreate";
		
		Openchatroom ochr = new Openchatroom();
		ochr.setUserName(userName);
		ochr.setParticipantName(userName);
		ochr.setLeftTheChat(false);
		new OpenchatroomDAO().create(ochr);

		try {
			List<Openchatroom> ochrs = new OpenchatroomDAO().read(userName, 0);
			assertThat(ochrs.size(), is(1));
		} finally {
			new OpenchatroomDAO().deleteByUsername(userName);
		}
	}
	
	@Test
	public void readAll() {
		List<Openchatroom> ochrs = new OpenchatroomDAO().readAll();
		assertThat(ochrs.size(), is(0));

		String userName = "userNameReadAll";
		String participantName1 = "participantNam1";
		String participantName2 = "participantNam2";
		
		Openchatroom ochr = new Openchatroom();
		ochr.setUserName(userName);
		ochr.setParticipantName(participantName1);
		ochr.setLeftTheChat(false);
		new OpenchatroomDAO().create(ochr);

		ochr = new Openchatroom();
		ochr.setUserName(userName);
		ochr.setParticipantName(participantName2);
		ochr.setLeftTheChat(false);
		new OpenchatroomDAO().create(ochr);
		
		try {
			ochrs = new OpenchatroomDAO().readAll();
			assertThat(ochrs.size(), is(2));
		} finally {
			new OpenchatroomDAO().deleteByUserNameAndParticipantName(userName, participantName1);
			new OpenchatroomDAO().deleteByUserNameAndParticipantName(userName, participantName2);
		}
		ochrs = new OpenchatroomDAO().readAll();
		assertThat(ochrs.size(), is(0));
	}
	
	@Test
	public void readActiveParticipants() {
		List<Openchatroom> ochrs = new OpenchatroomDAO().readAll();
		assertThat(ochrs.size(), is(0));

		String userName = "userNameReadAll";
		String participantName1 = "participantNam1";
		String participantName2 = "participantNam2";
		
		Openchatroom ochr = new Openchatroom();
		ochr.setUserName(userName);
		ochr.setParticipantName(participantName1);
		ochr.setLeftTheChat(false);
		new OpenchatroomDAO().create(ochr);

		ochr = new Openchatroom();
		ochr.setUserName(userName);
		ochr.setParticipantName(participantName2);
		ochr.setLeftTheChat(false);
		new OpenchatroomDAO().create(ochr);
		
		try {
			ochrs = new OpenchatroomDAO().readActiveParticipants(userName);
			assertThat(ochrs.size(), is(2));
		} finally {
			new OpenchatroomDAO().deleteByUserNameAndParticipantName(userName, participantName1);
			new OpenchatroomDAO().deleteByUserNameAndParticipantName(userName, participantName2);
		}
		ochrs = new OpenchatroomDAO().readAll();
		assertThat(ochrs.size(), is(0));
	}
	
	@Test
	public void deleteByUsername() {
		String userName = "userNameDelete";
		
		Openchatroom ochr = new Openchatroom();
		ochr.setUserName(userName);
		ochr.setParticipantName(userName);
		ochr.setLeftTheChat(false);
		new OpenchatroomDAO().create(ochr);

		try {
			List<Openchatroom> ochrs = new OpenchatroomDAO().read(userName, 0);
			assertThat(ochrs.size(), is(1));
		} finally {
			new OpenchatroomDAO().deleteByUsername(userName);
		}
		List<Openchatroom> ochrs = new OpenchatroomDAO().read(userName, 0);
		assertThat(ochrs.size(), is(0));
	}
	
	@Test
	public void deleteByUserNameAndParticipantName() {
		String userName = "userNameDeletUP";
		String participantName1 = "participantNam1";
		String participantName2 = "participantNam2";
		
		Openchatroom ochr = new Openchatroom();
		ochr.setUserName(userName);
		ochr.setParticipantName(participantName1);
		ochr.setLeftTheChat(false);
		new OpenchatroomDAO().create(ochr);

		ochr = new Openchatroom();
		ochr.setUserName(userName);
		ochr.setParticipantName(participantName2);
		ochr.setLeftTheChat(false);
		new OpenchatroomDAO().create(ochr);

		try {
			List<Openchatroom> ochrs = new OpenchatroomDAO().read(userName, 0);
			assertThat(ochrs.size(), is(2));
		} finally {
			new OpenchatroomDAO().deleteByUserNameAndParticipantName(userName, participantName1);
		}
		try {
			List<Openchatroom> ochrs = new OpenchatroomDAO().read(userName, 0);
			assertThat(ochrs.size(), is(1));
		} finally {
			new OpenchatroomDAO().deleteByUserNameAndParticipantName(userName, participantName2);
		}
		List<Openchatroom> ochrs = new OpenchatroomDAO().read(userName, 0);
		assertThat(ochrs.size(), is(0));
	}
}
