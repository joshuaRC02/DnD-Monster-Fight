import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.*;

public class Creature {
	///creature stats which should be in the same order as json and 5etools website
	//general info
	private String name;
	private String size;
	private String type;
	private String alignment;
	private int AC;
	private int HP;
	//speed stuff
	private int speed;
	private int fly;
	private int swim;
	private int burrow;
	private int climb;
	private boolean hover;
	//ability score stuff
	private int STR;
	private int STRMOD;
	private int DEX;
	private int DEXMOD;
	private int CON;
	private int CONMOD;
	private int INT;
	private int INTMOD;
	private int WIS;
	private int WISMOD;
	private int CHA;
	private int CHAMOD;
	//vul and res
	private Map<?,?> DMGVUL;
	private Map<?,?> DMGRES;
	//etc stuff
	private List<?> languages;
	private int CR;
	private int profBonus;
	//abilities
	private List<?> PSVAbilities;
	private Map<Object,Object> actions;
	//etc
	private List<?> environment;
	///combat info
	private Map<String,Object> defStats;
	
	


    public Creature(String creaturePath) throws IOException{
        //setting up the json reader and getting info from json
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get(creaturePath));
        Map<Object,Object> info = gson.fromJson(reader, Map.class);
        
        //setting all the info up for the creature
        //they make vals automatically null 
        name = (String)info.get("name");
        size = (String)info.get("size");
        type = (String)info.get("type");
        alignment = (String)info.get("alignment");
        AC = ((Double)info.getOrDefault("AC",10.0)).intValue();
        HP = tools.roll((String)info.get("HP"));
        speed = ((Double)info.get("speed")).intValue();
        STR = ((Double)info.getOrDefault("STR",10.0)).intValue();
        STRMOD = tools.scoreToMod(STR);
        DEX = ((Double)info.getOrDefault("DEX",10.0)).intValue();
        DEXMOD = tools.scoreToMod(DEX);
        CON = ((Double)info.getOrDefault("CON",10.0)).intValue();
        CONMOD = tools.scoreToMod(CON);
        INT = ((Double)info.getOrDefault("INT",10.0)).intValue();
        INTMOD = tools.scoreToMod(INT);
        WIS = ((Double)info.getOrDefault("WIS",10.0)).intValue();
        WISMOD = tools.scoreToMod(WIS);
        CHA = ((Double)info.getOrDefault("CHA",10.0)).intValue();
        CHAMOD = tools.scoreToMod(CHA);
        DMGVUL = tools.listToMap((List<?>) info.get("DMGVUL"));
        DMGRES = tools.listToMap((List<?>) info.get("DMGRES"));
        languages = (List<?>) info.get("languauges");
        CR = ((Double)info.getOrDefault("CR",0.0)).intValue();
        profBonus = ((Double)info.getOrDefault("profBonus",2.0)).intValue();
        PSVAbilities = (List<?>) info.get("PSVAbilities");
        actions = (Map<Object, Object>) info.get("actions");
        environment = (List<?>)info.get("environment");
        
        //setting up defStats
        defStats = new HashMap<String,Object>();
        defStats.put("AC",AC);
        defStats.put("DMGVUL",DMGVUL);
        defStats.put("DMGRES",DMGRES);
        defStats.put("PSVAbilities",PSVAbilities);
        
    }
    
    public Map<String,Object> test() {
    	return Actions.meleeWeaponAttack((Map<Object,Object>)actions.get("Rake"),"none",defStats);
    }    
}
