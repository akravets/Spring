package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeTest {
	@Mock
	Developer developerMock;
	
	@Test
	public void testGetType(){
		/**
		 *  We can define our mock object like this, or we can remove this line and use 
		 *  @Mock annotation as we did above
		 */
		//IEmployee developerMock = mock(Developer.class);
		when(developerMock.getType()).thenReturn(IEmployee.Type.CONTRACT);
		
		Developer developer = new Developer();
		
		System.out.println(developer.getId());
		assertEquals(developerMock.getType(), developer.getType());
		
	}

}
