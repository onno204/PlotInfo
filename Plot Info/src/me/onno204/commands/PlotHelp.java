package me.onno204.commands;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlotHelp implements CommandExecutor {
	 
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) { 
		if(args.length != 0){
			sender.sendMessage(ChatColor.RED + "/" + cmd.getLabel() + "");
			return true;
		}
		CommandSender player = sender;
		player.sendMessage(ChatColor.BLUE + "===========================");
		player.sendMessage(ChatColor.AQUA + "Makelaar Help Menu");
		player.sendMessage("/MakelaarAdd <Player>" + ChatColor.RED + " Voegt een nieuwe eigenaar toe.");
		player.sendMessage("/MakelaarRemove <Player>" + ChatColor.RED + " Verwijderd een eigenaar.");
		player.sendMessage("/MakelaarNieuw <Player>" + ChatColor.RED + " Maakt iemand de nieuwe eigenaar & Verwijderd huidige spelers.");
		player.sendMessage(ChatColor.BLUE + "===========================");
		player.sendMessage(ChatColor.AQUA + "Default Help Menu");
		player.sendMessage("/PlotInfo" + ChatColor.RED + " Laat informatie over het plot zien.");
		player.sendMessage("/PlotAdd <player>" + ChatColor.RED + " Voeg iemand toe aan je plot.");
		player.sendMessage("/PlotRemove <Player>" + ChatColor.RED + " Verwijder iemand van je plot.");
		player.sendMessage("/PlotClear" + ChatColor.RED + " Verwijder iedereen van je plot.");
		player.sendMessage(ChatColor.BLUE + "===========================");
		if (sender instanceof Player){ 
			((Player) sender).playSound(((Player) sender).getLocation(), Sound.ANVIL_LAND , 10, 1);
		}
		return true;
	}
	
	
	
}
