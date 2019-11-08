package control.impl;

import java.util.List;

import control.ITourControl;
import data.TouDAO;
import data.impl.TourDAOImpl;
import entities.Tour;

public class TourControlImpl implements ITourControl {
	private TouDAO tourDAO;

	public TourControlImpl() {
		// TODO Auto-generated constructor stub
		tourDAO = new TourDAOImpl();
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
	 * Xóa tour
	 * 
	 * @param tour
	 * @return true hoặc false
	 */
	@Override
	public boolean xoaTour(Tour tour) {
		// TODO Auto-generated method stub
		return tourDAO.xoaTour(tour);
	}
	
	
	/**
	 * Lấy danh sách tour theo yêu cầu
	 * 
	 * @param yc <br>
	 *           yc = 1 Lấy toan bộ danh sách tour <br>
	 *           yc = 2 Lấy danh sách tour đã đằng ký <br>
	 *           yc = 3 Lấy danh sách tour chưa đăng ký <br>
	 * @return Danh sách tour theo yêu cầu
	 */
	@Override
	public List<Tour> layDsTourTheoYeuCau(int yc) {
		switch (yc) {
		case 1:
			return tourDAO.layDSTour();
		case 2:
			return tourDAO.layDSTourDaDangKy();
		case 3:
			return tourDAO.layDSTourChuaDangKy();
		}
		return null;
	}

}
