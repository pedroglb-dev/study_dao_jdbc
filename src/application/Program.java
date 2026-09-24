package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		IO.println("===TESTE 1: Sellerr findById ====");
		Seller seller = sellerDao.findById(3);

		IO.println(seller);
	}

}
