import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
		
	}
}
