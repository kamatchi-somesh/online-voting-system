package test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.zip.CRC32;

public class Server {
    private static final int SERVER_PORT = 12345;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(SERVER_PORT)) {
            System.out.println("Server started. Waiting for client connections...");

            while (true) {
                byte[] buffer = new byte[BUFFER_SIZE];
                DatagramPacket packet = new DatagramPacket(buffer, BUFFER_SIZE);

                // Receive packet from client
                socket.receive(packet);
                System.out.println("Received packet from client.");

                // Print client's IP header
                InetAddress clientAddress = packet.getAddress();

                System.out.println("Client IP Header:");
                System.out.println("IP Address: " + clientAddress.getHostAddress());
                System.out.println("Hostname: " + clientAddress.getHostName());

                // Process packet data
                String data = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received data: " + data);

                // Verify checksum
                boolean checksumValid = verifyChecksum(packet.getData());
                if (checksumValid) {
                    System.out.println("Checksum is valid. Packet received successfully.");

                    // Extract username, candidate name, and checksum from the packet data
                    String[] parts = data.split("\n");
                    if (parts.length >= 3) {
                        String username = parts[0];
                        String candidateName = parts[1];
                        int receivedChecksum = Integer.parseInt(parts[2]);

                        // Verify received checksum against calculated checksum
                        int calculatedChecksum = calculateChecksum(username, candidateName);
                        if (receivedChecksum == calculatedChecksum) {
                            // Checksum matches, proceed with storing candidate in the database
                            storeCandidateInDatabase(username, candidateName);
                            System.out.println("Candidate '" + candidateName + "' stored in database for username: " + username);

                            // TODO: Send response to client if needed
                        } else {
                            System.out.println("Received checksum does not match the calculated checksum.");
                            // TODO: Handle checksum mismatch
                        }
                    } else {
                        System.out.println("Invalid packet format. Expected username, candidate name, and checksum.");
                        // TODO: Handle invalid packet format
                    }
                } else {
                    System.out.println("Checksum is invalid. Packet may have been corrupted during transmission.");
                    // TODO: Handle checksum failure (e.g., request packet retransmission)
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
public static boolean verifyChecksum(byte[] ipPacket) {
    CRC32 crc32 = new CRC32();
    crc32.update(ipPacket);
    long receivedChecksum = crc32.getValue();

    // Compare the received checksum with the calculated checksum
    long calculatedChecksum = calculateChecksum(ipPacket);

    return receivedChecksum == calculatedChecksum;
}

public static long calculateChecksum(byte[] ipPacket) {
    CRC32 crc32 = new CRC32();
    crc32.update(ipPacket);
    return crc32.getValue();
}
  


    private static void storeCandidateInDatabase(String username, String candidateName) {
        String url = "jdbc:mysql://localhost:3306/login";
        String dbUsername = "root";
        String password = "1234";
        String query = "UPDATE user SET candidate = ? WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, candidateName);
            statement.setString(2, username);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Candidate name updated in the database.");
            } else {
                System.out.println("Failed to update candidate name in the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
