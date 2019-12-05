package utils;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import control.ITourControl;
import control.impl.TourControlImpl;
import entities.Tour;

public class TableMouseListener extends MouseAdapter {
	private JTable table;
	private ITourControl tourControl;

	public TableMouseListener(JTable table) {
		super();
		this.table = table;
		tourControl = new TourControlImpl();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (table.isEnabled()) {
			int r = table.rowAtPoint(e.getPoint());
			if (r >= 0 && r < table.getRowCount()) {
				table.setRowSelectionInterval(r, r);
			} else {
				table.clearSelection();
			}
			int rowindex = table.getSelectedRow();
			if (rowindex < 0)
				return;
			Tour tour = tourControl.layTourTheoMa(table.getValueAt(rowindex, 1).toString());
			if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
				JPopupMenu popupMenu = new JPopupMenu();
				JMenuItem mnuXemTTTour = new JMenuItem("Xem chương trình tour");
				mnuXemTTTour.setFont(new Font("Arial", Font.PLAIN, 18));
				mnuXemTTTour.setIcon(new ImageIcon(this.getClass().getResource("/images/info_25px.png")));
				popupMenu.add(mnuXemTTTour);

				mnuXemTTTour.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						TienIch.hienFilePDF(tour.getMoTa());
					}
				});
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
}
