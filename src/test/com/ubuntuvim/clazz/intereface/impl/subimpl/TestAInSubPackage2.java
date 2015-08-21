package test.com.ubuntuvim.clazz.intereface.impl.subimpl;

import test.com.ubuntuvim.clazz.intereface.ITestA;

public class TestAInSubPackage2 implements ITestA {

//	static  {
//		System.out.println("TestAInSubPackage2 init.....");
//	}
	
	@Override
	public void test() {
		System.out.println("TestAInSubPackage2....");
	}

}
