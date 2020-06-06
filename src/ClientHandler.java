import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;

public class ClientHandler extends Thread{
    final Socket socket;
    final DataInputStream input;
    final DataOutputStream output;
    DBConn dbc = DBConn.getDBConn();
    Connection con = dbc.getConnection();

    public ClientHandler(Socket socket, DataInputStream input, DataOutputStream output) {
        this.socket = socket;
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        while(true) {
            try {
                output.writeUTF("");
                String received = input.readUTF();
                if (received.equals("Done")) {
                    this.socket.close();
                    System.out.println("Connection terminated");
                    break;
                }
                System.out.println(received);
                PhoneNumberData pnd = new PhoneNumberData(received);
                String sql = "SELECT * FROM contacts WHERE phoneNumber='" + pnd.getPhoneNumber() + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    if (rs.getString(5).equals(pnd.getPhoneNumber())) {
                        output.writeUTF("OCUPAT");
                        break;
                    }
                }
                if(!rs.isFirst()) {
                    output.writeUTF("DISPONIBIL");
                }
            }
            catch (IOException | SQLException i) {
                System.out.println(i);
                break;
            }
        }
        try{
            this.input.close();
            this.output.close();
        }
        catch (IOException i){
            System.out.println(i);
        }
    }
}
