package View;

import Common.Message;
import Common.MessageType;
import Model.ClientUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class storehouse_update extends JFrame implements ActionListener {

    JLabel Wno,Wname,Waddr;
    JTextField Wno_jtf,Wname_jtf,Waddr_jtf;
    JButton enter_jb,cancle_jb;
    JPanel jp;

    public storehouse_update(Vector house)
    {
        Wno = new JLabel("仓库编号");
        Wname = new JLabel("仓库名称");
        Waddr = new JLabel("仓库地址");
//        Wtel = new JLabel("仓库电话");

        Wno_jtf = new JTextField(20);
        Wname_jtf = new JTextField(20);
        Waddr_jtf = new JTextField(20);
//        Wtel_jtf = new JTextField(20);

        Wno_jtf.setText((String)house.get(0));
        Wno_jtf.setEditable(false);
        Wname_jtf.setText((String)house.get(1));
        Waddr_jtf.setText((String)house.get(2));
//        Wtel_jtf.setText((String)house.get(3));

        enter_jb = new JButton("确定");
        enter_jb.addActionListener(this);
        cancle_jb = new JButton("取消");
        cancle_jb.addActionListener(this);

        jp = new JPanel(new FlowLayout());
        jp.add(Wno);
        jp.add(Wno_jtf);
        jp.add(Wname);
        jp.add(Wname_jtf);
        jp.add(Waddr);
        jp.add(Waddr_jtf);
//        jp.add(Wtel);
//        jp.add(Wtel_jtf);
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
//        v.addElement("旺旺");
//        v.addElement("北京");
//        v.addElement("12345612345");
//        new supplier_update(v);
//    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enter_jb)
        {
            if(Wno_jtf.getText() == null || Wname_jtf.getText() == null
                    || Waddr_jtf.getText() == null )
            {
                JOptionPane.showMessageDialog(this,"请补全空白项");
                return;
            }
            Vector v = new Vector();
            v.addElement((String)Wno_jtf.getText());
            v.addElement((String)Wname_jtf.getText());
            v.addElement((String)Waddr_jtf.getText());
//            v.addElement((String)Wtel_jtf.getText());

            Message ms = new Message();
            ms.setV(v);
            ms.setMesType(MessageType.message_update_storehouseTable);
            ClientUser clientUser = new ClientUser();
            if(clientUser.SendInfo(ms)!=0)
            {
                JOptionPane.showMessageDialog(this,"修改成功");
            }
            else JOptionPane.showMessageDialog(this,"修改失败");
        }
        else if(e.getSource() == cancle_jb)
        {
            this.dispose();
        }
    }
}
