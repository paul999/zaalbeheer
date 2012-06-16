package tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(TestDag.class);
		suite.addTestSuite(TestDagCollectie.class);
		//$JUnit-END$
		return suite;
	}

}
