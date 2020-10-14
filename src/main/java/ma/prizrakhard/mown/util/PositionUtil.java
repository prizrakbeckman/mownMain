package ma.prizrakhard.mown.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import ma.prizrakhard.mown.model.DirectionEnum;
import ma.prizrakhard.mown.model.Grille;
import ma.prizrakhard.mown.model.Position;
import ma.prizrakhard.mown.model.Tondeuse;

public class PositionUtil {

	private static final int X_MIN = 0;
	private static final int Y_MIN = 0;

	// n taille de la longueur de la grille (X)
	// m taille de la largeur de grille

	private static boolean verifierFrontieres(Position position, int n, int m) {
		Predicate<Position> p = pos -> pos.getX() <= n && pos.getY() <= m && pos.getX() >= X_MIN && pos.getY() >= Y_MIN;

		return p.test(position);
	}

	private PositionUtil() {

	}

	// Orientation c.à.d gauche ou droite

	public static void getPosition(Position positionCourante, String orientation) {

		DirectionEnum de = positionCourante.getDirection();

		if (Constantes.DROITE.equalsIgnoreCase(orientation)) {
			if (DirectionEnum.EST == de) {
				positionCourante.setDirection(DirectionEnum.SUD);
			} else if (DirectionEnum.NORD == de) {
				positionCourante.setDirection(DirectionEnum.EST);
			} else if (DirectionEnum.OUEST == de) {
				positionCourante.setDirection(DirectionEnum.NORD);
			} else if (DirectionEnum.SUD == de) {
				positionCourante.setDirection(DirectionEnum.OUEST);
			}
		}
		if (Constantes.GAUCHE.equals(orientation)) {
			if (DirectionEnum.EST == de) {
				positionCourante.setDirection(DirectionEnum.NORD);
			} else if (DirectionEnum.NORD == de) {
				positionCourante.setDirection(DirectionEnum.OUEST);
			} else if (DirectionEnum.OUEST == de) {
				positionCourante.setDirection(DirectionEnum.SUD);
			} else if (DirectionEnum.SUD == de) {
				positionCourante.setDirection(DirectionEnum.EST);
			}
		}
	}

	public static void avancerTondeuse(Position p, int longueur, int largeur, int[][] positionsOccupees) {

		Position pCourant = new Position();
		boolean inMown = false;

		int x = p.getX();
		int y = p.getY();

		pCourant.setX(x);
		pCourant.setY(y);

		if (DirectionEnum.OUEST == p.getDirection()) {
			pCourant.setX(x - 1);
			inMown = verifierFrontieres(pCourant, longueur, largeur)
					&& !verifierCollisionTondeuses(pCourant, positionsOccupees);
			if (inMown)
				p.setX(x - 1);
		} else if (DirectionEnum.EST == p.getDirection()) {
			pCourant.setX(x + 1);
			inMown = verifierFrontieres(pCourant, longueur, largeur)
					&& !verifierCollisionTondeuses(pCourant, positionsOccupees);
			if (inMown)
				p.setX(x + 1);
		}

		else if (DirectionEnum.SUD == p.getDirection()) {
			pCourant.setY(y - 1);
			inMown = verifierFrontieres(pCourant, longueur, largeur)
					&& !verifierCollisionTondeuses(pCourant, positionsOccupees);
			if (inMown)
				p.setY(y - 1);
		} else if (DirectionEnum.NORD == p.getDirection()) {
			pCourant.setY(y + 1);
			inMown = verifierFrontieres(pCourant, longueur, largeur)
					&& !verifierCollisionTondeuses(pCourant, positionsOccupees);
			if (inMown)
				p.setY(y + 1);
		}

	}

	public static List<Tondeuse> getTondeusesFromFile(InputStreamReader is, Grille g) throws Exception {
		List<Tondeuse> tondeuses = new ArrayList<>();
		String line = "";
		try (BufferedReader bf = new BufferedReader(is)) {
			String[] ligneTaille = bf.readLine().split(Constantes.BLANK);
			if (ligneTaille.length == 2) {

				int x = Integer.parseInt(ligneTaille[0]);
				int y = Integer.parseInt(ligneTaille[1]);

				g.setLongueur(x);
				g.setLargeur(y);
			}
			while ((line = bf.readLine()) != null) {
				String[] linePosition = line.split(Constantes.BLANK);
				Tondeuse d = new Tondeuse();
				Position p = new Position();
				if (linePosition.length >= 3) {
					p.setX(Integer.parseInt(linePosition[0]));
					p.setY(Integer.parseInt(linePosition[1]));
					p.setDirection(DirectionEnum.getFromCode(linePosition[2]));

					d.setPosition(p);
				}
				if ((line = bf.readLine()) != null) {
					d.setSequenceOfDirectives(line);
				}
				tondeuses.add(d);

			}

		}
		return tondeuses;
	}

	private static boolean verifierCollisionTondeuses(Position p, int[][] positionsOccupees) {
		Predicate<Position> predicate = (pos -> pos.getX() == 0 && pos.getY() == 0 && positionsOccupees[0][0] == 1);
		
		if(!predicate.test(p)){
		predicate.or(pos -> positionsOccupees[pos.getX() - 1][pos.getY() - 1] == 1);

		predicate.or(pos -> pos.getX() == 0 && pos.getY() > 0 ? positionsOccupees[0][pos.getY() - 1] == 1
				: positionsOccupees[pos.getX()][pos.getY() - 1] == 1);

		predicate.or(pos -> pos.getY() == 0 && pos.getX() > 0 ? positionsOccupees[p.getX()][0] == 1
				: positionsOccupees[pos.getX()][pos.getY() - 1] == 1);

		}
		
		boolean isCollision = false;
		if (predicate.test(p))
			isCollision = true;
		return isCollision;
	}
}
