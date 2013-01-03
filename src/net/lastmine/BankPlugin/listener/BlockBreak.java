package net.lastmine.BankPlugin.listener;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		if(e.getBlock().getState() instanceof Sign){
			Sign s = (Sign)e.getBlock().getState();
			if(s.getLine(0).equalsIgnoreCase("§8[§3Bank§8]")){
				e.setCancelled(true);
			}
		}
	}
}
