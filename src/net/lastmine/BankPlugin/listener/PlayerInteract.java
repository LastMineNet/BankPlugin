package net.lastmine.BankPlugin.listener;

import java.util.ArrayList;
import java.util.List;

import net.lastmine.BankPlugin.main;
import net.lastmine.BankPlugin.Utils.Signs;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class PlayerInteract implements Listener {

	static List<String> AmoutListIn = new ArrayList<String>();
	public static Plugin pl = null;

	@EventHandler
	public void onPlayerInteract(final PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getState() instanceof Sign) {
				Sign s = (Sign) e.getClickedBlock().getState();
				if (s.getLine(0).equalsIgnoreCase("�8[�3Bank�8]")) {
					if (e.getPlayer().hasPermission("bp.sign.use")) {
						String PosComp = e.getClickedBlock().getLocation()
								.getX()
								+ ";"
								+ e.getClickedBlock().getLocation().getY()
								+ ";"
								+ e.getClickedBlock().getLocation().getZ()
								+ ";"
								+ e.getClickedBlock().getLocation()
										.getWorld().getName();
						if(Signs.getConfig().getInt("Signs."+PosComp.replace(".", ",")+".Type") == 0){
							double Guthaben = main.economy.getBalance(e.getPlayer().getName());
							String Geliehen = "";
							String Zins = "";
							e.getPlayer().sendMessage("�8[�3BankPlugin�8] �4Dein Guthaben: "+Guthaben);
							e.getPlayer().sendMessage("�8[�3BankPlugin�8] �4Geliehen: "+Geliehen);
							e.getPlayer().sendMessage("�8[�3BankPlugin�8] �4Aktueller Zins: "+Zins+"%");
						} else if(Signs.getConfig().getInt("Signs."+PosComp.replace(".", ",")+".Type") == 1){
							if(PlayerChat.UserAmount.containsKey(e.getPlayer().getName())){
								
							} else {
								e.getPlayer().sendMessage("�8[�3BankPlugin�8] �4Bitte klicke mit links auf das Schild um den Betrag einzustellen!");
							}
						} else if(Signs.getConfig().getInt("Signs."+PosComp.replace(".", ",")+".Type") == 2){
							if(PlayerChat.UserAmount.containsKey(e.getPlayer().getName())){
								
							} else {
								e.getPlayer().sendMessage("�8[�3BankPlugin�8] �4Bitte klicke mit links auf das Schild um den Betrag einzustellen!");
							}
						}
					} else {
						e.getPlayer()
								.sendMessage(
										"�8[�3BankPlugin�8] �4Du hast keine Permission f�r diese Aktion");
					}
				}
			}
		}
		if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (e.getClickedBlock().getState() instanceof Sign) {
				Sign s = (Sign) e.getClickedBlock().getState();
				if (s.getLine(0).equalsIgnoreCase("�8[�3Bank�8]")) {
					if (e.getPlayer().hasPermission("bp.sign.use")) {
						if (!AmoutListIn.contains(e.getPlayer().getName())) {
							String PosComp = e.getClickedBlock().getLocation()
									.getX()
									+ ";"
									+ e.getClickedBlock().getLocation().getY()
									+ ";"
									+ e.getClickedBlock().getLocation().getZ()
									+ ";"
									+ e.getClickedBlock().getLocation()
											.getWorld().getName();
							if (Signs.getConfig().getInt(
									"Signs." + PosComp.replace(".", ",")
											+ ".Type") != 0) {

								e.getPlayer()
										.sendMessage(
												"�8[�3BankPlugin�8] �4Bitte gib den Betrag jetzt in den Chat ein!");
								AmoutListIn.add(e.getPlayer().getName());
								int schduler = pl
										.getServer()
										.getScheduler()
										.scheduleSyncDelayedTask(pl,
												new Runnable() {

													@Override
													public void run() {
														AmoutListIn.remove(e
																.getPlayer()
																.getName());
														e.getPlayer()
																.sendMessage(
																		"�8[�3BankPlugin�8] �4Ab jetzt kannst den Betrag nichtmehr eingeben!");

													}

												}, 1200L);
							}
						} else {
							e.getPlayer().sendMessage("�8[�3BankPlugin�8] �4Bitte gib den Betrag ein!");
						}

					} else {
						e.getPlayer()
								.sendMessage(
										"�8[�3BankPlugin�8] �4Du hast keine Permission f�r diese Aktion");
					}
				}
			}
		}
	}
}
