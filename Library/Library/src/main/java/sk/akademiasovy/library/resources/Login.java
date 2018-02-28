package sk.akademiasovy.library.resources;

import sk.akademiasovy.library.Credentials;
import sk.akademiasovy.library.User;
import sk.akademiasovy.library.db.MySQL;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/book")
public class Login {

        @POST
        @Path("/login")
        @Produces(MediaType.APPLICATION_JSON)
        public String checkCredentials(Credentials credential){
            System.out.println(credential.getUsername());
            MySQL mySQL = new MySQL();
            User user=mySQL.getUser(credential.username, credential.password);
            if(user==null){
                return "{}";
            }
            else{
                return "{\"token\":\""+user.getToken()+"\"}";
            }


    }
    }
