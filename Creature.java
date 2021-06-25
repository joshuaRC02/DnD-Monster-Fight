import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.*;

public class Creature implements Comparable<Creature>{
	///creature stats which should be in the same order as json and 5etools website
	//general info
	public String name,size,type,alignment;
	public int AC,HP;
	public int speed,fly,swim,burrow,climb;
	public boolean hover;
	//ability score stuff
	public int STR,STRMOD,STRSAVE;
	public int DEX,DEXMOD,DEXSAVE;
	public int CON,CONMOD,CONSAVE;
	public int INT,INTMOD,INTSAVE;
	public int WIS,WISMOD,WISSAVE;
	public int CHA,CHAMOD,CHASAVE;
	//skills
	public int acrobatics,animalHandling,arcana,athletics,deception,history,insight,intimidation,investigation,medicine,nature,perception,persuasion,religion,sleightOfHand,stealth,survival;
	//etc info
	public Map<?,?> DMGVUL;
	public Map<?,?> DMGRES;
	public int PSVPerception;
	public int initiative;
	public List<?> languages;
	public int CR;
	public int profBonus;
	public List<?> PSVAbilities;
	public Map<Object,Object> actions;
	public List<?> environment;
	///combat info
	public Map<String,Object> defStats;
	public Map<String,Object> atkInfo;
	
	


    public Creature(String creatureName){
        //setting up the json reader and getting info from json
    	Map<Object,Object> info = null;
    	try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("creatures\\" + creatureName + ".json"));
            info = gson.fromJson(reader, Map.class);
    		
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	
        //setting all the info up for the creature
        //they make vals automatically null 
    	//geting all the general info
        name = (String)info.get("name");
        size = (String)info.get("size");
        type = (String)info.get("type");
        alignment = (String)info.get("alignment");
        languages = (List<?>) info.get("languauges");
        environment = (List<?>)info.get("environment");
        CR = ((Double)info.getOrDefault("CR",0)).intValue();
        AC = ((Double)info.getOrDefault("AC",10)).intValue();
        profBonus = ((Double)info.getOrDefault("profBonus",2)).intValue();
        HP = tools.roll((String)info.get("HP"));
        speed = ((Double)info.getOrDefault("speed",0)).intValue();
        
        //getting ability score and it's mod and its save
        STR = ((Double)info.getOrDefault("STR",10)).intValue();
        STRMOD = tools.scoreToMod(STR);
        STRSAVE = ((Double)info.getOrDefault("STRSAVE",STRMOD)).intValue();
        DEX = ((Double)info.getOrDefault("DEX", 10)).intValue();
        DEXMOD = tools.scoreToMod(DEX);
        DEXSAVE = ((Double)info.getOrDefault("DEXSAVE",DEXMOD)).intValue();
        CON = ((Double)info.getOrDefault("CON", 10)).intValue();
        CONMOD = tools.scoreToMod(CON);
        CONSAVE = ((Double)info.getOrDefault("CONSAVE", CONMOD)).intValue();
        INT = ((Double)info.getOrDefault("INT", 10)).intValue();
        INTMOD = tools.scoreToMod(INT);
        INTSAVE = ((Double)info.getOrDefault("INTSAVE",INTMOD)).intValue();
        WIS = ((Double)info.getOrDefault("WIS", 10)).intValue();
        WISMOD = tools.scoreToMod(WIS);
        WISSAVE = ((Double)info.getOrDefault("WISSAVE",WISMOD)).intValue();
        CHA = ((Double)info.getOrDefault("CHA",10)).intValue();
        CHAMOD = tools.scoreToMod(CHA);
        CHASAVE = ((Double)info.getOrDefault("CHASAVE",CHAMOD)).intValue();
        
        //getting skills
        acrobatics = ((Double)info.getOrDefault("acrobatics",DEXMOD)).intValue();
    	animalHandling = ((Double)info.getOrDefault("animalHandling",WISMOD)).intValue();
    	arcana = ((Double)info.getOrDefault("arcana",WISMOD)).intValue();
    	athletics = ((Double)info.getOrDefault("athletics",STRMOD)).intValue();
    	deception = ((Double)info.getOrDefault("deception",CHAMOD)).intValue();
    	history = ((Double)info.getOrDefault("history",INTMOD)).intValue();
    	insight = ((Double)info.getOrDefault("insight",WISMOD)).intValue();
    	intimidation = ((Double)info.getOrDefault("intimidation",CHAMOD)).intValue();
    	investigation = ((Double)info.getOrDefault("investigation",INTMOD)).intValue();
    	medicine = ((Double)info.getOrDefault("medicine",WISMOD)).intValue();
    	nature = ((Double)info.getOrDefault("nature",INTMOD)).intValue();
    	perception = ((Double)info.getOrDefault("perception",WISMOD)).intValue();
    	persuasion = ((Double)info.getOrDefault("persuasion",CHAMOD)).intValue();
    	religion = ((Double)info.getOrDefault("religion",WISMOD)).intValue();
    	sleightOfHand = ((Double)info.getOrDefault("sleightOfHand",DEXMOD)).intValue();
    	stealth = ((Double)info.getOrDefault("stealth",DEXMOD)).intValue();
    	survival = ((Double)info.getOrDefault("survival",WISMOD)).intValue();
        
    	//getting random other crap
        DMGVUL = tools.listToMap((List<?>) info.get("DMGVUL"));
        DMGRES = tools.listToMap((List<?>) info.get("DMGRES"));
        PSVPerception = ((Double)info.getOrDefault("PSVPerception",10+WISMOD)).intValue();
        initiative = ((Double)info.getOrDefault("initiative",DEXMOD)).intValue();
        PSVAbilities = (List<?>) info.get("PSVAbilities");
        actions = (Map<Object, Object>) info.get("actions");
        
        //setting up defStats
        defStats = new HashMap<String,Object>();
        defStats.put("AC",AC);
        defStats.put("DMGVUL",DMGVUL);
        defStats.put("DMGRES",DMGRES);
        defStats.put("PSVPerception",PSVPerception);
        defStats.put("PSVAbilities",PSVAbilities);
        defStats.put("STRSAVE",STRSAVE);
        defStats.put("DEXSAVE",DEXSAVE);
        defStats.put("CONSAVE",CONSAVE);
        defStats.put("INTSAVE",INTSAVE);
        defStats.put("WISSAVE",WISSAVE);
        defStats.put("CHASAVE",CHASAVE);
        
        //setting up atkInfo
        atkInfo = new HashMap<String,Object>();
        atkInfo.put("advOrDis", "none");
        
    }
    
    public boolean isAlive() {
    	return HP > 0;
    }
    
    public Map<String,Object> test() {
    	return Actions.meleeWeaponAttack((Map<Object,Object>)actions.get("Rake"),"none",defStats);
    }    
    
    //implementing comparable to sort creatures based on dex
    @Override
    public int compareTo(Creature c) {
    	if(initiative < c.initiative) {
    		return -1;
    	}else if (initiative > c.initiative) {
    		return 1;
    	}else {
    		return 0;
    	}
    }
}
