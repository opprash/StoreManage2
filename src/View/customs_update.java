package View;

import Common.Message;
import Common.MessageType;
import Model.ClientUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class customs_update extends JFrame implements ActionListener {

    JLabel Cno,Cname,Csex,Caddr,Ctel;
    JTextField Cno_jtf,Cname_jtf,Caddr_jtf,Ctel_jtf;
    JComboBox Csex_jcb;
    JButton enter_jb,cancle_jb;
    JPanel jp;

    public customs_update(Vector ct)
    {
        Cno = new JLabel("�ͻ����");
        Cname = new JLabel("�ͻ�����");
        Csex = new JLabel("�ͻ��Ա�");
        Caddr = new JLabel("�ͻ���ַ");
        Ctel = new JLabel("�ͻ��绰");

        Cno_jtf = new JTextField(20);
        Cname_jtf = new JTextField(20);
        Caddr_jtf = new JTextField(20);
        Ctel_jtf = new JTextField(20);

        Csex_jcb = new JComboBox();
        Csex_jcb.setModel(new DefaultComboBoxModel(new String[]{"��","Ů"}));
        Csex_jcb.setPreferredSize(new Dimension(155,18));
        Csex_jcb.setSelectedIndex(0);

        Cno_jtf.setText((String)ct.get(0));
        Cno_jtf.setEditable(false);
        Cname_jtf.setText((String)ct.get(1));
        Caddr_jtf.setText((String)ct.get(2));
        Ctel_jtf.setText((String)ct.get(3));

        enter_jb = new JButton("ȷ��");
        enter_jb.addActionListener(this);
        cancle_jb = new JButton("ȡ��");
        cancle_jb.addActionListener(this);

        jp = new JPanel(new FlowLayout());
        jp.add(Cno);
        jp.add(Cno_jtf);
        jp.add(Cname);
        jp.add(Cname_jtf);
        jp.add(Csex);
        jp.add(Csex_jcb);
        jp.add(Caddr);
        jp.add(Caddr_jtf);
        jp.add(Ctel);
        jp.add(Ctel_jtf);
        jp.add(enter_jb);
        jp.add(cancle_jb);

        this.add(jp);
        this.setSize(280,250);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


    public static void main(String[] args)
    {
        Vector v = new Vector();
        v.addElement("");
        v.addElement("");
        v.addElement("");
        v.addElement("");
        new customs_update(v);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enter_jb)
        {
            if(Cno_jtf.getText() == null || Cname_jtf.getText() == null
                    || Caddr_jtf.getText() == null || Ctel_jtf.getText() == null)
            {
                JOptionPane.showMessageDialog(this,"�벹ȫ�հ���");
                return;
            }
            Vector v = new Vector();
            v.addElement((String)Cno_jtf.getText());
            v.addElement((String)Cname_jtf.getText());
            v.addElement(String.valueOf(Csex_jcb.getSelectedItem()));
            v.addElement((String)Caddr_jtf.getText());
            v.addElement((String)Ctel_jtf.getText());

            Message ms = new Message();
            ms.setV(v);
            ms.setMesType(MessageType.message_update_customsTable);
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
