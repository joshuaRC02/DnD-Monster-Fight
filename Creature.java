import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.google.gson.*;

public class Creature {
	///creature info which be in the same order as json and 5etools website
	//general info
	private String name;
	private String size;
	private String type;
	private String alignment;
	private int AC;
	private int HP;
	//speed stuff
	private int speed = 0;
	private int fly = 0;
	private int swim = 0;
	private int burrow = 0;
	private int climb = 0;
	private boolean hover = false;
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
	private int CR = 0;
	//abilities
	private List<?> PSVAbilities;
	private Map<?,?> actions;
	
	


    public Creature(String creaturePath) throws IOException{
        //setting up the json reader and getting info from json
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get(creaturePath));
        Map<?,?> info = gson.fromJson(reader, Map.class);
        
        //setting all the info up for the creature
        //they make vals automatically null 
        name = (String)info.get("name");
        size = (String)info.get("size");
        type = (String)info.get("type");
        alignment = (String)info.get("alignment");
        AC = ((Double)info.get("AC")).intValue();
        HP = tools.roll((String)info.get("HP"));
        speed = ((Double)info.get("speed")).intValue();
        STR = ((Double)info.get("STR")).intValue();
        STRMOD = tools.scoreToMod(STR);
        DEX = ((Double)info.get("DEX")).intValue();
        DEXMOD = tools.scoreToMod(DEX);
        CON = ((Double)info.get("CON")).intValue();
        CONMOD = tools.scoreToMod(CON);
        INT = ((Double)info.get("INT")).intValue();
        INTMOD = tools.scoreToMod(INT);
        WIS = ((Double)info.get("WIS")).intValue();
        WISMOD = tools.scoreToMod(WIS);
        CHA = ((Double)info.get("CHA")).intValue();
        CHAMOD = tools.scoreToMod(CHA);
        DMGVUL = tools.listToMap((List<?>) info.get("DMGVUL"));
        DMGRES = tools.listToMap((List<?>) info.get("DMGRES"));
        languages = (List<?>) info.get("languauges");
        CR = ((Double)info.get("CR")).intValue();
        PSVAbilities = (List<?>) info.get("PSVAbilities");
        actions = (Map<?, ?>) info.get("actions");
    }
    
    public String info() {
    	return actions.get("Rake").toString();
    }
}
