package net.lastmine.BankPlugin.Utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class UserBank {
	public static Plugin pl = null;
	private static File ConfigFile = null;
	private static FileConfiguration FileConfig = null;
	
	public static void ReLoadConfig(){
		if(ConfigFile == null){
			ConfigFile = new File(pl.getDataFolder()+"/Users.yml");
		}
		FileConfig = YamlConfiguration.loadConfiguration(ConfigFile);
	}
	public static FileConfiguration FileConfiguration(){
		return FileConfig;
	}
	public static void SaveConfig(){
		try {
			FileConfig.save(ConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
