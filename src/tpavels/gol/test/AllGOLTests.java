package tpavels.gol.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllGOLTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllGOLTests.class.getName());

		//$JUnit-BEGIN$
		suite.addTestSuite(CellImplTest.class);
		suite.addTestSuite(FieldImplTest.class);
		suite.addTestSuite(FieldIterImplTest.class);
		//$JUnit-END$

		return suite;
	}

}
