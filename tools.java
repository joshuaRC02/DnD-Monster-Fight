import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
public class tools {
	public static int roll(String info) {
		//dealing with the added bit
		int val = 0;
		if(info.contains("+")) {
			val+=Integer.parseInt(info.substring(info.indexOf('+')));
			info = info.substring(0,info.indexOf('+'));
		}
		
		//getting the roll info
		String[] split = info.split("d");
		int numOfRolls = Integer.parseInt(split[0]);
		int die = Integer.parseInt(split[1]);
		
		//rolling the die
		Random rand = new Random();
		for(int i = 0; i < numOfRolls; i++) {
			val+= rand.nextInt(die) + 1;
		}
		
		return val; 
	}
	public static int roll(String info, boolean critical) {
		//dealing with the added bit
		int val = 0;
		if(info.contains("+")) {
			val+=Integer.parseInt(info.substring(info.indexOf('+')));
			info = info.substring(0,info.indexOf('+'));
		}
		
		//getting the roll info
		String[] split = info.split("d");
		int numOfRolls = Integer.parseInt(split[0]);
		int die = Integer.parseInt(split[1]);
		
		//rolling the die
		Random rand = new Random();
		for(int i = 0; i < numOfRolls; i++) {
			val+= (rand.nextInt(die) + 1) * 2;
		}
		
		return val; 
	}
	
	
	
	public static int scoreToMod(int score) {
		return (int)Math.floor((score - 10) / 2);
	}
	public static int modToScore(int mod) { // i dont think this ever used but why not
		return mod * 2 + 10;
	}
	
	
	
	public static Map<?,?> listToMap(List<?> list){
		Hashtable<String, Boolean> map = new Hashtable<String, Boolean>();
		for(Object o:list) {
			map.put((String)o, true);
		}
		return map;
	}
}
