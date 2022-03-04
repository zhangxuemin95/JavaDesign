import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetP {
    public static void main(String[] args) {
        try {
            InetAddress localhost = InetAddress.getByName("DESKTOP-DKV0KEG");
            System.out.println(localhost);
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
