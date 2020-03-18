package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface SellerDao {

	void insert(Seller obj);

	void update(Seller obj);

	void deleteById(Integer id);

	Seller findById(Integer id);// vai consultar se tem o id solicitado

	List<Seller> findAll();// vai retornar todos os vendedores(seller)

	List<Seller> findByDepartment(Department department);// Vai retornar todos os departamentos solicitados(Seller)

}
