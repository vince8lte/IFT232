package classes;

public class Player {
	private Color color;
	
	public enum Color {
		WHITE,
		BLACK,
		NONE
	}	
	
	private Player(Color color) {
		this.color = color;
	}
	
	public static Player createPlayer(Color color)
	{
	    if (color != Color.NONE)
	        return new Player(color);
	    else
	        return null;
	}
	
	public Color getColor() {
		return color;
	}
}
