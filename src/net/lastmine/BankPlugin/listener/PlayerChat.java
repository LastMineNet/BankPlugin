package net.lastmine.BankPlugin.listener;

import java.util.HashMap;

import net.lastmine.BankPlugin.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.Plugin;

@SuppressWarnings("deprecation")
public class PlayerChat implements Listener{
	
	static HashMap<String, Integer> UserAmount = new HashMap<String, Integer>();
	public static Plugin pl = null;
	@EventHandler
	public void onPlayerChat(PlayerChatEvent e){
		if(e.getPlayer().hasPermission("bp.sign.use")){
			if(PlayerInteract.AmoutListIn.contains(e.getPlayer().getName())){
				if(!UserAmount.containsKey(e.getPlayer().getName())){
					
					if(isNumberAplicable(e.getMessage()) != 0){
						int amount = isNumberAplicable(e.getMessage());
						PlayerInteract.AmoutListIn.remove(e.getPlayer().getName());
						UserAmount.put(e.getPlayer().getName(), amount);
						e.getPlayer().sendMessage("§8[§3BankPlugin§8] §4Dein eingegebener Betrag: "+amount+ " "+main.economy.currencyNamePlural());
						pl.getServer().getScheduler().cancelTask(PlayerInteract.schedulernumber);
						e.setCancelled(true);
					} else {
						e.getPlayer().sendMessage("§8[§3BankPlugin§8] §4Deine eigegebene Zahl ist nicht gültig");
						PlayerInteract.AmoutListIn.remove(e.getPlayer().getName());
					}

				}
			}
		} else {
			e.getPlayer().sendMessage("§8[§3BankPlugin§8] §4Du hast keine Permission für diese Aktion");
		}
	}
	
	
	public int isNumberAplicable(String amount){
		try{
			return Integer.parseInt(amount);
		} catch(NumberFormatException exc){
			exc.printStackTrace();
			return 0;
		}
	}
}
