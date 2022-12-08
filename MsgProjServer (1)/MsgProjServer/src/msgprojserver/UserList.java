
package msgprojserver;
import java.util.HashMap;

public class UserList {
    
    private HashMap<String, User> userList;
    public UserList(){
        userList = new HashMap<>();
        
    }
   protected void register(String U, String PW){
       User user = new User(U,PW);
       userList.put(U, user);
        }
   
   protected boolean userExists(String U){
       return userList.containsKey(U);
        }
   protected boolean checkPassword(String U,String PW){
      return userList.get(U).passwordCheck(PW);
   }
   protected void login(String U){
       userList.get(U).logIn();
   }
   protected void logout(String U){
       userList.get(U).logOut();
   }
   protected void follow(String U, String F){
       userList.get(U).follow(F);
       userList.get(F).addUser(U);
   }
   protected void unfollow(String U, String F){
       userList.get(U).unfollow(F);
       userList.get(F).removerUser(U);
   }
   protected boolean isFollowing(String U, String F){
       return userList.get(F).followers.contains(U);
   }
   protected void addMsg(String U, String M){
       userList.get(U);
       for(String follower : userList.get(U).followers){
           userList.get(follower).addMsg(M);
       }
   }
   protected User get(String U){
       return userList.get(U);
   }
   protected boolean isLogged(String U){
       return userList.get(U).isLogged();
   }
   protected String getIp(String U){
       return userList.get(U).getIp();
   }
}
 
