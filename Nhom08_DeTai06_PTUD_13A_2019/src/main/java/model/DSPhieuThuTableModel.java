package model;

import java.sql.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import entities.LoaiPhieu;
import entities.PhieuThuChi;

public class DSPhieuThuTableModel extends AbstractTableModel {
	private String[] tieuDe = { "STT", "Mã phiếu", "Ngày tạo", "Khách hàng", "Nhân Viên", "Loại phiếu", "Số tiền" };
	private List<PhieuThuChi> dsPhieuTC = new ArrayList<PhieuThuChi>();
	private final Class<?>[] columnClass = new Class<?>[] { Integer.class, String.class, Date.class, String.class,
			String.class, LoaiPhieu.class, String.class };

	public DSPhieuThuTableModel(List<PhieuThuChi> dsPhieuTC) {
		super();
		this.dsPhieuTC = dsPhieuTC;
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
	public int getRowCount() {
		// TODO Auto-generated method stub
		return dsPhieuTC.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return tieuDe.length;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int row, int colum) {
		PhieuThuChi ptc = dsPhieuTC.get(row);
		switch (colum) {
		case 0:
			return row + 1;
		case 1:
			return ptc.getMaPhieuChi();
		case 2:
			return ptc.getNgayTaoPhieuChi();
		case 3:
			return ptc.getPdk().getKh().getHoVaTen();
		case 4:
			return ptc.getPdk().getNv().getHoVaTen();
		case 5:
			return ptc.getLoaiPhieu();
		case 6:
			return NumberFormat.getCurrencyInstance(new Locale("vi", "vn")).format(ptc.getSoTien());
		}

		return null;
	}

}
