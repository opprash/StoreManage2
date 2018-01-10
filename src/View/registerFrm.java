package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

import Common.Message;
import Common.MessageType;
import Common.User;
import Model.*;
import jdk.nashorn.internal.scripts.JO;

public class registerFrm extends JFrame implements ActionListener{

    JPanel jp1,jp2,jp3,jp4,jp5;
    JLabel tittle_jbl,name_jbl,pwd_jbl,pwd_jbl2;
    JButton enter_jb,cancle_jb;
    JTextField name_jtf;
    JPasswordField pwd_jpf,pwd_jpf2;

    public registerFrm()
    {
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        tittle_jbl = new JLabel("注册");
        name_jbl = new JLabel(" 用戶名 ");
        pwd_jbl = new JLabel("  密码  ");
        pwd_jbl2 = new JLabel("确认密码");
        enter_jb = new JButton("确认");
        enter_jb.addActionListener(this);
        cancle_jb = new JButton("重置");
        cancle_jb.addActionListener(this);
        name_jtf = new JTextField(20);
        pwd_jpf = new JPasswordField(20);
        pwd_jpf2 = new JPasswordField(20);

        jp1.add(tittle_jbl);
        jp2.add(name_jbl);
        jp2.add(name_jtf);
        jp3.add(pwd_jbl);
        jp3.add(pwd_jpf);
        jp4.add(pwd_jbl2);
        jp4.add(pwd_jpf2);
        jp5.add(enter_jb);
        jp5.add(cancle_jb);

        this.setLayout(new GridLayout(5,1));
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);
        this.setSize(300,250);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
    }

    public static void main(String [] args)
    {
        registerFrm register = new registerFrm();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enter_jb)
        {
            if(checkEmpty.isEmpty(name_jtf.getText()))
            {
                JOptionPane.showMessageDialog(this,"用户名不能为空");
            }
            if(checkEmpty.isEmpty(new String(pwd_jpf.getPassword())))
            {
                JOptionPane.showMessageDialog(this,"密码不能为空");
            }
            else if(checkEmpty.isEmpty(new String(pwd_jpf2.getPassword())))
            {
                JOptionPane.showMessageDialog(this,"请确认密码");
            }
            else if(!new String(pwd_jpf.getPassword()).equals(new String(pwd_jpf2.getPassword())))
            {
                JOptionPane.showMessageDialog(this,"两次输入密码不一致");
            }

            String name = name_jtf.getText();
            String pwd = new String(pwd_jpf.getPassword());

            User u = new User();
            u.setUsername(name);
            u.setUserpwd(pwd);

            Message ms = new Message();
            ms.setU(u);
            ms.setMesType(MessageType.message_register);

//            if()
            ClientUser clientUser = new ClientUser();

            u.setType(clientUser.checkUser(ms));

//            System.out.println(ms.getU().getType());

            if(u.getType() != 0)
            {
//                System.out.println("register succeed");
                JOptionPane.showMessageDialog(this,"注册成功");
//                System.out.println(u.getType());
//                new MainView(u);
            }
            else
            {
                JOptionPane.showMessageDialog(this,"注册失败");
            }
//            Connection con =
        }
        else if(e.getSource() == cancle_jb)
        {
            name_jtf.setText("");
            pwd_jpf.setText("");
            pwd_jpf2.setText("");
        }
    }
}
