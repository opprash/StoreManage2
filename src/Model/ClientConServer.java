package Model;

import Common.Message;
import Common.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.Vector;

public class ClientConServer {

    public Socket s;

    {
        try {
            s = new Socket("127.0.0.1",8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int SendLoginInfoToServer(Message m)
    {
        int sym = 0;
        try {
//            s = new Socket("127.0.0.1",8888);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(m);

            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            Message ms = (Message)ois.readObject();

            if(ms.getMesType().equals(MessageType.message_login_success))
            {
                sym = ms.getU().getType();
            }
            else if(ms.getMesType().equals(MessageType.message_register_success))
            {
                sym = ms.getU().getType();
            }
//            System.out.println(ms.getMesType().toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sym;
    }

    public Vector SendTableSelectInfoToServer(Message m)
    {
        Message ms = new Message();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(m);

            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            ms = (Message)ois.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ms.getV();
    }

    public int SendInfoToServer(Message m)
    {
        int sym = 0;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(m);

            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            Message ms = (Message)ois.readObject();

            sym = Integer.parseInt(ms.getCon());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sym;
    }
}
