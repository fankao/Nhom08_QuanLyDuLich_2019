package utils;

import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class SimpleHeaderRenderer extends JLabel implements TableCellRenderer {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public SimpleHeaderRenderer() {
		setFont(new Font("Arial", Font.BOLD, 16));
		setBorder(BorderFactory.createEtchedBorder());
		setHorizontalAlignment(SwingConstants.CENTER);
		
	}

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        return this;
    }

}
