/**
 * 
 */
package tests;

import nl.paul.sohier.ttv.libary.Dag;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author paul
 *
 */
public class TestDag extends TestCase {

	/**
	 * @param name
	 */
	public TestDag(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * Test method for {@link nl.paul.sohier.ttv.libary.Dag#Dag(int, int, int)}.
	 */
	public void testDagIntIntInt() {
		int d = 1;
		int m = 2;
		int j = 3;
		Dag d2 = new Dag(d, m, j);
		
		Assert.assertEquals(d2.getDag(), d);
		Assert.assertEquals(d2.getMaand(), m);
		Assert.assertEquals(d2.getJaar(), j);
		
	}

	/**
	 * Test method for {@link nl.paul.sohier.ttv.libary.Dag#Dag()}.
	 */
	public void testDag() {
		Dag d2 = new Dag();
		
		Assert.assertEquals(0, d2.getDag());
		Assert.assertEquals(0, d2.getMaand());
		Assert.assertEquals(0, d2.getJaar());
	}

	/**
	 * Test method for {@link nl.paul.sohier.ttv.libary.Dag#isOpen()}.
	 */
	public void testIsOpen() {
		Dag d2 = new Dag();
		
		d2.setOpen(true);

		Assert.assertTrue(d2.isOpen());
		
		d2.setOpen(false);
		Assert.assertFalse(d2.isOpen());
		
		
		
	}

	/**
	 * Test method for {@link nl.paul.sohier.ttv.libary.Dag#setOpen(boolean)}.
	 */
	public void testSetOpen() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.paul.sohier.ttv.libary.Dag#getZaaldienst()}.
	 */
	public void testGetZaaldienst() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.paul.sohier.ttv.libary.Dag#setZaaldienst(nl.paul.sohier.ttv.libary.Zaaldienst)}.
	 */
	public void testSetZaaldienst() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.paul.sohier.ttv.libary.Dag#getDag()}.
	 */
	public void testGetDag() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.paul.sohier.ttv.libary.Dag#setDag(int)}.
	 */
	public void testSetDag() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.paul.sohier.ttv.libary.Dag#getMaand()}.
	 */
	public void testGetMaand() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.paul.sohier.ttv.libary.Dag#setMaand(int)}.
	 */
	public void testSetMaand() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.paul.sohier.ttv.libary.Dag#getJaar()}.
	 */
	public void testGetJaar() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.paul.sohier.ttv.libary.Dag#setJaar(int)}.
	 */
	public void testSetJaar() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.paul.sohier.ttv.libary.Dag#equals(java.lang.Object)}.
	 */
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nl.paul.sohier.ttv.libary.Dag#toString()}.
	 */
	public void testToString() {
		fail("Not yet implemented");
	}

}
