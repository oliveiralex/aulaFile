package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.entities.Product;

public class Program {
	
	public static void main(String[] args) {
		
		String strPath = "//media//alexandre//Arquivos HD//Alexandre Oliveira//tmp//teste//input.csv";
		List<Product> products = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(strPath))) {
			String line = br.readLine();
			
			while (line != null) {
				//System.out.println(line);
	
				String[] values = line.split(",");
				
				String name = values[0];
				Double price = Double.parseDouble(values[1]);
				Integer quantity = Integer.parseInt(values[2]);				
				
				Product product = new Product(name, price, quantity);
				products.add(product);				
				
				line = br.readLine();
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		File path = new File(strPath);
		//System.out.println(path.getParent() + "/out");
		
		boolean success = new File(path.getParent() + "/out").mkdir();
		//System.out.println("Directory created successfully: " + success);
		
		
		if (success) {
			
			String strOutPath = path.getParent() + "/out/summary.csv";
			//System.out.println(strOutPath);
			
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(strOutPath))) {
				
				for (Product p : products) {
					Double subtotal = p.getPrice() * p.getQuantity();
					String line = p.getName() + "," + subtotal;
					
					bw.write(line);
					bw.newLine();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Erro ao criar pasta out");
		}		
	}
}
