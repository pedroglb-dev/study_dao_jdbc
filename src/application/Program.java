package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		IO.println("===TESTE 1: Sellerr findById ====");
		Seller seller = sellerDao.findById(3);
		IO.println(seller);
		
		IO.println("\n===TESTE 2: Sellerr findByDepartment ====");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for(Seller obj : list) {
			IO.println(obj);
		}
		
		IO.println("\n===TESTE 3: Sellerr findAll ====");
		list = sellerDao.findAll();
		for(Seller obj : list) {
			IO.println(obj);
		}
		
		IO.println("\n===TESTE 4: Sellerr Insert  ====");
		Seller newSeller = new Seller(null, "Joao", "joao@gmail.com", new Date(), 2500.0, department);
		sellerDao.insert(newSeller);
		IO.println("Inserted! New Id = " + newSeller.getId());
		
		IO.println("\n===TESTE 5: Sellerr Update ====");
		seller = sellerDao.findById(1);
		seller.setName("Thomas Waine");
		sellerDao.update(seller);
		IO.println("Update Completed!");
		
		IO.println("\n===TESTE 6: Sellerr Update ====");
		IO.println("enter id for delete test ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		IO.println("Delete complete");
		
		sc.close();
		
	}

}
