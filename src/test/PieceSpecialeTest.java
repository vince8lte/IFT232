package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Piece.*;
import classes.Player.Color;

public class PieceSpecialeTest {

	@Before
	public void setUp()
	{

	}

	@After
	public void tearDown()
	{

	}
	
	@Test
	public void hasMovedTest()
	{
		Pion pionTest = new Pion(Color.BLACK);
		assertEquals((boolean)Boolean.TRUE, pionTest.canSpecialMove());
		
		pionTest.hasMoved();
		assertEquals((boolean)Boolean.FALSE, pionTest.canSpecialMove());
	}

}
