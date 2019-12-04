package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entities.KhachHangThamGia;

public class DSKhachHangTGTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static boolean isEdit = false;
	private String[] tieuDe = { "STT", "Họ tên", "Ngày sinh", "Tuổi", "Ghi chú" };
	private List<KhachHangThamGia> dsKhachThamGia = new ArrayList<KhachHangThamGia>();
	private final Class<?>[] columnClass = new Class<?>[] { Integer.class, String.class, Date.class, Integer.class,
			String.class };

	public DSKhachHangTGTableModel(List<KhachHangThamGia> dsKhachThamGia) {
		super();
		this.dsKhachThamGia = dsKhachThamGia;
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
		return dsKhachThamGia.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return tieuDe.length;
	}

	@Override
	public Object getValueAt(int row, int colum) {
		KhachHangThamGia khtg = dsKhachThamGia.get(row);
		switch (colum) {
		case 0:
			return row + 1;
		case 1:
			return khtg.getHoTenKHTG();
		case 2:
			return khtg.getNgaySinh();
		case 3:
			return khtg.tinhTuoiKhachHang();
		case 4:
			return khtg.getDoTuoi();

		}
		return null;
	}

	public List<KhachHangThamGia> getDsKhachThamGia() {
		return dsKhachThamGia;
	}

	public void setDsKhachThamGia(List<KhachHangThamGia> dsKhachThamGia) {
		this.dsKhachThamGia = dsKhachThamGia;
	}

}
