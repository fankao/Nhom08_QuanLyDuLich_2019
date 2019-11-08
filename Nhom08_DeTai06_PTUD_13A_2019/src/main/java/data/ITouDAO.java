package data;

import java.util.List;

import entities.Tour;

public interface ITouDAO {

	public List<Tour> layDSTour();

	public List<Tour> layDSTourTheoMa(String maTour);

	public List<Tour> layDSTourChuaDangKy();

	public List<Tour> layDSTourDaDangKy();

	public Tour layTourTheoMa(String maTour);

	public Tour themTour(Tour tour);

	public Tour suaTour(Tour tour);

	public boolean xoaTour(Tour tour);

}
