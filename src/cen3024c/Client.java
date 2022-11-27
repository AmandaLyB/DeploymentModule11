package cen3024c;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        DataOutputStream outputToServer = null;
        DataInputStream inputFromServer = null;
        Scanner scan = new Scanner(System.in);
        int i = 1;
        while(i > 0) {
            System.out.println("Enter negative number to Quit. \nEnter an integer: ");
            socket = new Socket(host.getHostName(), 1236);
            outputToServer = new DataOutputStream(socket.getOutputStream());
            int userNum = scan.nextInt();
            if (userNum < 0) {
                break;
            }
            outputToServer.writeInt(userNum);
            inputFromServer = new DataInputStream(socket.getInputStream());
            int num = inputFromServer.readInt();
            if (num == 0) {
                System.out.println("Server says your number is not prime");
            }
            else {
                System.out.println("Server says your number is prime");
            }
            outputToServer.close();
            inputFromServer.close();
            Thread.sleep(100);
        }
    }
}
