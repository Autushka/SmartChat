package modernbox.smartchat.dal;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import modernbox.smartchat.dal.OpenchatroomsmessageDAO;
import modernbox.smartchat.dal.model.Openchatroomsmessage;

import org.junit.Before;
import org.junit.Test;


public class OpenchatroomsmessageDAOTest {
    
	@Before
    public void setUp() throws Exception {
		SetUp.setUp();
    }

	@Test
	public void readAndCreate() {
		String sPerformer = "TestReadPerform";
		String sUserName = "TestReadUser";
		String sMessage = "TestReadMessage_" + // should be more than 255 characters 
				"0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_" +
				"0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_" +
				"0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_0123456789_";
		
		Openchatroomsmessage ochrm = new Openchatroomsmessage();
		ochrm.setUserName(sPerformer);
		ochrm.setParticipantName(sUserName);
		ochrm.setMessage(sMessage);
		ochrm.setCreatedEpochTime(System.currentTimeMillis() / 1000);
		new OpenchatroomsmessageDAO().create(ochrm);
		
		try {
			List<Openchatroomsmessage> ochrms = new OpenchatroomsmessageDAO().read(sPerformer, 0L);
			assertThat(ochrms.size(), is(1));
		} finally {
			long epochTime = System.currentTimeMillis() / 1000 + 60;// current time plus 1 minute
			new OpenchatroomsmessageDAO().deleteByPerformer(sPerformer, epochTime);
		}
	}
	
	@Test
	public void deleteByUserName() {
		String sPerformer = "TestDeleteByUse";
		String sUserName = "TestDeleteByUse";
		String sMessage = "TestDeleteByUserMessage";
		
		Openchatroomsmessage ochrm = new Openchatroomsmessage();
		ochrm.setUserName(sPerformer);
		ochrm.setParticipantName(sUserName);
		ochrm.setMessage(sMessage);
		ochrm.setCreatedEpochTime(System.currentTimeMillis() / 1000);
		new OpenchatroomsmessageDAO().create(ochrm);
		
		List<Openchatroomsmessage> ochrms = new OpenchatroomsmessageDAO().read(sPerformer, 0L);
		try {
			assertThat(ochrms.size(), is(1));
		} finally {
			new OpenchatroomsmessageDAO().deleteByUserName(sPerformer);
		}
		ochrms = new OpenchatroomsmessageDAO().read(sPerformer, 0L);
		assertThat(ochrms.size(), is(0));
	}
	
	@Test 
	public void deleteByPerformer() {
		String sPerformer = "TestDeleteByPer";
		String sUserName = "TestDeleteByPer";
		String sMessage = "TestDeleteByPerformerMessage";
		
		Openchatroomsmessage ochrm = new Openchatroomsmessage();
		ochrm.setUserName(sPerformer);
		ochrm.setParticipantName(sUserName);
		ochrm.setMessage(sMessage);
		ochrm.setCreatedEpochTime(System.currentTimeMillis() / 1000);
		new OpenchatroomsmessageDAO().create(ochrm);
		
		List<Openchatroomsmessage> ochrms = new OpenchatroomsmessageDAO().read(sPerformer, 0L);
		try {
			assertThat(ochrms.size(), is(1));
		} finally {
			long epochTime = System.currentTimeMillis() / 1000 + 60;// current time plus 1 minute
			new OpenchatroomsmessageDAO().deleteByPerformer(sPerformer, epochTime);
		}
		ochrms = new OpenchatroomsmessageDAO().read(sPerformer, 0L);
		assertThat(ochrms.size(), is(0));
	}
	
	@Test 
	public void getMaxRowID() {
		String sPerformer = "TestMaxRowIDPer";
		String sUserName = "TestMaxRowIDUse";
		String sMessage = "TestMaxRowIDMessage";
		
		Openchatroomsmessage ochrm = new Openchatroomsmessage();
		ochrm.setUserName(sPerformer);
		ochrm.setParticipantName(sUserName);
		ochrm.setMessage(sMessage);
		ochrm.setCreatedEpochTime(System.currentTimeMillis() / 1000);
		new OpenchatroomsmessageDAO().create(ochrm);
		Long maxRowID1 = new OpenchatroomsmessageDAO().getMaxRowID(sPerformer);

		ochrm = new Openchatroomsmessage();
		ochrm.setUserName(sPerformer);
		ochrm.setParticipantName(sUserName);
		ochrm.setMessage(sMessage);
		ochrm.setCreatedEpochTime(System.currentTimeMillis() / 1000);
		new OpenchatroomsmessageDAO().create(ochrm);
		Long maxRowID2 = new OpenchatroomsmessageDAO().getMaxRowID(sPerformer);

		try {
			assertThat(maxRowID1, is(maxRowID2 - 1));
		} finally {
			long epochTime = System.currentTimeMillis() / 1000 + 60;// current time plus 1 minute
			new OpenchatroomsmessageDAO().deleteByPerformer(sPerformer, epochTime);
		}
	}
}
