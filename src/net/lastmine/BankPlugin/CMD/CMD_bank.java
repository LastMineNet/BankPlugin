package net.lastmine.BankPlugin.CMD;

import net.lastmine.BankPlugin.Utils.Signs;

import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CMD_bank implements CommandExecutor {

	private Plugin plugin;

	public CMD_bank(Plugin thisplugin) {
		this.plugin = thisplugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable,
			String[] args) {
		Player p = (Player) sender;
		if (args.length == 0) {
			if (p.hasPermission("bp.commands.bank.admin")) {
				p.sendMessage("�8]�7=====�a{�8[�3BankPlugin�8]�a}�7=====�8[");
				p.sendMessage("�a/�3bank <Player>  �8-  �7Stand eines Benutzers abfragen");
				p.sendMessage("�a/�3bank delete  �8-  �7Um ein Bank Schild zu l�schen");
				return true;
			} else {
				p.sendMessage("�8[�3BankPlugin�8] �4Du hast keine Permission f�r diese Aktion");
				return true;
			}
		}
		if(args[0].equalsIgnoreCase("delete")){
			if(args.length == 1){
				if(p.getTargetBlock(null, 15).getState() instanceof Sign){
					Sign s = (Sign)p.getTargetBlock(null, 15).getState();
					if(s.getLine(0).equalsIgnoreCase("�8[�3Bank�8]")){
						String PosComp = s.getLocation()
								.getX()
								+ ";"
								+ s.getLocation().getY()
								+ ";"
								+ s.getLocation().getZ()
								+ ";"
								+ s.getLocation()
										.getWorld().getName();
						Signs.getConfig().set("Signs."+PosComp.replace(".", ","), null);
						Signs.SaveConfig();
						s.getBlock().breakNaturally();
						p.sendMessage("�8[�3BankPlugin�8] �4Bank Schild gel�scht!");
					}
				} else {
					
				}
			}
		}
		return false;
	}

}