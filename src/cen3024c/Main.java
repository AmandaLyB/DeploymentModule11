package cen3024c;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static ServerSocket server;
    private static int port = 1236;


//    public static void GUI() {
//        JFrame f = new JFrame();
//        JFrame.setDefaultLookAndFeelDecorated(true);
//        f.setTitle("Deployment Assignment");
//        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        server = new ServerSocket(port);

        while(true){
            System.out.println("Awaiting input:\n");
            Socket socket = server.accept();
            DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
            int num = inputFromClient.readInt();
            System.out.println("Client has input " + num);
            DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

            // flag 0 = prime
            // flag 1 = not prime
            int flag = 0;

            // 0 and 1
            if (num == 0 || num == 1){
                flag = 1;
            }
            for (int i = 2; i <= num / 2; ++i){
                if (num % i == 0) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                outputToClient.writeInt(0);
            }
            else {
                outputToClient.writeInt(1);
            }
            inputFromClient.close();
            outputToClient.close();
            socket.close();
            if(num < 0) break;
        }

//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                GUI();
//            }
//        });
    }
}
