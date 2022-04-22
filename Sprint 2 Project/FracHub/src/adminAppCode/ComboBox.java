package adminAppCode;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;

public class ComboBox {
	
	@SuppressWarnings("serial")
	public void setComboBox(JComboBox<String> list) {
		
		list.setRenderer(new DefaultListCellRenderer() {
			@SuppressWarnings("rawtypes")
			@Override
	        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
	                boolean cellHasFocus) {

	        			
	        			list.setSelectionBackground(new Color(0,102,204));
	        			list.setSelectionForeground(Color.white);
	        			list.setBackground(Color.white);
	        	
	            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	        }
	    });
}

}
