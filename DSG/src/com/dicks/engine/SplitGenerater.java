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

	public static void main(String[] args) {
		SplitGenerater.cache(10);
		SplitGenerater.buildIndex(10);

		// System.out.println(Spilt.getCombinations(10, 4));
//		Item[] items = new Item[5];
//		items[0] = new Item(0);
//		items[1] = new Item(1);
//		items[2] = new Item(2);
//		items[3] = new Item(3);
//		items[4] = new Item(4);
//		System.out.println(SplitGenerater.getCombinations(5, 2));
//		Combination[] combinations = SplitGenerater
//				.getCombinations(5, 2, items);
//		System.out.println(combinations[0].getPakages()[0].getItems()[3]
//				.getIndex());
//
//		Product shoes = new Product(1 + "", "shoes", 50, 10, 2);
//
//		Product hat = new Product(2 + "", "hat", 10, 4, 2);
//		Product shirt = new Product(3 + "", "shirt", 20, 8, 5);
//
//		Orders order = null;
//		try {
//			order = OrdersDAO.getInstance().getById(2);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		PackageE p1 = new PackageE(order);
//		p1.addProduct(shoes);
//		PackageE p2 = new PackageE(order);
//		p2.addProduct(hat);
//		p2.addProduct(shirt);
//		p2.addProduct(shoes);
//		p2.setSplitNum(1);
//
//		ArrayList<PackageTest> tests = getTests(p2);
//
//		// System.out.println("size: " + tests.size());
//
//		for (PackageTest t : tests) {
//			System.out.println(t);
//		}
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

		// Combination[] combinations = new Combination[methods.length];
		// for(int k=0;k<methods.length;k++){
		// combinations[k] = new Combination();
		// String[] packageMethod = methods[k].split("/");
		// Package_M[] packages = new Package_M[packageMethod.length];
		// combinations[k] .setPakages(packages);
		// for(int l = 0;l<packageMethod.length ;l++){
		// packages[l] = new Package_M();
		// String[] itemMethod = packageMethod[l].split(",");
		// Item[] items1 = new Item[itemMethod.length];
		// packages[l].setItems(items1);
		// for(int m=0; m<itemMethod.length;m++){
		// items1[m] = items[Integer.valueOf(itemMethod[m])];
		// }
		// }
		// }
		return results;
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

	public static void cache(int n) {
		int end = -1;

		File cacheFile = new File("cache.txt");
		File indexFile = new File("index.txt");
		OutputStream os;
		BufferedOutputStream bos;
		OutputStream os1;
		BufferedOutputStream bos1;
		try {
			if (cacheFile.exists()) {
				cacheFile.delete();
			}

			if (indexFile.exists()) {
				indexFile.delete();
			}
			cacheFile.createNewFile();
			indexFile.createNewFile();
			os = new FileOutputStream("cache.txt");
			bos = new BufferedOutputStream(os);
			os1 = new FileOutputStream("index.txt");
			bos1 = new BufferedOutputStream(os1);

			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= i; j++) {
					String str = split(i, j);
					bos.write(str.getBytes());
					bos1.write(((end + 1) + "," + (end + str.length() + 1) + "/")
							.getBytes());
					end += str.length();
				}
			}
			bos.close();
			os.close();
			bos1.close();
			os1.close();
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

	public static HashMap<String, String> buildIndex(int n) {
		index = new HashMap<String, String>();

		StringBuffer sb = new StringBuffer();
		try {
			FileReader fr = new FileReader("index.txt");

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

		String[] indexResult = sb.toString().split("/");
		int k = 0;
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j <= i; j++) {

				index.put(i + "," + j, indexResult[k]);
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
