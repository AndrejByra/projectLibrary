package sk.akademiasovy.library.resources;

import sk.akademiasovy.library.Author;
import sk.akademiasovy.library.BookName;
import sk.akademiasovy.library.Genre;
import sk.akademiasovy.library.db.MySQL;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/book")
public class Book {
    @GET
    @Path("/allbook")       //show all books
    @Produces(MediaType.APPLICATION_JSON)
    public String getBook(){
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
        return result;
    }
    @GET
    @Path("/allauthor")       //show all books
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllAuthor(){
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
        return result;
    }

    @GET
    @Path("/genre/{genres}")        // input genre output bookname
    @Produces(MediaType.APPLICATION_JSON)
    public String getGenre(@PathParam("genres")String genre) throws SQLException
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
        return result;
    }

    @GET
    @Path("/bookauthor/{name}")        // input author output bookname (correnct input is is ful name -
    @Produces(MediaType.APPLICATION_JSON)       //or only part of them
    public String getAuthorBook(@PathParam("name")String name) throws SQLException
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
        return result;
    }
    @GET
    @Path("/author/{name}")     //input bookname output author
    @Produces(MediaType.APPLICATION_JSON)
    public String getName(@PathParam("name") String genre) {

        String name = new MySQL().getName(genre);
        String result = "{\"name\":\""+name+"\"}";
        return result;
    }

    @GET
    @Path("/info/{book}")     //input bookname output all info NOT WORKING CORRECTLY
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoAboutBook(@PathParam("book") String bookname) {

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

        return result;
    }

    @GET
    @Path("/userinfo/{user}")     //input username output all
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserInfo(@PathParam("user") String user) {

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

        return result;
    }

    @GET
    @Path("/borrowed")       //show all borrowed books
    @Produces(MediaType.APPLICATION_JSON)
    public String getBorrowedBook(){
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
        return result;
    }
    @GET
    @Path("/free")       //show all free books to borrow
    @Produces(MediaType.APPLICATION_JSON)
    public String getFreeBook(){
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
        return result;
    }


}