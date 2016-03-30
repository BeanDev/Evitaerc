package us.evitaerc.dev.utilities;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import us.evitaerc.dev.Evitaerc;

public class Events implements Listener
{
	Evitaerc instance = Evitaerc.instance;
	
	///////////////////////
	//       Events      //
	///////////////////////
	
	@EventHandler
	public void onJoin(PlayerJoinEvent __event)
	{
		// Removing join message
		// MAY CAUSE MEMORY LEAK
		__event.setJoinMessage(null);
		// Reloading config to use new messages if changed
		instance.reloadConfig();
		// Getting the List<String> from 'join' list in the config.yml
		List<String> join = instance.getConfig().getStringList("join");
		for(int i = 0; i < join.size(); i++)
		{
			// Broadcasting index of 'i' for 'join' in the config.yml
			instance.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', join.get(i).replace("{>}", "»").replace("{NAME}", __event.getPlayer().getDisplayName())));
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent __event)
	{
		// Removing quit message
		// MAY CAUSE MEMORY LEAK
		__event.setQuitMessage(null);
		// Reloading config to use new messages if changed
		instance.reloadConfig();
		// Getting the List<String> from 'quit' list in the config.yml
		List<String> quit = instance.getConfig().getStringList("quit");
		for(int i = 0; i < quit.size(); i++)
		{
			// Broadcasting index of 'i' for 'quit' in the config.yml
			instance.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', quit.get(i).replace("{>}", "»").replace("{NAME}", __event.getPlayer().getDisplayName())));
		}		
	}
}
