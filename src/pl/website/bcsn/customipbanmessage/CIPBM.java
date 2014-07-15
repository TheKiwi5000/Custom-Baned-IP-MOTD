package pl.website.bcsn.customipbanmessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CIPBM extends JavaPlugin {
	
	public static CIPBM instance;
	public static String MOTD_FOR_BANNED_IP = null;
	
	public static File configFile;
	public static FileConfiguration config;
	
	
	public void onEnable(){
		configFile = new File(getDataFolder(), "config.yml");
		if (!configFile.exists()) {
			configFile.getParentFile().mkdirs();
			copy(getResource("config.yml"), configFile);
		}
		
		config = new YamlConfiguration();
		try {
			config.load(configFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		MOTD_FOR_BANNED_IP = config.getString("message");
		
		instance = getInstance();
		
		Listeners l = new Listeners();
		l.init();
		getServer().getPluginManager().registerEvents(l, this);
	}
	
	
	
	
	
	public CIPBM getInstance(){
		return (CIPBM) Bukkit.getServer().getPluginManager().getPlugin(getName());
	}
	private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
