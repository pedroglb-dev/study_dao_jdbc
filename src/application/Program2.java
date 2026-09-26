package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		IO.println("===TESTE 1: Department findById ====");
		Department department = departmentDao.findById(3);
		IO.println(department);
		
		IO.println("\n===TESTE 3: Sellerr findAll ====");
		List<Department> list = new ArrayList<>();
		list = departmentDao.findAll();
		for(Department obj : list) {
			IO.println(obj);
		}
		
		IO.println("\n===TESTE 4: Department Insert  ====");
		Department newDepartment = new Department(9, "Music");
		departmentDao.insert(newDepartment);
		IO.println("Inserted! New Id = " + newDepartment.getId());
		
		IO.println("\n===TESTE 5: Department Update ====");
		department = departmentDao.findById(1);
		department.setName("VideoGames");
		departmentDao.update(department);
		IO.println("Update Completed!");
		
		IO.println("\n===TESTE 6: Department Delete ====");
		IO.println("enter id for delete test ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		IO.println("Delete complete");
		
		sc.close();
	}

}
