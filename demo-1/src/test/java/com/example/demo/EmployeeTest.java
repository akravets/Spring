package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class EmployeeTest {
	@Test
	public void testGetType(){
		IEmployee developerMock = mock(Developer.class);
		when(developerMock.getType()).thenReturn(IEmployee.Type.SALARY);
		
		Developer developer = new Developer();
		
		assertEquals(developerMock.getType(), developer.getType());
		
	}

}
