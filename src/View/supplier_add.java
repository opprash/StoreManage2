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

public class supplier_add extends JFrame implements ActionListener {

    JLabel Sno,Sname,Saddr,Stel;
    JTextField Sno_jtf,Sname_jtf,Saddr_jtf,Stel_jtf;
    JButton enter_jb,cancle_jb;
    JPanel jp;

    public supplier_add()
    {
        Sno = new JLabel("��Ӧ�̴���");
        Sname = new JLabel("��Ӧ������");
        Saddr = new JLabel("��Ӧ�̵�ַ");
        Stel = new JLabel("��Ӧ�̵绰");
        Sno_jtf = new JTextField(25);
        Sname_jtf = new JTextField(25);
        Saddr_jtf = new JTextField(25);
        Stel_jtf = new JTextField(25);
        enter_jb = new JButton("ȷ��");
        enter_jb.addActionListener(this);
        cancle_jb = new JButton("����");
        cancle_jb.addActionListener(this);

        jp = new JPanel(new FlowLayout());
        jp.add(Sno);jp.add(Sno_jtf);
        jp.add(Sname);jp.add(Sname_jtf);
        jp.add(Saddr);jp.add(Saddr_jtf);
        jp.add(Stel);jp.add(Stel_jtf);
        jp.add(enter_jb);jp.add(cancle_jb);

//        this.setLayout(new FlowLayout());
        this.setTitle("��ӹ�Ӧ��");
        this.add(jp);
//        this.add(Sno);this.add(Sno_jtf);
//        this.add(Sname);this.add(Sname_jtf);
//        this.add(Saddr);this.add(Saddr_jtf);
//        this.add(Stel);this.add(Stel_jtf);
//        this.add(enter_jb);this.add(cancle_jb);
        this.setSize(300,230);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enter_jb)
        {
            if(checkEmpty.isEmpty(Sname_jtf.getText()) || checkEmpty.isEmpty(Sno_jtf.getText())
                    || checkEmpty.isEmpty(Saddr_jtf.getText()) || checkEmpty.isEmpty(Stel_jtf.getText()))
            {
                JOptionPane.showMessageDialog(this,"�벹��հ���");
            }
            else if(Sno_jtf.getText().length()!=4)
            {
                JOptionPane.showMessageDialog(this,"��Ӧ�̴�����Ϊ4λ");
            }
            else if(Stel_jtf.getText().length()!=11)
            {
                JOptionPane.showMessageDialog(this,"��Ӧ�̵绰��Ϊ11λ");
            }
            String Sno = Sno_jtf.getText();
            String Sname = Sname_jtf.getText().toString();
//            System.out.println(Sname);
            String Saddr = Saddr_jtf.getText().toString();
//            System.out.println(Saddr);
            String Stel = Stel_jtf.getText();
            Vector v = new Vector();
            v.addElement(Sno);
            v.addElement(Sname);
            v.addElement(Saddr);
            v.addElement(Stel);

//            for(int i=0;i<4;i++)
//            {
//                System.out.println(v.get(i));
//            }

            Message ms = new Message();
            ms.setV(v);
            ms.setMesType(MessageType.message_insert_supplierTable);
            ClientUser clientUser = new ClientUser();

            if(clientUser.SendInfo(ms)!=0)
            {
                JOptionPane.showMessageDialog(this,"��ӳɹ�");
            }
            else JOptionPane.showMessageDialog(this,"���ʧ��");
        }
        else if(e.getSource() == cancle_jb)
        {
            Sno_jtf.setText("");
            Sname_jtf.setText("");
            Saddr_jtf.setText("");
            Stel_jtf.setText("");
        }
    }

    public static void main(String[] args)
    {
        new supplier_add();
    }
}
