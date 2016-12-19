package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Piece.*;
import classes.Player.Color;

public class PieceTest {
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
		//Le fou n'a pas de coup special
		Fou fouTest = new Fou(Color.BLACK);
		assertEquals((boolean)Boolean.FALSE, fouTest.canSpecialMove());

		fouTest.hasMoved();
		assertEquals((boolean)Boolean.FALSE, fouTest.canSpecialMove());
	}

	/*
	 * REMARQUE : 
	 * pionTest.getPattern(0, -1) ie 
	 * pionTest.getPattern(patternPionAvance.getDirectionX, patternPionAvance.getDirectionY)
	 */
	@Test 
	public void getPatternTest()
	{
		Fou fouTest = new Fou(Color.BLACK);
		Pion pionTest = new Pion(Color.BLACK);
		Tour tourTest = new Tour(Color.BLACK);
		Reine reineTest = new Reine(Color.BLACK);

		PiecePattern patternLigne = new PiecePattern(1,0,8,true,true);
		PiecePattern patternDiagonale = new PiecePattern(1,1,8,true,true);
		PiecePattern patternPionAvance = new PiecePattern(0,-1,2,false,true);

		//Pattern Fou
		//peut-il bouger en ligne ? 
		assertEquals(null,fouTest.getPattern(1, 0));
		//peut-il bouger en diagonale ? 
		assertEquals(patternDiagonale,fouTest.getPattern(1, 1));

		//Pattern Pion
		//Peut-il reculer ? 
		assertEquals(null,pionTest.getPattern(0, 1));
		//Peut-il bouger d'une seule case vers le haut ? 
		assertEquals(patternPionAvance , pionTest.getPattern(0, -1));
		//Peut-il faire son coup special ? 
		assertEquals(patternPionAvance, pionTest.getPattern(0, -2));

		//Pattern Tour
		//Diagonale ? 
		assertEquals(null, tourTest.getPattern(1,1));
		//Ligne ? 
		assertEquals(patternLigne, tourTest.getPattern(1, 0));

		//Pattern Reine
		//Diagonale ? 
		assertEquals(patternDiagonale, reineTest.getPattern(1, 1));
		//Ligne ?
		assertEquals(patternLigne, reineTest.getPattern(1, 0));
	}

}
