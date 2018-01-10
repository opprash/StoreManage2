package View;

import Common.Message;
import Common.MessageType;
import Common.User;
import Model.ClientUser;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class UserManage extends JFrame implements ActionListener,MouseListener{

    JTable UserTable;
    JTextField S_username_jtf;
    JTextField username_jtf,userpwd_jtf,usernum_jtf;
    JComboBox usertype;
    JButton search_jb,enter_jb,delete_jb;
    JLabel jbl1,jbl2,jbl3,jbl4,jbl5,jbl6;
    JPanel jp1,jp2,jp3,jp4,jp5,jp6;
    JScrollPane jsp;

    public UserManage()
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
        jbl1 = new JLabel("用户名");
        S_username_jtf = new JTextField(20);
        search_jb = new JButton("查询");
        search_jb.addActionListener(this);

        jp1.add(jbl1);
        jp1.add(S_username_jtf);
        jp1.add(search_jb);

        jp2 = new JPanel();
        jsp = new JScrollPane();
        UserTable = new JTable();
        UserTable.setModel(new DefaultTableModel(new Object[][]{},new String[]{"用户编号","用户名","密码","权限"}));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        UserTable.setDefaultRenderer(Object.class,r);
        UserTable.setPreferredScrollableViewportSize(new Dimension(450,100));

        UserTable.addMouseListener(this);

        jsp.setViewportView(UserTable);
        jsp.setVisible(true);
        jp2.add(jsp);

        jp3 = new JPanel(new GridLayout(3,1));
//        jp3.setSize(450,200);
        jp3.setBorder(new TitledBorder(null,"表格操作"));
        jbl1 = new JLabel("用户编号");
        jbl2 = new JLabel(" 用户名");
        jbl3 = new JLabel("  密 码 ");
        jbl4 = new JLabel(" 权 限 ");
        enter_jb = new JButton("确认修改");
        enter_jb.addActionListener(this);
        delete_jb = new JButton("删除信息");
        delete_jb.addActionListener(this);
        username_jtf = new JTextField(20);
        userpwd_jtf = new JTextField(20);
        usernum_jtf = new JTextField(20);
        usernum_jtf.setEditable(false);
        usertype = new JComboBox();
        usertype.setPreferredSize(new Dimension(125,18));
        usertype.setModel(new DefaultComboBoxModel(new String[] {"User","Manager"}));
        usertype.setSelectedIndex(-1);

        jp4 = new JPanel();
        jp4.add(jbl1);
        jp4.add(usernum_jtf);
        jp4.add(jbl2);
        jp4.add(username_jtf);

        jp5 = new JPanel();
        jp5.add(jbl3);
        jp5.add(userpwd_jtf);
        jp5.add(jbl4);
        jp5.add(usertype);

        jp6 = new JPanel();
        jp6.add(enter_jb);
        jp6.add(delete_jb);

        jp3.add(jp4);
        jp3.add(jp5);
        jp3.add(jp6);

        this.add(jp1,"North");
        this.add(jp2,"Center");
        this.add(jp3,"South");
        this.setTitle("用户管理");
        this.setSize(500,400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    void fillTable(String name) throws Exception {
        DefaultTableModel dtm = (DefaultTableModel)UserTable.getModel();
        dtm.setRowCount(0);
        Message ms = new Message();
        User u = new User();
        u.setUsername(name);
        ms.setU(u);
        ms.setMesType(MessageType.message_select_userTable);

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
        UserManage userManage = new UserManage();
    }

    @Override
    public void actionPerformed(ActionEvent ee) {
        if(ee.getSource() == enter_jb)
        {
            String name = username_jtf.getText();
            String pwd = userpwd_jtf.getText();
            String type = (String)usertype.getSelectedItem();
            int Id = Integer.parseInt(usernum_jtf.getText());
            User u = new User();
            u.setUsername(name);
            u.setUserpwd(pwd);
            u.setUserId(Id);
            if(type.equals("Manager"))
                u.setType(2);
            else u.setType(3);
            Message ms = new Message();
            ms.setU(u);

            ms.setMesType(MessageType.message_update_user);

            ClientUser clientUser = new ClientUser();
//            System.out.println("123");
            if(clientUser.updateUser(ms)!=0)
            {
                JOptionPane.showMessageDialog(this,"修改成功");
                try {
                    fillTable(null);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            else JOptionPane.showMessageDialog(this,"修改失败");
        }
        else if(ee.getSource() == delete_jb)
        {
//            System.out.println("123321123321");
//            String name = username_jtf.getText();
            int t = Integer.parseInt(usernum_jtf.getText());

            User u = new User();
//            u.setUsername(name);
            u.setUserId(t);
            Message ms = new Message();
            ms.setU(u);
            ms.setMesType(MessageType.message_delete_user);

            ClientUser clientUser = new ClientUser();

            if(clientUser.updateUser(ms)!=0)
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
        else if(ee.getSource() == search_jb)
        {
            try {
                fillTable(S_username_jtf.getText());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() == UserTable)
        {
            int row = UserTable.getSelectedRow();
            usernum_jtf.setText(String.valueOf(UserTable.getValueAt(row,0)));
            username_jtf.setText((String)UserTable.getValueAt(row,1));
            userpwd_jtf.setText((String)UserTable.getValueAt(row,2));

            int n = usertype.getItemCount();
            for(int i=0;i<n;i++)
            {
                String item = (String)usertype.getItemAt(i);
                if(UserTable.getValueAt(row,3).equals(item))
                    usertype.setSelectedIndex(i);
            }
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
