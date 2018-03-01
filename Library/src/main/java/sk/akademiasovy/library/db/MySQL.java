package sk.akademiasovy.library.db;

import sk.akademiasovy.library.Author;
import sk.akademiasovy.library.BookName;
import sk.akademiasovy.library.Genre;
import sk.akademiasovy.library.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQL {
    private Connection conn;
    private String driver = "com.mysql.jdbc.Driver";
    private String url="jdbc:mysql://localhost:3306/library?autoReconnect=true&useSSL=false";
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
    public List<String> getAllAuthor(){
        List<String> list = new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,username,password);
            String query = "SELECT author from books";
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

    public List<String> getGenres(String genre) throws SQLException {
        List<String> list = new ArrayList<String>();
        try {
            Class.forName(driver).newInstance();

            conn = DriverManager.getConnection(url, username, password);
            String query = "select name from books inner join books_details on books.id = books_details.id where genre LIKE '" +genre+ "'";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String g = rs.getString("name");

                list.add(g);


                System.out.println(g);
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }


        return list;

    }
    public List<String> getAuthorBook(String name) throws SQLException {
        List<String> list = new ArrayList<String>();
        try {
            Class.forName(driver).newInstance();

            conn = DriverManager.getConnection(url, username, password);
            String query = "select name from books where author LIKE '%" +name+ "%'";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String g = rs.getString("name");

                list.add(g);


                System.out.println(g);
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }


        return list;

    }

    public String getName(String bookName) {
        String result = "";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, username, password);
            String query = "select author from books where name like '" +bookName+ "'";
            PreparedStatement p = conn.prepareStatement(query);
            ResultSet rs = p.executeQuery();
            while(rs.next())
                result = rs.getString("author");
        } catch (Exception e) {
            System.out.println("Error: " +e.getMessage());
        }
        return result;
    }

    public List<String> getInfoAboutBook(String bookname){
        List<String> list = new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, username, password);
            String query = "select author from books where name like ?";
            PreparedStatement p = conn.prepareStatement(query);
            p.setString(1,bookname);
            ResultSet rs = p.executeQuery();
            rs.next();
            String author=rs.getString("author");
            System.out.println("1");
            list.add(author);
            System.out.println("2");
            query = "select name from books where name like ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,bookname);
            ResultSet rs2 = ps.executeQuery();
            rs.next();
            System.out.println("3");
            String name=rs2.getString("name");
            list.add(name);
            query = "SELECT isbn FROM  books_details INNER JOIN books ON books_details.books = books.name LIKE ?";
            PreparedStatement ps4 = conn.prepareStatement(query);
            ps.setString(1,bookname);
            ResultSet rs4 = ps4.executeQuery();
            System.out.println("5");
            rs.next();
            String genre=rs4.getString("genre");
            list.add(genre);
            query = "SELECT image FROM  books_details INNER JOIN books ON books_details.books = books.name LIKE '" + bookname+"'";
            PreparedStatement ps5 = conn.prepareStatement(query);
            ResultSet rs5 = ps5.executeQuery();
            System.out.println("6");
            rs.next();
            String image=rs5.getString("image");
            list.add(image);
            query = "SELECT info FROM  books_details INNER JOIN books ON books_details.books = books.name LIKE '" + bookname+"'";
            PreparedStatement ps6 = conn.prepareStatement(query);
            ResultSet rs6 = ps6.executeQuery();
            System.out.println("7");
            rs.next();
            String info=rs6.getString("info");
            list.add(info);
            System.out.println("8");
        } catch (Exception e) {
            System.out.println("Error: " +e.getMessage());
        }

        return list;
    }

    public List<String> getBorroweBook(){
        List<String> list = new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,username,password);
            String query = "Select name from books INNER JOIN record ON books.id = record.idb";
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
    public List<String> getFreeBook(){
        List<String> list = new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,username,password);
            String query = "Select name from books INNER JOIN record ON books.id != record.idb";
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
    public List<String> getAllusers(){
        List<String> list = new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,username,password);
            String query = "Select name from books INNER JOIN record ON books.id != record.idb";
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
    public User getUser(String username, String password){
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, this.username, this.password);

            String query = "SELECT * from users where username like ? and password like ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                User user=new User(rs.getString("name"),rs.getString("surename"),rs.getString("username"),rs.getString("email"),
                        rs.getString("phone"),rs.getString("adress"));
                query = "UPDATE tokens SET token=? WHERE idu=?";
                ps = conn.prepareStatement(query);
                ps.setString(1, user.getToken());
                ps.setInt(2,rs.getInt("id"));

                ps.executeUpdate();
                System.out.println(ps);
                return user;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }



}