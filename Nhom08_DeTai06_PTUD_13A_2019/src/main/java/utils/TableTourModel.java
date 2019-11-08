package utils;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import entities.DiaDanh;
import entities.Tour;

public class TableTourModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	public static int MATOUR = 0;
	public static int TENTOUR = 1;
	public static int NGAYKH = 2;
	public static int NGAYKT = 3;
	public static int DONGIANGUOILON = 4;
	public static int DONGIATREEM = 5;
	public static int SONGUOITHAMGIA = 6;
	public static int DIADANH = 7;

	private ArrayList<Tour> dsTour;
	private String[] tieuDe = {
			"Mã tour,Tên tour, Ngày khởi hành, Ngày kết thúc,Đơn giá người lớn, Đơn giá trẻ em,Số người tối đa,Địa danh" };

	private Class<?>[] columnsClass = { String.class, String.class, Date.class, Date.class, Double.class, Double.class,
			Integer.class, DiaDanh.class };

	public TableTourModel() {
		dsTour = new ArrayList<Tour>();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return tieuDe.length ;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return dsTour.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		
		return null;
	}

}
