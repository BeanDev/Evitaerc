package us.evitaerc.dev;

import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import us.evitaerc.dev.utils.EventHandler;
import us.evitaerc.dev.utils.apis.API;

public class Evitaerc extends JavaPlugin
{
	public static Evitaerc plugin;
		
	public void onEnable()
	{
		// Preventing memory leaks
		plugin = this;
		// Getting and saving config
		API.saveDefaultConfig(API.getConfigFile("config.yml"));
		API.saveDefaultConfig(API.getConfigFile("formats.yml"));
		
		// Registering Events
		plugin.getServer().getPluginManager().registerEvents(new EventHandler(), this);
		
		// Making auto-broadcaster
		plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
		{
			int i = 0;
			public void run()
			{
				i = i + 1;
				List<String> messages = API.getConfig(API.getConfigFile("config.yml")).getStringList("broadcast.messages");
				List<String> format = API.getConfig(API.getConfigFile("formats.yml")).getStringList("broadcast");
				
				if(messages.size()-1 < i)
				{
					i = 0;
				}
				API.broadcastListMsg(API.formatListMsg(format, "{MESSAGE}", messages.get(i).toString()));
			}
		}, 0, API.getConfig(API.getConfigFile("config.yml")).getInt("broadcast.delay")*20);
	}
	
	public void onDisable()
	{
		// Preventing memory leaks
		plugin = null;
	}
}
