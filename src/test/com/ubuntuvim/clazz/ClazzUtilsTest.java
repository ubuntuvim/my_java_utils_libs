package test.com.ubuntuvim.clazz;

import java.util.List;

import org.junit.Test;

import test.com.ubuntuvim.clazz.intereface.ITestA;

import com.ubuntuvim.clazz.ClazzUtils;

public class ClazzUtilsTest {

//	private static ClazzUtils cu = null;
//	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		cu = new ClazzUtils();
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//		cu = null;
//	}

	@Test
	public void testGetClassByInterface() {
		List<Class<?>> cls = ClazzUtils.getClassByInterface(ITestA.class);
//		List<Class<?>> cls = ClazzUtils.getClassByInterface(ITestB.class);
		for (Class<?> c : cls) {
			System.out.println(c.getName());
		}
	}

}
