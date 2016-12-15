package Piece;

public class PiecePatterns {

	private int directionX;
	private int directionY;
	private int distanceMax;
	private boolean isAttackPattern;

	public PiecePatterns(int x, int y, int bound, boolean forAttack)
	{
		this.directionX = x;
		this.directionY = y;
		this.distanceMax = bound;
		this. isAttackPattern = forAttack;
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
