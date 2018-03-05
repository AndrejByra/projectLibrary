package sk.akademiasovy.library.db;

import sk.akademiasovy.library.*;


import java.sql.*;
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
            String query = "select author from books INNER JOIN books_details ON where name like '" +bookName+ "'";
            PreparedStatement p = conn.prepareStatement(query);
            ResultSet rs = p.executeQuery();
            while(rs.next())
                result = rs.getString("author");
        } catch (Exception e) {
            System.out.println("Error: " +e.getMessage());
        }
        return result;
    }

    public List<String> getInfoAuthor(String bookname){
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
            System.out.println(author);
            list.add(author);

        } catch (Exception e) {
            System.out.println("Error: " +e.getMessage());
        }

        return list;
    }
    public List<String> getInfoAboutBook(String bookname){
        List<String> list = new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,username,password);

            String query = "select name from books where name like ?";
            PreparedStatement p = conn.prepareStatement(query);
            p.setString(1,bookname);
            ResultSet rs = p.executeQuery();
            rs.next();
            String image = rs.getString("name");
            list.add(image);


            query = "select author from books where name like ?";
            p = conn.prepareStatement(query);
            p.setString(1,bookname);
            rs = p.executeQuery();
            rs.next();
            String author = rs.getString("author");
            list.add(author);

            query = "SELECT isbn FROM  books_details INNER JOIN books ON books_details.books = books.name LIKE ?";
            p = conn.prepareStatement(query);
            p.setString(1,bookname);
            rs = p.executeQuery();
            rs.next();
            String isbn = rs.getString("isbn");
            list.add(isbn);

            query = "select genre from books_details inner join books on books.id = books_details.id where name LIKE ?";
            p = conn.prepareStatement(query);
            p.setString(1,bookname);
            rs = p.executeQuery();
            rs.next();
            String genre = rs.getString("genre");
            list.add(genre);

            query = "SELECT detailsOfBook FROM  books_details INNER JOIN books ON books_details.books = books.name LIKE ?";
            p = conn.prepareStatement(query);
            p.setString(1,bookname);
            rs = p.executeQuery();
            rs.next();
            String info = rs.getString("detailsOfBook");
            list.add(info);

            query = "select status from books where name like ?";
            p = conn.prepareStatement(query);
            p.setString(1,bookname);
            rs = p.executeQuery();
            rs.next();
            String status = rs.getString("status");
            list.add(status);

        } catch (Exception e) {
            System.out.println("Error: " +e.getMessage());
        }

        return list;
    }

    public List<String> getUserInfo(String user){
        List<String> list = new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,username,password);

            String query = "select name from users where username like ?";
            PreparedStatement p = conn.prepareStatement(query);
            p.setString(1,user);
            ResultSet rs = p.executeQuery();
            rs.next();
            String image = rs.getString("name");
            list.add(image);


            query = "select surename from users where username like ?";
            p = conn.prepareStatement(query);
            p.setString(1,user);
            rs = p.executeQuery();
            rs.next();
            String surename = rs.getString("surename");
            list.add(surename);

            query = "select username from users where username like ?";
            p = conn.prepareStatement(query);
            p.setString(1,user);
            rs = p.executeQuery();
            rs.next();
            String username = rs.getString("username");
            list.add(username);

            query = "select email from users_details INNER JOIN users ON users_details.IDuser = users.id where username like ?";
            p = conn.prepareStatement(query);
            p.setString(1,user);
            rs = p.executeQuery();
            rs.next();
            String email = rs.getString("email");
            list.add(email);

            query = "select phone from users_details INNER JOIN users ON users_details.IDuser = users.id where username like ?;";
            p = conn.prepareStatement(query);
            p.setString(1,user);
            rs = p.executeQuery();
            rs.next();
            String phone = rs.getString("phone");
            list.add(phone);

            query = "select adress from users_details INNER JOIN users ON users_details.IDuser = users.id where username like ?";
            p = conn.prepareStatement(query);
            p.setString(1,user);
            rs = p.executeQuery();
            rs.next();
            String adress = rs.getString("adress");
            list.add(adress);

            query = "select town from users_details INNER JOIN users ON users_details.IDuser = users.id where username like ?";
            p = conn.prepareStatement(query);
            p.setString(1,user);
            rs = p.executeQuery();
            rs.next();
            String town = rs.getString("town");
            list.add(town);

            query = "select postcode from users_details INNER JOIN users ON users_details.IDuser = users.id where username like ?";
            p = conn.prepareStatement(query);
            p.setString(1,user);
            rs = p.executeQuery();
            rs.next();
            String postcode = rs.getString("postcode");
            list.add(postcode);

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



            //login - registration etc...
    public User getUser(String username, String password){
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, this.username, this.password);

            String query = "SELECT * from( users inner join users_details on users.id= users_details.IDuser) where users.username like ? and password like ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                User user=new User(rs.getString("name"),rs.getString("surename"),rs.getString("username"),rs.getString("email"),
                        rs.getString("phone"),rs.getString("adress"),rs.getString("postcode"),rs.getString("city"));
                query = "UPDATE tokens SET token=? WHERE idu=?";
                ps = conn.prepareStatement(query);
                ps.setString(1, user.getToken());
                ps.setInt(2,rs.getInt("id"));
                System.out.println(ps);
                ps.executeUpdate();

                return user;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void logout( String token) {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, this.username, this.password);

            String query = "UPDATE tokens SET token=\"\" where token like ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,token);
            System.out.println(ps);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean checkIfEmailOrUsernameExist(String username, String email) {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, this.username, this.password);

            String query = "SELECT count(*) as num FROM users inner join users_details on users.id=users_details.IDuser WHERE username like ? OR email like ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,email);
            ResultSet rs=ps.executeQuery();
            System.out.println(ps);

            rs.next();
            if(rs.getInt("num")==0)
                return false;  // email and login don't exist
            else
                return true;  // login or email is already in database

        }catch(Exception e){
            e.printStackTrace();
        }

        return true;
    }
    public void insertNewUserIntoDb(Registration registration) {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, this.username, this.password);
            String query = "INSERT INTO users(name, surename, username, password) "+
                    " VALUES (?,?,?,?)";
            PreparedStatement ps= conn.prepareStatement(query);
            ps.setString(1,registration.name);
            ps.setString(2,registration.surename);
            ps.setString(3,registration.username);
            ps.setString(4,registration.password);
            ps.executeUpdate();
            query = "INSERT INTO users_details(phone, email, adress, city,postcode) "+
                    " VALUES (?,?,?,?,?)";
            PreparedStatement ps1= conn.prepareStatement(query);
            ps1.setString(1,registration.phone);
            ps1.setString(2,registration.email);
            ps1.setString(3,registration.adress);
            ps1.setString(4,registration.city);
            ps1.setString(4,registration.postcode);

            ps1.executeUpdate();



        }catch(Exception e){
            e.printStackTrace();
        }
    }

}