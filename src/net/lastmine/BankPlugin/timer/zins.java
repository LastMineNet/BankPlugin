package net.lastmine.BankPlugin.timer;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.lastmine.BankPlugin.Utils.Config;
import net.lastmine.BankPlugin.Utils.UserBank;

import org.bukkit.plugin.Plugin;

public class zins {

	public static Plugin pl = null;
	
	
	public static void ZinsTimer(){
		pl.getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable(){

			@Override
			public void run() {
				SimpleDateFormat time = new SimpleDateFormat("HH:mm");
				if(Config.FileConfiguration().getString("Config.Zins.addTime").equalsIgnoreCase(time.format(new Date()))){
				
					for(String s : UserBank.FileConfiguration().getConfigurationSection("Users").getKeys(false)){
						
						int amount = UserBank.FileConfiguration().getInt("Users."+s+".in");
						int zins = Config.FileConfiguration().getInt("Config.Zins.Value");
						String setting = String.valueOf(amount+(amount/zins));
						UserBank.FileConfiguration().set("Users."+s+".in", setting);
						UserBank.SaveConfig();
					}
				}
				
			}
			
		}, 1200L, 1200L);

	}
}
