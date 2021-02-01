package studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageQueries;

import studentMessMaybeWork.studentPlatform.databaseQueries.DatabaseConnectionHelper;
import studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageEntities.MessageEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetLastMessagesInConversation {
    public static List<MessageEntity> getLastMessagesInConversation(String user1Id, String user2Id, int lastPosition){
        List<MessageEntity> lastMessages = null;
        if(isConversationWith.isConversation(user1Id, user2Id)){
            Connection conn = null;
            PreparedStatement preparedStatement = null;


            try{
                conn = DatabaseConnectionHelper.getConnection();
                preparedStatement = conn.prepareStatement("SELECT * FROM `participants` WHERE conversation_id IN(SELECT conversation_id FROM participants WHERE users_id = (?)) and users_id = (?)");
                preparedStatement.setString(1, user1Id);
                preparedStatement.setString(2, user2Id);

                ResultSet rs = preparedStatement.executeQuery();

                String conversation_id = null;

                while (rs.next()){
                    conversation_id = rs.getString("conversation_id");
                }

                preparedStatement = conn.prepareStatement("SELECT * FROM `messages` WHERE conversation_id = (?) ORDER BY `messages`.`created_at` DESC LIMIT "+(lastPosition-30)+", "+lastPosition+"");
                preparedStatement.setString(1, conversation_id);

                System.out.println(preparedStatement);

                ResultSet rs1 = preparedStatement.executeQuery();
                MessageEntity messageEntity = null;
                lastMessages = new ArrayList<>();

                while(rs1.next()){
                    String sender_id = rs1.getString("sender_id");
                    String messageText = rs1.getString("message_text");
                    String attachment = rs1.getString("attachment");
                    String created_at = rs1.getString("created_at");

                    messageEntity = new MessageEntity(conversation_id, sender_id, messageText, attachment, created_at);
                    lastMessages.add(messageEntity);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                DatabaseConnectionHelper.close(conn);
            }

        }
        return lastMessages;
    }
}