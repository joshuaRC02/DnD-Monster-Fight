import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
public class tools {
	public static int roll(String info) {
		//dealing with the added bit
		int dmg = 0;
		if(info.contains("+")) {
			dmg+=Integer.parseInt(info.split("\\+")[1]);
			info = info.split("\\+")[0];
		}
		
		//getting the roll info
		String[] split = info.split("d");
		int numOfRolls = Integer.parseInt(split[0]);
		int die = Integer.parseInt(split[1]);
		
		//rolling the die
		Random rand = new Random();
		int dieDmg = 0;
		for(int i = 0; i < numOfRolls; i++) {
			dieDmg+=rand.nextInt(die) + 1;
		}
		
		dmg+=dieDmg;
		
		return dmg; 
	}
	public static int roll(String info, boolean crit, double multiplier) {
		//dealing with the added bit
		int dmg = 0;
		if(info.contains("+")) {
			dmg+=Integer.parseInt(info.split("\\+")[1]);
			info = info.split("\\+")[0];
		}
		
		//getting the roll info
		String[] split = info.split("d");
		int numOfRolls = Integer.parseInt(split[0]);
		int die = Integer.parseInt(split[1]);
		
		//rolling the die
		Random rand = new Random();
		int dieDmg = 0;
		for(int i = 0; i < numOfRolls; i++) {
			dieDmg+=rand.nextInt(die) + 1;
		}
		
		//deealing with critical and adding the dice dmg
		if(crit) {
			dieDmg*=2;
		}
		dmg+=dieDmg;
		
		//dealing with vuln and res with mult
		dmg*=multiplier;
				
				
		return dmg; 
	}
	
	public static int d20Roll() {
		return roll("1d20");
	}
	
	public static int d20Roll(String advOrDis) {
		int atkRoll = roll("1d20");
		int atkRoll2 = roll("1d20");
		if((advOrDis.equals("adv") && atkRoll2 > atkRoll) || (advOrDis.equals("dis") && atkRoll2 < atkRoll)) {
			atkRoll = atkRoll2;
		}
		return atkRoll;
	}
	
	public static double defStatsToMult(String dmgType, Map<String,Object> defStats) {
		double multiplier = 1;
		if(defStats.containsKey("DMGVUL") && ((Map<String,Object>)defStats.get("DMGVUL")).containsKey("dmgType")) {
			multiplier*=2;
		}else if(defStats.containsKey("DMGRES") && ((Map<String,Object>)defStats.get("DMGVUL")).containsKey("dmgType")) {
			multiplier*=.5;
		}
		return multiplier;
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
