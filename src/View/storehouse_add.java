package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import Common.Message;
import Common.MessageType;
import Model.ClientUser;
import Model.checkEmpty;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

public class storehouse_add extends JFrame implements ActionListener {

    JLabel Wno,Wname,Wsize,Waddr;
    JTextField Wno_jtf,Wname_jtf,Wsize_jtf,Waddr_jtf;
    JButton enter_jb,cancle_jb;

    public storehouse_add()
    {
        Wno = new JLabel("仓库代码");
        Wname = new JLabel("仓库名称");
        Wsize = new JLabel("仓库大小");
        Waddr = new JLabel("仓库地址");
        Wno_jtf = new JTextField(25);
        Wname_jtf = new JTextField(25);
        Wsize_jtf = new JTextField(25);
        Waddr_jtf = new JTextField(25);

        enter_jb = new JButton("确认");
        enter_jb.addActionListener(this);
        cancle_jb = new JButton("重置");
        cancle_jb.addActionListener(this);


        this.setLayout(new FlowLayout());
        this.setTitle("添加仓库");
        this.add(Wno);this.add(Wno_jtf);
        this.add(Wname);this.add(Wname_jtf);
        this.add(Wsize);this.add(Wsize_jtf);
        this.add(Waddr);this.add(Waddr_jtf);
        this.add(enter_jb);this.add(cancle_jb);
        this.setSize(250,125);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enter_jb)
        {
            if(checkEmpty.isEmpty(Wname_jtf.getText()) || checkEmpty.isEmpty(Wno_jtf.getText())
                    || checkEmpty.isEmpty(Waddr_jtf.getText()) || checkEmpty.isEmpty(Wsize_jtf.getText()))
            {
                JOptionPane.showMessageDialog(this,"请补充空白项");
            }
            else if(Wno_jtf.getText().length()!=2)
            {
                JOptionPane.showMessageDialog(this,"客户代码需为2位");
            }

            String Wno = Wno_jtf.getText();
            String Wname = Wname_jtf.getText().toString();
            int Wsize = Integer.parseInt(Wsize_jtf.getText());
            System.out.println(Wsize);
            String Waddr = Waddr_jtf.getText().toString();
            Vector v = new Vector();
            v.addElement(Wno);
            v.addElement(Wname);
            v.addElement(Wsize);
            v.addElement(Waddr);
            System.out.println(v.get(2));

//            for(int i=0;i<4;i++)
//            {
//                System.out.println(v.get(i));
//            }

            Message ms = new Message();
            ms.setV(v);
            ms.setMesType(MessageType.message_insert_storehouseTable);
            ClientUser clientUser = new ClientUser();

            if(clientUser.SendInfo(ms)!=0)
            {
                JOptionPane.showMessageDialog(this,"添加成功");
            }
            else JOptionPane.showMessageDialog(this,"添加失败");
        }
        else if(e.getSource() == cancle_jb)
        {
            Wno_jtf.setText("");
            Wname_jtf.setText("");
            Waddr_jtf.setText("");
            Wsize_jtf.setText("");
        }
    }

    public static void main(String[] args)
    {
        new storehouse_add();
    }
}
