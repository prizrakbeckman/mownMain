package ma.prizrakhard.mown.model;

public class Tondeuse {
	
	private Position position;
	private String sequenceOfDirectives;
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position p) {
		this.position = p;
	}

	public String getSequenceOfDirectives() {
		return sequenceOfDirectives;
	}
	
	public void setSequenceOfDirectives(String sequenceOfDirectives) {
		this.sequenceOfDirectives = sequenceOfDirectives;
	}
	
	@Override
	public String toString() {
		return this.position.toString();
	}

}
