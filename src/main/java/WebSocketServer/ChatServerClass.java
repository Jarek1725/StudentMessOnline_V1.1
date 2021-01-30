package WebSocketServer;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.json.JSONObject;
import studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageEntities.shortMessageEntity;
import studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageQueries.isConversationWith;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ServerEndpoint("/ws/{id}")
public class ChatServerClass {

    private Gson gson = new Gson();
    private static String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";
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

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException, EncodeException {
        shortMessageEntity shortMessageEntity = gson.fromJson(message, shortMessageEntity.class);
        String userToKey = "";
        shortMessageEntity.setSenderId(String.valueOf(decodeJWT(shortMessageEntity.getSenderId()).get("userId")));
        if(isConversationWith.isConversation(shortMessageEntity.getSenderId(), shortMessageEntity.getToWho())){
            System.out.println("JEST");
            if(sessions.containsKey(session.getId())){
                for (Map.Entry<String , String> entry : sessions.entrySet()) {
                    if (decodeJWT(entry.getValue()).get("userId").equals(shortMessageEntity.getToWho())) {
                        userToKey = entry.getKey();
                        break;
                    }
                }
                for (Session peer : session.getOpenSessions()) {
                    if(peer.getId().equals(userToKey)){
                        peer.getBasicRemote().sendText(gson.toJson(shortMessageEntity));
                        break;
                    }
                }
            }
        } else{
            System.out.println("Nie ma");
        }
    }



    public static Claims decodeJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
    }
}
