package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.toedter.calendar.JDateChooser;

import gui.frmMain;
import utils.address.Province;

/**
 * TienIch.java
 * 
 * @author Chien
 * @version 1.0 Ngày tạo: 30/09/2019
 * 
 *
 */
public class TienIch {

	/**
	 * Hàm đổi màu cho button khi có sự kiện nhấn
	 * 
	 * @param btnDoiMau : button cần đổi màu
	 * @param buttons:  danh sach button không được chọn
	 */
	public static void doiMauButton(JButton btnDoiMau, JButton... buttons) {
		btnDoiMau.setBackground(new Color(41, 57, 80));
		for (JButton btn : buttons) {
			btn.setBackground(new Color(23, 35, 51));
			btn.setEnabled(true);
		}
		btnDoiMau.setEnabled(false);

	}

	/**
	 * Thêm đường dẫn khi chọn hiển thị một giao diện bất kỳ
	 * 
	 * @param root
	 * @param subs
	 */
	public static void themDuongDan(JPanel root, String... subs) {
		for (int i = 0; i < subs.length; i++) {
			JButton btn = new JButton("> " + subs[i]);
			btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btn.setFocusable(false);
			btn.setBackground(new Color(242, 247, 247));
			btn.setBorder(new EmptyBorder(0, 0, 0, 0));
			root.add(btn);

		}

	}

	public static void xoaDuongDan(JPanel pnDich, int kieuXoa) {
		switch (kieuXoa) {
		case 1:
			// Chọn xoá hoàn toàn đường dẫn
			frmMain.getPnButtonBar().removeAll();
			frmMain.getPnButtonBar().repaint();
			frmMain.getPnButtonBar().validate();
			break;
		case 2:
			// Chọn xoá đường dẫn cấp 1
			frmMain.getPnButtonBar().remove(1);
			frmMain.getPnButtonBar().repaint();
			frmMain.getPnButtonBar().validate();
			break;
		default:
			break;
		}
	}

	/**
	 * Hàm chuyển panel khi nhấn vào button tương ứng
	 * 
	 * @param root:   panel hiện tại
	 * @param pnDich: panel chuyển đến
	 */
	public static void chuyenPanelKhiNhan(JPanel root, JPanel pnDich) {
		root.removeAll();
		root.add(pnDich, BorderLayout.CENTER);
		root.repaint();
		root.validate();
	}

	public static void chinhKichThuocTable(JTable tbl, int tongKichThuoc, double... tyLe) {
		// chỉnh chiều cao của dòng
		tbl.setRowHeight(30);

		tbl.getTableHeader().setPreferredSize(new Dimension(tbl.getTableHeader().getWidth(), 35));

		// chỉnh kích thước tiêu đề
		tbl.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());

		tbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		double total = 0;
		for (int i = 0; i < tbl.getColumnModel().getColumnCount(); i++) {
			total += tyLe[i];
		}

		for (int i = 0; i < tbl.getColumnModel().getColumnCount(); i++) {
			TableColumn column = tbl.getColumnModel().getColumn(i);
			column.setPreferredWidth((int) (tongKichThuoc * (tyLe[i] / total)));
		}
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}

	public static void chinhKichThuocTitleTrenBorder(JPanel[] pns, String fontName, int fontStyle, int size) {
		for (JPanel pn : pns) {
			((TitledBorder) pn.getBorder()).setTitleFont(new Font(fontName, fontStyle, size));
			pn.repaint();
		}

	}

	/**
	 * Hàm chính kích thước ảnh cho vừa với kích thước khung chứa
	 * 
	 * @param lbl:  khung chứa
	 * @param icon: ảnh
	 */
	public static void chinhKichThuocAnh(JLabel lbl, ImageIcon icon) {
		Dimension size = lbl.getSize();
		Image imageResize = icon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
		lbl.setIcon(new ImageIcon(imageResize));
	}

	/**
	 * Hàm phát sinh mã trên cơ sở dữ liệu
	 * 
	 * @param loaiPS : loại phát sinh mã
	 * @return: một mã
	 * @throws Exception
	 */
	public static String phatSinhMa(int loaiPS) {
		try {
			Gson gson = new Gson();

			MaPhatSinh maps = gson.fromJson(new FileReader("data\\json\\MaPhatSinh.json"), MaPhatSinh.class);

			switch (loaiPS) {
			case 1:
				int indexTour = Integer.parseInt(maps.getMaTuor()) + 1;

				return indexTour + "";
			case 2:
				int indexKH = Integer.parseInt(maps.getMaKH()) + 1;

				return indexKH + "";
			case 3:

				int thangPDK = new Date(System.currentTimeMillis()).getMonth() + 1;
				String maPDK = maps.getMaPDK();
				String[] s = maPDK.split("/");
				s[0] = "" + (Integer.parseInt(s[0]) + 1);
				s[1] = "" + thangPDK;
				String maNoi = s[0] + "/" + s[1];

				return maNoi;

			case 4:
				int indexKHTG = Integer.parseInt(maps.getMaKHTG()) + 1;

				return indexKHTG + "";
			case 5:

				int thangPC = new Date(System.currentTimeMillis()).getMonth() + 1;
				String maPC = maps.getMaPC();
				String[] pc = maPC.split("/");
				pc[0] = "" + (Integer.parseInt(pc[0]) + 1);
				pc[1] = "" + thangPC;
				String mapc = pc[0] + "/" + pc[1];

				return mapc;

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Câp nhật mã trong file json sau khi đã phát sinh mã
	 * 
	 * @throws IOException
	 */
	public static void capNhatMaPhatSinh(int loaiPS, String maPS) {
		try {
			Gson gson = new Gson();

			MaPhatSinh maps = gson.fromJson(new FileReader("data\\json\\MaPhatSinh.json"), MaPhatSinh.class);
			PrintWriter out = new PrintWriter(new FileWriter("data\\json\\MaPhatSinh.json"), true);

			String json = null;

			switch (loaiPS) {
			case 1:
				maps.setMaTour(maPS);
				json = gson.toJson(maps);
				out.println(json);
				break;
			case 2:
				maps.setMaKH(maPS);
				json = gson.toJson(maps);
				out.println(json);
				break;
			case 3:
				maps.setMaPDK(maPS);
				json = gson.toJson(maps);
				out.println(json);
				break;
			case 4:
				maps.setMaKHTG(maPS);
				json = gson.toJson(maps);
				out.println(json);
				break;
			case 5:
				maps.setMaPC(maPS);
				json = gson.toJson(maps);
				out.println(json);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Hàm ẩn hiện các control trên giao diện
	 * 
	 * @param hien:    giá trị boolean; true: cho phép hiện, false: ẩn các control
	 * @param controls
	 */
	public static void hienAnCacControl(boolean hien, Object... controls) {
		for (Object object : controls) {
			if (hien) {
				if (object instanceof JTextField) {
					((JTextField) object).setEditable(true);
				}
				if (object instanceof JFormattedTextField) {
					((JFormattedTextField) object).setEditable(true);
				}
				if (object instanceof JButton) {
					((JButton) object).setEnabled(true);
				}
				if (object instanceof JDateChooser) {
					// ((JTextComponent)
					// ((JDateChooser)object).getDateEditor().getUiComponent()).setEditable(true);
					((JDateChooser) object).getCalendarButton().setEnabled(true);
				}
				if (object instanceof JSpinner) {
					((JSpinner) object).setEnabled(true);
				}
				if (object instanceof JTextPane) {
					((JTextPane) object).setEditable(true);
				}
				if (object instanceof JComboBox) {
					((JComboBox) object).setEnabled(true);

				}
				if (object instanceof JCheckBox) {
					((JCheckBox) object).setEnabled(true);
				}
			} else {
				if (object instanceof JTextField) {
					((JTextField) object).setEditable(false);
				}
				if (object instanceof JFormattedTextField) {
					((JFormattedTextField) object).setEditable(false);
				}
				if (object instanceof JButton) {
					((JButton) object).setEnabled(false);
				}
				if (object instanceof JDateChooser) {

					// ((JTextComponent)
					// ((JDateChooser)object).getDateEditor().getUiComponent()).setEditable(false);
					((JDateChooser) object).getCalendarButton().setEnabled(false);
				}
				if (object instanceof JSpinner) {
					((JSpinner) object).setEnabled(false);
				}
				if (object instanceof JTextPane) {
					((JTextPane) object).setEditable(false);
				}
				if (object instanceof JComboBox) {
					((JComboBox) object).setEnabled(false);
				}
				if (object instanceof JCheckBox) {
					((JCheckBox) object).setEnabled(false);
				}
			}
		}
	}

	/**
	 * Chuyển chuỗi tiếng việt có dấu thành không dấu
	 * 
	 * @param chuoiVN: chuỗi có dấu
	 * @return
	 */
	public static String chuyenChuoiTiengVietThanhChuoiKhongDau(String str) {
		/*
		 * String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
		 * Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		 * return pattern.matcher(nfdNormalizedString).replaceAll("");
		 */

		str = str.replaceAll("à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ", "a");
		str = str.replaceAll("è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ", "e");
		str = str.replaceAll("ì|í|ị|ỉ|ĩ", "i");
		str = str.replaceAll("ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ", "o");
		str = str.replaceAll("ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ", "u");
		str = str.replaceAll("ỳ|ý|ỵ|ỷ|ỹ", "y");
		str = str.replaceAll("đ", "d");

		str = str.replaceAll("À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ", "A");
		str = str.replaceAll("È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ", "E");
		str = str.replaceAll("Ì|Í|Ị|Ỉ|Ĩ", "I");
		str = str.replaceAll("Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ", "O");
		str = str.replaceAll("Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ", "U");
		str = str.replaceAll("Ỳ|Ý|Ỵ|Ỷ|Ỹ", "Y");
		str = str.replaceAll("Đ", "D");
		return str;
	}

	/**
	 * Hàm xoá trắng nội dung trong các JTextField
	 * 
	 * @param txts
	 */
	public static void xoaTrangCacJTextField(Object... txps) {
		for (Object txt : txps) {
			if (txt instanceof JTextField) {
				((JTextField) txt).setText("");
			}
			if (txt instanceof JTextPane) {
				((JTextPane) txt).setText("");
			}
			if (txt instanceof JFormattedTextField) {
				((JFormattedTextField) txt).setValue(0);
			}
		}
	}

	/**
	 * Hàm chỉnh look and feel cho panel
	 * 
	 * @param panels: panel cần chỉnh sửa
	 */
	public static void chinhLookAndFeelChoPanel(JPanel... panels) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < panels.length; i++) {
			SwingUtilities.updateComponentTreeUI(panels[i]);
		}

	}

	public static List<Province> layDiaLyHanhChinh() {
		Gson gson = new Gson();
		List<Province> province = null;
		try {

			province = gson.fromJson(new FileReader(new File(TienIch.class.getResource("/local.json").toURI())),
					new TypeToken<List<Province>>() {
					}.getType());
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException | URISyntaxException e) {
			e.printStackTrace();
		}
		return province;
	}

}
