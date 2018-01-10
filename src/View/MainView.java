package View;

import Common.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//

public class MainView extends JFrame implements ActionListener{

    JMenuBar jmb;
    JMenu menu1,menu2,menu3;
    JMenuItem item1_m1,item2_m1;
    JMenuItem item1_m2,item2_m2,item3_m2,item4_m2,item5_m2,item6_m2;
    JMenuItem item1_m3,item2_m3,item3_m3,item4_m3,item5_m3;
    JPanel jp,jp_top,jp_wel,jp_top_img,jp_menu,jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8,jp_time,jp_bottom;
    JLabel top_img;
    JButton exit_jb;

    public MainView(User u)
    {
        jmb = new JMenuBar();
        menu1 = new JMenu("�˻�����");
        menu2 = new JMenu("����");
//        menu3 = new JMenu("�������");

        item1_m1 = new JMenuItem("�û�����");
        item2_m1 = new JMenuItem("�����û�");
        item1_m1.addActionListener(this);
        item2_m1.addActionListener(this);

        item1_m2 = new JMenuItem("��ϵ����");
//        item2_m2 = new JMenuItem("��Ӧ�̲�ѯ");
//        item3_m2 = new JMenuItem("�ͻ���ѯ");
//        item4_m2 = new JMenuItem("�ֿ��ѯ");
//        item5_m2 = new JMenuItem("����¼��ѯ");
//        item6_m2 = new JMenuItem("�����¼��ѯ");
//        item1_m2.addActionListener(this);
//        item2_m2.addActionListener(this);
//        item3_m2.addActionListener(this);
//        item4_m2.addActionListener(this);
//        item5_m2.addActionListener(this);
//        item6_m2.addActionListener(this);


////        item1_m3 = new JMenuItem("������");
////        item2_m3 = new JMenuItem("�������");
//        item3_m3 = new JMenuItem("��Ӧ�̹���");
//        item4_m3 = new JMenuItem("�ͻ�����");
//        item5_m3 = new JMenuItem("�ֿ����");
////        item1_m3.addActionListener(this);
////        item2_m3.addActionListener(this);
//        item3_m3.addActionListener(this);
//        item4_m3.addActionListener(this);
//        item5_m3.addActionListener(this);

        if(u.getType() == 1)
        {
            menu1.add(item1_m1);
            menu1.add(item2_m1);
            jmb.add(menu1);
        }

        menu2.add(item1_m2);
//        menu2.add(item2_m2);
//        menu2.add(item3_m2);
//        menu2.add(item4_m2);
//        menu2.add(item5_m2);
//        menu2.add(item6_m2);
        jmb.add(menu2);
//
//        if(u.getType() == 1 || u.getType() == 2)
//        {
////            menu3.add(item1_m3);
////            menu3.add(item2_m3);
//            menu3.add(item3_m3);
//            menu3.add(item4_m3);
//            menu3.add(item5_m3);
//            jmb.add(menu3);
//        }


        JTabbedPane jtap = new JTabbedPane();
        top_img = new JLabel("��ӭʹ�òֿ����ϵͳ");
        top_img.setFont(new Font("����",1,60));
//        Icon top_img_ic = new ImageIcon("image/timg2.jpg");
//        top_img = new JLabel();
        jp_top_img = new JPanel();
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();
        jp7 = new JPanel();
        jp8 = new JPanel();
        jp_menu = new JPanel();
        jp_time = new JPanel();

//        jp_top_img.setOpaque(false);
        jp_top_img.add(top_img,"Center");
//        jp_bottom = new JPanel(new BorderLayout());
//
//        exit_jb = new JButton("�˳�");
//        exit_jb.addActionListener(this);
//
        jp_time.add(new TimeFrame());
//
//        jp_bottom.add(jp_time,"West");
//        jp_bottom.add(exit_jb,"East");

        Icon icon = new ImageIcon("image/male.png");
        JLabel label = new JLabel(icon);
        if (u.getUsername() != null) {
            label.setText("<html><font color='black' size=5 fonts='Times New Roman'>��ӭ����</font><font color='#336699'><b>" + u.getUsername()
                    + "</b></font></html>");
        } else {
            label.setText("<html><font color='black' size=5 fonts='Times New Roman'>��ӭ����</font><font color='#336699'><b></b></font></html>");
        }
        jp_wel = new JPanel();
        jp_wel.setPreferredSize(new Dimension(180, 40));
        jp_wel.setOpaque(false);
        jp_wel.add(label);


        jp_top = new JPanel(new BorderLayout());
        jp_top.add(jmb,"West");
        jp_top.add(jp_time,"Center");
        jp_top.add(jp_wel,"East");

        jp1.add(new store_search(u));
        jp2.add(new store_detl_search(u));
        jp3.add(new OutOfDate_search());
        jp4.add(new supplier_search(u));
        jp5.add(new customs_search(u));
        jp6.add(new storehouse_search(u));
        jp7.add(new Input_search());
        jp8.add(new Output_search());

        jtap.add(jp1,"�洢�ſ�");
        jtap.add(jp2,"�洢����");
        jtap.add(jp3,"���ڻ���");
        jtap.add(jp4,"��Ӧ�̹���");
        jtap.add(jp5,"�ͻ�����");
        jtap.add(jp6,"�ֿ����");
        jtap.add(jp7,"����¼��ѯ");
        jtap.add(jp8,"�����¼��ѯ");

        jp_menu.add(jtap);

//        this.setJMenuBar(jmb);
        this.setSize(850,630);
        this.setTitle("�ֿ����ϵͳ");
//        this.setLayout(new FlowLayout());
//        this.add(jp);
//        this.add(jp_bottom);
        this.add(jp_top,"North");
        this.add(jp_top_img,"Center");
        this.add(jp_menu,"South");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String [] args)
    {
        User u = new User();
        u.setType(1);
       new MainView(u);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == item1_m1)
        {
            new UserManage();
        }
        else if(e.getSource() == item2_m1)
        {
            new registerFrm();
        }
    }
}