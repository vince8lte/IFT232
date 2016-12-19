package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import GraphicsInterface.IRenderable;
import Piece.Piece;
import Piece.*;
import classes.Board;
import classes.Player;
import classes.Player.Color;

public class BoardTest 
{
	private Board board;
	private Player[] players;
	private FileReader fileReader ;
	private BufferedReader br;
	private String filePath = "ressources/gridForTest.txt";
	private final int INITWHITEKINGX =4;
	private final int INITWHITEKINGY =0;
	private final int INITBLACKKINGX =4;
	private final int INITBLACKKINGY =7;
	private final int BOARD_SIZE = 8;
	private Piece[][] boardTest;

	@Before
	public void setUp() throws FileNotFoundException
	{
		this.board = new Board();
		players = new Player[2];
		players[0] = Player.createPlayer(Color.WHITE);
		players[1] = Player.createPlayer(Color.BLACK);
		fileReader = new FileReader(filePath);
		br = new BufferedReader(fileReader);
	}

	@After
	public void tearDown()
	{
		this.board = null;
		players = null;
		fileReader = null;
		br = null;
	}

	/*TESTS*/
	@Test
	public void loadBoardTest()
	{	
		//voir fichier gridForTest.txt
		//fonction a tester
		board.loadBoard(br);


		//construit la grille test
		InitTestBoard();

		//Recupere une LinkedList a partir d'un Piece[][]
		//voir board.getBoard && board.getPiece
		LinkedList<IRenderable> piecesLL = new LinkedList<IRenderable>();
		for (int i = 0; i < (BOARD_SIZE*BOARD_SIZE); ++i)
			piecesLL.add(boardTest[i%8][i/8]);

		assertEquals(piecesLL, board.getBoard());
	}

	@Test
	public void SelectedPieceTest()
	{
		board.loadBoard(br);
		//initialisation : No selected piece
		assertEquals(this.board.pieceIsSelected() , (boolean)Boolean.FALSE);
		//on selectionne une piece
		board.selectPiece(INITWHITEKINGX,INITWHITEKINGY, players[0].getColor());
		assertEquals(this.board.pieceIsSelected(), (boolean)Boolean.TRUE);
		board.unselectPiece();
		assertEquals(this.board.pieceIsSelected() , (boolean)Boolean.FALSE);

	}

	@Test
	public void equalsPieceSelectedTest()
	{
		board.loadBoard(br);

		//selected = Roi Blanc
		board.selectPiece(INITWHITEKINGX,INITWHITEKINGY, players[0].getColor());
		assertEquals((boolean)Boolean.TRUE, board.equalsPieceSelected(INITWHITEKINGX, INITWHITEKINGY));
		board.unselectPiece();

		//selected = Roi Noir
		board.selectPiece(INITBLACKKINGX,INITBLACKKINGY, players[0].getColor());
		assertEquals((boolean)Boolean.FALSE, board.equalsPieceSelected(INITWHITEKINGX, INITWHITEKINGY));
	}

	@Test
	public void moveSelectedPieceTest()
	{
		board.loadBoard(br);

		//selected = Roi blanc
		board.selectPiece(INITWHITEKINGX,INITWHITEKINGY, players[0].getColor());
		//on met le roi sur une case deja occupee
		assertEquals((boolean)Boolean.FALSE , board.moveSelectedPieceTo(3, 0, Color.WHITE));
		board.unselectPiece();

		//selected = Pion blanc
		board.selectPiece(4,1, players[0].getColor());
		//case libre
		assertEquals((boolean)Boolean.TRUE , board.moveSelectedPieceTo(4, 2, Color.WHITE));

		//selected = Pion noir
		board.selectPiece(4,6, players[1].getColor());
		//Saut du pion (1er mouvement)
		assertEquals((boolean)Boolean.TRUE , board.moveSelectedPieceTo(4, 4, Color.WHITE));

		//TESTS A AJOUTER? 
	}

	/* NOT A TEST*/
	private void InitTestBoard()
	{
		this.boardTest = new Piece[BOARD_SIZE][BOARD_SIZE];

		/*WHITES*/
		this.boardTest[0][0]= new Tour(Color.WHITE);
		this.boardTest[1][0]= new Cavalier(Color.WHITE);
		this.boardTest[2][0]= new Fou(Color.WHITE);
		this.boardTest[3][0]= new Reine(Color.WHITE);
		this.boardTest[4][0]= new Roi(Color.WHITE);
		this.boardTest[5][0]= new Fou(Color.WHITE);
		this.boardTest[6][0]= new Cavalier(Color.WHITE);
		this.boardTest[7][0]= new Tour(Color.WHITE);
		for (int i=0; i<BOARD_SIZE ; i++)
		{
			this.boardTest[i][1]=new Pion(Color.WHITE);
		}

		/*BLACKS*/
		for (int i=0; i<BOARD_SIZE ; i++)
		{
			this.boardTest[i][6]=new Pion(Color.BLACK);
		}
		this.boardTest[0][7]= new Tour(Color.BLACK);
		this.boardTest[1][7]= new Cavalier(Color.BLACK);
		this.boardTest[2][7]= new Fou(Color.BLACK);
		this.boardTest[3][7]= new Reine(Color.BLACK);
		this.boardTest[4][7]= new Roi(Color.BLACK);
		this.boardTest[5][7]= new Fou(Color.BLACK);
		this.boardTest[6][7]= new Cavalier(Color.BLACK);
		this.boardTest[7][7]= new Tour(Color.BLACK);

	}

}
