package Model;

import Common.*;

public class ClientUser {

    public int checkUser(Message ms )
    {
        return new ClientConServer().SendLoginInfoToServer(ms);
    }
}
