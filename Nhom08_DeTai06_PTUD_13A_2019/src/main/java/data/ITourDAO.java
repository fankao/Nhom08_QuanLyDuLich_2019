package data;

import java.util.List;

import entities.Tour;

public interface ITourDAO {

	public List<Tour> layDSTour();

	public Tour layTourTheoMa(String maTour);

	public Tour themTour(Tour tour);

	public Tour suaTour(Tour tour);

}
