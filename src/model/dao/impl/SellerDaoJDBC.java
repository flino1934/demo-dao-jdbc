package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			
			st.setInt(1, id);//a opera��o esta passando como parametro o id que vai ser recebido como argumento pelo metodo
			rs = st.executeQuery();
			
			if (rs.next()) {
				
				//Foi criado um Department para passar os dados 
				Department dep = instantiateDepartment(rs);
				
				//Foi criado um Seller para passar os dados para a classe!
				Seller obj = instatiateSeller(rs,dep);
				
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
	
	@Override
	public List<Seller> findByDepartment(Department department) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");
					
			
			st.setInt(1, department.getId());//a opera��o esta passando como parametro o id que vai ser recebido como argumento pelo metodo
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();//Vai ser utilizado de uma forma que ele verifica se h� o departamento
			
			while (rs.next()) {

				Department dep = map.get(rs.getInt("DepartmentId"));//Vai buscar no map o id do departamento, caso n�o tenha vai retornar nulo
				
				if (dep == null) {//vai verificar se dep � nulo se for vai criar o obj
					
					dep = instantiateDepartment(rs);//se for nulo vai intanciar o departamento
					map.put(rs.getInt("DepartmentId"), dep);//Vai pegar o valor do id do departamento como chave e passar (dep)como valor
				}

				Seller obj = instatiateSeller(rs, dep);
				list.add(obj);
			}

			return list;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {

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

	private Department instantiateDepartment(ResultSet rs) throws SQLException {//Metodo de intancia��o do Department
		// TODO Auto-generated method stub
		
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));//vai passar o ID que esta no banco para a classe!
		dep.setName(rs.getString("DepName"));//vai passar o nome do departamento para a classe!
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name");

			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();//Vai ser utilizado de uma forma que ele verifica se h� o departamento
			
			while (rs.next()) {

				Department dep = map.get(rs.getInt("DepartmentId"));//Vai buscar no map o id do departamento, caso n�o tenha vai retornar nulo
				
				if (dep == null) {//vai verificar se dep � nulo se for vai criar o obj
					
					dep = instantiateDepartment(rs);//se for nulo vai intanciar o departamento
					map.put(rs.getInt("DepartmentId"), dep);//Vai pegar o valor do id do departamento como chave e passar (dep)como valor
				}

				Seller obj = instatiateSeller(rs, dep);
				list.add(obj);
			}

			return list;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);

		}
	}

	

}
