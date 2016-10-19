package me.onno204.config;

import java.io.File;
import java.io.IOException; 

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
 

public class config {
 
	public static FileConfiguration UserData; 
	public static FileConfiguration Config; 
	public static String datadir; 
	public static String title; 
	
	
	public static String UserData(Boolean Create, Boolean get, Boolean BSet, String Set, Object item, Boolean Save, CommandSender sender, Boolean gets, String geto){
		File file = new File(datadir, "UserData.yml"); 
		if (Create){ try { if (file.createNewFile()) { if(sender != null){sender.sendMessage(title + "File is created!");} }  } catch (IOException e) { e.printStackTrace();  }  }
		if(get){ if (UserData == null){UserData = YamlConfiguration.loadConfiguration(file); } }
		if(item.equals(true)){item = "true"; }
		if (BSet){ UserData.set(Set, item); }
		if(Save){ try { UserData.save(file); } catch (IOException e) { } }
		if (gets){ String rtrn = UserData.getString(geto); return rtrn; }
		return "true";
	}
	
	public static String Config(Boolean Create, Boolean get, Boolean BSet, String Set, Object item, Boolean Save, CommandSender sender, Boolean gets, String geto){
		File file = new File(datadir, "Config.yml"); 
		if (Create){ try { if (file.createNewFile()) { if(sender != null){sender.sendMessage(title + "File is created!");} }  } catch (IOException e) { e.printStackTrace();  }  }
		if(get){ if (Config == null){ Config = YamlConfiguration.loadConfiguration(file);System.out.println("Getting some important stuff..."); } }
		if(item.equals(true)){item = "true"; }
		if (BSet){ Config.set(Set, item); } 
		if(Save){ try { Config.save(file); } catch (IOException e) { } } 
		if (gets){ String rtrn = Config.getString(geto); return rtrn; }
		return "true";
	}
	
} 