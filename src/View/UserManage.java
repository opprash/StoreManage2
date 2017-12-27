package View;

import Common.Message;
import Common.MessageType;
import Common.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

public class UserManage extends JFrame {

    JTable UserTable;
    JTextField S_username_jtf;
    JTextField username_jtf,userpwd_jtf;
    JComboBox usertype;
    JButton search_jb,enter_jb,delete_jb;
    JLabel jbl1,jbl2,jbl3,jbl4,jbl5,jbl6;
    JPanel jp1,jp2,jp3;
    JScrollPane jsp;

    public UserManage()
    {

    }
    void init()
    {
        jp1 = new JPanel();
        jbl1 = new JLabel("用户名");
        S_username_jtf = new JTextField(10);
        search_jb = new JButton("查询");
        jp1.add(jbl1);
        jp1.add(S_username_jtf);
        jp1.add(search_jb);

        jp2 = new JPanel();
        jsp = new JScrollPane();
        UserTable = new JTable();

    }

    void fillTable(String name)
    {
        DefaultTableModel dtm = (DefaultTableModel)UserTable.getModel();
        dtm.setRowCount(0);
        Message ms = new Message();
        User u = new User();
        u.setUsername(name);
        ms.setU(u);
        ms.setMesType(MessageType.message_select);


//        ResultSet rs =
    }

}
