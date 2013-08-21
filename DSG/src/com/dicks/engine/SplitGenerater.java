package com.dicks.engine;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.dicks.dao.OrdersDAO;
import com.dicks.pojo.Product;
import com.dicks.pojo.Orders;

public class SplitGenerater {
	private static HashMap<String, String> combinations;
	private static ArrayList<Integer[]> arrays;
	private static HashMap<String, String> index;
	private static HashMap<String, Integer> size;

	public static void main(String[] args) {
		SplitGenerater.cache(10);
		SplitGenerater.buildIndex(10);
	}

	public static ArrayList<PackageTest> getTests(PackageE pack) {
		Product[] products = pack.getProducts().toArray(
				new Product[pack.getProducts().size()]);

		String[] methods = getCombinations(products.length,
				pack.getSplitNum() + 1).split("&");

		ArrayList<PackageTest> results = new ArrayList<PackageTest>(
				methods.length);

		for (int k = 0; k < methods.length; k++) {
			PackageTest t = new PackageTest(pack);
			String[] packageMethod = methods[k].split("/");
			ArrayList<Parcel> parcels = new ArrayList<Parcel>(
					packageMethod.length);
			t.setParcels(parcels);

			for (int l = 0; l < packageMethod.length; l++) {
				Parcel parcel = new Parcel(pack);
				String[] itemMethod = packageMethod[l].split(",");

				for (int m = 0; m < itemMethod.length; m++) {
					Product p = pack.getProduct(Integer.valueOf(itemMethod[m]));
					// System.out.println("Product added: " + p);
					parcel.addProduct(p);
				}
				t.addParcel(parcel);
			}
			results.add(t);
		}
		return results;
	}
	
	public static int getSizeFrom0ToN(int packageSize, int n){
		int result=0;
		for(int i = 1 ; i<=packageSize ; i++){
			result+= size.get(packageSize+","+i);
		}
		return result;
//		int num= 0;
//		for(int i = 1; i<=n ;i++){
//			String[] combinations = getCombinations(packageSize, i).split("&");
//			num+=combinations.length;
//		}
//		return num;
	}


	public static String getCombinations(int n, int m) {
		StringBuffer sb = new StringBuffer();
		try {
			FileReader fr = new FileReader("cache.txt");

			BufferedReader br = new BufferedReader(fr);

			String str;

			while (null != (str = br.readLine())) {
				sb.append(str);
			}
			br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] indexResult = index.get(n + "," + m).split(",");
		return sb.toString().substring(Integer.valueOf(indexResult[0]),
				Integer.valueOf(indexResult[1]));

	}
	
	
	public static int getTotalSize(int packageSize){
		return getSizeFrom0ToN(packageSize, packageSize);
	}	

	public static void cache(int n) {
		int end=-1;
		
		File cacheFile = new File("cache.txt");
		File indexFile = new File("index.txt");
		File sizeFile = new File("size.txt");
		OutputStream  os;
		BufferedOutputStream bos;
		OutputStream  os1;
		BufferedOutputStream bos1;
		OutputStream  os2;
		BufferedOutputStream bos2;
		try {
			if(cacheFile.exists()){
				cacheFile.delete();
			}
			
			if(indexFile.exists()){
				indexFile.delete();
			}
			
			if(sizeFile.exists()){
				sizeFile.delete();
			}
			
			cacheFile.createNewFile();
			indexFile.createNewFile();
			sizeFile.createNewFile();
			
			os = new FileOutputStream("cache.txt");
			bos = new BufferedOutputStream(os);
			os1 = new FileOutputStream("index.txt");
			bos1 = new BufferedOutputStream(os1);
			os2 = new FileOutputStream("size.txt");
			bos2 = new BufferedOutputStream(os2);
			
			for(int i =1;i<=n;i++){
				for(int j =1; j<=i;j++){
					String str = split(i,j);
					String size = str.split("&").length+"&";
					bos.write(str.getBytes());
					bos1.write(((end+1)+","+(end+str.length()+1)+"/").getBytes());
					bos2.write(size.getBytes());
					end+=str.length();
				}
			}		
			
			bos.close();
			os.close();
			bos1.close();
			os1.close();
			bos2.close();
			os2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}  

	private static String split(int n, int m) {
		arrays = new ArrayList<Integer[]>();
		combinations = new HashMap<String, String>();

		Integer[] array = new Integer[n];
		for (int i = 0; i < n; i++) {
			array[i] = i;
		}

		for (int i = 0; i < array.length; i++) {
			Integer[] newArray = new Integer[i + 1];
			for (int j = 0; j <= i; j++) {
				newArray[j] = array[j];
			}
			arrays.add(newArray);
		}
		return split(array, m);
	}

	private static String split(Integer[] inputArray, int n) {

		int m = inputArray.length;

		String checkResult = check(m, n, inputArray);
		if ("".equals(checkResult))
			return null;
		if (checkResult != null)
			return checkResult;

		String combinationKey1 = (m - 1) + "," + (n - 1);
		String combinationKey2 = (m - 1) + "," + (n);
		Integer[] nextArray = arrays.get(m - 2);
		String result1 = null;
		String result2 = null;

		if (combinations.containsKey(combinationKey1)) {
			result1 = combinations.get(combinationKey1);
		} else {
			String cr1 = check(m - 1, n - 1, nextArray);
			if (null == cr1) {
				result1 = split(nextArray, n - 1);
				put(combinationKey1, result1);
			} else if ("".equals(cr1)) {
			} else {
				result1 = cr1;
				put(combinationKey1, cr1);
			}
		}

		if (combinations.containsKey(combinationKey2)) {
			result2 = combinations.get(combinationKey2);
		} else {
			String cr2 = check(m - 1, n, nextArray);
			if (null == cr2) {
				result2 = split(nextArray, n);
				put(combinationKey2, result2);
			} else if ("".equals(cr2)) {
			} else {
				result2 = cr2;
				put(combinationKey2, result2);
			}

		}

		int newItem = inputArray[m - 1];

		StringBuffer sb = new StringBuffer();
		String[] result1Array = result1.split("&");
		String[] result2Array = result2.split("&");

		for (int i = 0; i < result1Array.length; i++) {
			sb.append(result1Array[i]).append("/").append(newItem).append("&");
		}

		for (int i = 0; i < result2Array.length; i++) {
			String[] strs = result2Array[i].split("/");
			for (int j = 0; j < strs.length; j++) {
				String str = strs[j] + "," + newItem;
				for (int k = 0; k < strs.length; k++) {
					if (k != j)
						sb.append(strs[k]).append("/");
				}
				sb.append(str).append("&");
			}
		}

		sb.deleteCharAt(sb.length() - 1);
		String result = sb.toString();

		String key = m + "," + n;
		put(key, result);
		return result;
	}

	private static void put(String key, String value) {
		if (!combinations.containsKey(key))
			combinations.put(key, value);
	}

	private static String check(int m, int n, Integer[] inputArray) {
		if (n < 1)
			return "";

		if (m == n) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < inputArray.length; i++) {
				sb.append(inputArray[i]).append("/");
			}
			sb.deleteCharAt(sb.length() - 1);
			String result = sb.toString();
			String key = m + "," + n;
			put(key, result);

			return result;
		}

		if (n == 1) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < inputArray.length; i++) {
				sb.append(inputArray[i]).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			String result = sb.toString();
			String key = m + "," + n;
			put(key, result);

			return result;
		}
		return null;
	}

	public static HashMap<String,String> buildIndex(int n) {
		index = new HashMap<String,String>();
		size = new HashMap<String,Integer>();
		
		StringBuffer sb =new StringBuffer();
		StringBuffer sb1 =new StringBuffer();
		try{
			FileReader fr = new FileReader(
					"index.txt");
	
			FileReader fr1 = new FileReader(
					"size.txt");
			
			BufferedReader br = new BufferedReader(fr);
			BufferedReader br1 = new BufferedReader(fr1);
			
			String str;
			String str1;
	
			while (null != (str = br.readLine()))
			{
				sb.append(str);
			}
			
			while (null != (str1 = br1.readLine()))
			{
				sb1.append(str1);
			}
			fr.close();
			br.close();
			fr1.close();
			br1.close();
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] indexResult = sb.toString().split("/");
		String[] sizeResult = sb1.toString().split("&");
		int k=0;
		for(int i=1;i<n+1;i++){
			for(int j=1;j<=i;j++){
				size.put(i+","+j, Integer.valueOf(sizeResult[k]));
				index.put(i+","+j,indexResult[k]);
				k++;
			}
		}
		
		
		return index;
	}

	public static Combination[] getCombinations(int i, int j, Item[] items) {
		String[] methods = getCombinations(i, j).split("&");
		Combination[] combinations = new Combination[methods.length];
		for (int k = 0; k < methods.length; k++) {
			combinations[k] = new Combination();
			String[] packageMethod = methods[k].split("/");
			Package_M[] packages = new Package_M[packageMethod.length];
			combinations[k].setPakages(packages);
			for (int l = 0; l < packageMethod.length; l++) {
				packages[l] = new Package_M();
				String[] itemMethod = packageMethod[l].split(",");
				Item[] items1 = new Item[itemMethod.length];
				packages[l].setItems(items1);
				for (int m = 0; m < itemMethod.length; m++) {
					items1[m] = items[Integer.valueOf(itemMethod[m])];
				}
			}
		}
		return combinations;
	}

	private static class Item {
		private int index;

		public Item(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}
	}

	private static class Package_M {
		private Item[] items;

		public Item[] getItems() {
			return items;
		}

		public void setItems(Item[] items) {
			this.items = items;
		}
	}

	private static class Combination {
		private Package_M[] packages;

		public Package_M[] getPakages() {
			return packages;
		}

		public void setPakages(Package_M[] packages) {
			this.packages = packages;
		}
	}
}
