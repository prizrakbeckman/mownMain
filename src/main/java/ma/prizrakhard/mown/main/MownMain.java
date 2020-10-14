package ma.prizrakhard.mown.main;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;

import org.apache.log4j.Logger;

import ma.prizrakhard.mown.model.Grille;
import ma.prizrakhard.mown.model.Position;
import ma.prizrakhard.mown.model.Tondeuse;
import ma.prizrakhard.mown.util.Constantes;
import ma.prizrakhard.mown.util.PositionUtil;

public class MownMain {

	// Cet attribut est utilisé pour le test unitaire sur les valeurs données en
	// exemple dans le test.
	private static List<Tondeuse> listFinal = new ArrayList<>();

	private static final Logger log = Logger.getLogger(MownMain.class);

	public static void main(String[] args) {
		printValuesOfPositions();
	}

	private static void initPositionOccupees(int[][] positionOccupees) {
		for (int i = 0; i < positionOccupees.length; i++) {
			for (int j = 0; j < positionOccupees[i].length; j++) {
				positionOccupees[i][j] = 0;
			}
		}
	}

	public static void printValuesOfPositions() {
		
		int[][] positionOccupees;
		Predicate<String> predicateAvance = Constantes.AVANCER::equalsIgnoreCase;
		Predicate<String> predicateGauche = Constantes.GAUCHE::equalsIgnoreCase;
		Predicate<String> predicateGaucheOuDroite = predicateGauche.or(Constantes.DROITE::equalsIgnoreCase);

		log.info("Beginning of program");
		
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			String resourceName = "conf/appProperties.properties"; // could also be a constant
			InputStream resourceStream = loader.getResourceAsStream(resourceName);

			Properties props = new Properties();
			props.load(resourceStream);

			URL url = MownMain.class.getResource(props.getProperty("fileToBeRead"));
			if (url == null)
				return;
			try (InputStream is = url.openStream()) {

				InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
				Grille g = new Grille();
				List<Tondeuse> tondeuses = PositionUtil.getTondeusesFromFile(streamReader, g);
				int grilleLongueur = g.getLongueur();
				int grilleLargeur = g.getLargeur();
				positionOccupees = new int[grilleLongueur][grilleLargeur];
				initPositionOccupees(positionOccupees);
				tondeuses.stream().forEach(t -> {

					String[] sequences = t.getSequenceOfDirectives().split("");
					Position p = t.getPosition();

					Arrays.stream(sequences).forEach(orientation -> {
						if (predicateAvance.test(orientation)) {
							PositionUtil.avancerTondeuse(p, grilleLongueur, grilleLargeur, positionOccupees);
						} else if (predicateGaucheOuDroite.test(orientation)) {
							PositionUtil.getPosition(p, orientation);
						}
					});

					positionOccupees[t.getPosition().getX() - 1][t.getPosition().getY() - 1] = 1;
					log.info(t);
					listFinal.add(t);
				});

			} catch (Exception e) {
				log.error(e.getMessage());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			log.error("End of program");
		}
	}

	public static List<Tondeuse> getListFinal() {
		return listFinal;
	}

}
