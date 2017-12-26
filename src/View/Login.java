package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import java.sql.*;

import Common.*;
import Model.*;
import Dao.*;
import Model.*;

public class Login extends JFrame implements ActionListener{

    JPanel jp1,jp2,jp3;
    JLabel tittle_jbl,name_jbl,pwd_jbl;
    JTextField name_jtf;
    JPasswordField pwd_jpf;
    JButton enter_jb,register_jb;

    sqlConnection sql = new sqlConnection();
    dao_Login daoLogin = new dao_Login();

    public Login()
    {
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        tittle_jbl = new JLabel("欢迎使用仓库管理系统",JLabel.CENTER);
        tittle_jbl.setFont(new java.awt.Font("黑体",1,15));
        name_jbl = new JLabel("用户名",JLabel.CENTER);
        pwd_jbl = new JLabel(" 密码 ",JLabel.CENTER);
        name_jtf = new JTextField(35);
        pwd_jpf = new JPasswordField(35);
        enter_jb = new JButton("确认");
        enter_jb.addActionListener(this);
        register_jb = new JButton("注册");
        register_jb.addActionListener(this);

        jp1.add(name_jbl);
        jp1.add(name_jtf);
        jp2.add(pwd_jbl);
        jp2.add(pwd_jpf);
        jp3.add(enter_jb);
        jp3.add(register_jb);

//        jp1.add(tittle_jbl);

        this.setLayout(new GridLayout(4,1));
        this.add(tittle_jbl);
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.setTitle("登录");
        this.setSize(500,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String [] args)
    {
        Login login = new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enter_jb)
        {
            String username = name_jtf.getText().toString();
            String userpwd = new String(pwd_jpf.getPassword());
            System.out.println(username+" "+userpwd);
            if(checkEmpty.isEmpty(username))
            {
                JOptionPane.showMessageDialog(this,"用户名不能为空");
                return;
            }
            if(checkEmpty.isEmpty(userpwd))
            {
                JOptionPane.showMessageDialog(this,"密码不能为空");
                return;
            }

            User u = new User();
            u.setUsername(username);
            u.setUserpwd(userpwd);

            Message ms = new Message();
            ms.setU(u);
            ms.setMesType(MessageType.message_login);

            System.out.println(ms.getU().getUsername());
            ClientUser clientUser = new ClientUser();

            if(clientUser.checkUser(ms))
            {
                System.out.println("success");
            }
            else
            {
                JOptionPane.showMessageDialog(this,"用户名或密码错误");
            }


        }
        else if(e.getSource() == register_jb)
        {
//            this.dispose();
            new registerFrm();
        }
    }
}
