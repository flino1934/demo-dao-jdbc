package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	public static SellerDao createSellerDao() {//vai expor a interface ao inves de expor a implementação 
		
		return new SellerDaoJDBC();
		
	}

}
