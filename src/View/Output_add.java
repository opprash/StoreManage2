package View;

import Common.Message;
import Common.MessageType;
import Model.ClientUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.text.SimpleDateFormat;
import java.util.Date;
import Model.checkEmpty;
import sun.plugin2.ipc.windows.WindowsNamedPipe;

public class Output_add extends JFrame implements ActionListener,ItemListener{

    JLabel Gno,Gname,Tno,Tname,Wno,Inum,Cno,Cname,Onum;
    JTextField Gno_jtf,Tno_jtf,Tname_jtf,Inum_jtf,Cno_jtf,Onum_jtf;
    JComboBox Gno_jcb,Gname_jcb,Wno_jcb,Cname_jcb;
    JButton enter_jb,cancle_jb;
    Vector v1,v2,v3,v4;


    public Output_add()
    {
        Gno = new JLabel("货物编号");
        Gname = new JLabel("货物名称");
        Tno = new JLabel("货物类型号");
        Tname = new JLabel("货物类型");
        Wno = new JLabel("存放仓库号");
        Inum = new JLabel("货物存量");
        Cno = new JLabel("客户编号");
        Cname = new JLabel("客户名称");
        Onum = new JLabel("出货数量");

        Gno_jtf = new JTextField(20);
        Gno_jtf.setEditable(false);
        Tno_jtf = new JTextField(20);
        Tno_jtf.setEditable(false);
        Tname_jtf = new JTextField(20);
        Tname_jtf.setEditable(false);
        Inum_jtf = new JTextField(20);
        Inum_jtf.setEditable(false);
        Cno_jtf = new JTextField(20);
        Cno_jtf.setEditable(false);
        Onum_jtf = new JTextField(20);

        enter_jb = new JButton("确认");
        enter_jb.addActionListener(this);
        cancle_jb = new JButton("重置");
        cancle_jb.addActionListener(this);

        Message ms = new Message();
        Message ms2 = new Message();
        ms.setMesType(MessageType.message_select_storeTable_dintinct);
        ms2.setMesType(MessageType.message_select_customsTable);
        ClientUser clientUser = new ClientUser();
        v1 = clientUser.getTable(ms);
        v2 = new Vector();
        v3 = clientUser.getTable(ms2);
        v4 = new Vector();
        int i = 1;
        while(i<v1.size())
        {
            v2.addElement(v1.get(i));
            i+=4;
        }
        i = 1;
        while(i < v3.size())
        {
            v4.addElement(v3.get(i));
            i+=5;
        }

        Gname_jcb = new JComboBox(v2);
        Gname_jcb.setPreferredSize(new Dimension(120,18));
        Gname_jcb.setSelectedIndex(-1);
        Gname_jcb.addItemListener(this);

        Cname_jcb = new JComboBox(v4);
        Cname_jcb.setPreferredSize(new Dimension(120,18));
        Cname_jcb.setSelectedIndex(-1);
        Cname_jcb.addItemListener(this);

        Wno_jcb = new JComboBox();
        Wno_jcb.setPreferredSize(new Dimension(120,18));
        Wno_jcb.setSelectedIndex(-1);
        Wno_jcb.addItemListener(this);

        this.setLayout(new FlowLayout());
        this.add(Gno);
        this.add(Gno_jtf);
        this.add(Gname);
        this.add(Gname_jcb);
        this.add(Tno);
        this.add(Tno_jtf);
        this.add(Tname);
        this.add(Tname_jtf);
        this.add(Wno);
        this.add(Wno_jcb);
        this.add(Inum);
        this.add(Inum_jtf);
        this.add(Cno);
        this.add(Cno_jtf);
        this.add(Cname);
        this.add(Cname_jcb);
        this.add(Onum);
        this.add(Onum_jtf);
        this.add(enter_jb);
        this.add(cancle_jb);
        this.setSize(220,240);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enter_jb)
        {
            if(Wno_jcb.getSelectedItem()==null ||Gname_jcb.getSelectedItem()==null || Cname_jcb.getSelectedItem() == null || Onum_jtf.getText()==null)
            {
                JOptionPane.showMessageDialog(this,"请补全空白项");
                return;
            }
            else if(Integer.parseInt(Inum_jtf.getText()) < Integer.parseInt(Onum_jtf.getText()))
            {
                JOptionPane.showMessageDialog(this,"货物数量不足,请调整出货量");
                return;
            }

            Vector v = new Vector();
            v.addElement((String)Gno_jtf.getText());
            v.addElement((String)Gname_jcb.getSelectedItem());
            v.addElement((String)Tno_jtf.getText());
            v.addElement((String)Tname_jtf.getText());
            v.addElement((String)Wno_jcb.getSelectedItem());
            v.addElement((String)Cno_jtf.getText());
            v.addElement((String)Cname_jcb.getSelectedItem());
            v.addElement((String)Onum_jtf.getText());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            v.addElement(df.format(new Date()));

            Message m = new Message();
            m.setV(v);
            m.setMesType(MessageType.message_insert_OutputTable);
            ClientUser clientUser = new ClientUser();
            if(clientUser.SendInfo(m)!=0)
            {
                JOptionPane.showMessageDialog(this,"出库成功");
                Gname_jcb.setSelectedIndex(-1);
                Wno_jcb.setSelectedIndex(-1);
                Cname_jcb.setSelectedIndex(-1);
                Gno_jtf.setText("");
                Tno_jtf.setText("");
                Inum_jtf.setText("");
                Tname_jtf.setText("");
                Cno_jtf.setText("");
                Onum_jtf.setText("");
            }
            else JOptionPane.showMessageDialog(this,"出库失败");
            }
        else if(e.getSource() == cancle_jb)
        {
            Gname_jcb.setSelectedIndex(-1);
            Wno_jcb.setSelectedIndex(-1);
            Cname_jcb.setSelectedIndex(-1);
            Gno_jtf.setText("");
            Tno_jtf.setText("");
            Inum_jtf.setText("");
            Tname_jtf.setText("");
            Cno_jtf.setText("");
            Onum_jtf.setText("");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getItemSelectable() == Gname_jcb)
        {
            String Gname = (String)Gname_jcb.getSelectedItem();
            for(int i = 1;i<v1.size();i+=4)
            {
                if(v1.get(i).equals(Gname))
                {
                    Gno_jtf.setText((String)v1.get(i-1));
                    Tno_jtf.setText((String)v1.get(i+1));
                    Tname_jtf.setText((String)v1.get(i+2));
//                    Wno_jtf.setText((String)v1.get(i+3));
//                    Inum_jtf.setText(String.valueOf(v1.get(i+4)));


                }
            }

            Message m = new Message();
            m.setCon((String)Gno_jtf.getText());
            m.setMesType(MessageType.message_select_stores_Gno);
            ClientUser clientUser2 = new ClientUser();
            Vector vv = clientUser2.getTable(m);
            Wno_jcb.removeAllItems();
            for(int i = 0;i<vv.size();i++)
            {
                Wno_jcb.addItem((String)vv.get(i));
            }
            Wno_jcb.setSelectedIndex(0);

        }
        else if(e.getItemSelectable() == Cname_jcb)
        {
            String Cname = (String)Cname_jcb.getSelectedItem();
            for(int i = 1;i<v3.size();i++)
            {
                if(v3.get(i).equals(Cname))
                {
                    Cno_jtf.setText((String)v3.get(i-1));
                }
            }
        }
        else if(e.getItemSelectable() == Wno_jcb)
        {
            String Wno = (String)Wno_jcb.getSelectedItem();
            String Gno = (String)Gno_jtf.getText();
            Message ms = new Message();
            ms.setMesType(MessageType.message_select_storeTable);
            ClientUser clientUser = new ClientUser();
            Vector v = clientUser.getTable(ms);

//            System.out.println(v);
            for(int i = 0;i<v.size();i=i+6)
            {
                if(v.get(i).equals(Gno) && v.get(i+4).equals(Wno))
                {
                    Inum_jtf.setText(String.valueOf(v.get(i+5)));
                }
            }
        }
    }

    public static void main(String[] args)
    {
        new Output_add();
    }


}