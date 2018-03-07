package sk.akademiasovy.library.resources;

import sk.akademiasovy.library.Credentials;
import sk.akademiasovy.library.Registration;
import sk.akademiasovy.library.User;
import sk.akademiasovy.library.db.MySQL;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class Login {

        @POST
        @Path("/login")
        @Produces(MediaType.APPLICATION_JSON)
        public Response checkCredentials(Credentials credential){
            System.out.println(credential.getUsername());
            MySQL mySQL = new MySQL();
            User user=mySQL.getUser(credential.username, credential.password);
            if(user==null){
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
            else{
                String result;
                result="{\"name\":\""+user.getName()+"\" , ";
                result+="\"surename\":\""+user.getSurename()+"\" , ";
                result+="\"username\":\""+user.getUsername()+"\" , ";
                result+="\"email\":\""+user.getEmail()+"\" , ";
                result+="\"phone\":\""+user.getPhone()+"\" , ";
                result+="\"address\":\""+user.getAddress()+"\" , ";
                result+="\"city\":\""+user.getCity()+"\" , ";
                result+="\"postcode\":\""+user.getPostcode()+"\" , ";
                result+="\"token\":\""+user.getToken()+"\"}";
                return  Response.ok(result,MediaType.APPLICATION_JSON_TYPE).build();
            }


    }
    @GET
    @Path("/logout/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@PathParam("token")  String token){
        MySQL mySQL = new MySQL();
        mySQL.logout( token);
        return Response.ok().build();
    }
    @POST
    @Path("/registration")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  createNewUser (Registration registration)
    {
        MySQL mySQL = new MySQL();
        boolean exist = mySQL.checkIfEmailOrUsernameExist(registration.username.trim(),registration.email.trim());
        if(exist)
        {
            Response.status(406).build();
        }
        else
        {
            System.out.println("Do Registration");
            mySQL.insertNewUserIntoDb(registration);
        }

        return Response.status(201).build();

    }
}
