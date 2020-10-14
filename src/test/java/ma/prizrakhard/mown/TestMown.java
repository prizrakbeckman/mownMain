package ma.prizrakhard.mown;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import ma.prizrakhard.mown.main.MownMain;
import ma.prizrakhard.mown.model.DirectionEnum;
import ma.prizrakhard.mown.model.Position;
import ma.prizrakhard.mown.model.Tondeuse;

public class TestMown {
	
	private static Logger logger = Logger.getLogger(MownMain.class);

	
	@Before
	public void initlistTondeuse() {
		logger.info("Tester le programme ");
		
	}
	@Test
	public void testMown() {

		Predicate<List<Tondeuse>> predicateTondeuse = (tondeuss) ->{
			Position p0 = tondeuss.get(0).getPosition();
			return (p0.getX() == 1 && p0.getY()==3 && p0.getDirection().equals(DirectionEnum.NORD) );
			 
		};
		
		MownMain.printValuesOfPositions();
		assertTrue(predicateTondeuse.test(MownMain.getListFinal()));
	}
	
	

}
