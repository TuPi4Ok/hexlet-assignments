package exercise;
import exercise.connections.*;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection implements Connection {
    private String ip;
    private int port;
    private Connection connection;
    private String buffer;

    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.connection = new Disconnected(this);
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void connect() {
        this.connection.connect();
    }

    @Override
    public void disconnect() {
        this.connection.disconnect();
    }

    @Override
    public void write(String buffer) {
        this.connection.write(buffer);
    }

    @Override
    public String getCurrentState() {
        return connection.getCurrentState();
    }
}
// END
