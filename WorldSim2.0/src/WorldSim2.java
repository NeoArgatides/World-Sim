//drake3

import java.util.Random;
import java.util.Scanner;

public class WorldSim2 
{
	public WorldSim2()
	{
		
	}
	
	public static void main(String[] args)
    {
		
    }
	
	public static String gen_countryname() { //generate a name for country
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
	
}


class civilization {

		String name = "";		
		int population = 0;
		int stage = 1; //demographic transition model
		int wealth = 0; //gross domestic product (economy) (not evaluated until dtm stage 2)
		
		int food_supply = 0; //1 food unit feeds 1,000 people for a month
		int health = 0; // it takes 150 farmers to produce a food unit in a month
		int technology = 0;
		
}