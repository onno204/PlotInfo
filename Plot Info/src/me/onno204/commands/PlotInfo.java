package me.onno204.commands; 

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor; 
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender; 
import org.bukkit.entity.Player; 
import org.bukkit.plugin.Plugin;

import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Polygonal2DSelection; 
import com.sk89q.worldguard.bukkit.WorldGuardPlugin; 
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager; 
import com.sk89q.worldguard.protection.regions.GlobalProtectedRegion;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedPolygonalRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class PlotInfo implements CommandExecutor {
	String title = me.onno204.main.main.title;
	Plugin plugin = Bukkit.getPluginManager().getPlugin("PlotInfo"); 
	
	List<String> members1 = new ArrayList<String>();
	List<String> owners1 = new ArrayList<String>(); 
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length != 0){
			sender.sendMessage(title + "/" + cmd.getLabel() + "");
			return true;
		}
		if(!(sender instanceof Player)){
			sender.sendMessage(title + "I'm sorry but it looks like you'r not a player, am i right?");
			return true;
		}
		Player player = (Player) sender;   
		WorldGuardPlugin worldGuard = getWorldGuard(); 
		RegionManager regionManager = null;
		try { regionManager = checkRegionManager(worldGuard, player.getWorld());
		} catch (CommandException e) { 
		sender.sendMessage(ChatColor.RED + e.getMessage());
		return true;} 	
		ProtectedRegion region = null;
		try { region = checkRegionStandingIn(regionManager, player, false);
		} catch (CommandException e) {  
		sender.sendMessage(ChatColor.RED + e.getMessage());
		return true; } 	
		

		owners1.clear();   
		for (UUID id : region.getOwners().getUniqueIds()){ 
			owners1.add(Bukkit.getOfflinePlayer(id).getName()); 
		} 
		members1.clear();
		for (UUID id : region.getMembers().getUniqueIds()){ 
			members1.add(Bukkit.getOfflinePlayer(id).getName()); 
		}
		sender.sendMessage(ChatColor.BLUE + "Plot Informatie: ");
		sender.sendMessage(ChatColor.AQUA + "Je staat momenteel op het plot: " + ChatColor.YELLOW + region.getId() );
		sender.sendMessage(ChatColor.AQUA + "De eigenaar/eigenaren van dit plot is/zijn: " + ChatColor.DARK_AQUA + owners1.toString() );
		sender.sendMessage(ChatColor.AQUA + "De mensen met toegang zijn: " + ChatColor.DARK_AQUA + members1.toString() );

		player.playSound(player.getLocation(), Sound.LEVEL_UP , 10, 1);
		player.playSound(player.getLocation(), Sound.ANVIL_USE , 10, 1);
		
		return true;
	}
	

	public static WorldGuardPlugin getWorldGuard() { 
	    Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard"); 
	    // WorldGuard may not be loaded
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) { 
	        return null;
	    } 
	    return (WorldGuardPlugin) plugin;  
	}
	
	
	/*
	
	 public Player getPlayerByUuid(UUID uuid) {
	        for(Player p : Bukkit.getOnlinePlayers())
	            if(p.getUniqueId().equals(uuid)){
	                return p;
	            }
	        throw new IllegalArgumentException();
	    }
	
	*/
	
	
	
	
	
	public static ProtectedRegion checkRegionStandingIn(RegionManager regionManager, Player player, boolean allowGlobal)
		    throws CommandException
		  {
		    ApplicableRegionSet set = regionManager.getApplicableRegions(player.getLocation()); 
		    ProtectedRegion region1 = null;
			try { region1 = checkExistingRegion(getWorldGuard().getRegionManager( player.getWorld() ), "een", false);
			} catch (CommandException e) { } 
		    set.getRegions().remove(region1);
		    if (set.size() == 0)
		    {
		      if (allowGlobal)
		      {
		        ProtectedRegion global = checkExistingRegion(regionManager, "__global__", true);
		        player.sendMessage(ChatColor.GRAY + "You're not standing in any " + "regions. Using the global region for this world instead.");
		        
		        return global;
		      }
		      throw new CommandException("Geen plot gevonden op de plek waar je nu staat!");
		    } 
		    if (set.size() > 1)
		    {
		      StringBuilder builder = new StringBuilder();
		      boolean first = true;
		      for (ProtectedRegion region : set)
		      {
		        if (!first) {
		          builder.append(", "); 
		        }
		        first = false;
		        builder.append(region.getId());
		      }
		      throw new CommandException("ERROR! je bent in meerdere plots tegelijk?.\nje bent in: " + builder.toString());
		    }
		    return (ProtectedRegion)set.iterator().next();
		  }
		
		  protected static ProtectedRegion checkExistingRegion(RegionManager regionManager, String id, boolean allowGlobal)
				    throws CommandException
				  {
				    checkRegionId(id, allowGlobal);
				    
				    ProtectedRegion region = regionManager.getRegion(id);
				    if (region == null)
				    {
				      if (id.equalsIgnoreCase("__global__"))
				      {
				        region = new GlobalProtectedRegion(id);
				        regionManager.addRegion(region);
				        return region;
				      }
				      throw new CommandException("No region could be found with the name of '" + id + "'.");
				    }
				    return region;
				  }
		  
		  protected static String checkRegionId(String id, boolean allowGlobal)
				    throws CommandException
				  {
				    if (!ProtectedRegion.isValidId(id)) {
				      throw new CommandException("The region name of '" + id + "' contains characters that are not allowed.");
				    }
				    if ((!allowGlobal) && (id.equalsIgnoreCase("__global__"))) {
				      throw new CommandException("Sorry, you can't use __global__ here.");
				    }
				    return id;
				  }

		  
		  public static void setPlayerSelection(Player player, ProtectedRegion region)
				    throws CommandException
				  {
				    WorldEditPlugin worldEdit = WorldGuardPlugin.inst().getWorldEdit();
				    
				    World world = player.getWorld();
				    if ((region instanceof ProtectedCuboidRegion))
				    {
				      ProtectedCuboidRegion cuboid = (ProtectedCuboidRegion)region;
				      Vector pt1 = cuboid.getMinimumPoint();
				      Vector pt2 = cuboid.getMaximumPoint();
				      CuboidSelection selection = new CuboidSelection(world, pt1, pt2);
				      worldEdit.setSelection(player, selection);
				      player.sendMessage(ChatColor.YELLOW + "Region selected as a cuboid.");
				    }
				    else if ((region instanceof ProtectedPolygonalRegion))
				    {
				      ProtectedPolygonalRegion poly2d = (ProtectedPolygonalRegion)region;
				      
				      Polygonal2DSelection selection = new Polygonal2DSelection(world, poly2d.getPoints(), poly2d.getMinimumPoint().getBlockY(), poly2d.getMaximumPoint().getBlockY());
				      worldEdit.setSelection(player, selection);
				      player.sendMessage(ChatColor.YELLOW + "Region selected as a polygon.");
				    }
				    else
				    {
				      if ((region instanceof GlobalProtectedRegion)) {
				        throw new CommandException("Can't select global regions! That would cover the entire world.");
				      }
				      throw new CommandException("Unknown region type: " + region.getClass().getCanonicalName());
				    }
				  }
		  
		  public static RegionManager checkRegionManager(WorldGuardPlugin plugin, World world)
				    throws CommandException
				  {
				    if (!plugin.getGlobalStateManager().get(world).useRegions) {
				      throw new CommandException("Region support is disabled in the target world. It can be enabled per-world in WorldGuard's configuration files. However, you may need to restart your server afterwards.");
				    }
				    RegionManager manager = plugin.getRegionContainer().get(world);
				    if (manager == null) {
				      throw new CommandException("Region data failed to load for this world. Please ask a server administrator to read the logs to identify the reason.");
				    }
				    return manager;
				  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
