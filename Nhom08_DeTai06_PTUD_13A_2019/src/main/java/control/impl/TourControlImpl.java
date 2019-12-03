package control.impl;

import java.util.Date;
import java.util.List;

import control.ITourControl;
import data.IDiaDanhDAO;
import data.ITourDAO;
import data.impl.DiaDanhDAOImpl;
import data.impl.TourDAOImpl;
import entities.DiaDanh;
import entities.NgayKhoiHanh;
import entities.Tour;

/**
 * TourControlImpl.java
 * 
 * @author Thanh Trí, Ngày tạo :8/11/2019 <br>
 */

public class TourControlImpl implements ITourControl {
	private ITourDAO tourDAO;

	private IDiaDanhDAO diaDanhDAO;

	public TourControlImpl() {
		// TODO Auto-generated constructor stub
		tourDAO = new TourDAOImpl();
		diaDanhDAO = new DiaDanhDAOImpl();
	}

	/**
	 * Lấy thông tin tour theo mã tour
	 * 
	 * @param maTour
	 * @return tour
	 */
	@Override
	public Tour layTourTheoMa(String maTour) {
		// TODO Auto-generated method stub
		return tourDAO.layTourTheoMa(maTour);
	}

	/**
	 * Thêm tour
	 * 
	 * @param tour
	 * @return Tour thêm
	 */
	@Override
	public Tour themTour(Tour tour) {
		// TODO Auto-generated method stub
		return tourDAO.themTour(tour);
	}

	/**
	 * Sửa tour
	 * 
	 * @param tour
	 * @return Tour sửa
	 */
	@Override
	public Tour suaTour(Tour tour) {
		// TODO Auto-generated method stub
		return tourDAO.suaTour(tour);
	}

	/**
	 * Lấy danh sách tour theo yêu cầu
	 * 
	 * @param yc <br>
	 *           yc = 1 Lấy toan bộ danh sách tour chưa duyệt<br>
	 *           yc = 2 Lấy danh sách tour theo do nhân viên tạo <br>
	 *           yc = 3 Lấy danh sách tour đã được duyệt mở bán <br>
	 *           yc = 4 Lấy danh sách tour <br>
	 * @return Danh sách tour theo yêu cầu
	 */
	@Override
	public List<Tour> layDsTourTheoYeuCau(int yc, String... arg) {
		switch (yc) {
		case 1:
			return tourDAO.layDSTourChuaDuyet();
		case 2:
			return tourDAO.layDSTourTheoNhanVien(arg[0]);
		case 3:
			return tourDAO.layDSTourDaDuyet();
		case 4:
			return tourDAO.layDSTour();
		}
		return null;
	}

	@Override
	public List<DiaDanh> layDSDiaDanh() {
		return diaDanhDAO.layDSDiaDanh();
	}

	@Override
	public List<NgayKhoiHanh> layDSNgayKhoiHanhTheoTour(String maTour) {

		return tourDAO.layDSNgayKhoiHanhTheoTour(maTour);
	}

	@Override
	public String phatSinhNgayKhoiHanh(String maTour) {

		return tourDAO.phatSinhNgayKhoiHanh(maTour);
	}

	@Override
	public List<NgayKhoiHanh> layDSNgayKhoiHanhTheoNgayKhoiHanh(String maTour, Date tuNgay, Date denNgay) {

		return tourDAO.layDSNgayKhoiHanhTheoNgayKhoiHanh(maTour, tuNgay, denNgay);
	}

}
