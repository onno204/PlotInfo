package me.onno204.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.material.Door;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Openable;
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

@SuppressWarnings("deprecation")
public class IrondoorLsitener implements Listener {
	HashMap<Player, Integer> AntiBlockBreak = new HashMap<Player, Integer>();
	HashMap<Player, Integer> ItemStealTimer = new HashMap<Player, Integer>();
	
	
	public static boolean Koevoet = false;
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void KlikBlock(org.bukkit.event.player.PlayerInteractEvent e) {
		if(e.isCancelled()){ return; }
		if( (e.getAction() == Action.LEFT_CLICK_AIR ) || (e.getAction() == Action.RIGHT_CLICK_AIR) ) { return; }
		
		List<Material> DeniedMaterials = new ArrayList<Material>(); 
		
		DeniedMaterials.add(Material.LEVER);
		DeniedMaterials.add(Material.ACACIA_DOOR);
		DeniedMaterials.add(Material.BIRCH_DOOR);
		DeniedMaterials.add(Material.DARK_OAK_DOOR);
		DeniedMaterials.add(Material.IRON_DOOR);
		DeniedMaterials.add(Material.IRON_DOOR_BLOCK);
		DeniedMaterials.add(Material.JUNGLE_DOOR);
		DeniedMaterials.add(Material.JUNGLE_DOOR);
		DeniedMaterials.add(Material.SPRUCE_DOOR);
		DeniedMaterials.add(Material.TRAP_DOOR);
		DeniedMaterials.add(Material.IRON_TRAPDOOR);
		DeniedMaterials.add(Material.ACACIA_FENCE_GATE);
		DeniedMaterials.add(Material.BIRCH_FENCE_GATE);
		DeniedMaterials.add(Material.DARK_OAK_FENCE_GATE);
		DeniedMaterials.add(Material.JUNGLE_FENCE_GATE);
		DeniedMaterials.add(Material.SPRUCE_FENCE_GATE);
		DeniedMaterials.add(Material.FENCE_GATE);
		DeniedMaterials.add(Material.WOODEN_DOOR);
		DeniedMaterials.add(Material.WOOD_DOOR);
		DeniedMaterials.add(Material.ACACIA_FENCE);
		DeniedMaterials.add(Material.ACACIA_FENCE_GATE);
		DeniedMaterials.add(Material.ANVIL);
		DeniedMaterials.add(Material.ARMOR_STAND);
		DeniedMaterials.add(Material.BED);
		DeniedMaterials.add(Material.BED_BLOCK);
		DeniedMaterials.add(Material.STONE_BUTTON);
		DeniedMaterials.add(Material.WOOD_BUTTON);
		DeniedMaterials.add(Material.BURNING_FURNACE);
		DeniedMaterials.add(Material.BOAT);
		DeniedMaterials.add(Material.CAKE);
		DeniedMaterials.add(Material.CAKE_BLOCK);
		DeniedMaterials.add(Material.CHEST);
		DeniedMaterials.add(Material.CROPS);
		DeniedMaterials.add(Material.DAYLIGHT_DETECTOR);
		DeniedMaterials.add(Material.DAYLIGHT_DETECTOR_INVERTED);
		DeniedMaterials.add(Material.REDSTONE_COMPARATOR);
		DeniedMaterials.add(Material.REDSTONE_COMPARATOR_OFF);
		DeniedMaterials.add(Material.REDSTONE_COMPARATOR_ON);
		DeniedMaterials.add(Material.PISTON_BASE);
		DeniedMaterials.add(Material.PISTON_EXTENSION);
		DeniedMaterials.add(Material.PISTON_MOVING_PIECE);
		DeniedMaterials.add(Material.PISTON_STICKY_BASE);
		DeniedMaterials.add(Material.DISPENSER);
		DeniedMaterials.add(Material.DIODE);
		DeniedMaterials.add(Material.DIODE_BLOCK_OFF);
		DeniedMaterials.add(Material.DIODE_BLOCK_ON);
		DeniedMaterials.add(Material.CHEST);
		DeniedMaterials.add(Material.ENDER_CHEST);
		DeniedMaterials.add(Material.TRAPPED_CHEST);
		DeniedMaterials.add(Material.IRON_TRAPDOOR);
		DeniedMaterials.add(Material.TRAP_DOOR);
		DeniedMaterials.add(Material.DRAGON_EGG);
		//DeniedMaterials.add(Material.DROPPER);
		DeniedMaterials.add(Material.EGG);
		DeniedMaterials.add(Material.ITEM_FRAME);
		DeniedMaterials.add(Material.EMPTY_MAP);
		DeniedMaterials.add(Material.FURNACE);
		DeniedMaterials.add(Material.BREWING_STAND);
		DeniedMaterials.add(Material.HOPPER);
		DeniedMaterials.add(Material.JUKEBOX);
		DeniedMaterials.add(Material.MAP);
		DeniedMaterials.add(Material.MINECART);
		DeniedMaterials.add(Material.TNT);
		DeniedMaterials.add(Material.MINECART);
		DeniedMaterials.add(Material.COMMAND_MINECART);
		DeniedMaterials.add(Material.EXPLOSIVE_MINECART);
		DeniedMaterials.add(Material.HOPPER_MINECART);
		DeniedMaterials.add(Material.POWERED_MINECART);
		DeniedMaterials.add(Material.STORAGE_MINECART);
		DeniedMaterials.add(Material.WORKBENCH);
		
		Block block = e.getClickedBlock();
		if(block == null){ return; }
		if(block.getType() == null){ return; }
		if(!DeniedMaterials.contains(block.getType())){ return; }
		
		
		Player p = e.getPlayer();
		if((!(IsMember(p,block.getLocation()))) && (!HasPerms(p)) ){
			e.setCancelled(true);
			p.sendMessage(main.title + "Alleen eigenaren en medewerkers van een plot kunnen deze actie uitvoeren!");
			return;
		}
		
		if(AntiBlockBreak.keySet().contains(e.getPlayer())){
			int Current = ((int)System.currentTimeMillis());
			int Last = AntiBlockBreak.get(e.getPlayer());
			
			if(Last >= Current-1000 ){
				e.setCancelled(true);
				e.getPlayer().sendMessage(main.title + "Je kan niet meteen iets openen nadat je een blockje breekt.");
				return;
			}
		}
		
		if(!(block.getType().toString().contains("DOOR") || block.getType().toString().contains("CHEST"))){ return ; }
		
		e.setCancelled(false);
		if(block.getType().toString().contains("_DOOR")){
			if (block.getData() >= 8) {
		          block = block.getRelative(BlockFace.DOWN);
		    }
		    if (block.getType().toString().contains("BLOCK")) {
			    if (block.getData() < 4) {
				    block.setData((byte)(block.getData() + 4));
				    block.getWorld().playEffect(block.getLocation(), Effect.DOOR_TOGGLE, 0);
			    }else {
				    block.setData((byte)(block.getData() - 4));
				    block.getWorld().playEffect(block.getLocation(), Effect.DOOR_TOGGLE, 0);
			    }
		    }
		    block.getState().update();
		}else if(block.getType().equals(Material.IRON_TRAPDOOR)){
			if(IsOwner(p, block.getLocation())){ 
				BlockState s = block.getState();
			    if (((s.getData() instanceof Door)) && (((Door)s.getData()).isTopHalf())) {
			      s = block.getRelative(BlockFace.DOWN).getState();
			    }
			    Openable d = (Openable)s.getData();
			    d.setOpen(!d.isOpen());
			    s.setData((MaterialData)d);
			    s.update();
			}else { p.sendMessage(main.title + "Alleen eigenaren kunnen Iron Trapdoors openen!"); return; }
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
	public void BreakBlockEvent(org.bukkit.event.block.BlockBreakEvent e) {
		if(AntiBlockBreak.keySet().contains(e.getPlayer())){
			AntiBlockBreak.remove(e.getPlayer());
		}
		AntiBlockBreak.put(e.getPlayer(), ((int)System.currentTimeMillis()) );
	}
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
	public void InventoryChange(org.bukkit.event.inventory.InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.isCancelled()){ return; }
		if((e.getAction() == InventoryAction.PICKUP_ALL) || (e.getAction() == InventoryAction.PICKUP_HALF) ||  (e.getAction() == InventoryAction.PICKUP_ONE) || (e.getAction() == InventoryAction.PICKUP_SOME)||  (e.getAction() == InventoryAction.PICKUP_ONE) || (e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY)||  (e.getAction() == InventoryAction.PICKUP_ONE) || (e.getAction() == InventoryAction.HOTBAR_MOVE_AND_READD) || (e.getAction() == InventoryAction.HOTBAR_SWAP)){
			if(p.getItemInHand().getType().equals(Material.GOLD_SWORD)){
				if(ItemStealTimer.keySet().contains(p)){
					
					if(ItemStealTimer.keySet().contains(p)){
						int Current = ((int)System.currentTimeMillis());
						int Last = ItemStealTimer.get(p);
						if(Last >= Current-120000 ){
							e.setCancelled(true);
							p.sendMessage(main.title + "Je kan maar 1 item stelen per minuut!");
							return;
						}
					}
					ItemStealTimer.remove(p);
					ItemStealTimer.put(p, ((int)System.currentTimeMillis()) );
				}else{
					ItemStealTimer.put(p, ((int)System.currentTimeMillis()) );
					return;
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
	public void InventoryEdit(org.bukkit.event.inventory.InventoryOpenEvent e) {
		if(e.isCancelled()){ return; }
		List<String> types = new ArrayList<String>(); 
		types.add("CHEST");
		types.add("DROPPER");
		types.add("DISPENSER");
		boolean next = false;
		for(String s : types){
			if(e.getInventory().getName().contains(s.toLowerCase())){
				next = true;
			}
		}
		//No Chest
		if(!next){ return; }
		Player p = (Player) e.getPlayer();
		if((!IsMember(p, e.getPlayer().getLocation())) && (!HasPerms(p))){
			e.setCancelled(true);
			//p.sendMessage(main.title + "Alleen eigenaren en medewerkers van een plot kunnen Chests openen!");
		}
	}
	
	public static boolean IsMember(Player p, Location ClickedBlock){
		List<String> owners1 = new ArrayList<String>(); 
		WorldGuardPlugin worldGuard = getWorldGuard(); 
		RegionManager regionManager = null;
		try { regionManager = checkRegionManager(worldGuard, p.getWorld());
		} catch (CommandException ee) { p.sendMessage(ChatColor.RED + ee.getMessage()); return false;} 	
		ProtectedRegion region = null;
		try { region = checkRegionStandingIn(regionManager, p, false, ClickedBlock);
		} catch (CommandException ee) { p.sendMessage(ChatColor.RED + ee.getMessage()); return false; }
	
		owners1.clear();   
		for (UUID id : region.getOwners().getUniqueIds()){ owners1.add(Bukkit.getOfflinePlayer(id).getName()); } 
		for (UUID id : region.getMembers().getUniqueIds()){ owners1.add(Bukkit.getOfflinePlayer(id).getName()); } 
		if(owners1.contains(p.getName())){
			return true;
		}
		
		if(p.getItemInHand() != null && p.getItemInHand().getType() != null){
			if(p.getItemInHand().getType().equals(Material.GOLD_SWORD) ){
				if (Koevoet){
					p.sendMessage(main.title + "Ahh man. Moet dit item nou echt gebruikt worden :(.");
					return true;
				}else{
					p.sendMessage(main.title + "Deze kut dingen staan momenteel uit. vraag een admin, Dev of owner om ze aan te zetten.");
					return false;
				}
			}
		}
		
		if(p.hasPermission("PlotInfo.admin")){
			p.sendMessage(main.title + "HAXER! STIEKEM PERMS GEBRUIKEN HE!");
			return true;
		}
		
		return false;
		
	}

	public static boolean IsOwner(Player p, Location ClickedBlock){
		
		if(p.getItemInHand() != null && p.getItemInHand().getType() != null){
			if(p.getItemInHand().getType().equals(Material.GOLD_SWORD) ){
				p.sendMessage(main.title + "Deze items zijn helaas niet toegankelijk op owner items.");
				return false;
			}
		}
		
		List<String> owners1 = new ArrayList<String>(); 
		WorldGuardPlugin worldGuard = getWorldGuard(); 
		RegionManager regionManager = null;
		try { regionManager = checkRegionManager(worldGuard, p.getWorld());
		} catch (CommandException ee) { p.sendMessage(ChatColor.RED + ee.getMessage()); return false;} 	
		ProtectedRegion region = null;
		try { region = checkRegionStandingIn(regionManager, p, false, ClickedBlock);
		} catch (CommandException ee) { p.sendMessage(ChatColor.RED + ee.getMessage()); return false; }
	
		owners1.clear();   
		for (UUID id : region.getOwners().getUniqueIds()){ owners1.add(Bukkit.getOfflinePlayer(id).getName()); } 
		if(owners1.contains(p.getName())){
			return true;
		}
		
		if(p.hasPermission("PlotInfo.admin")){
			p.sendMessage(main.title + "HAXER! STIEKEM PERMS GEBRUIKEN HE!");
			return true;
		}
		
		return false;
		
	}

	public static boolean HasPerms(Player p){
		
		if(p.hasPermission("PlotInfo.admin")){
			p.sendMessage(main.title + "HAXER! STIEKEM PERMS GEBRUIKEN HE!");
			return true;
		}
		
		return false;
		
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





	public static ProtectedRegion checkRegionStandingIn(RegionManager regionManager, Player player, boolean allowGlobal, Location ClickedBlock)
		    throws CommandException
		  {
		    ApplicableRegionSet set = regionManager.getApplicableRegions(ClickedBlock); 
		    ProtectedRegion region1 = null;
			try { region1 = checkExistingRegion(getWorldGuard().getRegionManager( player.getWorld() ), "een", false);
			} catch (CommandException e) { } 
		    set.getRegions().remove(region1);
		    if (set.size() == 0)
		    {
		      if (allowGlobal) {
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
