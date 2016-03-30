package us.evitaerc.dev;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import us.evitaerc.dev.commands.Gamemode;
import us.evitaerc.dev.commands.Message;
import us.evitaerc.dev.utilities.Events;

public class Evitaerc extends JavaPlugin
{
	///////////////////////
	//   Enable/Disable  //
	///////////////////////
	
	public static Evitaerc instance;
	
	public void onEnable()
	{
		// Preventing memory leaks.
		instance = this;
		
		// Creating 'config.yml'
		instance.saveDefaultConfig();
		
		// Registering Events
		instance.getServer().getPluginManager().registerEvents(new Events(), this);
		
		// Registering Commands
		instance.getCommand("gamemode").setExecutor(new Gamemode());
		instance.getCommand("gm").setExecutor(new Gamemode());
		instance.getCommand("gma").setExecutor(new Gamemode());
		instance.getCommand("gmc").setExecutor(new Gamemode());
		instance.getCommand("gms").setExecutor(new Gamemode());
		instance.getCommand("gmsp").setExecutor(new Gamemode());
		instance.getCommand("msg").setExecutor(new Message());
		instance.getCommand("tell").setExecutor(new Message());
		
		// Create auto-broadcast run
		instance.getServer().getScheduler().scheduleSyncRepeatingTask(instance, new Runnable()
		{
			List<String> messages = instance.getConfig().getStringList("messages");
			int i0 = 0;
			public void run()
			{
				i0 = i0 + 1;
				if(i0 > messages.size())
				{
					i0 = 0;
				}
				List<String> format = instance.getConfig().getStringList("broadcast");
				for(int i1 = 0; i1 < format.size(); i1++)
				{
					instance.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', format.get(i1).replace("{>}", "»").replace("{BROADCAST}", messages.get(i0))));
				}
			}
		}, 0, instance.getConfig().getInt("broadcastDelay")*20);
	}
	
	public void onDisable()
	{
		// Preventing memory leaks.
		instance = null;
	}
}
