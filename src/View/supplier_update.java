package View;

import Common.Message;
import Common.MessageType;
import Model.ClientUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class supplier_update extends JFrame implements ActionListener {

    JLabel Sno,Sname,Saddr,Stel;
    JTextField Sno_jtf,Sname_jtf,Saddr_jtf,Stel_jtf;
    JButton enter_jb,cancle_jb;
    JPanel jp;

    public supplier_update(Vector sup)
    {
        Sno = new JLabel("��Ӧ�̱��");
        Sname = new JLabel("��Ӧ������");
        Saddr = new JLabel("��Ӧ�̵�ַ");
        Stel = new JLabel("��Ӧ�̵绰");

        Sno_jtf = new JTextField(20);
        Sname_jtf = new JTextField(20);
        Saddr_jtf = new JTextField(20);
        Stel_jtf = new JTextField(20);

        Sno_jtf.setText((String)sup.get(0));
        Sno_jtf.setEditable(false);
        Sname_jtf.setText((String)sup.get(1));
        Saddr_jtf.setText((String)sup.get(2));
        Stel_jtf.setText((String)sup.get(3));

        enter_jb = new JButton("ȷ��");
        enter_jb.addActionListener(this);
        cancle_jb = new JButton("ȡ��");
        cancle_jb.addActionListener(this);

        jp = new JPanel(new FlowLayout());
        jp.add(Sno);
        jp.add(Sno_jtf);
        jp.add(Sname);
        jp.add(Sname_jtf);
        jp.add(Saddr);
        jp.add(Saddr_jtf);
        jp.add(Stel);
        jp.add(Stel_jtf);
        jp.add(enter_jb);
        jp.add(cancle_jb);

        this.add(jp);
        this.setSize(280,250);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

//
//    public static void main(String[] args)
//    {
//        Vector v = new Vector();
//        v.addElement("S001");
//        v.addElement("����");
//        v.addElement("����");
//        v.addElement("12345612345");
//        new supplier_update(v);
//    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enter_jb)
        {
            if(Sno_jtf.getText() == null || Sname_jtf.getText() == null
                    || Saddr_jtf.getText() == null || Stel_jtf.getText() == null)
            {
                JOptionPane.showMessageDialog(this,"�벹ȫ�հ���");
                return;
            }
            Vector v = new Vector();
            v.addElement((String)Sno_jtf.getText());
            v.addElement((String)Sname_jtf.getText());
            v.addElement((String)Saddr_jtf.getText());
            v.addElement((String)Stel_jtf.getText());

            Message ms = new Message();
            ms.setV(v);
            ms.setMesType(MessageType.message_update_supplierTable);
            ClientUser clientUser = new ClientUser();
            if(clientUser.SendInfo(ms)!=0)
            {
                JOptionPane.showMessageDialog(this,"�޸ĳɹ�");
            }
            else JOptionPane.showMessageDialog(this,"�޸�ʧ��");


        }
        else if(e.getSource() == cancle_jb)
        {
            this.dispose();
        }
    }
}
