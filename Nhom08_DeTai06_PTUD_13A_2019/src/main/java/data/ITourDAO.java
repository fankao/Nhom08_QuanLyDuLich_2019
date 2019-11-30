package data;

import java.util.List;

import entities.NgayKhoiHanh;
import entities.Tour;

public interface ITourDAO {

	public List<Tour> layDSTourChuaDuyet();

	public List<Tour> layDSTour();

	public List<Tour> layDSTourDaDuyet();

	public List<Tour> layDSTourTheoNhanVien(String maNV);

	public List<NgayKhoiHanh> layDSNgayKhoiHanhTheoTour(String maTour);

	public Tour layTourTheoMa(String maTour);

	public Tour themTour(Tour tour);

	public Tour suaTour(Tour tour);

	public String phatSinhMaTour();
	public String phatSinhNgayKhoiHanh(String maTour);

}
