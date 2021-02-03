package studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageQueries;

import studentMessMaybeWork.studentPlatform.databaseEntities.UserClass;
import studentMessMaybeWork.studentPlatform.databaseEntities.userToReturn;
import studentMessMaybeWork.studentPlatform.databaseQueries.DatabaseConnectionHelper;
import studentMessMaybeWork.studentPlatform.databaseQueries.FriendRequest;
import studentMessMaybeWork.studentPlatform.databaseQueries.GetUserClass;
import studentMessMaybeWork.studentPlatform.databaseQueries.GetUserDetails;
import studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageEntities.lastConversation;
import studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageEntities.MessageEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetUserLastConversations {
    public static List<lastConversation> getLastConversations(String currentUserId, String currUserClass){
        List<lastConversation> lastConversationList = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        MessageEntity lastMsg = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT DISTINCT par.conversation_id, par.users_id, us.*,ms.created_at FROM participants as par \n" +
                    "INNER JOIN user as us ON us.user_id = par.users_id \n" +
                    "INNER JOIN messages as ms ON ms.conversation_id = par.conversation_id \n" +
                    "WHERE par.conversation_id IN (SELECT conversation_id FROM `participants` WHERE participants.users_id = (?)) AND par.users_id != (?)\n" +
                    "ORDER BY ms.created_at DESC");
//            preparedStatement = conn.prepareStatement("SELECT ms.sender_id FROM participants AS ptc INNER JOIN messages AS ms ON ms.conversation_id = ptc.conversation_id WHERE users_id = 3 ORDER BY ms.created_at DESC")
            preparedStatement.setString(1, currentUserId);
            preparedStatement.setString(2, currentUserId);

            ResultSet rs = preparedStatement.executeQuery();
            ResultSet rs2 = null;

            lastConversationList = new ArrayList<>();

            userToReturn user = null;

            List<String> isInList = new ArrayList<>();

            while(rs.next()){

                String userId = rs.getString("user_id");
                if(isInList.contains(userId)){
                    continue;
                } else{
                    isInList.add(userId);
                }

                String convId = rs.getString("conversation_id");
                String classId = rs.getString("class_id");
                UserClass userClass = GetUserClass.getUserClass(classId);
                String phoneNumber = rs.getString("phone");
                String email = rs.getString("email");
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                String birthDate = rs.getString("data_urodzenia");
                String nationality = rs.getString("obywatelstwo");
                String profilePhoto = rs.getString("profilePhoto");

                int is_friend = FriendRequest.isFriend(currentUserId, userId);
                int isSameClass = GetUserDetails.isSameClass(userId, currentUserId, currUserClass);

                preparedStatement = conn.prepareStatement("SELECT * FROM `messages` WHERE conversation_id = (?) ORDER BY created_at DESC LIMIT 1");
                preparedStatement.setString(1, convId);

                rs2 = preparedStatement.executeQuery();
                while (rs2.next()){
                    String msgId = rs2.getString("conversation_id");
                    String senderId = rs2.getString("sender_id");
                    String msgText = rs2.getString("message_text");
                    String attachment = rs2.getString("attachment");
                    String createdAt = rs2.getString("created_at");
                    lastMsg = new MessageEntity(convId, senderId, msgText, attachment, createdAt);
                }

                user = new userToReturn(userId,classId, userClass,phoneNumber,email,fName,lName, birthDate, nationality, profilePhoto, is_friend, isSameClass);

                lastConversationList.add(new lastConversation(convId, user, lastMsg));

                lastMsg = null;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }

        return lastConversationList;
    }

}