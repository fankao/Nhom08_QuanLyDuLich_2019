package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import constant.HangSo;
import entities.PhieuDangKy;

public class DSPhieuDangKyModel extends AbstractTableModel {
	private static boolean isEdit = false;
	private String[] tieuDe = { "STT", "Mã phiếu", "Ngày tạo", "Nhân viên", "Trạng thái" };
	private List<PhieuDangKy> dsPhieuDangKy = new ArrayList<PhieuDangKy>();
	private final Class<?>[] columnClass = new Class<?>[] { Integer.class, String.class, Date.class, String.class,
			String.class };

	public DSPhieuDangKyModel(List<PhieuDangKy> dsPhieuDangK) {
		super();
		this.dsPhieuDangKy = dsPhieuDangK;
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
		return dsPhieuDangKy.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return tieuDe.length;
	}

	@Override
	public Object getValueAt(int row, int colum) {
		PhieuDangKy pdk = dsPhieuDangKy.get(row);
		switch (colum) {
		case 0:
			return row + 1;
		case 1:
			return pdk.getMaPhieuDK();
		case 2:
			return pdk.getNgayTaoPhieu();
		case 3:
			return pdk.getNv().getHoVaTen();
		case 4:
			if (pdk.getNgayKhoiHanh().isDaXoaDoKhongDuSoLuong())
				return HangSo.CHOHUYPHIEUDK;
			if (pdk.isDaHoanThanhTour())
				return HangSo.DAHOANTHANHTOUR;
			if (pdk.isDaHuyPhieu())
				return HangSo.DAHUYPHIEUDK;
			return HangSo.DANGXULY;
		}
		return null;
	}
}
