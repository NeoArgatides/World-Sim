//drake3

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

		String[] syllables_1 = { "Kek", "Tru", "Ve", "Swed", "Dra", "Sea", "Se", "Ame", "Co", "Las", "Lac", "Azer", "Colum", "Bhu", "Bar"};
		String[] syllables_2 = { "ki", "ik", "ak", "", "i", "ek", "ti", "ji", "bi", "ri", "ri", "ru"};
		String[] syllables_3 = { "stan", "land", "la", "go", "en", "ca", "an", "any", "na", "len", "tia"};
		
		
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
		int population = rn(5, 25);
		int stage = 1; 
		int wealth = rn(1, 6);
		int food_supply = (int)(population/2); //make sure dont break. thx
		int health = rn(40, 60); 
		int technology = 1;
		int incentive = 70; //incentive to have kids
		
		int id = total_civ_count;
		
		int x = rn(0, 100);
		int y = rn(0, 100);
		
		civ new_civ = new civ(name, population, stage, wealth, food_supply, health, technology, id, x, y, incentive);	
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
				simulate(newCiv);
				
				year++;
				String printLine = "Year " + year + " simulated.";
				print(printLine);
				
			} else if (input == 2) {
				printReport(newCiv);
			}
			
			print("---");
			print("0: End simulation");
			print("1: Simulate new year");
			print("2: Generate report");
			Scanner scan = new Scanner(System.in);
			input = scan.nextInt();
			
		}
	}

	
	public static civ simulate(civ civ2) {  //simulates 1 year
		
		//natural population growth
		float m_population = (civ2.incentive - (100-civ2.health)) / 100;
		int d_population = (int)(m_population * (civ2.population / 10)); //10 is the exponential modifier may need to be changed (very important)
		
		//starvation
		if (civ2.food_supply*10 < civ2.population) {
			int population_over_food = (civ2.population - (civ2.food_supply*10)); //people not supported by current food
			d_population -= population_over_food/2;
		}
		
		//change in the tech and the health
		float m_technology = (-(2/civ2.wealth)+100)/100; //2 may need to be changed if tech changes too radically (reciprocal 1/x function)
		int d_tech = (int)(5*m_technology); 
		
		float m_health = -(float)(Math.pow(civ2.technology, -0.25)) + 1;  //health aka inverse deathrate needs to go up with technology, and will go down with infectious diseases which we havent made yet. ok
		int d_health = (int)(5*m_health); 
		
		int disease_spike;
		disease_spike = rn(0, 20);
		if (disease_spike == 1) {
			civ2.disease_severity += rn(20, 40);
			civ2.health -= civ2.disease_severity;
		
		}
		
		if (civ2.disease_severity > 0) {
			d_population -= (int)((civ2.disease_severity / 100) * (civ2.population / 10)); //dying of disease
			
			civ2.disease_severity -= (int)(6*m_health); //disease severity going down based on technology as defined above	
		}
				
		//determine delta incentive to have sex
		int d_incentive = 0;
		d_incentive -= (int)((100 - civ2.health) + civ2.disease_severity)/10;
		d_incentive += (int)(5*(-(10/civ2.wealth)+100)/100);
		d_incentive++;
		
		//producing da food
		civ2.food_supply += (int) (civ2.population * 1.4 * m_health);
		civ2.food_supply--; //da food go bad
		
		//eatin da food
		civ2.food_supply -= (int)(civ2.population/10);
		
		//apply deltas
		civ2.population += d_population;
		civ2.technology += d_tech;
		civ2.health += d_health;
		civ2.incentive += d_incentive;
		
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
		
		if (civ2.population < 0) {
			
		}
		
		return civ2;
	}

	
	
	
	
	public static void printReport(civ civ2) {
		String printLine = "";
		print("--- REPORT ---");
		printLine = civ2.name + ", " + civ2.id;
		print(printLine);
		print("---");
		
		printLine = "Stage: " + civ2.stage;
		print(printLine);
	
		printLine = "Population: " + civ2.population;
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
	int population;
	int stage; 
	int wealth;
	int food_supply; 
	int health; 
	int technology;
	int incentive;
	int disease_severity;
	
	int id;
	
	int x;
	int y;
	
	public civ(String i_name, int i_population, int i_stage, int i_wealth, int i_food_supply, int i_health, int i_technology, int i_id, int i_x, int i_y, int i_incentive) {
		name = i_name;
		population = i_population;
		stage = i_stage;
		wealth = i_wealth;
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