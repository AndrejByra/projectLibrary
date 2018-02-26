package sk.akademiasovy.library.db;

import sk.akademiasovy.library.Author;
import sk.akademiasovy.library.BookName;
import sk.akademiasovy.library.Genre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySQL {
    private Connection conn;
    private String driver = "com.mysql.jdbc.Driver";
    private String url="jdbc:mysql://localhost:3306/library";
    private String username="root";
    private String password="";

    public List<String> getBook(){
        List<String> list = new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,username,password);
            String query = "SELECT NAME from books";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                String name=rs.getString("name");
                list.add(name);
            }
        }catch(Exception ex){
            System.out.println("Error: "+ ex.getMessage());
        }
        return list;
    }
    public List<String> getAuthor(){
        List<String> list = new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,username,password);
            String query = "SELECT Author from books";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                String name=rs.getString("author");
                list.add(name);
            }
        }catch(Exception ex){
            System.out.println("Error: "+ ex.getMessage());
        }
        return list;
    }
    public List<String> getGenre(Genre genre){
        List<String> list = new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,username,password);
            String query = "select name from books inner join books_details on books.id = books_details.id where genre LIKE ?";;

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,genre.getGenre());
            System.out.println(genre);
            System.out.println(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                String name=rs.getString("name");
                list.add(name);
            }

        }catch(Exception ex){
            System.out.println("Error: "+ ex.getMessage());
        }
        return list;
    }
    public List<String> getAuthorname(Author author){
        List<String> list = new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,username,password);
            String query = "select name from books inner join books_details on books.id = books_details.id where author LIKE ?";;

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,author.getAuthorname());
            System.out.println(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                String name=rs.getString("name");
                list.add(name);
            }

        }catch(Exception ex){
            System.out.println("Error: "+ ex.getMessage());
        }
        return list;
    }
    public List<String> getAllInfoAboutBook(BookName bookName){
        List<String> list = new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,username,password);
            String query = "SELECT author from books WHERE name LIKE ?";;
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,bookName.getBookname());
            ResultSet rs=ps.executeQuery();
            rs.next();
            String name=rs.getString("author");
            list.add(name);
            query = "SELECT name from books WHERE name LIKE ?";;
            PreparedStatement ps2 = conn.prepareStatement(query);
            ps2.setString(1,bookName.getBookname());
            rs=ps2.executeQuery();
            rs.next();
            String name2=rs.getString("name");
            list.add(name2);
            System.out.println("1");
            query = "SELECT isbn FROM  books_details INNER JOIN books ON books_details.books = books.name LIKE ?";;
            PreparedStatement ps3 = conn.prepareStatement(query);
            ps3.setString(1,bookName.getBookname());
            rs=ps3.executeQuery();
            rs.next();
            String name3=rs.getString("isbn");
            list.add(name3);
            query = "SELECT genre FROM  books_details INNER JOIN books ON books_details.books = books.name LIKE ?";;
            PreparedStatement ps4 = conn.prepareStatement(query);
            ps4.setString(1,bookName.getBookname());
            rs=ps4.executeQuery();
            rs.next();
            String name4=rs.getString("genre");
            list.add(name4);
            query = "SELECT image FROM  books_details INNER JOIN books ON books_details.books = books.name LIKE ?";;
            PreparedStatement ps5 = conn.prepareStatement(query);
            ps5.setString(1,bookName.getBookname());
            rs=ps5.executeQuery();
            rs.next();
            String name5=rs.getString("image");
            list.add(name5);
            System.out.println(list);

        }catch(Exception ex){
            System.out.println("Error: "+ ex.getMessage());
        }
        return list;
    }


}