package org.elevenfifty.smoothie;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.elevenfifty.smoothie.beans.Ingredient;
import org.elevenfifty.smoothie.beans.Recipe;
import org.elevenfifty.smoothie.decoratored.beans.Smoothie;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	private static final Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//Recipe Example
		Recipe r = context.getBean("anotherSmoothie", Recipe.class);
		
		logger.info(r.getName());
		logger.info(printPretty("Ingredients:", r.getIngredients()));
		logger.info(printPretty("Instructions:", r.getInstructions()));
		logger.info(r.getCost());
		
		
		//Decorator Pattern Example
		Smoothie s = new Smoothie(context.getBean("orange", Ingredient.class));
		s = new Smoothie(context.getBean("banana", Ingredient.class), s);

		logger.info(printPretty("Ingredients:", s.getIngredients()));
		logger.info(printPretty("Instructions:", s.getInstructions()));
		logger.info(s.getCost());
	
		context.registerShutdownHook();
		context.close();
	}
	
	private static String printPretty(String preamble, List<? extends Object> lines){
		StringBuffer b = new StringBuffer(preamble);
		b.append("\n");
		for(Object line : lines){
			b.append("\t");
			b.append(line.toString());
			b.append("\n");
		}
		return b.toString();
	}
}
