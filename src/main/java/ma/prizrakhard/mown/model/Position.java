package ma.prizrakhard.mown.model;

public class Position {
	
	
	private int x;
	private int y;
	private DirectionEnum direction;
	

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public DirectionEnum getDirection() {
		return direction;
	}
	public void setDirection(DirectionEnum direction) {
		this.direction = direction;
	}
	
	@Override
	public String toString() {
		return this.x+" "+this.y + " "+ this.direction;
	}
}
