package gui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.NhanVien;
import gui.FrmMain;
import utils.TienIch;

/**
 * 
 * @author NMC
 *
 */
public class pnlQuanLyTour extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnCapNhatTour;
	private JButton btnDKTour;
	private JButton btnHuyDKTour;
	private NhanVien nhanVien;

	/**
	 * Create the panel.
	 */
	public pnlQuanLyTour(NhanVien nv) {
		this.nhanVien = nv;
		setBackground(Color.WHITE);
		JPanel pnChucNang = new JPanel();
		pnChucNang.setBackground(Color.WHITE);
		pnChucNang.setBorder(new EmptyBorder(0, 60, 0, 60));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(20)
						.addComponent(pnChucNang, GroupLayout.PREFERRED_SIZE, 936, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(196)
						.addComponent(pnChucNang, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(260, Short.MAX_VALUE)));
		pnChucNang.setLayout(new GridLayout(0, 3, 30, 20));

		btnCapNhatTour = new JButton("Cập nhật\r\n tour");
		btnCapNhatTour.setIcon(new ImageIcon(pnlQuanLyTour.class.getResource("/images/capnhat.png")));
		btnCapNhatTour.setForeground(Color.WHITE);
		btnCapNhatTour.setBackground(new Color(255, 204, 102));
		btnCapNhatTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCapNhatTour.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnCapNhatTour.setFocusable(false);
		btnCapNhatTour.setBorder(new EmptyBorder(0, 10, 0, 10));
		pnChucNang.add(btnCapNhatTour);

		btnDKTour = new JButton("Đăng ký tour");
		btnDKTour.setIcon(new ImageIcon(pnlQuanLyTour.class.getResource("/images/dangkytour.png")));
		btnDKTour.setForeground(Color.WHITE);
		btnDKTour.setBackground(new Color(153, 204, 102));
		btnDKTour.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnDKTour.setFocusable(false);
		btnDKTour.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnChucNang.add(btnDKTour);

		btnHuyDKTour = new JButton("Huỷ đăng ký tour");
		btnHuyDKTour.setIcon(new ImageIcon(pnlQuanLyTour.class.getResource("/images/huydangky.png")));
		btnHuyDKTour.setForeground(Color.WHITE);
		btnHuyDKTour.setBackground(new Color(255, 0, 102));
		btnHuyDKTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHuyDKTour.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnHuyDKTour.setFocusable(false);
		btnHuyDKTour.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnChucNang.add(btnHuyDKTour);
		setLayout(groupLayout);

		btnCapNhatTour.addActionListener(this);
		btnDKTour.addActionListener(this);
		btnHuyDKTour.addActionListener(this);
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();
		if (o.equals(btnCapNhatTour)) {
			//TienIch.chuyenPanelKhiNhan(frmMain.getPnContent(), new pnlCapNhat_TTTour(nhanVien));
			TienIch.chuyenPanelKhiNhan(FrmMain.getPnContent(), new PnlTaoTour(nhanVien));
			TienIch.themDuongDan(FrmMain.getPnButtonBar(), "Cập nhật tour");
		} else if (o.equals(btnDKTour)) {
			//TienIch.chuyenPanelKhiNhan(frmMain.getPnContent(), new pnlPhieuDangKyTour());
			TienIch.chuyenPanelKhiNhan(FrmMain.getPnContent(), new PnlDangKyTour());
			TienIch.themDuongDan(FrmMain.getPnButtonBar(), "Đăng ký tour du lịch");
		} else if (o.equals(btnHuyDKTour)) {
			TienIch.chuyenPanelKhiNhan(FrmMain.getPnContent(), new pnlHuyDangKyTour());
			TienIch.themDuongDan(FrmMain.getPnButtonBar(), "Hủy phiếu đăng ký");
		}
	}
}
