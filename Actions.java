import java.util.HashMap;
import java.util.Map;

public class Actions {
	public static Map<String,Object> meleeWeaponAttack(Map<Object,Object> action, String advOrDis, Map<String,Object> defStats){
		//getting all the info
		int atkBonus = ((Double)action.getOrDefault("atkBonus",0.0)).intValue();
		int critLB = ((Double)action.getOrDefault("crirLB",20.0)).intValue();
		int AC = (int)defStats.get("AC");

		//storing the result as i go
		Map<String,Object> result = new HashMap<String,Object>();
		
		//checking if it hits and is a crit
		int atkRoll = tools.d20Roll(advOrDis);
		result.put("atkRoll", atkRoll);
		boolean critical = atkRoll >= critLB;
		result.put("critical", critical);
		boolean hit = critical || (atkRoll + atkBonus > AC && atkRoll != 1);
		result.put("hit", hit);
		
		//dealing with dmg
		Map<Object,Object> dmgDice = (Map<Object,Object>)action.get("dmg");
		int dmg = 0;
		for(Object key: dmgDice.keySet()) {
			if(!hit) {break;}
			String dmgType = (String)key;
			//dealing with vul/res etc.
			double multiplier = tools.defStatsToMult(dmgType, defStats);
			//getting the dmg
			String dmgRoll = (String)dmgDice.get(dmgType);
			dmg+=tools.roll(dmgRoll,critical,multiplier);
		}
		result.put("dmg", dmg);
		
		return result;		
	}
}
