package View;

import Common.Message;
import Common.MessageType;
import Model.ClientUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import Model.checkEmpty;

public class Input_add extends JFrame implements ActionListener {

    JLabel Gno,Gname,Tno,Tname,Sno,Wno,Inum;
    JTextField Gno_jtf,Gname_jtf,Tno_jtf,Sno_jtf,Wno_jtf,Inum_jtf;
    JComboBox Gno_jcb,Gname_jcb,Tno_jcb,Sno_jcb,Wno_jcb;
    JButton enter_jb,cancle_jb;

    public Input_add()
    {
        Gno = new JLabel("货物编号");
        Gname = new JLabel("货物名称");
        Tno = new JLabel("货物类型号");
        Tname = new JLabel("货物类型");
        Sno = new JLabel("供应商编号");
        Wno = new JLabel("货物存放仓库号");
        Inum = new JLabel("进货数量");

        Gno_jtf = new JTextField(10);
        Gno_jtf.setEditable(false);
        Tno_jtf = new JTextField(10);
        Tno_jtf.setEditable(false);
        Sno_jtf = new JTextField(10);
        Sno_jtf.setEditable(false);
        Inum_jtf = new JTextField(10);

        Message ms = new Message();
        ms.setMesType(MessageType.message_select_goods);
        ClientUser clientUser = new ClientUser();
        Vector v1 = clientUser.getTable(ms);
        Vector v2 = null;
        int i = 2;
        while(v1.get(i)!=null)
        {
            v2.addElement(v1.get(i));
            i+=5;
        }



    }






    @Override
    public void actionPerformed(ActionEvent e) {

    }
}