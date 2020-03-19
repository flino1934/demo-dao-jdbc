package application;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println("=== TEST 1: seller findById =====");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);

		System.out.println("\n=== TEST 2: seller findByDepartment =====");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller obj : list) {
			System.out.println(obj);

		}

		System.out.println("\n=== TEST 3: seller findByAll =====");

		list = sellerDao.findAll();

		for (Seller obj1 : list) {
			System.out.println(obj1);
		}
		
		/*"\n=== TEST 3: seller Insert =====");
		Seller newSeller = new Seller(null,"Greg","greg@gmail.com",new Date(),4000.0,department);
		sellerDao.insert(newSeller);
		System.out.println("Inserted new Id "+newSeller.getId());
		*/
		
		System.out.println("\n=== TEST 5: seller findByDepartment =====");
		seller = sellerDao.findById(1);//Vai trocar todos do id passado
		seller.setName("Martha Waine");//novo nome
		seller.setEmail("martha@gmail.com");//novo email
		sellerDao.update(seller);
		System.out.println("Update Complete");
		
		System.out.println("\n=== TEST 6: seller findByDepartment =====");
		int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o id que deseja deletar"));
		sellerDao.deleteById(id);
		System.out.println("Delete");
	}

}
