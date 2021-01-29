package WebSocketServer;

import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ServerEndpoint("/ws/{id}")
public class ChatServerClass {

    private HttpSession session;

    private static Map<String, String> sessions = new HashMap<String, String>();

    @OnOpen
    public void open(Session session, @PathParam("id") String userId) throws IOException {
        String sessionId = session.getId();
        sessions.put(sessionId, userId);
    }

    @OnClose
    public void close(Session session){
        sessions.remove(session.getId());
    }

    public static List<String> getActiveUsers(){
        List<String> activeUsers = new ArrayList<>();
        sessions.forEach((k,v)->{
            activeUsers.add(v);
        });

        return activeUsers;
    }
}
