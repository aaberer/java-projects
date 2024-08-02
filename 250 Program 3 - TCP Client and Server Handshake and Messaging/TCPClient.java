package cs250.hw3;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class TCPClient {
    // java cs250.hw3.TCPClient <server-host> <server-port>
    private static int numMessages;
    private static int seed;
    private static DataOutputStream outToServer;
    private static DataInputStream inFromServer;
    private static Scanner scnr = new Scanner(System.in);
    private static Socket clientSocket;
    private static long senderSum;
    private static int numOfSentMessages;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Error: java cs250.hw3.TCPClient <server-host> <server-port>");
            System.exit(-1);
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        TCPClientStart(host, port);
    }

    public static int receiveNum(){
        try {
            int response = inFromServer.readInt();
            return response;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return -1;
    }

    public static void sendNum(int num){
        try {
            outToServer.writeInt(num);
            outToServer.flush();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void receiveHandshake(){
        try {
            numMessages = inFromServer.readInt();
            seed = inFromServer.readInt();
            System.out.println("Received config");
            System.out.println("number of messages = " + numMessages);
            System.out.println("seed = " + seed);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void finalClean(){
        try {
            scnr.close();
            clientSocket.close();
            outToServer.close();
            inFromServer.close();
            System.exit(0);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void TCPClientStart(String host, int port){
        scnr = new Scanner(System.in);
        try {
            clientSocket = new Socket(host, port);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new DataInputStream(clientSocket.getInputStream());
            receiveHandshake();
            Thread.sleep(10000);
            Random rand = new Random(seed);
            System.out.println("Starting to send messages to server..");
            senderSum = 0;
            numOfSentMessages = 0;
            int randomInt = rand.nextInt();
            for (int i = 0; i < numMessages; ++i){
                sendNum(randomInt);
                senderSum += randomInt;
                ++numOfSentMessages;
                randomInt = rand.nextInt();
            }
            System.out.println("Finished sending messages to server.");
            System.out.println("Total messages sent: " + numOfSentMessages);
            System.out.println("Sum of messages sent: " + senderSum);


            int numOfReceivedMessages = 0;
            long receiverSum = 0;
            System.out.println("Starting to listen for messages from server...");
            for (int i = 0; i < numMessages; ++i){
                int receivedNum = receiveNum();
                receiverSum += receivedNum;
                ++numOfReceivedMessages;
            }
            System.out.println("Finished listening for messages from server.");

            System.out.println("Total messages received: " + numOfReceivedMessages);
            System.out.println("Sum of messages received: " + receiverSum);

            finalClean();
        } catch(Exception e) {
            System.out.println("Error: Client Not Found");
        } finally {
            finalClean();
        }
    }
}