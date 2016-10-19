package me.onno204.main;
 
import java.util.logging.Logger;

import me.onno204.config.config;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor; 
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile; 
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin { 
	public static String title = ChatColor.DARK_BLUE + "[" + ChatColor.DARK_AQUA + "PlotInfo" + ChatColor.DARK_BLUE + "]" + ChatColor.YELLOW;
	PluginDescriptionFile pdfFile = this.getDescription();
	public final Logger logger = Logger.getLogger("Minecraft");
	public Plugin pl;
	
	public void onEnable(){		
		//this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Is getting enabled!" + ", Created By onno204!");
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + pdfFile.getName() + ChatColor.YELLOW + " Version " + pdfFile.getVersion() + ChatColor.AQUA + " Is getting enabled...");
		
		System.out.println("Checking DataFolder...");
		if(!getDataFolder().exists()){ getDataFolder().mkdir(); System.out.println("[MinetopiaUpgrade] DATAFOLDER CREATED"); }
		config.datadir = getDataFolder().getPath();   
		System.out.println("Adding perms..."); 
		PluginManager pl = Bukkit.getPluginManager();
        pl.addPermission(new Permission("PlotInfo.admin")); 
        pl.addPermission(new Permission("PlotInfo.Makelaar"));
		System.out.println("Fixing Commands...");
		getCommand("PlotInfo").setExecutor(new me.onno204.commands.PlotInfo() );
		getCommand("PlotInfo").setPermissionMessage(ChatColor.RED + "not enough perms g");
		getCommand("PlotAdd").setExecutor(new me.onno204.commands.PlotAdd() );
		getCommand("PlotAdd").setPermissionMessage(ChatColor.RED + "not enough perms g");
		getCommand("PlotRemove").setExecutor(new me.onno204.commands.PlotRemove() );
		getCommand("PlotRemove").setPermissionMessage(ChatColor.RED + "not enough perms g");
		getCommand("PlotHelp").setExecutor(new me.onno204.commands.PlotHelp() );
		getCommand("PlotHelp").setPermissionMessage(ChatColor.RED + "not enough perms g");
		getCommand("PlotClear").setExecutor(new me.onno204.commands.PlotClear() );
		getCommand("PlotClear").setPermissionMessage(ChatColor.RED + "not enough perms g");
		getCommand("MakelaarAdd").setExecutor(new me.onno204.commands.MakelaarAdd() );
		getCommand("MakelaarAdd").setPermissionMessage(ChatColor.RED + "not enough perms g");
		getCommand("MakelaarRemove").setExecutor(new me.onno204.commands.MakelaarRemove() );
		getCommand("MakelaarRemove").setPermissionMessage(ChatColor.RED + "not enough perms g");
		getCommand("MakelaarNieuw").setExecutor(new me.onno204.commands.MakelaarNieuweEigenaar() );
		getCommand("MakelaarNieuw").setPermissionMessage(ChatColor.RED + "not enough perms g");
		getCommand("Koevoet").setExecutor(new me.onno204.commands.Koevoet() );
		getCommand("Koevoet").setPermission("Plotinfo.koevoet");
		getCommand("Koevoet").setPermissionMessage(ChatColor.RED + "not enough perms g");
		
		pl.addPermission(new Permission("Plotinfo.koevoet"));
		pl.registerEvents(new IrondoorLsitener(), this);
		
		
		
		
		//pl.registerEvents(new me.onno204.events.LoopEvent(), this);
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + pdfFile.getName() + ChatColor.YELLOW + " Version " + pdfFile.getVersion() + ChatColor.AQUA + " Has Been Enabled!" + " Created By onno204!");
		//this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Enabled!" + ", Created By onno204!");
		this.pl = Bukkit.getPluginManager().getPlugin("PlotInfo");
	} 
	
	public void onDisable() {
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + pdfFile.getName() + " Is getting Disabled!  " +ChatColor.RED + "");
		PluginDescriptionFile pdfFile = this.getDescription();

		System.out.println("Removing perms..."); 
		PluginManager pl = Bukkit.getPluginManager();
        pl.removePermission(new Permission("PlotInfo.admin"));
        pl.removePermission(new Permission("PlotInfo.Makelaar"));
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + pdfFile.getName() + " Has Been Disabled!  " +ChatColor.RED + ":(");
	}
	
	
	
}
