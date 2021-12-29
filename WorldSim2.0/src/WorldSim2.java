//drake3

//change notes: 

/*
String print = "";
System.out.println(print);
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries; 
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
//import org.jfree.ui.Spacer;


public class WorldSim2 
{

	public static void main(String[] args)
    {
		int gamemode = init();
		
		if (gamemode == 1) {
			defaultSim();
		}
		
		if (gamemode == 2) {
			singleSim();
		}
    }
		
	public static String gen_name() { //generate a name for country

		String[] syllables_1 = { "Kek", "Dra", "Se", "Ame", "Le", "Lac", "Bar", "Arc'"}; 
		String[] syllables_2 = { "ki", "ak", "", "i", "ke", "ti", "ji", "ri", "ki", "ru", "te"};
		String[] syllables_3 = { "stan", "land", "la", "go", "en", "ca", "an", "na", "tia", "ryx"};
		
		Random rand = new Random(); 
		int rand_int1 = rand.nextInt(syllables_1.length);
		int rand_int2 = rand.nextInt(syllables_2.length);
		int rand_int3 = rand.nextInt(syllables_3.length);
		
		String gen_name = new String();
		
		gen_name = syllables_1[rand_int1] + syllables_2[rand_int2] + syllables_3[rand_int3];
		
		//System.out.println(gen_name);
		return(gen_name);
	}

	public static int init() { //initialize the game. Introductory text
		print("WorldSim2 v1.0.0");
		print("Enter gamemode selection:");
		print("1 - Default World Simulation");
		print("2 - Single Civilization Simulation");
		
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		
		return input;
	}

	public static civ initCiv(int total_civ_count) {	//init new civilization stats, generate name etc. total civ count is inputted for ID
		
		String name = gen_name();		
		int population = rn(6, 20); //ppl in the civ
		int stage = 1; //determined by technology 
		int wealth = 5; //abstract concept
		int industry = 80; //percent of people working
		int food_supply = 0;
		int health = rn(30, 50); //wellbeing of population
		int technology = 1; //advancement of the country
		int incentive = 0; //incentive to have kids
		
		int id = total_civ_count;
		
		int x = rn(0, 100);
		int y = rn(0, 100);
		
		civ new_civ = new civ(name, population, stage, wealth, food_supply, health, technology, id, x, y, incentive, industry);	
		return new_civ;
	}


	//==============================================================
	public static void defaultSim() { //initializes and runs default simulation w no user input
		ArrayList<civ> civ_list = new ArrayList<civ>();
		
		//civ civ_array[];
		int total_civ_count = 0;
		for (int i=0; i< rn(60, 80); i++) { //start game with 10 civilizations, change later. Add each to array. 
			 total_civ_count++; //dont type naything ok so so when when i ad to the array lis ima use init civ is that a yesn word peepee poo balls hmm 
			 civ_list.add(initCiv(total_civ_count));
		}
	}
	
	public static void singleSim() { //runs a simulation with only a single civ generated. no user input
		civ newCiv = initCiv(1);
		
		int year = 0;
		
		int input = 1;
		while (input != 0) {
			
			if (input == 1) {
				simulate(newCiv, 0);
				
				year++;
				String printLine = "Year " + year + " simulated.";
				print(printLine);
				
			} else if (input == 2) {
				printReport(newCiv);
				
			} else if (input == 3) {
				simulate(newCiv, 1);
	
				year++;
				String printLine = "Year " + year + " simulated (+ debug).";
				print(printLine);
			} else if (input == 4) {
				
				print("How many years? ");
				Scanner scan = new Scanner(System.in);
				int input2 = scan.nextInt();
				
				for (int i = 0; i < input2; i++) {
					simulate(newCiv, 0);
					year++;
					String printLine = "Year " + year + " simulated.";
					print(printLine);
				}
				
			}
			
			print("\n");
			print("---");
			print("0: End simulation");
			print("1: Simulate new year");
			print("2: Generate report");
			print("3: Simulate new year + debug");
			print("4: Simulate [x] years.");
			Scanner scan = new Scanner(System.in);
			input = scan.nextInt();
			
		}
	}

	public static civ simulate(civ civ2, int debug) {  //simulates 1 year
		
		
		 
		civ2.wealth += 1;
		
		
		
		
		//cap variables above 0 below 100
		if (civ2.wealth < 1)
			civ2.wealth = 1;
		if (civ2.population < 0)
			civ2.population = 0;
		if (civ2.food_supply < 0)
			civ2.food_supply = 0;
		if (civ2.health < 0)
			civ2.health = 0;
		if (civ2.incentive < 0)
			civ2.incentive = 0;
		if (civ2.disease_severity < 0)
			civ2.disease_severity = 0;
		if (civ2.disease_severity > 100)
			civ2.disease_severity = 100;
		
		if (debug == 1) {
			print("\n");
			print("--- DEBUG ---");
			String printLine = civ2.name + ", " + civ2.id;
			print(printLine);
			print("---");
			
			print("---");
		}
		return civ2;
	}

	public static void printReport(civ civ2) {
		print("\n");
		print("--- REPORT ---");
		String printLine = civ2.name + ", " + civ2.id;
		print(printLine);
		print("---");
		
		printLine = "Stage: " + civ2.stage;
		print(printLine);
	
		printLine = "Population: " + (int)civ2.population;
		print(printLine);
		
		printLine = "Food Supply: " + civ2.food_supply;
		print(printLine);
		
		printLine = "Wealth: " + civ2.wealth;
		print(printLine);
		
		printLine = "Technology: " + civ2.technology;
		print(printLine);
		
		printLine = "Health: " + civ2.health;
		print(printLine);
		
		printLine = "Incentive: " + civ2.incentive;
		print(printLine);
		
		printLine = "Disease: " + civ2.disease_severity;
		print(printLine);
		print("---");
	}
	
	public static void print(String input) {
		System.out.println(input);
	}
	public static int rn(int min, int max) {
		return new Random().nextInt((max - min) + 1) + min;
	}

	//==============================================================
}


class civ {
	String name;		
	float population;
	int stage; 
	int wealth;
	int industry;
	int food_supply; 
	int health; 
	int technology;
	int incentive;
	int disease_severity;
	
	int id;
	
	int x;
	int y;
	
	public civ(String i_name, int i_population, int i_stage, int i_wealth, int i_food_supply, int i_health, int i_technology, int i_id, int i_x, int i_y, int i_incentive, int i_industry) {
		name = i_name;
		population = i_population;
		stage = i_stage;
		wealth = i_wealth;
		industry = i_industry;
		food_supply = i_food_supply;
		health = i_health;
		technology = i_technology;
		incentive = i_incentive;
		id = i_id;
		x = i_x;
		y = i_y;
		disease_severity = 0;
	}	
	
	
} 