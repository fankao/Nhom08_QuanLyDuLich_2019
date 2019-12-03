package utils;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;

public class TableMouseListener extends MouseAdapter {
	private JTable table;
	private JPopupMenu popupMenu;

	public TableMouseListener(JTable table, JPopupMenu popupMenu) {
		super();
		this.table = table;
		this.popupMenu = popupMenu;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTableHeader header = table.getTableHeader();
		if (SwingUtilities.isRightMouseButton(e)) {
			if (table.isEnabled()) {
				popupMenu.show(header, e.getX(), e.getY());
				Point point = e.getPoint();
				int currentRow = table.rowAtPoint(point);
				table.setRowSelectionInterval(currentRow, currentRow);
			}
		}
	}
}
