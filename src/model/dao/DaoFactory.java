package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	public static SellerDao createSellerDao() {//vai expor a interface ao inves de expor a implementação 
		
		return new SellerDaoJDBC(DB.getConnection());
		
	}

}
