package me.onno204.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List; 

import org.bukkit.Bukkit; 
 

public class PlotPerms {
	static List<String> PlayerPerms = new ArrayList<String>(); 
	static List<String> helper = new ArrayList<String>();
	static List<String> moderator = new ArrayList<String>();
	static List<String> admin = new ArrayList<String>();
	static List<String> channel = new ArrayList<String>();
	static Collection<String> perms = new ArrayList<String>();
	public static String group;
		static String player;
	 
 	@SuppressWarnings("deprecation")
	public static boolean CheckPerm(String player1, String perms){
		player = player1.toLowerCase(); 
		GroupSetup(player);
		PlayerPerms.clear();
		if (!group.equalsIgnoreCase("")){ Setup(); }  
		if (group.equalsIgnoreCase("helper")){
			for (String s1 : helper){ PlayerPerms.add(s1.toLowerCase()); }
		}else if (group.equalsIgnoreCase("moderator")){
			for (String s1 : moderator){ PlayerPerms.add(s1.toLowerCase()); }
		}else if (group.equalsIgnoreCase("admin")){
			for (String s1 : admin){ PlayerPerms.add(s1.toLowerCase()); }
		}else if (group.equalsIgnoreCase("channel")){
			for (String s1 : channel){ PlayerPerms.add(s1.toLowerCase()); }
		} 
		
		if(PlayerPerms.contains(perms.toLowerCase())){ 
			return true;
		}else if (Bukkit.getPlayer(player1).hasPermission(perms)){ 
			return true;
		}else{ 
			return false;
		}
	}
	
	public static void GroupSetup(String player){
			group = ""; 
			if (player.equalsIgnoreCase("onno204")){group = "admin"; }  
			
			if (player.equalsIgnoreCase("CONSOLE")){group = "admin"; }  
	} 
	
	public static void SetChanneller(){
		channel.clear(); 
		channel.add("ban.channals");
		channel.add("channals.staff");  
	}
	
	public static void SetHelper(){
		helper.clear();
		moderator.clear();
		admin.clear(); 
		for(Object s : helper.toArray()){ 
			moderator.add((String) s);
		}
	}
	public static void SetMod(){
		SetHelper(); 
		for(Object s : moderator.toArray()){
			admin.add((String) s); 
		}
	}
	public static void SetAdmin(){
		SetMod(); 
		admin.add("PlotInfo.admin");
		admin.add("PlotInfo.Makelaar");
	}
	public static void Setup(){ 
		SetAdmin();
		SetChanneller();
	}
	
}
