package me.onno204.main;

import java.util.Timer;
import java.util.logging.Logger;
 







import me.onno204.Runable.CheckLevel;
import me.onno204.config.config;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin { 
	public static String title = ChatColor.DARK_BLUE + "[" + ChatColor.DARK_AQUA + "MinetopiaUpgrade" + ChatColor.DARK_BLUE + "]" + ChatColor.YELLOW;
	PluginDescriptionFile pdfFile = this.getDescription();
	public final Logger logger = Logger.getLogger("Minecraft");
	public static Plugin pl = Bukkit.getPluginManager().getPlugin("MinetopiaUpgradePL");

	Timer timer = new Timer();
	
	public void onEnable(){
		
		
		for (Player p : Bukkit.getOnlinePlayers()){
			p.kickPlayer(ChatColor.RED + "" + ChatColor.BOLD + "NIET METEEN RECONNECTEN! \n " + ChatColor.RED + "" + ChatColor.BOLD + "NIET METEEN RECONNECTEN! \n " + ChatColor.RED + "" + ChatColor.BOLD + "NIET METEEN RECONNECTEN! \n " + ChatColor.GRAY + ChatColor.BOLD + "Je bent uit de server gegooid omdat er een reload was... \n door deze reload "+
		" moeten de gegevens opnieuw in geladen worden \n" + ChatColor.RED + "" + ChatColor.RED + "" + ChatColor.BOLD + "NIET METEEN RECONNECTEN! \n " + ChatColor.RED + "" + ChatColor.BOLD + "NIET METEEN RECONNECTEN! \n " + ChatColor.RED + "" + ChatColor.BOLD + "NIET METEEN RECONNECTEN! \n " + ChatColor.RED + "" + ChatColor.BOLD + "NIET METEEN RECONNECTEN! \n " + ChatColor.RED + "" + ChatColor.BOLD + "NIET METEEN RECONNECTEN! \n " + ChatColor.BOLD + "NIET METEEN RECONNECTEN!");
		}
		
		
		
		//this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Is getting enabled!" + ", Created By onno204!");
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + pdfFile.getName() + ChatColor.YELLOW + " Version " + pdfFile.getVersion() + ChatColor.AQUA + " Is getting enabled...");
		
		System.out.println("Checking DataFolder...");
		if(!getDataFolder().exists()){ getDataFolder().mkdir(); System.out.println("[MinetopiaUpgrade] DATAFOLDER CREATED"); }
		config.datadir = getDataFolder().getPath(); 
		PluginManager pl = Bukkit.getPluginManager();
		
		System.out.println("Setting up perms...");
        pl.addPermission(new Permission("MinetopiaUpgrade.getlvlmoney"));
        pl.addPermission(new Permission("MinetopiaUpgrade.lvlup"));
        pl.addPermission(new Permission("MinetopiaUpgrade.setlvl"));

		System.out.println("Fixing Commands...");
		getCommand("setlvl").setExecutor(new me.onno204.commands.setlvl() );
		getCommand("setlvl").setPermission("");
		getCommand("setlvl").setPermissionMessage(ChatColor.RED + "not enough perms g");
		
		getCommand("MineTopiaUpgrade").setExecutor(new me.onno204.commands.showhelp() );
		getCommand("MineTopiaUpgrade").setPermission("");
		getCommand("MineTopiaUpgrade").setPermissionMessage(ChatColor.RED + "not enough perms g");
		
		getCommand("MUDisablePlugin").setExecutor(new me.onno204.commands.DisablePlugin() );
		getCommand("MUDisablePlugin").setPermission("");
		getCommand("MUDisablePlugin").setPermissionMessage(ChatColor.RED + "not enough perms g");
		
		getCommand("GetLevelMoney").setExecutor(new me.onno204.commands.getlvlmoney() );
		getCommand("GetLevelMoney").setPermission("");
		getCommand("GetLevelMoney").setPermissionMessage(ChatColor.RED + "not enough perms g");

		getCommand("LvlUp").setExecutor(new me.onno204.commands.lvlup() );
		getCommand("LvlUp").setPermission("");
		getCommand("LvlUp").setPermissionMessage(ChatColor.RED + "not enough perms g");

		getCommand("CheckLevel").setExecutor(new me.onno204.commands.CheckLevel() );
		getCommand("CheckLevel").setPermission("");
		getCommand("CheckLevel").setPermissionMessage(ChatColor.RED + "not enough perms g");
		
		me.onno204.config.Functions.setup(); 

		System.out.println("Creating Timer...");
		timer.schedule(new CheckLevel(), 0, 20 * 1000);

		System.out.println("Registering events...");
		pl.registerEvents(new me.onno204.events.LeaveEvent(), this);
		pl.registerEvents(new me.onno204.events.JoinEvent(), this);
		pl.registerEvents(new me.onno204.events.ChatEvent(), this);
		//pl.registerEvents(new me.onno204.events.LoopEvent(), this);
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + pdfFile.getName() + ChatColor.YELLOW + " Version " + pdfFile.getVersion() + ChatColor.AQUA + " Has Been Enabled!" + " Created By onno204!");
		//this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Enabled!" + ", Created By onno204!");
	} 
	
	public void onDisable() {
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + pdfFile.getName() + "Is getting Disabled!  " +ChatColor.RED + "");
		System.out.println("Cancelling Timer...");
		timer.cancel();
		PluginDescriptionFile pdfFile = this.getDescription();

		System.out.println("Removing perms...");
		PluginManager pl = Bukkit.getPluginManager(); 
        pl.removePermission(new Permission("MinetopiaUpgrade.getlvlmoney"));
        pl.removePermission(new Permission("MinetopiaUpgrade.lvlup"));
        pl.removePermission(new Permission("MinetopiaUpgrade.setlvl"));
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + pdfFile.getName() + "Has Been Disabled!  " +ChatColor.RED + ":(");
	}
	
	
	
}
