package intexsoftBookLibrary.library.tests.library.create;

import static org.junit.Assert.*;
import intexsoftBookLibrary.dao.mysql.exceptions.HiberanteConfigException;
import intexsoftBookLibrary.library.Library;
import intexsoftBookLibrary.library.tests.library.LibraryTest;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NormalWithoutBooks extends LibraryTest {	
	@Before
	public void setUp() throws Exception {
		super.init();
	}

	@After
	public void tearDown() throws Exception {
		finish();
	}
	
	@Test
	public void test() throws HibernateException, HiberanteConfigException{
		//�������� �������� ����������
		Library expected = new Library(null, "TestLibrary");
		
		//���������� �������� ���������� � ��
		saveLibrary(expected);
		
		//������ ���������� ���������� �� ��
		Library actual = getLibrary(expected.getId());
		
		// ��������� �������� ��������� � �������� ���������� 
		assertEquals(expected, actual);
	}
}
