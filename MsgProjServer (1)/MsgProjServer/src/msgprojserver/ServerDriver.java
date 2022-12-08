/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package msgprojserver;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;
import java.net.*;

import java.util.ArrayList;
import java.util.HashMap;




public class ServerDriver {
    
    public static void main(String[] args) throws UnknownHostException {
        UserList UL = new UserList();
        HashMap<String, ArrayList<String>> AllMsgs = new HashMap<>();
        InetAddress clientIP = InetAddress.getByName(null);
        int x = 1;
        try {
	    ServerSocket listen = new ServerSocket(2008);
            System.out.println( "Listening on port:  " + listen.getLocalPort() );

	    while (x==1){

		Socket client = listen.accept();
		PrintWriter out =
		    new PrintWriter(client.getOutputStream(), true);
                
                Scanner in = new Scanner(client.getInputStream());
                String com = in.nextLine();
                if(com.startsWith("REG")){
                    String U = in.nextLine();
                    String P = in.nextLine();
                    if(UL.userExists(U)==false){
                        UL.register(U, P);
                        out.println("LOGS");
                    }else{
                        out.println("EXISTS");
                    }
                }else if(com.startsWith("TREG")){
                    String U = in.nextLine();
                    String P = in.nextLine();
                    UL.register(U, P);
                    U = in.nextLine();
                    P = in.nextLine();
                    UL.register(U, P);
                    U = in.nextLine();
                    P = in.nextLine();
                    UL.register(U, P);
                    U = in.nextLine();
                    P = in.nextLine();
                    UL.register(U, P);
                }else if(com.startsWith("LOGIN")){
                    String U = in.nextLine();
                    String P = in.nextLine();
                    if(UL.userExists(U)){
                        if(UL.checkPassword(U, P)){
                            UL.login(U);
                            out.println("LOGGED");
                            UL.get(U).ip = listen.getInetAddress().toString();
                            out.println(U);
                        }else{
                            out.println("BADPASS");
                        }
                    }else{
                        out.println("DNE");
                    }
                    
                }else if(com.startsWith("FOLLOWCHECK")){
                    String U = in.nextLine();
                    System.out.println("follow check success");
                    if(UL.get(U).following.isEmpty()){
                        out.println("NOFOLLOW");
                        System.out.println("No follow success");
                    }
                    else{
                        System.out.println(UL.get(U).following.size());
                        out.println(UL.get(U).following.size());
                        for(int i=0; i < UL.get(U).following.size(); i++){
                            //String h = Integer.toString(i);
                            out.println(UL.get(U).following.get(i));
                        }
                    }
                }else if(com.startsWith("LOGOUT")){
                    String U = in.nextLine();
                    UL.logout(U);
                    out.println("EXIT");
                }else if(com.startsWith("FOLLOW")){
                    String U = in.nextLine();
                    String F = in.nextLine();
                    
                    if(UL.userExists(U)){
                        if(UL.userExists(F)){
                            if(UL.isLogged(U)){
                                if(!UL.isFollowing(U, F)){
                                    UL.follow(U, F);
                                    //out.println("FOLLOWED");
                                }
                            }
                        }else{
                            out.println("DNE");
                        }
                    }else{
                        out.println("EXIT");
                    }
                
                }else if(com.startsWith("SEARCHUSER")){
                    String U = in.nextLine();
                    String F = in.nextLine();
                    
                    if(UL.userExists(U)){
                        if(UL.userExists(F)){
                            out.println(UL.get(F).getName());
                            /*if(!UL.isFollowing(U, F)){
                                UL.follow(U, F);
                            }*/
                        }else{
                            out.println("DNE");
                        }
                    }else{
                        out.println("EXIT");
                    }
                }else if(com.startsWith("UNFOLLOW")){
                    String U = in.nextLine();
                    String F = in.nextLine();
                    
                    if(UL.userExists(U)){
                        if(UL.userExists(F)){
                            if(UL.isFollowing(U, F)){
                                UL.unfollow(U, F);
                                //out.println("UNFOLLOWED");
                            }
                        }else{
                            out.println("DNE");
                        }
                    }else{
                        out.println("EXIT");
                    }
                }else if(com.startsWith("MSGCHECK")){
                    String U =in.nextLine();
                     if(!UL.get(U).messages.isEmpty()){
                        out.println(UL.get(U).messages.size());
                        for (String message : UL.get(U).messages) {
                            out.println(message);
                        }
                     }else{
                         out.println("NOMSG");
                     }
                }else if(com.startsWith("SENDMSG")){
                    String hash = in.nextLine();
                    String msg = in.nextLine();
                    String U = in.nextLine();
                    AllMsgs.put(hash,new ArrayList<String>());
                    if(!UL.get(U).followers.isEmpty()){
                        UL.addMsg(U, msg);
                    }else{
                        System.out.println("No Valid Followers");
                    }
                }else if(com.startsWith("KILL")){
                    x=4;
                }else if(com.startsWith("SEARCHMSG")){
                    String M=in.nextLine();
                    if(AllMsgs.containsKey(M)){
                        out.print(AllMsgs.get(M));
                    }else{
                        out.println("DNE");
                    }
                }else if(com.startsWith("ISONLINE")){
                    String U = in.nextLine();
                    if(UL.isLogged(U)){
                        out.println("ONLINE");
                    }else{
                        out.println("OFFLINE");
                    }
                }else if(com.startsWith("GETIP")){
                    String U = in.nextLine();
                    String F = in.nextLine();
                    
                    if(UL.userExists(U)){
                        if(UL.userExists(F)){
                            if(UL.isLogged(U)){
                                if(UL.isLogged(F)){
                                    UL.getIp(F);
                                }
                            }
                        }
                    }else{
                        out.println("DNE");
                    }
                    
                }
                
		out.close();
		client.close();
	    }

	} catch(IOException e) {
	    System.err.println(e.getMessage());
	}

    }
    
}
