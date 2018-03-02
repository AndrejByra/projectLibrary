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
            //informacie o knihe
    @GET
    @Path("/infoauthor/{book}")     //input bookname output author
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoAuthor(@PathParam("book") String bookname) {

        List<String>list= new MySQL().getInfoAuthor(bookname);
        System.out.println("list"+list);
        boolean b= false;
        String result= "({\"Author\":[";
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
    @Path("/infoabout/{book}")     //input bookname output about book
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoAbout(@PathParam("book") String bookname) {

        List<String>list= new MySQL().getInfoAbout(bookname);
        System.out.println("list"+list);
        boolean b= false;
        String result= "({\"About\":[";
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
    @Path("/infogenre/{book}")     //input bookname output genre
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoGenre(@PathParam("book") String bookname) {

        List<String>list= new MySQL().getInfoGenre(bookname);
        System.out.println("list"+list);
        boolean b= false;
        String result= "({\"Genre\":[";
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
    @Path("/infophoto/{book}")     //input bookname output photo
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoPhoto(@PathParam("book") String bookname) {

        List<String>list= new MySQL().getInfoPhoto(bookname);
        System.out.println("list"+list);
        boolean b= false;
        String result= "({\"Genre\":[";
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
    @Path("/infoisbn/{book}")     //input bookname output isbn
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoISBN(@PathParam("book") String bookname) {

        List<String>list= new MySQL().getInfoISBN(bookname);
        System.out.println("list"+list);
        boolean b= false;
        String result= "({\"ISBN\":[";
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
    @GET
    @Path("/alluser")       //show all users NOT WORKING
    @Produces(MediaType.APPLICATION_JSON)
    public String getusers(){
        MySQL mySQL=new MySQL();
        List<String> list= mySQL.getAllusers();
        System.out.println(list);
        boolean b= false;
        String result= "getusers({\"name\":[";
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