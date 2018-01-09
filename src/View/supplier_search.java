package View;

import Common.Message;
import Common.MessageType;
import Common.User;
import Model.ClientUser;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class supplier_search extends JInternalFrame implements ActionListener,MouseListener{

    JLabel search_jbl,keywords_jbl,jbl3,jbl4,jbl5,jbl6;
    JTextField keywords_jtf;
    JButton search_jb,add_jb,delete_jb;
    JTable table;
    JScrollPane jsp;
    JComboBox jcb;
    JPanel jp1,jp2,jp3;
    String Sno;

    public supplier_search()
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
        search_jbl = new JLabel("供应商编号:");
        keywords_jtf = new JTextField(20);
        search_jb = new JButton("查询");
        search_jb.addActionListener(this);
        jp1.add(search_jbl);
        jp1.add(keywords_jtf);
        jp1.add(search_jb);

        jp2 = new JPanel();
        jsp = new JScrollPane();
        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{},new String[]{"供应商编号","供应商名字","供应商地址","供应商电话"}));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);
        table.setPreferredScrollableViewportSize(new Dimension(500,250));
        table.addMouseListener(this);

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
        add_jb = new JButton("增加供应商");
        add_jb.addActionListener(this);
        delete_jb = new JButton("删除供应商");
        delete_jb.addActionListener(this);
        jp3.add(add_jb);
        jp3.add(delete_jb);


        this.add(jp1,"North");
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

    void fillTable(String name) throws Exception {
        DefaultTableModel dtm = (DefaultTableModel)table.getModel();
        dtm.setRowCount(0);
        Message ms = new Message();
        ms.setCon(String.valueOf(keywords_jtf.getText()));
        ms.setMesType(MessageType.message_select_supplierTable);

        ClientUser clientUser = new ClientUser();

        Vector v = clientUser.getTable(ms);
        for(int i=0;i<v.size()/4;i++)
        {
            Vector v2 = new Vector();
            for (int j=0;j<4;j++) {
                v2.addElement(v.get(j+i*4));
            }
            dtm.addRow(v2);
        }
//        dtm.addRow(v);
    }

    public static void main(String [] args)
    {
        new supplier_search();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search_jb)
        {
            try {
                fillTable(String.valueOf(keywords_jtf.getText()));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else if(e.getSource() == add_jb)
        {
            new supplier_add();
            try {
                fillTable(null);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else if(e.getSource() == delete_jb)
        {
            Message ms = new Message();
            ms.setCon(Sno);
            ms.setMesType(MessageType.message_delete_supplierTable);
            ClientUser clientUser = new ClientUser();
            if(clientUser.SendInfo(ms)!=0)
            {
                JOptionPane.showMessageDialog(this,"删除成功");
                try {
                    fillTable(null);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            else JOptionPane.showMessageDialog(this,"删除失败");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() == table)
        {
            int row = table.getSelectedRow();
            Sno = String.valueOf(table.getValueAt(row,0));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
