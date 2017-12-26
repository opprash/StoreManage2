package Model;

import Common.Message;
import Common.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConServer {

    public Socket s;

    public boolean SendLoginInfoToServer(Message m)
    {
        boolean b = false;
        try {
            s = new Socket("127.0.0.1",8888);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(m);

            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            Message ms = (Message)ois.readObject();

            if(ms.getMesType().equals(MessageType.message_login_success))
            {
                b = true;
            }
//            System.out.println(ms.getMesType().toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return b;
    }
}
