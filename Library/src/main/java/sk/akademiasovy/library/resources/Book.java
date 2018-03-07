package sk.akademiasovy.library.resources;

import sk.akademiasovy.library.Author;
import sk.akademiasovy.library.BookName;
import sk.akademiasovy.library.Genre;
import sk.akademiasovy.library.db.MySQL;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/book")
public class Book {
    @GET
    @Path("/allbook")       //show all books
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(){
        MySQL mySQL=new MySQL();
        List<String> list= mySQL.getBook();
        System.out.println(list);
        boolean b= false;
        String result= "getBooks({\"name\":[";
        for(String temp:list){
            if(b == true){
                result+=',';
            } else
                b = true;
            result+="\""+temp+"\"";
        }

        result+="]})";
        return Response.status(200).build();
    }
    @GET
    @Path("/allauthor")       //show all books
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAuthor(){
        MySQL mySQL=new MySQL();
        List<String> list= mySQL.getAllAuthor();
        System.out.println(list);
        boolean b= false;
        String result= "getAllAuthor({\"name\":[";
        for(String temp:list){
            if(b == true){
                result+=',';
            } else
                b = true;
            result+="\""+temp+"\"";
        }

        result+="]})";
        return Response.status(200).build();
    }

    @GET
    @Path("/genre/{genres}")        // input genre output bookname
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGenre(@PathParam("genres")String genre) throws SQLException
    {
        System.out.println(genre);
        List <String> list = new MySQL().getGenres(genre);


        boolean b = false;

        String result = "getGenres({\"name\":[";
        for (String temp : list) {
            if (b == true) {
                result += ',';
            } else
                b = true;
            result += "\"" + temp + "\"";

        }
        result += "]})";
        return Response.status(200).build();
    }

    @GET
    @Path("/bookauthor/{name}")        // input author output bookname (correnct input is is ful name -
    @Produces(MediaType.APPLICATION_JSON)       //or only part of them
    public Response getAuthorBook(@PathParam("name")String name) throws SQLException
    {
        System.out.println(name);
        List <String> list = new MySQL().getAuthorBook(name);
        boolean b = false;
        String result = "getGenres({\"name\":[";
        for (String temp : list) {
            if (b == true) {
                result += ',';
            } else
                b = true;
            result += "\"" + temp + "\"";

        }
        result += "]})";
        return Response.status(200).build();
    }
    @GET
    @Path("/author/{name}")     //input bookname output author
    @Produces(MediaType.APPLICATION_JSON)
    public Response getName(@PathParam("name") String genre) {

        String name = new MySQL().getName(genre);
        String result = "{\"name\":\""+name+"\"}";
        return Response.status(200).build();
    }

    @GET
    @Path("/info/{book}")     //input bookname output all info NOT WORKING CORRECTLY
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfoAboutBook(@PathParam("book") String bookname) {

        List<String>list= new MySQL().getInfoAboutBook(bookname);
        System.out.println("list"+list);
        boolean b= false;
        String result= "getInfo({\"all\":[";
        for(String temp:list){
            if(b==true){
                result+=',';
            }else
                b=true;
            result+="\""+temp+"\"";
        }
        result+="]})";

        return Response.status(200).build();
    }

    @GET
    @Path("/userinfo/{user}")     //input username output all
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInfo(@PathParam("user") String user) {

        List<String>list= new MySQL().getUserInfo(user);
        System.out.println("list"+list);
        boolean b= false;
        String result= "getInfo({\"all\":[";
        for(String temp:list){
            if(b==true){
                result+=',';
            }else
                b=true;
            result+="\""+temp+"\"";
        }
        result+="]})";

        return Response.status(200).build();
    }
    @GET
    @Path("/borrowedby/{user}")     //input username output all borrowed book
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBorrowedby(@PathParam("user") String user) {

        List<String>list= new MySQL().getBorrowedby(user);
        System.out.println("list"+list);
        boolean b= false;
        String result= "getInfo({\"all\":[";
        for(String temp:list){
            if(b==true){
                result+=',';
            }else
                b=true;
            result +="\""+temp+"\"";
        }
        result+="]})";

        return Response.ok(result).build();
    }
    @GET
    @Path("/borrowed")       //show all borrowed books
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBorrowedBook(){
        MySQL mySQL=new MySQL();
        List<String> list= mySQL.getBorroweBook();
        System.out.println(list);
        boolean b= false;
        String result= "getBorroweBook({\"name\":[";
        for(String temp:list){
            if(b == true){
                result+=',';
            } else
                b = true;
            result+="\""+temp+"\"";
        }

        result+="]})";
        return Response.status(200).build();
    }

    @GET
    @Path("/free")       //show all free books to borrow
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFreeBook(){
        MySQL mySQL=new MySQL();
        List<String> list= mySQL.getFreeBook();
        System.out.println(list);
        boolean b= false;
        String result= "getFreeBook({\"name\":[";
        for(String temp:list){
            if(b == true){
                result+=',';
            } else
                b = true;
            result+="\""+temp+"\"";
        }

        result+="]})";
        return Response.status(200).build();
    }

    @GET
    @Path("/time/{bookname}")     //input username output all
    @Produces(MediaType.APPLICATION_JSON)
    public String getTime(@PathParam("bookname") String bookname){

        List<String>list= new MySQL().getTime(bookname);
        System.out.println("list"+list);
        boolean b= false;
        String result= "Date({\"date\":[";
        for(String temp:list){
            if(b==true){
                result+=',';
            }else
                b=true;
            result+="\""+temp+"\"";
        }
        result+="]})";

        return result;
    }


}