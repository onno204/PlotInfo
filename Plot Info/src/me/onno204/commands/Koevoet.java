package me.onno204.commands; 

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.onno204.main.IrondoorLsitener;
import me.onno204.main.main;

public class Koevoet implements CommandExecutor {
	String title = me.onno204.main.main.title;
	Plugin plugin = Bukkit.getPluginManager().getPlugin("PlotInfo"); 
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(IrondoorLsitener.Koevoet){
			for(Player p : Bukkit.getOnlinePlayers()){
				if(p == null){ continue; }
				p.sendMessage(main.title + "§4§l" + sender.getName().toUpperCase() + " HEEFT DIE MOOIE KOEVOETS UITGEZET. JE WINKEL IS VEILIG!! ! ! !! !");
				p.playSound(p.getLocation(), Sound.LEVEL_UP , 10, 1);
				p.playSound(p.getLocation(), Sound.ANVIL_USE , 10, 1);
			}
			IrondoorLsitener.Koevoet = !IrondoorLsitener.Koevoet;
		}else {
			for(Player p : Bukkit.getOnlinePlayers()){
				if(p == null){ continue; }
				p.sendMessage(main.title + "§4§l" + sender.getName().toUpperCase() + " HEEFT DIE MOOIE KOEVOETS AANGEZET. RED JE WINKEL!! ! ! !! !");
				p.playSound(p.getLocation(), Sound.LEVEL_UP , 10, 1);
				p.playSound(p.getLocation(), Sound.ANVIL_USE , 10, 1);
			}
			IrondoorLsitener.Koevoet = !IrondoorLsitener.Koevoet;
		}
		return true;
	}
}
