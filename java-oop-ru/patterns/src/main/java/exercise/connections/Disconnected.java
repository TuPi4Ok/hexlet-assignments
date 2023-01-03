package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {
    private TcpConnection tcpConnection;

    public Disconnected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void connect() {
        this.tcpConnection.setConnection(new Connected(this.tcpConnection));
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Connection already disconnected");
    }

    @Override
    public void write(String buffer) {
        System.out.println("Error! Connection not exist");
    }

    @Override
    public String getCurrentState() {
        return "disconnected";
    }
}
// END
