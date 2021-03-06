package net.lastmine.BankPlugin;

import net.lastmine.BankPlugin.CMD.CMD_bank;
import net.lastmine.BankPlugin.Utils.Config;
import net.lastmine.BankPlugin.Utils.Signs;
import net.lastmine.BankPlugin.Utils.UserBank;
import net.lastmine.BankPlugin.listener.BlockBreak;
import net.lastmine.BankPlugin.listener.BlockPlace;
import net.lastmine.BankPlugin.listener.PlayerChat;
import net.lastmine.BankPlugin.listener.PlayerInteract;
import net.lastmine.BankPlugin.listener.SignChange;
import net.lastmine.BankPlugin.timer.zins;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{
	
	public void onEnable(){
		getCommand("bank").setExecutor(new CMD_bank(this));
		getServer().getPluginManager().registerEvents(new SignChange(), this);
		getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
		getServer().getPluginManager().registerEvents(new PlayerChat(), this);
		getServer().getPluginManager().registerEvents(new BlockBreak(), this);
		getServer().getPluginManager().registerEvents(new BlockPlace(), this);
		System.out.println("[BankPlugin] Plugin by LastMine.net - Hosting and Coding Service");
		System.out.println("[BankPlugin] This plugin is developed by 'Andreelor'");
		Config.pl = this;
		UserBank.pl = this;
		Signs.pl = this;
		PlayerInteract.pl = this;
		PlayerChat.pl = this;
		zins.pl = this;
		Config.ReLoadConfig();
		UserBank.ReLoadConfig();
		Signs.ReLoadConfig();
		Config.FileConfiguration().addDefault("Config.Zins.addTime", "12:00");
		Config.FileConfiguration().addDefault("Config.Zins.Value", 10);
		Config.FileConfiguration().options().copyDefaults(true);
		Config.SaveConfig();
		setupEconomy();
		zins.ZinsTimer();

	}
	public void onDisable(){
		System.out.println("[BankPlugin] Disabling the Plugin!");
	}
	
	public static Economy economy = null;
    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
	

}
