package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Department obj) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("insert into Department (Name) values (?)", Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, obj.getName());
			
			int rowsAffected = pst.executeUpdate();
			
			if(rowsAffected>0) {
				ResultSet rs = pst.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Erro inesperado: Nenhuma linha alterada");
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(pst);
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("update Department set Name = ? where id = ?");
			pst.setString(1, obj.getName());
			pst.setInt(2, obj.getId());

			pst.executeUpdate();
			
		}catch(SQLException e ) {
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(pst);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("delete from Departments where Id = ?");
			pst.setInt(1, id);
			
			pst.executeUpdate();
			
		}catch(SQLException e ) {
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(pst);
		}
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			
			pst = conn.prepareStatement("select * from Department where Id = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);
				return dep;
			}else {
				return null;
			}
			
		}catch(SQLException e ) {
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			
			pst = conn.prepareStatement("select * from Department");
			rs = pst.executeQuery();
			
			List<Department> list = new ArrayList<>();
			
			while(rs.next()) {
				Department dep = instantiateDepartment(rs);
				list.add(dep);
			}
			return list;
			
		}catch(SQLException e ) {
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}
	
	

}
