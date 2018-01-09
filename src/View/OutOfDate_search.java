package View;

import Common.Message;
import Common.MessageType;
import Common.User;
import Model.ClientUser;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class OutOfDate_search extends JInternalFrame implements ActionListener{

    JLabel search_jbl,keywords_jbl,jbl3,jbl4,jbl5,jbl6;
    JTextField keywords_jtf;
    JButton refresh_jb,add_jb,delete_jb;
    JTable table;
    JScrollPane jsp;
    JComboBox jcb;
    JPanel jp1,jp2,jp3;
    String Cno;

    public OutOfDate_search()
    {
        init();
        try {
            fillTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void init()
    {
//        jp1 = new JPanel();
//        search_jbl = new JLabel("供应商编号:");
//        keywords_jtf = new JTextField(20);
//        search_jb = new JButton("查询");
//        search_jb.addActionListener(this);
//        jp1.add(search_jbl);
//        jp1.add(keywords_jtf);
//        jp1.add(search_jb);

        jp2 = new JPanel();
        jsp = new JScrollPane();
        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{},new String[]{"货物批次","货物编号","货物名称","类型编号","类型名称","仓库编号","仓库名称","存储数量","生产日期","到期时间"}));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);
        table.setPreferredScrollableViewportSize(new Dimension(750,250));
//        table.addMouseListener(this);

        jsp.setViewportView(table);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setVisible(true);
        jp2.add(jsp);

        jp3 = new JPanel();
        refresh_jb = new JButton("刷新");
        refresh_jb.addActionListener(this);
        jp3.add(refresh_jb);
//        delete_jb = new JButton("删除供应商");
//        delete_jb.addActionListener(this);
//        if(u.getType() == 3)
//        {
//            add_jb.setEnabled(false);
//            delete_jb.setEnabled(false);
//        }
//        jp3.add(add_jb);
//        jp3.add(delete_jb);


//        this.add(jp1,"North");
        this.add(jp2,"Center");
        this.add(jp3,"South");
        this.setSize(600,500);
//        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setVisible(true);
//        jcb = new JComboBox();
//        jcb.setModel(new DefaultComboBoxModel(new String[] {"","商品编号","商品名字","类型编号","类型名字","仓库编号","数量"}));
//        jcb.setSelectedIndex(-1);

    }

    void fillTable() throws Exception {
        DefaultTableModel dtm = (DefaultTableModel)table.getModel();
        dtm.setRowCount(0);
        Message ms = new Message();
//        ms.setCon(String.valueOf(keywords_jtf.getText()));
        ms.setMesType(MessageType.message_select_OutOfDate);

        ClientUser clientUser = new ClientUser();

        Vector v = clientUser.getTable(ms);
        for(int i=0;i<v.size()/10;i++)
        {
            Vector v2 = new Vector();
            for (int j=0;j<10;j++) {
                v2.addElement(v.get(j+i*10));
            }
            dtm.addRow(v2);
        }
//        dtm.addRow(v);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == refresh_jb)
        {
            try {
                fillTable();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
