package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

//Classe responsavel por implementar os DAO

public class SellerDaoJDBC implements SellerDao{
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
	
		this.conn = conn;
		
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"WHERE seller.Id = ?");
			
			st.setInt(1, id);//a operação esta passando como parametro o id que vai ser recebido como argumento pelo metodo
			rs = st.executeQuery();
			
			if (rs.next()) {
				
				//Foi criado um Department para passar os dados 
				Department department = instantiateDepartment(rs);
				
				//Foi criado um Seller para passar os dados para a classe!
				Seller obj = instatiateSeller(rs,department);
				
				return obj;
				
			}
			
			return null;
			
		}catch(SQLException e) {
			
			throw new DbException(e.getMessage());
			
		}finally {
			
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			
		}

	}

	private Seller instatiateSeller(ResultSet rs, Department department) throws SQLException {
		// TODO Auto-generated method stub

		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(department);

		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {//Metodo de intanciação do Department
		// TODO Auto-generated method stub
		
		Department department = new Department();
		department.setId(rs.getInt("DepartmentId"));//vai passar o ID que esta no banco para a classe!
		department.setName(rs.getString("DepName"));//vai passar o nome do departamento para a classe!
		return department;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
