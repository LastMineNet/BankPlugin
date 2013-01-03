package net.lastmine.BankPlugin.listener;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e){
		if(e.getBlockAgainst().getState() instanceof Sign){
			Sign s = (Sign)e.getBlockAgainst().getState();
			if(s.getLine(0).equalsIgnoreCase("§8[§3Bank§8]")){
				e.setCancelled(true);
			}
		}
	}
}
