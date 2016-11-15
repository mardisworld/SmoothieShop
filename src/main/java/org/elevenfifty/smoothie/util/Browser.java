package org.elevenfifty.smoothie.util;


import java.util.Scanner;
import org.elevenfifty.smoothie.Configuration;
import org.elevenfifty.smoothie.beans.Recipe;
import static org.elevenfifty.smoothie.util.Inventory.hasSufficientInventory;



public class Browser {
	private final Configuration config;
	private final Scanner in = new Scanner(System.in);

	public Browser(Configuration config) {
		this.config = config;
	}

	public void displayRecipes() {
		System.out.println();
		System.out.println("Available recipes: ");
		for (int i = 0; i < config.listRecipes().size(); i++) {
			Recipe r = config.getRecipe(i);
			
			if (hasSufficientInventory(r)) {
				System.out.format("%d: %s $%,01.2f%n", i + 1, r.getName(), r.getCost());
			} else {
				System.out.format("%d: %s (insufficient inventory)%n", i + 1, r.getName());
			}
		}
	

		
		//loadRecipes(recipes.csv);
//		for (int i = 0; i < config.listRecipes().size(); i++) {
//			System.out.println();
//			System.out.println("Available recipes: ");
//			Recipe r = config.getRecipe(i);
//			System.out.format("%d: %s $%,01.2f%n", i + 1, r.getName(), r.getCost());
//		}
	}

	public Recipe readRecipe() {
		System.out.println();
		System.out.print("Select a Smoothie Recipe(q to quit): ");
		
		

//		From step 4
//		try 
//		{
//			index=userIn.nextInt();
//		}
//		catch (InputMismatchException e)
//		{
//			userIn.nextLine();
//        	System.out.println("That's not an integer. Try again. \n");
//        	option=0;
//		}
		
		return config.getRecipe(in.nextInt() - 1);
	}

}
