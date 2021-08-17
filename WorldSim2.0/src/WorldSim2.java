//drake3

/*
String print = "";
System.out.println(print);
 */

import java.util.Random;
import java.util.Scanner;

public class WorldSim2 
{

	public static void main(String[] args)
    {
		int gamemode = init();
		
		if (gamemode == 1) {
			defaultSim();
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
		
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		
		return input;
	}

	public static civ initCiv(int total_civ_count) {	//init new civilization stats, generate name etc. total civ count is inputted for ID
		
		String name = gen_name();		
		int population = rn(5, 25);
		int stage = 1; 
		int wealth = rn(1, 6);
		int food_supply = (int)population/10; //make sure dont break. thx
		int health = rn(20, 40); 
		int technology = 1;
		
		int id = total_civ_count;
		
		int x = rn(0, 100);
		int y = rn(0, 100);
		
		civ new_civ = new civ(name, population, stage, wealth, food_supply, health, technology, id, x, y);	
		return new_civ;
	}
	
	//==============================================================
	public static void defaultSim() {
		civ civ_array[];
		int total_civ_count = 0;
		for (int i=0; i<10; i++) { //start game with 10 civilizations, change later. Add each to array. 
			 total_civ_count++; //dont type naything ok so so when when i ad to the array lis ima use init civ is that a yesn word peepee poo balls hmm 
			 
		}
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
	
	int id;
	
	int x;
	int y;
	
	public civ(String i_name, int i_population, int i_stage, int i_wealth, int i_food_supply, int i_health, int i_technology, int i_id, int i_x, int i_y) {
		name = i_name;
		population = i_population;
		stage = i_stage;
		wealth = i_wealth;
		food_supply = i_food_supply;
		health = i_health;
		technology = i_technology;
		id = i_id;
		x = i_x;
		y = i_y;
	}	
	
	
} 