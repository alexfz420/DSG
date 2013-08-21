package Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableWorkbook;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dicks.dao.LogDAO;
import com.dicks.dao.ProdCateDAO;
import com.dicks.dao.ProductDAO;
import com.dicks.dao.RuleCateDAO;
import com.dicks.dao.RuleDAO;
import com.dicks.dao.StoreCateDAO;
import com.dicks.dao.StoreDAO;
import com.dicks.pojo.Customer;
import com.dicks.pojo.Fee;
import com.dicks.pojo.Inventory;
import com.dicks.pojo.Log;
import com.dicks.pojo.LogId;
import com.dicks.pojo.Orders;
import com.dicks.pojo.OrderDetail;
import com.dicks.pojo.PackageDetail;
import com.dicks.pojo.Product;
import com.dicks.pojo.Rule;
import com.dicks.pojo.RuleCate;
import com.dicks.pojo.RuleCateId;
import com.dicks.pojo.Store;
import com.dicks.pojo.User;
import com.dicks.pojo.Vendor;


public class HibernateTest
{
//	private static SessionFactory sessionFactory;
	
	
	public static void main(String[] args) throws Exception
	{
		String[] array = ProductDAO.getInstance().getAllNames();
		
		for(String str: array){
			System.out.println(str);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		try{
//			Workbook book = Workbook.getWorkbook(new File("DKS_Store_Address_List.xls"));
//            Sheet sheet = book.getSheet(0);
//           
//            for(int i = 6 ; i<561;i++ ){
//            	Store store = new Store();
//            	store.setStoreId(Integer.valueOf(sheet.getCell(0, i).getContents()));
//            	store.setStoreName(sheet.getCell(1, i).getContents());
//            	store.setAddress(sheet.getCell(3, i).getContents());
//            	store.setCity(sheet.getCell(5, i).getContents());
//            	store.setState(sheet.getCell(6, i).getContents());
//            	store.setZip(sheet.getCell(7, i).getContents());
//            	store.setRegion(sheet.getCell(14, i).getContents());
//            	store.setStoreType("store");
//            	StoreDAO.getInstance().createStore(store);
//            }
//            book.close();
//		}catch (Exception e) {
//            System.out.println(e);
//        }

	}
	
}
