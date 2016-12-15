package Piece;

// Classe qui permet de déterminer un mouvement considéré autorisé d'une pièce
public class PiecePattern {

	private int directionX;
	private int directionY;
	private int distanceMax; // Exemple: Combien de L peut faire un cavalier? 1. Combien de mouvement peut faire un fou? 8.
	private boolean isAttackPattern; // Permet de dire si on doit faire uniquement ce mouvement si on "mange" une autre piece

	public PiecePattern(int x, int y, int bound, boolean forAttack)
	{
		this.directionX = x;
		this.directionY = y;
		this.distanceMax = bound;
		this.isAttackPattern = forAttack;
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


}
