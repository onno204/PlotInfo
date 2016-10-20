package me.onno204.commands; 

import java.util.HashMap;

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
	HashMap<Player, Integer> YesList = new HashMap<Player, Integer>();
	
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
			Player p =((Player) sender);
			if(YesList.keySet().contains(((Player) sender))){
				
				int Current = ((int)System.currentTimeMillis());
				int Last = YesList.get(p);
				
				if(Last >= Current-20000 ){
					sender.sendMessage(main.title + "Koevoets worden aangezet....");
					
					for(Player p1 : Bukkit.getOnlinePlayers()){
						if(p1 == null){ continue; }
						p1.sendMessage(main.title + "§4§l" + sender.getName().toUpperCase() + " HEEFT DIE MOOIE KOEVOETS AANGEZET. RED JE WINKEL!! ! ! !! !");
						p1.playSound(p1.getLocation(), Sound.LEVEL_UP , 10, 1);
						p1.playSound(p1.getLocation(), Sound.ANVIL_USE , 10, 1);
					}
					IrondoorLsitener.Koevoet = !IrondoorLsitener.Koevoet;
					YesList.remove(p);
					return true;
				}
			}
			if(YesList.keySet().contains(p) ){
				YesList.remove(p);
			}
			YesList.put(p, ((int)System.currentTimeMillis()) );
			sender.sendMessage(main.title + "Doe /koevoet opnieuw om koevoets aan te zetten!");
		}
		return true;
	}
}
