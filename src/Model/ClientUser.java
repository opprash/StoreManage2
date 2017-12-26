package Model;

import Common.*;

public class ClientUser {

    public boolean checkUser(Message ms )
    {
        return new ClientConServer().SendLoginInfoToServer(ms);
    }
}
