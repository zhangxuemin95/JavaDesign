package UI;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableCellText extends JTextArea implements TableCellRenderer {
    public TableCellText(){
        setLineWrap(true);//用了jtextarea的自动换行功能
        setWrapStyleWord(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        int height = 0;
        for (int i = 0;i<table.getColumnCount();i++){
            setText(""+table.getValueAt(row,i));
            setSize(table.getColumnModel().getColumn(column).getWidth(),0);
            height = Math.max(height,getPreferredSize().height);
        }
        if(table.getRowHeight(row)!=height)
            table.setRowHeight(row,height);//匹配当前行高度
        setText(value==null?"":value.toString());
        return this;
    }
}
