package UI;

import entity.ChattingRecord;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

public class chatRecord extends JFrame {
    int width = 800,height = 600;
    int count = 6;//设置列数
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    String[][] data = new String[][]{};//空数据方便创建表
    String[] chTable = {"姓名","ID","朋友","朋友ID","聊天记录","时间"};

    public static void main(String[] arg){
        ArrayList<chatTable> test = new ArrayList<>();
        for(int i=0;i<1000;i++){
            chatTable table = new chatTable("ss","ss","sssssssssssssssssssssssssssssssssss","ss","ss","ss");
            test.add(table);
        }
       // new chatRecord(test);
    }


    public chatRecord(ArrayList<ChattingRecord> arrayList){
        super();
        setTitle("聊天记录");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        this.setBounds(screenSize.width/2-width/2,screenSize.height/2-height/2,width,height);

        DefaultTableModel mm = new DefaultTableModel(data,chTable);//设置表头及根据容器信息设置表行数
        JTable table = new JTable(mm);//初始化
        User sender, accpter;
        for(int i=0;i<arrayList.size();i++){//循环添加行数据
            sender = arrayList.get(i).getSender();
            accpter = arrayList.get(i).getAccepter();
            String[] row = new String[6];
            row[0] = sender.getName();
            row[1] = sender.getAccountnum();
            row[2] = accpter.getName();
            row[3] = accpter.getAccountnum();
            row[4] = arrayList.get(i).getContent();
            row[5] = simpleDateFormat.format(arrayList.get(i).getDate());
            mm.addRow(row);
            table.setModel(mm);
        }
        for(int i =0;i<6;i++)//让每列列宽可手动调整
            table.getColumnModel().getColumn(i).setResizable(true);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setDefaultRenderer(Object.class,new TableCellText());//根据内容实现自动换行
        DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
        tableModel.setColumnCount(count);

        JScrollPane scrollPane = new JScrollPane(table);

        this.add(scrollPane);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });
        this.setVisible(true);

    }
}
