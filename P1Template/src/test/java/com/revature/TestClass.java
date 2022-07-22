package com.revature;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.junit.Test;
import org.mockito.*;
import org.mockito.stubbing.OngoingStubbing;

import com.revature.daos.*;
import com.revature.models.*;

public class TestClass {

	//This is the class where you'll write your tests. 
	
	//If our Mockito lesson has to be moved, basic tests are fine. assertTrue/False, assertNull/NotNull, etc.
	
	@Test
	@SuppressWarnings("unchecked")
	public void testInsertReimb() {
		Reimbursement mockReimb = Mockito.spy(new Reimbursement(1, (Timestamp)null,(Timestamp)null,
				"",1,0,1,1,0));
		ReimbursementDAO reimbDAO = Mockito.spy(new ReimbursementDAO());
		
		ArrayList<Reimbursement> mockList = Mockito.mock(ArrayList.class);
		mockList = reimbDAO.getAllReimb();
		Mockito.verify(reimbDAO).getAllReimb();
		
		int preInsert = mockList.size();
		reimbDAO.insertReimb(mockReimb);
		Mockito.verify(reimbDAO).insertReimb(mockReimb);
		mockList = reimbDAO.getAllReimb();
		
		int postInsert = mockList.size();
		assertTrue(preInsert < postInsert);
		
	}
	
	@Test
	public void testGetRole() {
		
		RoleDAO mockRDAO = Mockito.spy(new RoleDAO());
		
		OngoingStubbing<Role> r = Mockito.when(mockRDAO.getRole(anyInt())).thenReturn(new Role(0,""));
		
		assertNotNull(r);
	}
	
}
