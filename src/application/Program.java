package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Department obj = new Department(1, "books");
		
		Seller seller = new Seller(1, "Carlos", "carlos@gmail.com", new Date(), 5000.0, obj );
		
		SellerDao sellerDao = DaoFactory.createSellerDao();

		IO.println(seller);
	}

}
