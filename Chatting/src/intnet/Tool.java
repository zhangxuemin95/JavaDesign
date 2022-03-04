package intnet;

import entity.ChattingRecord;
import entity.Sendor;
import entity.User;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Tool {
    public static Sendor acceptSender(Socket s)
    {
        Sendor sendor = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
            sendor = (Sendor) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sendor;
    }

    public static void sendSender(Socket s, Sendor sendor)
    {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(sendor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList transformFriend(ResultSet rs) throws SQLException {
        ArrayList<String> arrayList = new ArrayList<>();
        while (rs.next())
        {
            String friendnum = rs.getString(1);
            String friendname = rs.getString(2);
            arrayList.add(friendnum + "  " + friendname + "\n");
        }
        return arrayList;
    }

    public static ArrayList transformChattingRecord(ResultSet rs) throws SQLException {
        ArrayList<ChattingRecord> arrayList = new ArrayList<>();
        while (rs.next())
        {
            ChattingRecord chattingRecord = new ChattingRecord();
            User sender = new User();
            User accepter = new User();
            sender.setAccountnum(rs.getString(1));
            sender.setName(rs.getString(2));
            accepter.setAccountnum(rs.getString(3));
            accepter.setName(rs.getString(4));
            chattingRecord.setSender(sender);
            chattingRecord.setAccepter(accepter);
            chattingRecord.setContent(rs.getString(5));
            chattingRecord.setDate(new Date(rs.getTimestamp(6).getTime()));
            arrayList.add(chattingRecord);
        }
        return arrayList;
    }
}
