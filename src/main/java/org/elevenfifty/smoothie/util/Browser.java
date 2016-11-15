package org.elevenfifty.smoothie.util;

import java.util.Scanner;

import org.elevenfifty.smoothie.Configuration;
import org.elevenfifty.smoothie.beans.Recipe;

public class Browser {
	private final Configuration config;
	private final Scanner in = new Scanner(System.in);

	public Browser(Configuration config) {
		this.config = config;
	}

	public void displayRecipes() {
		//loadRecipes(recipes.csv);
		for (int i = 0; i < config.listRecipes().size(); i++) {
			Recipe r = config.getRecipe(i);
			System.out.format("%d: %s $%,01.2f%n", i + 1, r.getName(), r.getCost());
		}
	}

	public Recipe readRecipe() {
		System.out.println();
		System.out.print("Select a Smoothie Recipe: ");//It's printing this twice and I don't know why. 

		return config.getRecipe(in.nextInt() - 1);
	}

}
