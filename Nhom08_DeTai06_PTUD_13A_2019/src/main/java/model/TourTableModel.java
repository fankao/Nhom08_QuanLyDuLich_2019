package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entities.DiaDanh;
import entities.Tour;

public class TourTableModel extends AbstractTableModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String[] tieuDe = { "STT", "Mã tour", "Tên tour", "Địa danh", "Điểm xuất phát", "Điểm đến", "Thời gian",
			"Giá người lớn", "Giá trẻ em", "Phương tiện", "Ngày khởi hành" };
	private List<Tour> lstTour = new ArrayList<Tour>();
	private final Class<?>[] columnClass = new Class<?>[] { Integer.class, String.class, String.class, DiaDanh.class,
			String.class, String.class, String.class, Double.class, Double.class, String.class, String.class };

	public TourTableModel(List<Tour> lstTour) {
		this.lstTour = lstTour;
	}

	@Override
	public String getColumnName(int column) {
		return tieuDe[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnClass[columnIndex];
	}

	@Override
	public int getColumnCount() {

		return tieuDe.length;
	}

	@Override
	public int getRowCount() {
		return lstTour.size();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Tour tour = lstTour.get(row);
		switch (column) {
		case 0:
			return row + 1;
		case 1:
			return tour.getMaTour();
		case 2:
			return tour.getTenTour();

		case 3:
			return tour.getDiaDanh();

		case 4:
			return tour.getDiemKhoiHanh();

		case 5:
			return tour.getDiemDen();

		case 6:
			return tour.getThoiGian()[0] + " Ngày," + tour.getThoiGian()[1] + " đêm";

		case 7:
			return tour.getDonGiaNguoiLon();

		case 8:
			return tour.getDonGiaTreEm();

		case 9:
			return tour.getPhuongTien();
		case 10:
			return tour.getNgayKhoiHanh().size() > 0 ? tour.getNgayKhoiHanh().size() + "" : "Chưa có";

		}
		return null;

	}
}
