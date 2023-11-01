
package com.emergentes.dao;

import com.emergentes.modelo.Categoria;
import com.emergentes.utiles.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOimpl extends ConexionBD implements CategoriaDAO{

    @Override
    public void insert(Categoria categoria) throws Exception {
        String sql = "insert into categorias (categoria) Values (?)";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, categoria.getCategoria());
        ps.executeUpdate();
        this.desconectar();
    }

    @Override
    public void update(Categoria categoria) throws Exception {
        String sql = " update categorias set categoria=? where id=?";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1,categoria.getCategoria());
        ps.setInt(2,categoria.getId());
        ps.executeUpdate() ;
        this.desconectar();
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "delete from categorias where id=?";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        this.desconectar();
    }

    @Override
    public List<Categoria> getAll() throws Exception {
         List<Categoria>lista = null;
        String sql = " select * from categorias";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        lista = new ArrayList<Categoria>();
        while(rs.next()){
            Categoria avi = new Categoria();
            avi.setId(rs.getInt("id"));
            avi.setCategoria(rs.getString("categoria"));
            lista.add(avi);
        }
        this.desconectar();
        return lista;       
    }

    @Override
    public Categoria getById(int id) throws Exception {
        Categoria avi = new Categoria();
        try {
             String sql = "select * from categorias where id=?";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            avi.setId(rs.getInt("id"));
            avi.setCategoria(rs.getString("categoria"));
            
        }
        } catch (SQLException e) {
           throw e;
        } finally {
           this.desconectar();
        }
         
        return avi;
    }

}
