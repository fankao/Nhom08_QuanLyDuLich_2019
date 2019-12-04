package control;

import java.util.Date;
import java.util.List;

import entities.DiaDanh;
import entities.NgayKhoiHanh;
import entities.Tour;

public interface ITourControl {
	
	public Tour layTourTheoMa(String maTour);

	public Tour themTour(Tour tour);

	public Tour suaTour(Tour tour);

	public List<Tour> layDsTourTheoYeuCau(int yc, String... arg);

	public List<NgayKhoiHanh> layDSNgayKhoiHanhTheoTour(String maTour);
	public List<NgayKhoiHanh> layDSNgayKhoiHanhTheoNgayKhoiHanh(String maTour, Date tuNgay, Date denNgay);

	public List<DiaDanh> layDSDiaDanh();
	public String phatSinhNgayKhoiHanh(String maTour);
}
