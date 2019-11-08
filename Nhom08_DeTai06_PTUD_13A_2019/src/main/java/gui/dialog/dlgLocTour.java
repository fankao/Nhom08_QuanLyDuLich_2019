package gui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import entities.DiaDanh;

public class dlgLocTour extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JDateChooser dtcDenNgay;
	private static JDateChooser dtcTuNgay;
	private JLabel lblDiaDanh;
	private JLabel lblSoNguoi;
	private JLabel lblGiaTien;
	private JComboBox<DiaDanh> cmbDiaDanh;
	private JComboBox<String> cmbSoNguoi;
	private JComboBox<String> cmbGiaTien;
	private JButton btnLoc;
	private JButton btnHuy;

	private List<DiaDanh> lstDiaDanh;

	private static double[] giaTien;
	private static DiaDanh diaDanhDcChon;
	private static int[] soNguoi;
	private static boolean daChonLoc;
	private static Date tuNgay, denNgay;

	/**
	 * khởi tạo màn hình lọc tour
	 */
	public dlgLocTour(List<DiaDanh> diaDanhs) {

		giaTien = new double[2];
		soNguoi = new int[2];
		this.lstDiaDanh = diaDanhs;

		/*
		 * Thành phần của giao diện
		 */
		setIconImage(Toolkit.getDefaultToolkit().getImage(dlgLocTour.class.getResource("/images/iconFrm.png")));
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setTitle("Lọc tour theo yêu cầu");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 564, 317);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(5, 1, 0, 0));
		{
			JPanel pnlNgay = new JPanel();
			contentPanel.add(pnlNgay);
			pnlNgay.setLayout(new BoxLayout(pnlNgay, BoxLayout.X_AXIS));
			{
				JPanel pnlTuNgay = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnlTuNgay.getLayout();
				flowLayout.setVgap(10);
				flowLayout.setAlignment(FlowLayout.LEFT);
				pnlNgay.add(pnlTuNgay);
				{
					JLabel lblTuNgay = new JLabel("Từ ngày:");
					lblTuNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));
					pnlTuNgay.add(lblTuNgay);
				}
				{
					dtcTuNgay = new JDateChooser();
					dtcTuNgay.setLocale(new Locale("vi"));
					dtcTuNgay.setPreferredSize(new Dimension(150, 30));
					dtcTuNgay.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 20));
					dtcTuNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));
					dtcTuNgay.setDateFormatString("dd/MM/yyyy");
					pnlTuNgay.add(dtcTuNgay);
				}
			}
			{
				JPanel pnlDenNgay = new JPanel();
				FlowLayout flowLayout = (FlowLayout) pnlDenNgay.getLayout();
				flowLayout.setVgap(10);
				flowLayout.setAlignment(FlowLayout.LEFT);
				pnlNgay.add(pnlDenNgay);
				{
					JLabel lblDenNgay = new JLabel("Đến ngày:");
					lblDenNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));
					pnlDenNgay.add(lblDenNgay);
				}
				{
					dtcDenNgay = new JDateChooser();
					dtcDenNgay.setLocale(new Locale("vi"));
					dtcDenNgay.setPreferredSize(new Dimension(150, 30));
					dtcDenNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));
					dtcDenNgay.setDateFormatString("dd/MM/yyyy");
					pnlDenNgay.add(dtcDenNgay);
				}
			}
		}
		{
			JPanel pnlDiaDanh = new JPanel();
			FlowLayout fl_pnlDiaDanh = (FlowLayout) pnlDiaDanh.getLayout();
			fl_pnlDiaDanh.setAlignment(FlowLayout.LEFT);
			contentPanel.add(pnlDiaDanh);
			{
				lblDiaDanh = new JLabel("Chọn địa danh:");
				lblDiaDanh.setFont(new Font("Tahoma", Font.PLAIN, 20));
				pnlDiaDanh.add(lblDiaDanh);
			}
			{
				cmbDiaDanh = new JComboBox<DiaDanh>();
				cmbDiaDanh.setFont(new Font("Tahoma", Font.PLAIN, 20));
				pnlDiaDanh.add(cmbDiaDanh);
			}
		}
		{
			JPanel pnlSoNguoi = new JPanel();
			FlowLayout fl_pnlSoNguoi = (FlowLayout) pnlSoNguoi.getLayout();
			fl_pnlSoNguoi.setAlignment(FlowLayout.LEFT);
			contentPanel.add(pnlSoNguoi);
			{
				lblSoNguoi = new JLabel("Số người:");
				lblSoNguoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
				pnlSoNguoi.add(lblSoNguoi);
			}
			{
				cmbSoNguoi = new JComboBox<String>();
				cmbSoNguoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
				cmbSoNguoi.setModel(new DefaultComboBoxModel<String>(new String[] { "", "dưới 20 người",
						"từ 20 đến 30 người", "từ 30 đến 40 người", "trên 40 người" }));
				pnlSoNguoi.add(cmbSoNguoi);
			}
		}
		{
			JPanel pnlGiaTien = new JPanel();
			FlowLayout fl_pnlGiaTien = (FlowLayout) pnlGiaTien.getLayout();
			fl_pnlGiaTien.setAlignment(FlowLayout.LEFT);
			contentPanel.add(pnlGiaTien);
			{
				lblGiaTien = new JLabel("Giá tiền:");
				lblGiaTien.setFont(new Font("Tahoma", Font.PLAIN, 20));
				pnlGiaTien.add(lblGiaTien);
			}
			{
				cmbGiaTien = new JComboBox<String>();
				cmbGiaTien.setModel(new DefaultComboBoxModel<String>(new String[] { "", "dưới 2 triệu", "từ 2 đến 4 triệu",
						"từ 4 đến 6 triệu", "từ 6 đến 8 triệu", "trên 8 triệu" }));
				cmbGiaTien.setFont(new Font("Tahoma", Font.PLAIN, 20));
				pnlGiaTien.add(cmbGiaTien);
			}
		}
		{
			JPanel pnlLoc = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlLoc.getLayout();
			flowLayout.setVgap(10);
			flowLayout.setAlignment(FlowLayout.RIGHT);
			contentPanel.add(pnlLoc);
			{
				btnLoc = new JButton("Lọc yêu cầu");
				btnLoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
				pnlLoc.add(btnLoc);
			}
			{
				btnHuy = new JButton("Huỷ lọc");
				btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 20));
				pnlLoc.add(btnHuy);
			}
		}

		((JTextField) dtcTuNgay.getDateEditor().getUiComponent()).setEditable(false);
		((JTextField) dtcDenNgay.getDateEditor().getUiComponent()).setEditable(false);

		lblGiaTien.setPreferredSize(lblDiaDanh.getPreferredSize());
		lblSoNguoi.setPreferredSize(lblDiaDanh.getPreferredSize());

		DiaDanh d = new DiaDanh();
		d.setTenDiaDanh("");
		DefaultComboBoxModel<DiaDanh> cmbModel = new DefaultComboBoxModel<DiaDanh>();
		cmbModel.removeAllElements();
		cmbModel.addElement(d);
		diaDanhs.forEach(x -> {
			cmbModel.addElement(x);
		});
		cmbDiaDanh.setModel(cmbModel);
		setVisible(true);

		// gán sự kiện
		ganSuKien();

	}

	private void ganSuKien() {
		btnLoc.addActionListener(this);
		btnHuy.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLoc)) {
			if (dtcTuNgay.getDate() != null && dtcDenNgay.getDate() != null) {
				setTuNgay(new Date(dtcTuNgay.getDate().getTime()));
				setDenNgay(new Date(dtcDenNgay.getDate().getTime()));
			}

			if (cmbDiaDanh.getSelectedIndex() != 0) {
				DiaDanh dd = (DiaDanh) cmbDiaDanh.getSelectedItem();
				setDiaDanhDcChon(dd);
			}
			if (cmbSoNguoi.getSelectedIndex() != 0) {
				switch (cmbSoNguoi.getSelectedIndex()) {
				case 1:
					setSoNguoi(new int[] { 20, 0 });
					break;

				case 2:
					setSoNguoi(new int[] { 20, 30 });
					break;
				case 3:
					setSoNguoi(new int[] { 30, 40 });
					break;
				case 4:
					setSoNguoi(new int[] { 0, 40 });
					break;

				default:
					break;
				}
			}

			if (cmbGiaTien.getSelectedIndex() != 0) {
				switch (cmbGiaTien.getSelectedIndex()) {
				case 1:
					setGiaTien(new double[] { 2000000, 0.0 });
					break;
				case 2:
					setGiaTien(new double[] { 2000000, 4000000 });
					break;
				case 3:
					setGiaTien(new double[] { 4000000, 6000000 });
					break;
				case 4:
					setGiaTien(new double[] { 6000000, 8000000 });
					break;
				case 5:
					setGiaTien(new double[] { 0.0, 8000000 });
					break;
				default:
					break;
				}
			}
			setDaChonLoc(true);
			dispose();
		} else if (o.equals(btnHuy)) {
			setDaChonLoc(false);
			dispose();
		}
	}

	public static Date getTuNgay() {
		return tuNgay;
	}

	public static void setTuNgay(Date tuNgays) {
		tuNgay = tuNgays;
	}

	public static Date getDenNgay() {
		return denNgay;
	}

	public static void setDenNgay(Date denNgays) {
		denNgay = denNgays;
	}

	public static void setDtcTuNgay(JDateChooser dtcTuNgays) {
		dtcTuNgay = dtcTuNgays;
	}

	public static double[] getGiaTien() {
		return giaTien;
	}

	public static void setGiaTien(double[] giaTiens) {
		giaTien = giaTiens;
	}

	public static DiaDanh getDiaDanhDcChon() {
		return diaDanhDcChon;
	}

	public static void setDiaDanhDcChon(DiaDanh diaDanhDcChons) {
		diaDanhDcChon = diaDanhDcChons;
	}

	public static int[] getSoNguoi() {
		return soNguoi;
	}

	public static void setSoNguoi(int[] soNguois) {
		soNguoi = soNguois;
	}

	public static boolean isDaChonLoc() {
		return daChonLoc;
	}

	public static void setDaChonLoc(boolean daChonLoc) {
		daChonLoc = daChonLoc;
	}

}
