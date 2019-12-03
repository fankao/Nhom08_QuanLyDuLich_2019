package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entities.KhachHang;

public class DSKhachHangTableModel extends AbstractTableModel {
	private static boolean isEdit = false;
	private String[] tieuDe = { "STT", "Mã KH", "Họ tên", "Ngày sinh", "Số CMND", "Số điện thoại" };
	private List<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
	private final Class<?>[] columnClass = new Class<?>[] { Integer.class, String.class, String.class, Date.class,
			String.class, String.class };

	public DSKhachHangTableModel(List<KhachHang> dsKhachHang) {
		super();
		this.dsKhachHang = dsKhachHang;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnClass[columnIndex];
	}

	@Override
	public String getColumnName(int column) {
		return tieuDe[column];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return dsKhachHang.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return tieuDe.length;
	}

	@Override
	public Object getValueAt(int row, int colum) {
		KhachHang kh = dsKhachHang.get(row);
		switch (colum) {
		case 0:
			return row + 1;
		case 1:
			return kh.getMaKH();
		case 2:
			return kh.getHoVaTen();
		case 3:
			return kh.getNgaySinh();
		case 4:
			return kh.getSoCMND();

		case 5:
			return kh.getSoDienThoai();

		}
		return null;
	}

}
