//drake3

/*
String print = "";
System.out.println(print);
 */

import java.util.Random;
import java.util.Scanner;

public class WorldSim2 
{
	public WorldSim2()
	{
		
	}
	
	public static void main(String[] args)
    {
		int gamemode = init();
		
		if (gamemode == 1) {
			defaultSim();
		}
    }
		
	public static String gen_name() { //generate a name for country

		String[] syllables_1 = { "Kek", "Tru", "Ve", "Swed", "Dra", "Sea", "Se", "Ame", "Co", "Las", "Lac", "Azer", "Colum", "Bhu"};
		String[] syllables_2 = { "ki", "ik", "ak", "", "i", "ek", "ti", "ji", "bi", "ri", "ri"};
		String[] syllables_3 = { "stan", "land", "la", "go", "en", "ca", "an", "any", "na", "len"};
		
		
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

		String print = "WorldSim2 v1.0.0";
		System.out.println(print);
		
		print = "Enter gamemode selection:";
		System.out.println(print);
		print = "1 - Default World Simulation";
		System.out.println(print);
		
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		
		return input;
	}

	public static civ initCiv() {	//init new civilization stats, generate name etc
		civ new_civ = new civ();
		new_civ.name = gen_name();
		
		return new_civ;
	}
	
	//--------------------------------------------------------------------------------------------------------------------
	public static void defaultSim() {
		
		civ civ_array[]; 
		for (int i=0; i<10; i++) { //start game with 10 civilizations, change later. Add each to array. 
			 
		}
		
		
	}
}


class civ {

		String name = "";		
		int population = 0;
		int stage = 1; 
		int wealth = 0; 
		
		int food_supply = 0; 
		int health = 0; 
		int technology = 0;
		
		int id = 0;
		
		int x = 0;
		int y = 0;
		
}