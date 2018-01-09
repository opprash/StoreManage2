package View;

import Common.Message;
import Common.MessageType;
import Common.User;
import Model.ClientUser;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;


public class store_search extends JInternalFrame implements ActionListener{

    JLabel search_jbl,keywords_jbl,jbl3,jbl4,jbl5,jbl6;
    JTextField keywords_jtf;
    JButton search_jb,in_jb,out_jb;
    JTable table;
    JScrollPane jsp;
    JComboBox jcb;
    JPanel jp1,jp2,jp3;

    public store_search()
    {
        init();
        try {
            fillTable(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void init()
    {
        jp1 = new JPanel();
        search_jbl = new JLabel("商品编号:");
        keywords_jtf = new JTextField(20);
        search_jb = new JButton("查询");
        search_jb.addActionListener(this);
        jp1.add(search_jbl);
        jp1.add(keywords_jtf);
        jp1.add(search_jb);

        jp2 = new JPanel();
        jsp = new JScrollPane();
        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{},new String[]{"商品编号","商品名字","类型编号","类型名字","仓库编号","数量"}));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);
        table.setPreferredScrollableViewportSize(new Dimension(500,250));

        jsp.setViewportView(table);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setVisible(true);
        jp2.add(jsp);
//
//        UserTable = new JTable();
//        UserTable.setModel(new DefaultTableModel(new Object[][]{},new String[]{"用户编号","用户名","密码","权限"}));
//        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
//        r.setHorizontalAlignment(JLabel.CENTER);
//        UserTable.setDefaultRenderer(Object.class,r);
//        UserTable.setPreferredScrollableViewportSize(new Dimension(450,100));

        jp3 = new JPanel();
        in_jb = new JButton("入库操作");
        out_jb = new JButton("出库操作");
        in_jb.addActionListener(this);
        out_jb.addActionListener(this);
        jp3.add(in_jb);
        jp3.add(out_jb);


        this.add(jp1,"North");
        this.add(jp2,"Center");
        this.add(jp3,"South");
//        this.setMaximizable(true);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setSize(600,500);
//        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
//        jcb = new JComboBox();
//        jcb.setModel(new DefaultComboBoxModel(new String[] {"","商品编号","商品名字","类型编号","类型名字","仓库编号","数量"}));
//        jcb.setSelectedIndex(-1);

    }

    void fillTable(String name) throws Exception {
        DefaultTableModel dtm = (DefaultTableModel)table.getModel();
        dtm.setRowCount(0);
        Message ms = new Message();
        ms.setCon(name);
        ms.setMesType(MessageType.message_select_storeTable);

        ClientUser clientUser = new ClientUser();

        Vector v = clientUser.getTable(ms);
        for(int i=0;i<v.size()/6;i++)
        {
            Vector v2 = new Vector();
            for (int j=0;j<6;j++) {
                v2.addElement(v.get(j+i*6));
            }
            dtm.addRow(v2);
        }
//        dtm.addRow(v);
    }

    public static void main(String [] args)
    {
        new store_search();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search_jb)
        {
//            System.out.println("123");
            try {
                fillTable(keywords_jtf.getText());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else if(e.getSource() == in_jb)
        {

        }
        else if(e.getSource() == out_jb)
        {

        }
    }
}
