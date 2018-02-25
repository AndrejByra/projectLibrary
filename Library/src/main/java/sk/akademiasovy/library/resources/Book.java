package sk.akademiasovy.library.resources;

import sk.akademiasovy.library.Genre;
import sk.akademiasovy.library.db.MySQL;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/book")
public class Book {
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBook(){
        MySQL mySQL=new MySQL();
        List<String> list= mySQL.getBook();
        System.out.println(list);
        boolean b= false;
        String result= "func({\"name\":[";
        for(String temp:list){
            if(b == true){
                result+=',';
            } else
                b = true;
            result+="\""+temp+"\"";
        }

        result+="]})";
        return result;
    }

    @GET
    @Path("/author")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAuthor(){
        MySQL mySQL=new MySQL();
        List<String> list= mySQL.getAuthor();
        System.out.println(list);
        boolean b= false;
        String result= "func({\"Author Name \":[";
        for(String temp:list){
            if(b == true){
                result+=',';
            } else
                b = true;
            result+="\""+temp+"\"";
        }

        result+="]})";
        return result;
    }
    @POST
    @Path("/genres")
    @Produces(MediaType.APPLICATION_JSON)
    public String getGenres(Genre genre){
        System.out.println(genre);
        List<String>list= new MySQL().getGenre(genre);
        System.out.println(list);
        boolean b= false;
        String result= "Genres({\"genre\":[";
        for(String temp:list){
            if(b==true){
                result+=',';
            }
            else
                b=true;
            result+="\""+temp+"\"";
        }
        result+="]})";

        return result;
    }
}