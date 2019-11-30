package utils;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
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

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.toedter.calendar.JDateChooser;

import gui.FrmMain;
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
			JLabel lbl = new JLabel("> " + subs[i]);
			lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lbl.setFocusable(false);
			lbl.setBackground(new Color(242, 247, 247));
			lbl.setBorder(new EmptyBorder(0, 0, 0, 0));
			root.add(lbl);

		}

	}

	public static void xoaDuongDan(JPanel pnDich, int kieuXoa) {
		switch (kieuXoa) {
		case 1:
			// Chọn xoá hoàn toàn đường dẫn
			FrmMain.getPnButtonBar().removeAll();
			FrmMain.getPnButtonBar().repaint();
			FrmMain.getPnButtonBar().validate();
			break;
		case 2:
			// Chọn xoá đường dẫn cấp 1
			FrmMain.getPnButtonBar().remove(1);
			FrmMain.getPnButtonBar().repaint();
			FrmMain.getPnButtonBar().validate();
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
		CardLayout cardLayout = (CardLayout) root.getLayout();
		pnDich.repaint();
		pnDich.validate();
		cardLayout.show(root, pnDich.getName());

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
