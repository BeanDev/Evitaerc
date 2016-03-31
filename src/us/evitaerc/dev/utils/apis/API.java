package us.evitaerc.dev.utils.apis;

import java.io.File;
import java.util.List;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import us.evitaerc.dev.Evitaerc;

public class API
{
	///////////////
	static Evitaerc plugin = Evitaerc.plugin;
	///////////////
	
	public static File getConfigFile(String name)
	{
		File config = new File(plugin.getDataFolder(), name);
		saveDefaultConfig(config);
		return config;
	}
	
	public static FileConfiguration getConfig(File config)
	{
		saveDefaultConfig(config);
		FileConfiguration fileConfig;
		fileConfig = YamlConfiguration.loadConfiguration(config);
		return fileConfig;
	}
	
	public FileConfiguration reloadConfig(File config)
	{
		saveDefaultConfig(config);
		FileConfiguration fileConfig;
		fileConfig = YamlConfiguration.loadConfiguration(config);
		return fileConfig;
	}
	
	public static void saveDefaultConfig(File config)
	{
		if(!config.exists())
		{
			plugin.saveResource(config.getName(), false);
		}
	}
	
	///////////////
	
	public static String colorMsg(String message)
	{
		message = ChatColor.translateAlternateColorCodes('&', message);
		return message;
	}
	
	public static List<String> formatListMsg(List<String> messages, String replace, String replaceWith)
	{
		List<String> messagesFinal = messages;
		for(int i = 0; i < messages.size(); i++)
		{
			messagesFinal.set(i, ChatColor.translateAlternateColorCodes('&', messages.get(i).replace(replace, replaceWith)));
		}
		
		return messagesFinal;
	}
	
	public static void broadcastListMsg(List<String> messages)
	{
		for(int i = 0; i < messages.size(); i++)
		{
			plugin.getServer().broadcastMessage(messages.get(i));
		}
	}
	
	public static void sendConsoleListMsg(List<String> messages, ConsoleCommandSender console)
	{
		for(int i = 0; i < messages.size(); i++)
		{
			console.sendMessage(messages.get(i));
		}
	}
	
	public static void sendPlayerListMsg(List<String> messages, Player player)
	{
		for(int i = 0; i < messages.size(); i++)
		{
			player.sendMessage(messages.get(i));
		}
	}
	
	public static void sendPlayersListMsg(List<String> messages, List<Player> players)
	{
		for(int i = 0; i < players.size(); i++)
		{
			sendPlayerListMsg(messages, players.get(i));
		}
	}
	
	///////////////
}
