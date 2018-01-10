import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import View.Login;

import java.awt.*;

/**
 * 程序开始执行的类
 *
 */
public class Start {

    public static void main(String[] args) {
        try {
            // 设置窗口边框样式
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            // 隐藏设置按钮
            UIManager.put("RootPane.setupButtonVisible", false);


            String[] DEFAULT_FONT  = new String[]{
                    "Table.font"
                    ,"TableHeader.font"
                    ,"CheckBox.font"
                    ,"Tree.font"
                    ,"Viewport.font"
                    ,"ProgressBar.font"
                    ,"RadioButtonMenuItem.font"
                    ,"ToolBar.font"
                    ,"ColorChooser.font"
                    ,"ToggleButton.font"
                    ,"Panel.font"
                    ,"TextArea.font"
                    ,"Menu.font"
                    ,"TableHeader.font"
                    // ,"TextField.font"
                    ,"OptionPane.font"
                    ,"MenuBar.font"
                    ,"Button.font"
                    ,"Label.font"
                    ,"PasswordField.font"
                    ,"ScrollPane.font"
                    ,"MenuItem.font"
                    ,"ToolTip.font"
                    ,"List.font"
                    ,"EditorPane.font"
                    ,"Table.font"
                    ,"TabbedPane.font"
                    ,"RadioButton.font"
                    ,"CheckBoxMenuItem.font"
                    ,"TextPane.font"
                    ,"PopupMenu.font"
                    ,"TitledBorder.font"
                    ,"ComboBox.font"
            };
// 调整默认字体
            for (int i = 0; i < DEFAULT_FONT.length; i++)
                UIManager.put(DEFAULT_FONT[i],new Font("微软雅黑", Font.PLAIN,12));

        } catch (Exception e) {

        }
        // 初始化登陆窗口
        new Login();
    }


}
