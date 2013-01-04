package net.lastmine.BankPlugin.listener;

import net.lastmine.BankPlugin.Utils.Signs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChange implements Listener {

	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		if (e.getLine(0).equalsIgnoreCase("Bank")) {

			/*
			 * Types: 0 = Guthaben 1 = Einzahlen 2 = Auszahlen
			 */
			if(e.getPlayer().hasPermission("bp.sign.create")){
			String PosComp = e.getBlock().getLocation().getX() + ";"
					+ e.getBlock().getLocation().getY() + ";"
					+ e.getBlock().getLocation().getZ() + ";"
					+ e.getBlock().getLocation().getWorld().getName();
			if (e.getLine(1).equalsIgnoreCase("0")) {
				Signs.getConfig().set("Signs."+PosComp.replace(".", ",")+".Type", 0);
				e.setLine(0, "�8[�3Bank�8]");
				e.setLine(1, "�7Guthaben");
				e.setLine(2, "");
				e.setLine(3, "");
				Signs.SaveConfig();
			} else if (e.getLine(1).equalsIgnoreCase("1")) {
				Signs.getConfig().set("Signs."+PosComp.replace(".", ",")+".Type", 1);
				e.setLine(0, "�8[�3Bank�8]");
				e.setLine(1, "�7Einzahlen");
				e.setLine(2, "");
				e.setLine(3, "");
				Signs.SaveConfig();
			} else if (e.getLine(1).equalsIgnoreCase("2")) {
				Signs.getConfig().set("Signs."+PosComp.replace(".", ",")+".Type", 2);
				e.setLine(0, "�8[�3Bank�8]");
				e.setLine(1, "�7Auszahlen");
				e.setLine(2, "");
				e.setLine(3, "");
				Signs.SaveConfig();
			} else {
				e.getPlayer().sendMessage("�8[�3BankPlugin�8] �4Diesen Bank Typen gibt es leider nicht!");
				e.getBlock().breakNaturally();
			}
			} else{ 
				e.getPlayer().sendMessage("�8[�3BankPlugin�8] �4Du hast keine Permission f�r diese Aktion");
				e.getBlock().breakNaturally();
			}
		}
	}
}
