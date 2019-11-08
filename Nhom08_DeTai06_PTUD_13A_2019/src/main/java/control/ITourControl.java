package control;

import java.util.List;

import entities.Tour;

public interface ITourControl {

	public Tour layTourTheoMa(String maTour);

	public Tour themTour(Tour tour);

	public Tour suaTour(Tour tour);

	public boolean xoaTour(Tour tour);
	
	public List<Tour> layDsTourTheoYeuCau(int yc);
}
