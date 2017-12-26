package Dao;

import java.sql.*;
import Common.*;

public class dao_Login {

    public User login(Connection con,User user) throws SQLException {
        User resultuser = null;
        String sel = "select * from user where username=? and userpwd=?";
        PreparedStatement psmt = con.prepareStatement(sel);
        psmt.setString(1,user.getUsername());
        psmt.setString(2,user.getUserpwd());
        ResultSet rs = psmt.executeQuery();
        if(rs.next())
        {
            resultuser = new User();
            resultuser.setUsername(user.getUsername());
            resultuser.setUserpwd(user.getUserpwd());
            resultuser.setType(user.getType());
        }
        return resultuser;
    }

}
