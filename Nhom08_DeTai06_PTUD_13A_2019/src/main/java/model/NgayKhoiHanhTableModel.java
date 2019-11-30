package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entities.NgayKhoiHanh;

public class NgayKhoiHanhTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private String[] tieuDe = { "STT", "Mã ngày khởi hành", "Ngày khởi hành", "Số khách tối đa" };
	private List<NgayKhoiHanh> lstNgayKH = new ArrayList<NgayKhoiHanh>();
	private Class<?>[] columnClass = { Integer.class, String.class, String.class, Integer.class };

	public NgayKhoiHanhTableModel(List<NgayKhoiHanh> lstNgayKH) {
		this.lstNgayKH = lstNgayKH;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return tieuDe[column];
	}

	@Override
	public int getColumnCount() {

		return tieuDe.length;
	}

	@Override
	public int getRowCount() {
		return lstNgayKH.size();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnClass[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		NgayKhoiHanh ngayKhoiHanh = lstNgayKH.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return rowIndex + 1;
		case 1:
			return ngayKhoiHanh.getMaLT();
		case 2:
			return new SimpleDateFormat("dd/MM/yyyy").format(ngayKhoiHanh.getNgayKhoiHanh());

		case 3:
			return ngayKhoiHanh.getSoKhachDaDangKy();
		}
		return null;
	}

}
