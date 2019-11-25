package gui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.TienIch;
import utils.address.District;
import utils.address.Province;
import utils.address.Ward;

import java.awt.Dialog.ModalityType;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class DlgDiaChi extends JDialog implements ActionListener {
	private JComboBox cmbHuyen;
	private JComboBox cmbXa;
	private JButton btnLuu;
	private JComboBox cmbTinh;
	private List<Province> provinces;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDiaChi dialog = new DlgDiaChi();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("unchecked")
	public DlgDiaChi() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgDiaChi.class.getResource("/images/iconFrm.png")));
		setLocationRelativeTo(null);
		setSize(680, 260);
		setTitle("Địa chỉ khách hàng");
		setModalityType(ModalityType.APPLICATION_MODAL);
		{
			JPanel pnlDiaChi = new JPanel();
			getContentPane().add(pnlDiaChi, BorderLayout.CENTER);
			pnlDiaChi.setLayout(new BorderLayout(0, 0));

			JPanel panel = new JPanel();
			pnlDiaChi.add(panel);
			panel.setLayout(new GridLayout(1, 3, 0, 0));
			{
				JPanel pnlTinh = new JPanel();
				panel.add(pnlTinh);

				JLabel lblTinh = new JLabel("Tỉnh/Thành phố:");
				lblTinh.setFont(new Font("Tahoma", Font.PLAIN, 18));

				cmbTinh = new JComboBox();
				cmbTinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
				GroupLayout gl_pnlTinh = new GroupLayout(pnlTinh);
				gl_pnlTinh.setHorizontalGroup(
					gl_pnlTinh.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTinh.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_pnlTinh.createParallelGroup(Alignment.LEADING)
								.addComponent(cmbTinh, 0, 196, Short.MAX_VALUE)
								.addGroup(gl_pnlTinh.createSequentialGroup()
									.addComponent(lblTinh)
									.addGap(61)))
							.addContainerGap())
				);
				gl_pnlTinh.setVerticalGroup(
					gl_pnlTinh.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTinh.createSequentialGroup()
							.addGap(59)
							.addComponent(lblTinh)
							.addGap(5)
							.addComponent(cmbTinh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(58, Short.MAX_VALUE))
				);
				pnlTinh.setLayout(gl_pnlTinh);
			}
			{
				JPanel pnlHuyen = new JPanel();
				panel.add(pnlHuyen);

				JLabel lblHuyen = new JLabel("Huyện/Quận/Thị xã:");
				lblHuyen.setFont(new Font("Tahoma", Font.PLAIN, 18));

				cmbHuyen = new JComboBox();
				cmbHuyen.setFont(new Font("Tahoma", Font.PLAIN, 18));
				GroupLayout gl_pnlHuyen = new GroupLayout(pnlHuyen);
				gl_pnlHuyen.setHorizontalGroup(
					gl_pnlHuyen.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlHuyen.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_pnlHuyen.createParallelGroup(Alignment.LEADING)
								.addComponent(cmbHuyen, 0, 196, Short.MAX_VALUE)
								.addComponent(lblHuyen))
							.addContainerGap())
				);
				gl_pnlHuyen.setVerticalGroup(
					gl_pnlHuyen.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlHuyen.createSequentialGroup()
							.addGap(58)
							.addComponent(lblHuyen)
							.addGap(6)
							.addComponent(cmbHuyen, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(58, Short.MAX_VALUE))
				);
				pnlHuyen.setLayout(gl_pnlHuyen);
			}
			{
				JPanel pnlXa = new JPanel();
				panel.add(pnlXa);

				JLabel lblXa = new JLabel("Xã/Phường/Thị trấn:");
				lblXa.setFont(new Font("Tahoma", Font.PLAIN, 18));

				cmbXa = new JComboBox();
				cmbXa.setFont(new Font("Tahoma", Font.PLAIN, 18));
				GroupLayout gl_pnlXa = new GroupLayout(pnlXa);
				gl_pnlXa.setHorizontalGroup(gl_pnlXa.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlXa.createSequentialGroup().addContainerGap()
								.addGroup(gl_pnlXa.createParallelGroup(Alignment.LEADING)
										.addComponent(cmbXa, 0, 195, Short.MAX_VALUE).addComponent(lblXa))
								.addContainerGap()));
				gl_pnlXa.setVerticalGroup(gl_pnlXa.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlXa.createSequentialGroup().addGap(58).addComponent(lblXa).addGap(6)
								.addComponent(cmbXa, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(58, Short.MAX_VALUE)));
				pnlXa.setLayout(gl_pnlXa);
			}

			JPanel pnlButton = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlButton.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnlDiaChi.add(pnlButton, BorderLayout.SOUTH);

			btnLuu = new JButton("Lưu địa chỉ");
			btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 18));
			pnlButton.add(btnLuu);

			JButton btnHuy = new JButton("Huỷ chọn");
			btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 18));
			pnlButton.add(btnHuy);

			provinces = TienIch.layDiaLyHanhChinh();
			hienDiaDiem(provinces, cmbTinh);

			cmbTinh.addActionListener(this);
			cmbHuyen.addActionListener(this);

		}
	}

	/**
	 * Hiện danh sách địa chỉ: Tỉnh/Thành phố; Huyện/Quận/Thị xã; Xã/Phường/Thị trấn
	 * 
	 * @param lstdiaDiem: danh sách dịa chị
	 * @param cmbDiaDiem: combobox chứa tên tương ứng
	 */
	@SuppressWarnings("unchecked")
	private <T> void hienDiaDiem(List<T> lstdiaDiem, JComboBox<T> cmbDiaDiem) {
		DefaultComboBoxModel<T> cmbModel = (DefaultComboBoxModel<T>) cmbDiaDiem.getModel();
		cmbModel.removeAllElements();
		Province province = new Province();
		province.setName("-- Chọn --");
		cmbModel.addElement((T) province);
		for (T x : lstdiaDiem) {
			cmbModel.addElement(x);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(cmbTinh)) {
			if (cmbTinh.getSelectedIndex() != 0) {
				if (cmbHuyen.getItemCount() < 1) {
					List<District> districts = provinces.get(cmbTinh.getSelectedIndex() - 1).getDistricts();
					hienDiaDiem(districts, cmbHuyen);
					cmbHuyen.setEnabled(true);
				} else {
					((DefaultComboBoxModel<District>) cmbHuyen.getModel()).removeAllElements();
					((DefaultComboBoxModel<Ward>) cmbXa.getModel()).removeAllElements();
					cmbHuyen.setEnabled(false);
					cmbXa.setEnabled(false);

				}

			}
		} else if (o.equals(cmbHuyen)) {
			if (cmbHuyen.getSelectedIndex() != 0) {
				if (cmbXa.getItemCount() < 1) {
					((DefaultComboBoxModel<Ward>) cmbXa.getModel()).removeAllElements();
					List<Ward> wards = provinces.get(cmbTinh.getSelectedIndex() - 1).getDistricts()
							.get(cmbHuyen.getSelectedIndex() - 1).getWards();
					hienDiaDiem(wards, cmbXa);
					cmbXa.setEnabled(true);
				} else {
					((DefaultComboBoxModel<Ward>) cmbXa.getModel()).removeAllElements();
					cmbXa.setEnabled(false);

				}

			}

		}

	}
}
