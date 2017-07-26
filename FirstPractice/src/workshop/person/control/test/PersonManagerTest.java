package workshop.person.control.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


import workshop.person.control.PersonManager;
import workshop.person.entity.PersonEntity;

public class PersonManagerTest{

	PersonManager mgr;
	PersonEntity[] persons;
	
	@Before
	public void init() {
		mgr = new PersonManager();
		persons = new PersonEntity[10];
		
	}
	@Test
	public void findByGender() {
		mgr.filePersons(persons);
	}
	
	@Test
	@Ignore
	public void findByGensdder() {
		mgr.filePersons(persons);
	}
	
	@After
	public void finish() {
		
	}
	
}
