package com.yuyongsong.dao;

import com.yuyongsong.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao implements IUserDao{
    @Override
    public boolean saveUser(Connection con, User user) throws SQLException {
        String sql = "insert into register(id, username, password, email, gender, birthdate) values (?,?,?,?,?,?);";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1,user.getId());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setString(5, user.getGender());
        preparedStatement.setDate(6, (java.sql.Date) user.getBirthdate());
        preparedStatement.executeUpdate();
        return true;
    }

    @Override
    public int deleteUser(Connection con, User user) throws SQLException {
        String sql = "delete from register where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,user.getId());
        ps.executeUpdate();
        return 0;
    }

    @Override
    public int updateUser(Connection con, User user) throws SQLException {
        String sql = "update register set username ="+user.getUsername()+
                ", password = "+user.getPassword()+
                ", email = "+user.getEmail()+
                ", gender = "+user.getGender()+
                ", birthdate = "+user.getBirthdate()+" where id="+user.getId();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.executeUpdate();
        return 0;
    }

    @Override
    public User findById(Connection con, Integer id) throws SQLException {
        String sql = "select id ,username,password,email,gender,birthdate from register where id = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1,id);

        ResultSet rs = st.executeQuery();
        User user = null;
        if (rs.next()){
            user = new User();
            user.setId(rs.getString("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
        }
        return user;
    }

    @Override
    public User findByUsernamePassword(Connection con, String username, String password) throws SQLException {

        String sql = "select id ,username,password,email,gender,birthdate from register where username=? and password=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,username);
        st.setString(2,password);
        ResultSet rs = st.executeQuery();
        User user = null;
        if (rs.next()){
            user = new User();
            user.setId(rs.getString("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
        }
        return user;
    }

    @Override
    public List<User> findByUsername(Connection con, String username) throws SQLException {

        String sql = "select id ,username,password,email,gender,birthdate from register where username = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,username);
        ResultSet rs = st.executeQuery();
        User user = new User();
        List<User> list = new ArrayList<User>();
        while (rs.next()){
            user.setId(rs.getString("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
            list.add(user);
        }
        return list;
    }

    @Override
    public List<User> findByPassword(Connection con, String password) throws SQLException {
        String sql = "select id ,username,password,email,gender,birthdate from register where password = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,password);
        ResultSet rs = st.executeQuery();
        User user = new User();
        List<User> list = new ArrayList<User>();
        while (rs.next()){
            user.setId(rs.getString("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
            list.add(user);
        }
        return list;
    }

    @Override
    public List<User> findByEmail(Connection con, String email) throws SQLException {
        String sql = "select id ,username,password,email,gender,birthdate from register where email = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,email);
        ResultSet rs = st.executeQuery();
        User user = new User();
        List<User> list = new ArrayList<User>();
        while (rs.next()){
            user.setId(rs.getString("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
            list.add(user);
        }
        return list;
    }

    @Override
    public List<User> findByGender(Connection con, String gender) throws SQLException {
        String sql = "select id ,username,password,email,gender,birthdate from register where gender = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,gender);
        ResultSet rs = st.executeQuery();
        User user = new User();
        List<User> list = new ArrayList<User>();
        while (rs.next()){
            user.setId(rs.getString("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
            list.add(user);
        }
        return list;
    }

    @Override
    public List<User> findByBirthdate(Connection con, Date birthDate) throws SQLException {
        String sql = "select id ,username,password,email,gender,birthdate from register where birthdate = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setDate(1, (java.sql.Date) birthDate);
        ResultSet rs = st.executeQuery();
        User user = new User();
        List<User> list = new ArrayList<User>();
        while (rs.next()){
            user.setId(rs.getString("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
            list.add(user);
        }
        return list;
    }

    @Override
    public List<User> findAllUser(Connection con) throws SQLException {
        String sql = "select id ,username,password,email,gender,birthdate from register";
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        User user = new User();
        List<User> list = new ArrayList<User>();
        while (rs.next()){
            user.setId(rs.getString("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
            list.add(user);
        }
        return list;
    }
}
