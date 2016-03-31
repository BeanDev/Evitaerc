package us.evitaerc.dev.utils;

import java.util.List;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

import us.evitaerc.dev.Evitaerc;
import us.evitaerc.dev.utils.apis.API;

public class EventHandler implements Listener
{
	///////////////
	Evitaerc plugin = Evitaerc.plugin;
	///////////////
	
	@org.bukkit.event.EventHandler
	public void onLogin(PlayerLoginEvent event)
	{
		if(event.getResult().equals(Result.KICK_FULL) && event.getPlayer().hasPermission("evitaerc.join.full"))
		{
			event.setResult(Result.ALLOWED);
		}
	}
	
	@org.bukkit.event.EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		event.setJoinMessage(null);
		List<String> JoinMsg = API.getConfig(API.getConfigFile("formats.yml")).getStringList("join");
		API.broadcastListMsg(API.formatListMsg(JoinMsg, "{NAME}", event.getPlayer().getDisplayName()));
	}
	
	@org.bukkit.event.EventHandler
	public void onQuit(PlayerQuitEvent event)
	{
		event.setQuitMessage(null);
		List<String> QuitMsg = API.getConfig(API.getConfigFile("formats.yml")).getStringList("quit");
		API.broadcastListMsg(API.formatListMsg(QuitMsg, "{NAME}", event.getPlayer().getDisplayName()));
	}
}
