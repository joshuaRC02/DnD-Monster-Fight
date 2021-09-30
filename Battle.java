import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import jdk.tools.jlink.resources.plugins;

//need to redo with different methods and simpler data methods
public class Battle {
	public static void battle() {
		//getting a list to help with choosing
		File folder = new File("creatures");
		List<String> creatureNames = Arrays.asList(folder.list());
		for(String file: creatureNames) {
			System.out.println(file.split("\\.")[0]);
		}
		
		//adding monsters
		ArrayList<Creature> creatures = new ArrayList<Creature>();
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.print("Input creature #" + (creatures.size()+1) + " or type \"none\" to stop...");
			String creatureName = in.nextLine();
			if(creatureName.equals("none") || creatureName.equals("")) {break;}
			if(!creatureNames.contains(creatureName+".json")) {
				System.out.println("Invalid creature! Try again...\n\n");
				continue;
			}
			creatures.add(new Creature(creatureName));
		}
		
		//sorting based on creatures initiative
		Collections.sort(creatures);
		ArrayList<Creature> deadCreatures = new ArrayList<Creature>();
		
		//the actual battle
		int turn = 1;
		while(creatures.size() > 1) {
			//cycling through each creature on a turn
			for(int index = 0; index < creatures.size(); index++) {
				//geting the current creature
				Creature current = creatures.get(index);
				//getting all the other creatures
				ArrayList<Creature> otherCreatures = (ArrayList<Creature>) creatures.clone();
				otherCreatures.remove(index);
				//attacking a random creature
				
			}
			turn++;
		}
	}
	public static void main(String[] args) {
		battle();
	}
}
