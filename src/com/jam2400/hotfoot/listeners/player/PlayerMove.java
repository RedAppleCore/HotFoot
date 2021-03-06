package com.jam2400.hotfoot.listeners.player;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import com.jam2400.hotfoot.JamUHC;
import com.jam2400.hotfoot.listeners.HFListener;
import com.jam2400.hotfoot.utils.GameState;

public class PlayerMove extends HFListener {

	public PlayerMove(JamUHC pl) {
		super(pl);
	}
	
	private HashMap<String, Location> loc = new HashMap<String, Location>();
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		double x = p.getLocation().getX();
		double y = p.getLocation().getY() - 1;
		double z = p.getLocation().getZ();
		final Location loc = new Location(p.getWorld(), x, z, y);
		final Block block = loc.getBlock();
		
		if (GameState.isState(GameState.IN_GAME)){
			p.sendMessage("you moved");
			block.breakNaturally();
		}
			
	}

}
