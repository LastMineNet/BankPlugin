package net.lastmine.BankPlugin.CMD;

import net.lastmine.BankPlugin.main;
import net.lastmine.BankPlugin.Utils.Config;
import net.lastmine.BankPlugin.Utils.Signs;
import net.lastmine.BankPlugin.Utils.UserBank;

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
				p.sendMessage("§8]§7=====§a{§8[§3BankPlugin§8]§a}§7=====§8[");
				p.sendMessage("§a/§3bank <Player>  §8-  §7Stand eines Benutzers abfragen");
				p.sendMessage("§a/§3bank delete  §8-  §7Um ein Bank Schild zu löschen");
				return true;
			} else {
				p.sendMessage("§8[§3BankPlugin§8] §4Du hast keine Permission für diese Aktion");
				return true;
			}
		}
		if(args[0].equalsIgnoreCase("delete")){
			if(args.length == 1){
				if(p.getTargetBlock(null, 15).getState() instanceof Sign){
					Sign s = (Sign)p.getTargetBlock(null, 15).getState();
					if(s.getLine(0).equalsIgnoreCase("§8[§3Bank§8]")){
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
						p.sendMessage("§8[§3BankPlugin§8] §4Bank Schild gelöscht!");
					}
				} else {
					
				}
			}
		}
		if(args[0].equalsIgnoreCase("get")){
			if(args.length == 1){
				p.sendMessage("§8[§3BankPlugin§8] §4Syntax: §c/bank get <Player>");
			}
			if(args.length == 2){
				if(UserBank.FileConfiguration().contains("Users."+args[1])){
					double Guthaben = main.economy.getBalance(p.getName());
					String Geliehen = UserBank.FileConfiguration().getString("Users."+args[1]+".in");
					p.sendMessage(
							"§8[§3BankPlugin§8] §4"+args[1]+"'s Guthaben: "
									+ Guthaben+ " "+main.economy.currencyNamePlural());
					p.sendMessage(
							"§8[§3BankPlugin§8] §4"+args[1]+"'s Guthaben auf der Bank: "
									+ Geliehen+".0 "+main.economy.currencyNamePlural());
				} else {
					p.sendMessage("§8[§3BankPlugin§8] §4Spieler nicht gefunden!");
				}
			}
		}
		return false;
	}

}
