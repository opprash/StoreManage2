package Model;

import Common.*;

import java.sql.ResultSet;
import java.util.Vector;

public class ClientUser {

    public int checkUser(Message ms )
    {
        return new ClientConServer().SendLoginInfoToServer(ms);
    }

    public Vector getTable(Message ms)
    {
        return new ClientConServer().SendTableSelectInfoToServer(ms);
    }

    public int updateUser(Message ms)
    {
        return new ClientConServer().SendInfoToServer(ms);
    }

    public int SendInfo(Message ms)
    {
        return new ClientConServer().SendInfoToServer(ms);
    }

}
