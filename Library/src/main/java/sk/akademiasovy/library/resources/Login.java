package sk.akademiasovy.library.resources;

import sk.akademiasovy.library.Credentials;
import sk.akademiasovy.library.User;
import sk.akademiasovy.library.db.MySQL;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/login")
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
    @GET
    @Path("/logout/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public String logout(@PathParam("token")  String token){
        MySQL mySQL = new MySQL();
        mySQL.logout( token);
        return "{}";
    }
    }
