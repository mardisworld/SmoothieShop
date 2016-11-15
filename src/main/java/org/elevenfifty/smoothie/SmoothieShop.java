package org.elevenfifty.smoothie;

//import static java.lang.System.out;
//import static java.util.Arrays.asList;
//import static org.elevenfifty.smoothie.Configuration.configure;
//import static org.elevenfifty.smoothie.util.Inventory.consumeIngredients;
//import static org.elevenfifty.smoothie.util.Inventory.hasSufficientInventory;
//import static org.elevenfifty.smoothie.util.PrettyPrinter.prettyPrint;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.elevenfifty.smoothie.beans.Recipe;
//
////import java.util.List;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
////import org.apache.log4j.Logger;
//
////import org.elevenfifty.smoothie.decoratored.beans.Smoothie;
//import org.elevenfifty.smoothie.util.Browser;
//import org.elevenfifty.smoothie.util.PrettyPrinter;

import static java.lang.System.out;
import static java.util.Arrays.asList;
import static org.elevenfifty.smoothie.Configuration.configure;
import static org.elevenfifty.smoothie.util.Inventory.consumeIngredients;
import static org.elevenfifty.smoothie.util.Inventory.hasSufficientInventory;
import static org.elevenfifty.smoothie.util.PrettyPrinter.prettyPrint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elevenfifty.smoothie.beans.Recipe;
import org.elevenfifty.smoothie.util.Browser;

public class SmoothieShop implements Runnable {
	//private static final Logger logger = Logger.getLogger(SmoothieShop.class);

	public static void main(String[] args) throws IOException {
		new SmoothieShop(configure("recipes.csv", "ingredients.csv")).run();
		}


	private final Configuration config;
	private final Browser browser;
	private final List<Recipe> cart = new ArrayList<>();

	public SmoothieShop(Configuration config) {
		this.config = config;
		this.browser = new Browser(config);
	}

	@Override
	public void run() {
		out.println();
		out.println("** Smoothie Shoppe **");

		while (true) {
			switch (menu()) {
			case 'b':
				browse();
				break;
			case 'm':
				makeSmoothies();
				break;
			case 'q':
				return;
			}

		}
	}


	private char menu() {
		out.println();
		out.println("Application Menu:");
		out.println("\t[b] Browse Smoothies");
		out.println("\t[m] Make Smoothies");
		out.println("\t[q] Quit");
		out.println();
		out.print("Select an option: ");

		while (true) {
			String choice = config.getScanner().next();
			if (asList("b", "m", "q").stream().anyMatch(e -> e.equals(choice))) {
				return choice.charAt(0);
			}
			out.println("Invalid option");
		}

	}

	private void showCart() {
		out.println();
		out.println("Selected Smoothies:");
		for (Recipe r : cart) {
			out.println("\t" + r.getName());
		}
	}

	private void browse() {
		browser.displayRecipes();
		Recipe selectedRecipe = browser.readRecipe();

		if (hasSufficientInventory(selectedRecipe)) {
			cart.add(selectedRecipe);
			consumeIngredients(selectedRecipe);
			showCart();
		} else {
			out.println("Insufficient Inventory");
		}
	}

	private void makeSmoothies() {
		if(cart.isEmpty()) {
			out.println();
			out.println("No smoothies selected!");
			return;
		}
		for (Recipe r : cart) {
			prettyPrint(r);
		}
		cart.clear();
	}

}	

	
	
	
//		final Scanner in = new Scanner(System.in);
//		Configuration config = Configuration.configure("recipes.csv", "ingredients.csv");
		
//private static
//		// Recipe Example
//		Recipe r = config.getRecipe("Best Smoothie");
//		PrettyPrinter.print(r);
//
//		//logger.info(printPretty("Ingredients:", r.getIngredients()));
		//logger.info(printPretty("Instructions:", r.getInstructions()));
		//logger.info(r.getCost());

		// Decorator Pattern Example
//		Smoothie s = new Smoothie(config.getIngredient("Orange"));
//		s = new Smoothie(config.getIngredient("Banana"), s);
//		PrettyPrinter.print(s);
//-------------------STEP6------------------------
//		Browser browser = new Browser(config);
//
//		try {
//			while (true) {
//				browser.displayRecipes();
//				Recipe selectedRecipe = browser.readRecipe();
//
//				if (hasSufficientInventory(selectedRecipe)) {
//					selectedRecipe.consumeIngredients();
//					PrettyPrinter.print(selectedRecipe);
//				} else {
//					System.out.println("Insufficient Inventory");
//				}
//			}
//		} catch (InputMismatchException e) {
//			System.out.println("Have a nice day!");
////			in.nextLine();
////			if (in.nextLine().equalsIgnoreCase("q")){
////				System.out.println("Have a nice day!");
////			} else {
////				System.out.println("Please enter a valid response from 1-3. ");
////			}
//		}

//		
//		browser.displayRecipes();
//		Recipe selectedRecipe = browser.readRecipe();//*error 
//		selectedRecipe.consumeIngredients();
//		PrettyPrinter.print(selectedRecipe);
//		
	//	logger.info(printPretty("Ingredients:", s.getIngredients()));
	//	logger.info(printPretty("Instructions:", s.getInstructions()));
	//	logger.info(s.getCost());
//	}

//	private static String printPretty(String preamble, List<? extends Object> lines) {
//		StringBuffer b = new StringBuffer(preamble);
//		b.append("\n");
//		for (Object line : lines) {
//			b.append("\t");
//			b.append(line.toString());
//			b.append("\n");
//		}
//		return b.toString();
//	}
//}
