package data;

import java.util.List;
import java.util.Set;

import entities.NgayKhoiHanh;
import entities.Tour;

public interface ITourDAO {

	public List<Tour> layDSTour();

	public List<NgayKhoiHanh> layDSNgayKhoiHanhTheoTour(String maTour);

	public Tour layTourTheoMa(String maTour);

	public Tour themTour(Tour tour);

	public Tour suaTour(Tour tour);

}
