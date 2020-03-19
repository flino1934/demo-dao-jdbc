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

public class DepartamentDaoJDBC implements DepartmentDao {

	private Connection conn;

	public DepartamentDaoJDBC(Connection conn) {

		this.conn = conn;

	}

	@Override
	public void insert(Department obj) {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement(
					"INSERT INTO department "
							+"(Name) " 
							+"VALUES " 
							+"(?)", 
							Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1,obj.getName());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				
				ResultSet rs = st.getGeneratedKeys();//vai pegar a chave que vai ser o id
				
				if (rs.next()) {
					
					int id = rs.getInt(1);
					obj.setId(id);
				}else {
					
					throw new DbException("Erro inesperado");
				
				}
				
			}
			
		}catch(SQLException e) {
			
			throw new DbException(e.getMessage());
			
		}
		

	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {

				Department obj = instantiateDepartment(rs);//vai chamar o metodo
				
				return obj;//retorna o obj
			}

			return null;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {

			DB.closeResultSet(rs);
			DB.closeStatement(st);

		}

	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {// Metodo de intanciação do Department
		// TODO Auto-generated method stub

		Department dep = new Department();
		dep.setId(rs.getInt("ID"));// vai passar o ID que esta no banco para a classe!
		dep.setName(rs.getString("Name"));// vai passar o nome do departamento para a classe!
		return dep;
	}

	@Override
	public List<Department> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement(
					"SELECT * FROM department ORDER BY Name");
			
			rs = st.executeQuery();
			
			List<Department>list = new ArrayList();
			
			while(rs.next()) {
				
				Department obj = instantiateDepartment(rs);
				list.add(obj);//vai passar obj como argumento para lista
				
			}

			return list;
			
		}catch(SQLException e) {
			
			throw new DbException(e.getMessage());
			
		}
		
		
	}

}
