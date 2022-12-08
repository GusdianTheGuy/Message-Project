
package msgprojserver;
import java.util.ArrayList;

public class User {
    private String un;
    protected String pw;
    protected ArrayList<String> followers;
    protected ArrayList<String> following;
    protected ArrayList<String> messages;
    protected String ip;
    boolean loggedIn;
    public User(String UN,String PW){
        followers= new ArrayList<>();
        following= new ArrayList<>();
        messages= new ArrayList<>();
        un = UN;
        pw = PW;
        ip = "";
        loggedIn = false;
    }
  /*private void getInfo(){
        System.out.println(un);
        System.out.println(pw);
    }*/
    protected boolean passwordCheck(String PW){
        return PW.equals(pw);
    }
    protected void logIn(){
        loggedIn = true;
    }
    protected void logOut(){
        loggedIn = false;
    }
    protected void follow(String F){
        following.add(F);
    }
    protected void addUser(String U){
        followers.add(U);
    }
    protected void unfollow(String F){
        following.remove(F);
    }
    protected void addMsg(String M){
        messages.add(M);
    }
    protected void removerUser(String U){
        followers.remove(U);
    }
    protected String getName(){
        return un;
    }
    protected boolean isLogged(){
        return loggedIn;
    }
    protected String getIp(){
        return ip;
    }
}
