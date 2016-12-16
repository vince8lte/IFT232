package Piece;

import Piece.PiecePattern;

public class PatternFactory {
	
	private static PatternFactory singleton;
	
	public PatternFactory(){}
	
	public static PatternFactory getInstance(){
		if(singleton == null){
			singleton = new PatternFactory();
		}
		return singleton;
	}
	
	public PiecePattern[] getTowerPattern(){
		PiecePattern[] patterns = new PiecePattern[4];
		
		patterns[0] = new PiecePattern(1,0,8,true,true);
		patterns[1] = new PiecePattern(-1,0,8,true,true);
		patterns[2] = new PiecePattern(0,1,8,true,true);
		patterns[3] = new PiecePattern(0,-1,8,true,true);
		
		return patterns;
	}
	
	public PiecePattern[] getBishopPattern(){
		PiecePattern[] patterns = new PiecePattern[4];
		
		patterns[0] = new PiecePattern(1,1,8,true,true);
		patterns[1] = new PiecePattern(1,-1,8,true,true);
		patterns[2] = new PiecePattern(-1,1,8,true,true);
		patterns[3] = new PiecePattern(-1,-1,8,true,true);
		
		return patterns;
	}
	
	public PiecePattern[] getKingPattern(){
		PiecePattern[] patterns = new PiecePattern[8];
		
		patterns[0] = new PiecePattern(1,0,1,true,true);
		patterns[1] = new PiecePattern(-1,0,1,true,true);
		patterns[2] = new PiecePattern(0,1,1,true,true);
		patterns[3] = new PiecePattern(0,-1,1,true,true);
		patterns[4] = new PiecePattern(1,1,1,true,true);
		patterns[5] = new PiecePattern(1,-1,1,true,true);
		patterns[6] = new PiecePattern(-1,1,1,true,true);
		patterns[7] = new PiecePattern(-1,-1,1,true,true);
		
		return patterns;
	}
	
	public PiecePattern[] getQueenPattern(){
		PiecePattern[] patterns = new PiecePattern[8];
		
		patterns[0] = new PiecePattern(1,0,8,true,true);
		patterns[1] = new PiecePattern(-1,0,8,true,true);
		patterns[2] = new PiecePattern(0,1,8,true,true);
		patterns[3] = new PiecePattern(0,-1,8,true,true);
		patterns[4] = new PiecePattern(1,1,8,true,true);
		patterns[5] = new PiecePattern(1,-1,8,true,true);
		patterns[6] = new PiecePattern(-1,1,8,true,true);
		patterns[7] = new PiecePattern(-1,-1,8,true,true);
		
		return patterns;
	}
	
	public PiecePattern[] getKnightPattern(){
		PiecePattern[] patterns = new PiecePattern[8];
		
		patterns[0] = new PiecePattern(1,2,1,true,true);
		patterns[1] = new PiecePattern(1,-2,1,true,true);
		patterns[2] = new PiecePattern(-1,2,1,true,true);
		patterns[3] = new PiecePattern(-1,-2,1,true,true);
		patterns[4] = new PiecePattern(2,1,1,true,true);
		patterns[5] = new PiecePattern(2,-1,1,true,true);
		patterns[6] = new PiecePattern(-2,1,1,true,true);
		patterns[7] = new PiecePattern(-2,-1,1,true,true);
		
		return patterns;
	}
	
	public PiecePattern[] getWhitePawnPattern(){
		PiecePattern[] patterns = new PiecePattern[3];
		
		patterns[0] = new PiecePattern(1,1,1,true,false);
		patterns[1] = new PiecePattern(-1,1,1,true,false);
		patterns[2] = new PiecePattern(0,1,2,false,true);
		
		return patterns;
	}
	
	public PiecePattern[] getBlackPawnPattern(){
		PiecePattern[] patterns = new PiecePattern[3];
		
		patterns[0] = new PiecePattern(1,-1,1,true,false);
		patterns[1] = new PiecePattern(-1,-1,1,true,false);
		patterns[2] = new PiecePattern(0,-1,2,false,true);
		
		return patterns;
	}
	
	public PiecePattern[] getAllPattern(){
		PiecePattern[] patterns = new PiecePattern[16];
		
		patterns[0] = new PiecePattern(1,0,8,true,true);
		patterns[1] = new PiecePattern(-1,0,8,true,true);
		patterns[2] = new PiecePattern(0,1,8,true,true);
		patterns[3] = new PiecePattern(0,-1,8,true,true);
		patterns[4] = new PiecePattern(1,1,8,true,true);
		patterns[5] = new PiecePattern(1,-1,8,true,true);
		patterns[6] = new PiecePattern(-1,1,8,true,true);
		patterns[7] = new PiecePattern(-1,-1,8,true,true);
		patterns[8] = new PiecePattern(1,2,1,true,true);
		patterns[9] = new PiecePattern(1,-2,1,true,true);
		patterns[10] = new PiecePattern(-1,2,1,true,true);
		patterns[11] = new PiecePattern(-1,-2,1,true,true);
		patterns[12] = new PiecePattern(2,1,1,true,true);
		patterns[13] = new PiecePattern(2,-1,1,true,true);
		patterns[14] = new PiecePattern(-2,1,1,true,true);
		patterns[15] = new PiecePattern(-2,-1,1,true,true);
		
		return patterns;
	}
	

}
