package cs250.hw3;

import java.net.*;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class TCPServer {
    // java cs250.hw3.SubmissionTwoServer <port-number> <seed> <number-Of-Messages>
    private static int port;
    private static DataOutputStream outToClientOne;
    private static DataInputStream inFromClientOne;
    private static DataOutputStream outToClientTwo;
    private static DataInputStream inFromClientTwo;
    private static Scanner scnr = new Scanner(System.in);
    private static Socket clientSocketOne;
    private static Socket clientSocketTwo;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Error: java cs250.hw3.TCPServer <port-number> <seed> <number-Of-Messages>");
            System.exit(-1);
        }
        port = Integer.parseInt(args[0]);
        int seed = Integer.parseInt(args[1]);
        int numMessages = Integer.parseInt(args[2]);
        TCPServerStart(seed, numMessages); 
    }
    
    public static int receiveNumOne(){
        try {
            int response = inFromClientOne.readInt();
            return response;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return -1;
    }

    public static int receiveNumTwo(){
        try {
            int response = inFromClientTwo.readInt();
            return response;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return -1;
    }

    public static void sendNumOne(int num){
        try {
            outToClientOne.writeInt(num);
            outToClientOne.flush();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void sendNumTwo(int num){
        try {
            outToClientTwo.writeInt(num);
            outToClientTwo.flush();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void handshakeOne(int numMessages, int seed){
        try {
            outToClientOne.writeInt(numMessages);
            outToClientOne.writeInt(seed);
            outToClientOne.flush();
            System.out.println(clientSocketOne.getInetAddress().getHostName() + " " + seed);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void handshakeTwo(int numMessages, int seed){
        try {
            outToClientTwo.writeInt(numMessages);
            outToClientTwo.writeInt(seed);
            outToClientTwo.flush();
            System.out.println(clientSocketTwo.getInetAddress().getHostName() + " " + seed);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void finalClean(){
        try {
            scnr.close();
            clientSocketOne.close();
            outToClientOne.close();
            inFromClientOne.close();
            clientSocketTwo.close();
            outToClientTwo.close();
            inFromClientTwo.close();
            System.exit(0);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void TCPServerStart(int seed, int numMessages){
        try {
            if (!(port > 1024 && port <= 65535)){
                throw new Exception("Port number must be > 1024 <=65,535");
            }
            System.out.println("IP Address: " + InetAddress.getLocalHost() + "\nPort Number " + port);  
            serverSocket = new ServerSocket(port);
            Random rand = new Random(seed);

            System.out.println("waiting for client...");
            clientSocketOne = serverSocket.accept();
            outToClientOne = new DataOutputStream(clientSocketOne.getOutputStream());
            inFromClientOne = new DataInputStream(clientSocketOne.getInputStream());

            clientSocketTwo = serverSocket.accept();
            outToClientTwo = new DataOutputStream(clientSocketTwo.getOutputStream());
            inFromClientTwo = new DataInputStream(clientSocketTwo.getInputStream());
            System.out.println("Clients Connected!");

            System.out.println("Sending config to clients...");
            int randomIntOne = rand.nextInt();
            handshakeOne(numMessages, randomIntOne);
            int randomIntTwo = rand.nextInt();
            handshakeTwo(numMessages, randomIntTwo);
            System.out.println("Finished sending config to clients.");

            System.out.println("Starting to listen for client messages...");
            int countOne = 0;
            long serverSumOne = 0;
            while (countOne < numMessages) {
                int receivedNum = receiveNumOne();
                serverSumOne += receivedNum;
                ++countOne;
                sendNumTwo(receivedNum);
            }
            System.out.println(clientSocketOne.getInetAddress().getHostName());
            System.out.println("     Messages received: " + countOne);
            System.out.println("     Sum received: " + serverSumOne);

            int countTwo = 0;
            long serverSumTwo = 0;
            while (countTwo < numMessages) {
                int receivedNum = receiveNumTwo();
                serverSumTwo += receivedNum;
                ++countTwo;
                sendNumOne(receivedNum);
            }
            System.out.println(clientSocketTwo.getInetAddress().getHostName());
            System.out.println("     Messages received: " + countTwo);
            System.out.println("     Sum received: " + serverSumTwo);

            finalClean();

        } catch (Exception e) {
            System.out.println("Address already in use (Bind failed)");
            System.err.println(e.getMessage());
        } 
    }
}  