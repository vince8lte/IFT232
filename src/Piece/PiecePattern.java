package Piece;

// Classe qui permet de déterminer un mouvement considéré autorisé d'une pièce
public class PiecePattern {

	private int directionX;
	private int directionY;
	private int distanceMax; // Exemple: Combien de L peut faire un cavalier? 1. Combien de mouvement peut faire un fou? 8.
	private boolean isAttackPattern; 	//Indique si le pattern permet une attaque
	private boolean isMovementPattern; 	//Indique si le pattern permet le deplacement

	public PiecePattern(int x, int y, int bound, boolean forAttack, boolean forMouvement)
	{
		this.directionX = x;
		this.directionY = y;
		this.distanceMax = bound;
		this.isAttackPattern = forAttack;
		this.isMovementPattern = forMouvement;
	}

	public int getDirectionX() {
		return directionX;
	}

	public int getDirectionY() {
		return directionY;
	}

	public int getDistanceMax() {
		return distanceMax;
	}

	public boolean isAttackPattern() {
		return isAttackPattern;
	}
	
	public boolean isMouvementPattern() {
		return isMovementPattern;
	}
	
	@Override
	public String toString(){
		return "["+directionX+","+directionY+","+distanceMax+","+isAttackPattern+","+isMovementPattern+"]";
	}
}
