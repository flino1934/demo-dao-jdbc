package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

		System.out.println("=== TEST 1: findById =======");
		Department dep = departmentDao.findById(2);
		System.out.println(dep);

		System.out.println("\n=== TEST 2: findAll =======");
		List<Department> list = departmentDao.findAll();
		for (Department d : list) {
			System.out.println(d);
		}
		
		/*System.out.println("\n=== TEST 3: Insert =======");
		Department department = new Department(null,"T.i");
		departmentDao.insert(department);
		System.out.println("Inserted! New id: " + department.getId());*/

		/*System.out.println("\n=== TEST 4: Update =======");
		Department department = departmentDao.findById(1);
		department.setName("Food");
		departmentDao.update(department);
		System.out.println("Update Complete");*/
		
		
		
	}

}
