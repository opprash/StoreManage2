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

public class customs_add extends JFrame implements ActionListener {

    JLabel Cno,Cname,Csex,Caddr,Ctel;
    JTextField Cno_jtf,Cname_jtf,Caddr_jtf,Ctel_jtf;
    JComboBox Csex_jcb;
    JButton enter_jb,cancle_jb;
    JPanel jp;

    public customs_add()
    {
        Cno = new JLabel("客户代码");
        Cname = new JLabel("客户名称");
        Csex = new JLabel("客户性别");
        Csex_jcb = new JComboBox();
        Caddr = new JLabel("客户地址");
        Ctel = new JLabel("客户电话");
        Cno_jtf = new JTextField(25);
        Cname_jtf = new JTextField(25);
        Caddr_jtf = new JTextField(25);
        Ctel_jtf = new JTextField(25);
        enter_jb = new JButton("确认");
        enter_jb.addActionListener(this);
        cancle_jb = new JButton("重置");
        cancle_jb.addActionListener(this);

//        usertype.setModel(new DefaultComboBoxModel(new String[] {"User","Manager"}));
        Csex_jcb.setModel(new DefaultComboBoxModel(new String[]{"男","女"}));
        Csex_jcb.setPreferredSize(new Dimension(155,18));
        Csex_jcb.setSelectedIndex(0);

        jp = new JPanel(new FlowLayout());
        jp.add(Cno);jp.add(Cno_jtf);
        jp.add(Cname);jp.add(Cname_jtf);
        jp.add(Csex);jp.add(Csex_jcb);
        jp.add(Caddr);jp.add(Caddr_jtf);
        jp.add(Ctel);jp.add(Ctel_jtf);
        jp.add(enter_jb);jp.add(cancle_jb);

//        this.setLayout(new FlowLayout());
        this.setTitle("添加客户");
        this.add(jp);
//        this.add(Cno);this.add(Cno_jtf);
//        this.add(Cname);this.add(Cname_jtf);
//        this.add(Csex);this.add(Csex_jcb);
//        this.add(Caddr);this.add(Caddr_jtf);
//        this.add(Ctel);this.add(Ctel_jtf);
//        this.add(enter_jb);this.add(cancle_jb);
        this.setSize(300,250);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enter_jb)
        {
            if(checkEmpty.isEmpty(Cname_jtf.getText()) || checkEmpty.isEmpty(Cno_jtf.getText())
                    || checkEmpty.isEmpty(Caddr_jtf.getText()) || checkEmpty.isEmpty(Ctel_jtf.getText()))
            {
                JOptionPane.showMessageDialog(this,"请补充空白项");
            }
            else if(Cno_jtf.getText().length()!=4)
            {
                JOptionPane.showMessageDialog(this,"客户代码需为4位");
            }
            else if(Ctel_jtf.getText().length()!=11)
            {
                JOptionPane.showMessageDialog(this,"客户电话需为11位");
            }
            String Cno = Cno_jtf.getText();
            String Cname = Cname_jtf.getText().toString();
            String Csex = String.valueOf(Csex_jcb.getSelectedItem());
//            System.out.println(Sname);
            String Caddr = Caddr_jtf.getText().toString();
//            System.out.println(Saddr);
            String Ctel = Ctel_jtf.getText();
            Vector v = new Vector();
            v.addElement(Cno);
            v.addElement(Cname);
            v.addElement(Csex);
            v.addElement(Caddr);
            v.addElement(Ctel);

//            for(int i=0;i<4;i++)
//            {
//                System.out.println(v.get(i));
//            }

            Message ms = new Message();
            ms.setV(v);
            ms.setMesType(MessageType.message_insert_customsTable);
            ClientUser clientUser = new ClientUser();

            if(clientUser.SendInfo(ms)!=0)
            {
                JOptionPane.showMessageDialog(this,"添加成功");
            }
            else JOptionPane.showMessageDialog(this,"添加失败");
        }
        else if(e.getSource() == cancle_jb)
        {
            Cno_jtf.setText("");
            Cname_jtf.setText("");
            Csex_jcb.setSelectedIndex(0);
            Caddr_jtf.setText("");
            Ctel_jtf.setText("");
        }
    }

    public static void main(String[] args)
    {
        new customs_add();
    }
}
