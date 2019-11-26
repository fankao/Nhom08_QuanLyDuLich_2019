package control;

import java.util.List;

import entities.DiaDanh;
import entities.NgayKhoiHanh;
import entities.Tour;

public interface ITourControl {

	public Tour layTourTheoMa(String maTour);

	public Tour themTour(Tour tour);

	public Tour suaTour(Tour tour);

	public List<Tour> layDsTourTheoYeuCau(int yc);
	public List<NgayKhoiHanh> layDSNgayKhoiHanhTheoTour(String maTour);

	public List<DiaDanh> layDSDiaDanh();
}
