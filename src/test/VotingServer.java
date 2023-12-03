package test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VotingServer {
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            // Create a DatagramSocket to listen for UDP packets
            DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT);
            System.out.println("Server started on port " + SERVER_PORT);

            while (true) {
                // Create a buffer to receive the incoming UDP packet
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                // Receive the packet from the client
                serverSocket.receive(packet);
                

                // Create a new thread to handle the client request
                Thread clientThread = new Thread(() -> handleClientRequest(serverSocket, packet));
                clientThread.start();
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private static void handleClientRequest(DatagramSocket socket, DatagramPacket packet) {
        try {
            // Extract the client's IP address and port
            InetAddress clientAddress = packet.getAddress();
            int clientPort = packet.getPort();
            System.out.println("Client connected: \nClient ip header:\nClient ip address :" +clientAddress.getHostAddress()+"\nClient source port :" + clientPort);
            // Convert the received data to a string
            String receivedData = new String(packet.getData(), 0, packet.getLength());

            // Process the received data
            String[] dataParts = receivedData.split("\n");
            String username = dataParts[0];
            String candidateName = dataParts[1];

            // Store the vote in the database
            storeVoteInDatabase(username, candidateName);

            // Count the frequency of votes for each candidate
           
            // Create a StringBuilder to store the frequency results
            StringBuilder resultBuilder = new StringBuilder();
           

            // Send the frequency results back to the client as a UDP packet
            String frequencyResults = resultBuilder.toString();
            byte[] sendData = frequencyResults.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            socket.send(responsePacket);

            System.out.println("Vote stored in the database.\t"+username+"\t\t"+candidateName);

        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static ResultSet executeQuery(String query) throws SQLException {
        // Replace the connection details with your own
        String url = "jdbc:mysql://localhost:3306/login";
        String username = "root";
        String password = "1234";

        // Create a connection and statement, and execute the query
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        return resultSet;
    }

    private static void storeVoteInDatabase(String username, String candidateName) throws SQLException {
        // Replace the connection details with your own
        String url = "jdbc:mysql://localhost:3306/login";
        String dusername = "root";
        String password = "1234";

        // Create a connection and prepared statement, and insert the vote into the database
        Connection connection = DriverManager.getConnection(url, dusername, password);
        String insertQuery = "UPDATE user SET vote = ? WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(insertQuery);
        statement.setString(1, candidateName);
        statement.setString(2, username);
        statement.executeUpdate();

        // Close the statement and connection
        statement.close();
        connection.close();
    }

    
}
