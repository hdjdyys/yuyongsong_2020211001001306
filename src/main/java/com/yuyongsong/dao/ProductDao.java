package com.yuyongsong.dao;

import com.yuyongsong.model.Product;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao {
    @Override
    public int save(Product product, Connection con) throws SQLException {
        int n = 0;
        String sql = "insert into product(ProductName,ProductDescription,Picture,price,CategoryID) values(?,?,?,?,?)";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, product.getProductName());
        pt.setString(2, product.getProductDescription());
        if (product.getPicture() != null) {
            //for sql server
            pt.setBinaryStream(3, product.getPicture());
            //for mysql
            //   pt.setBlob(3, product.getPicture());
        }
        pt.setDouble(4, product.getPrice());
        pt.setInt(5, product.getCategoryId());
        n = pt.executeUpdate();
        if (n > 0) {
            return n;
        }
        return 0;
    }//end save

    @Override
    public int delete(Integer productId, Connection con) throws SQLException {
        int n = 0;
        String sql = "delete from product where productId = ?";
        PreparedStatement pt = null;
        pt = con.prepareStatement(sql);
        pt.setInt(1, productId);
        n = pt.executeUpdate();

        if (n > 0) {
            return n;
        }

        return 0;
    }

    @Override
    public int update(Product instance, Connection con) throws SQLException {
        int n = 0;
        String sql = "update product set ProductName = ?, ProductDescription = ?, Picture = ?, price = ?, CategoryID = ? where productId = ?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, instance.getProductName());
        pt.setString(2, instance.getProductDescription());
        if (instance.getPicture() != null) {
            //for sql server
            pt.setBinaryStream(3, instance.getPicture());
            //for mysql
            //   pt.setBlob(3, product.getPicture());
        }
        pt.setDouble(4, instance.getPrice());
        pt.setInt(5, instance.getCategoryId());
        if (n > 0) {
            return n;
        }
        return 0;
    }

    @Override
    public Product findById(Integer productId, Connection con) throws SQLException {
        String sql = "select * from product where productId = ?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setInt(1, productId);
        ResultSet rs = pt.executeQuery();
        Product product = null;
        if (rs.next()) {
            product = new Product();
            product.setProductId(productId);
            product.setProductName(rs.getString("productName"));
            product.setCategoryId(rs.getInt("categoryId"));
            product.setProductDescription(rs.getString("productDescription"));
            product.setPrice(rs.getDouble("price"));
            product.setPicture(rs.getBinaryStream("picture"));
        }

        return product;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId, Connection con) throws SQLException {
        String sql = "select * from product where categoryId = ?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setInt(1, categoryId);
        ResultSet rs = pt.executeQuery();
        List<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setCategoryId(categoryId);
            product.setProductDescription(rs.getString("productDescription"));
            product.setPrice(rs.getDouble("price"));
            product.setPicture(rs.getBinaryStream("picture"));
            list.add(product);
        }
        return list;
    }

    @Override
    public List<Product> findByPrice(double minPrice, double maxPrice, Connection con) throws SQLException {
        String sql = "select * from product where price >= ? and prive <= ?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setDouble(1, minPrice);
        pt.setDouble(2, maxPrice);
        ResultSet rs = pt.executeQuery();
        List<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setCategoryId(rs.getInt("categoryId"));
            product.setProductDescription(rs.getString("productDescription"));
            product.setPrice(rs.getDouble("price"));
            product.setPicture(rs.getBinaryStream("picture"));
            list.add(product);
        }
        return list;
    }

    @Override
    public List<Product> findAll(Connection con) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from product";
            PreparedStatement pt = con.prepareStatement(sql);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setProductDescription(rs.getString("productDescription"));
                product.setPrice(rs.getDouble("price"));
                product.setPicture(rs.getBinaryStream("picture"));
                list.add(product);
            }
            System.out.println("successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> findByProductName(String productName, Connection con) throws SQLException {
        String sql = "select * from product where productName = ?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, productName);
        ResultSet rs = pt.executeQuery();
        List<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setCategoryId(rs.getInt("categoryId"));
            product.setProductDescription(rs.getString("productDescription"));
            product.setPrice(rs.getDouble("price"));
            product.setPicture(rs.getBinaryStream("picture"));
            list.add(product);
        }
        return list;
    }

    @Override
    public InputStream getPicture(Integer productId, Connection con) throws SQLException {
        String sql = "select picture from product where productId = ?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setInt(1, productId);
        ResultSet rs = pt.executeQuery();
        return rs.getBinaryStream("picture");
    }

    public byte[] getPictureById(Integer productId, Connection con) throws SQLException {
        byte[] imgByte = null;
        String sql = "select picture from product where productId=?";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setInt(1, productId);
        ResultSet rs = pt.executeQuery();
        while (rs.next()) {
            Blob blob = rs.getBlob("picture");
            imgByte = blob.getBytes(1, (int) blob.length());
        }

        return imgByte;
    }
}
