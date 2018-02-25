package sk.akademiasovy.library.db;

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
            String query = "SELECT detailsOfBook from books_details WHERE Genre LIKE ?";;


            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,genre.getGenre());
            System.out.println(genre);
            System.out.println(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                String name=rs.getString("detailsOfBook");
                list.add(name);
            }

        }catch(Exception ex){
            System.out.println("Error: "+ ex.getMessage());
        }
        return list;
    }
    public List<String> getAllInfoAboutBook(BookName bookName){
        List<String> list = new ArrayList<>();
        class Info{
            String Author = "";
            String Namebook = "";

        }
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,username,password);
            String query = "SELECT author from books WHERE name LIKE ?";;
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,bookName.getBookname());
            System.out.println(bookName);
            System.out.println(query);
            ResultSet rs=ps.executeQuery();
            rs.next();
                String name=rs.getString("author");
            String query2 = "SELECT name from books WHERE name LIKE ?";;
            PreparedStatement ps2 = conn.prepareStatement(query);
            ps.setString(1,bookName.getBookname());
            System.out.println(bookName);
            System.out.println(query2);
            rs = ps2.executeQuery();
            rs.next();
            String name2=rs.getString("name");
            list.add(name2);


        }catch(Exception ex){
            System.out.println("Error: "+ ex.getMessage());
        }
        return list;
    }


}