package ma.prizrakhard.mown.model;

import java.util.ArrayList;
import java.util.List;

public enum DirectionEnum {
	
	EST("E","E"), OUEST("O","O"), NORD("N","N"), SUD("S","S");
	
	private static List<DirectionEnum> listDirection = new ArrayList<>();
	
	static {
		listDirection.add(EST);
		listDirection.add(OUEST);
		listDirection.add(NORD);
		listDirection.add(NORD);
	}
	
	private String code;
	private String value;
	
	DirectionEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public static DirectionEnum getFromCode(String value) {
		for(DirectionEnum direction:listDirection) {
		if(direction.code.equalsIgnoreCase(value)) {
			return direction;
		}
	}
		return null;

	}
	
	public static DirectionEnum getFromValue(String value) {
		for(DirectionEnum direction:listDirection) {
		if(direction.code.equalsIgnoreCase(value) ){
			return direction;
		}
	}
		return null;

	}
	
	@Override
	public String toString() {
		return this.value+"";
	}
}
