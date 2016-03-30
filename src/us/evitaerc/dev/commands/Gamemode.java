package us.evitaerc.dev.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_8_R3.command.ColouredConsoleSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import us.evitaerc.dev.Evitaerc;

public class Gamemode implements CommandExecutor
{
	Evitaerc instance = Evitaerc.instance;
	///////////////////////
	//      Gamemode     //
	///////////////////////
	
	public boolean onCommand(CommandSender __sender, Command __command, String __label, String[] __args)
	{
		List<String> gamemode = instance.getConfig().getStringList("gamemode");
		List<String> error = instance.getConfig().getStringList("error");
		if(__sender instanceof Player != true)
		{
			if(__sender instanceof ColouredConsoleSender || __sender instanceof ConsoleCommandSender)
			{
				for(int i = 0; i < error.size(); i++)
				{
					__sender.sendMessage(ChatColor.translateAlternateColorCodes('&', error.get(i).replace("{>}", "»").replace("{ERROR}", "You must be a player to preform this command!")));
				}
			}
			return true;
		}
		else if(__sender.hasPermission("evitaerc.gamemode"))
		{
			Player player = (Player) __sender;
			if(__command.getName().equalsIgnoreCase("gma"))
			{
				player.setGameMode(GameMode.ADVENTURE);
				for(int i = 0; i < gamemode.size(); i++)
				{
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', gamemode.get(i).replace("{>}", "»").replace("{MODE}", player.getGameMode().toString().toLowerCase())));
				}
			}
			if(__command.getName().equalsIgnoreCase("gmc"))
			{
				player.setGameMode(GameMode.CREATIVE);
				for(int i = 0; i < gamemode.size(); i++)
				{
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', gamemode.get(i).replace("{>}", "»").replace("{MODE}", player.getGameMode().toString().toLowerCase())));
				}
			}
			if(__command.getName().equalsIgnoreCase("gms"))
			{
				player.setGameMode(GameMode.SURVIVAL);
				for(int i = 0; i < gamemode.size(); i++)
				{
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', gamemode.get(i).replace("{>}", "»").replace("{MODE}", player.getGameMode().toString().toLowerCase())));
				}
			}
			if(__command.getName().equalsIgnoreCase("gmsp"))
			{
				player.setGameMode(GameMode.SPECTATOR);
				for(int i = 0; i < gamemode.size(); i++)
				{
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', gamemode.get(i).replace("{>}", "»").replace("{MODE}", player.getGameMode().toString().toLowerCase())));
				}
			}
			if(__command.getName().equalsIgnoreCase("gm") || __command.getName().equalsIgnoreCase("gamemode"))
			{
				if(__args.length < 1)
				{
					for(int i = 0; i < error.size(); i++)
					{
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', error.get(i).replace("{>}", "»").replace("{ERROR}", "Syntax: /gm [player] <gamemode>")));
					}
				}
				else
				{
					if(__args.length >= 2)
					{
						if(Bukkit.getPlayer(__args[0]).isOnline())
						{
							if(!__args[1].equalsIgnoreCase("adventure") && !__args[1].equalsIgnoreCase("creative") && !__args[1].equalsIgnoreCase("survival") && !__args[1].equalsIgnoreCase("spectator"))
							{
								for(int i = 0; i < error.size(); i++)
								{
									player.sendMessage(ChatColor.translateAlternateColorCodes('&', error.get(i).replace("{>}", "»").replace("{ERROR}", __args[1] + " is not a valid gamemode!")));
								}
							}
							else
							{
								player.setGameMode(GameMode.valueOf(__args[1]));
								for(int i = 0; i < gamemode.size(); i++)
								{
									player.sendMessage(ChatColor.translateAlternateColorCodes('&', gamemode.get(i).replace("{>}", "»").replace("{MODE}", player.getGameMode().toString().toLowerCase())));
								}
							}
						}
						else
						{
							for(int i = 0; i < error.size(); i++)
							{
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', error.get(i).replace("{>}", "»").replace("{ERROR}", __args[0] + " is not online!")));
							}
						}
					}
					else if(!__args[0].equalsIgnoreCase("adventure") && !__args[0].equalsIgnoreCase("creative") && !__args[0].equalsIgnoreCase("survival") && !__args[0].equalsIgnoreCase("spectator"))
					{
						player.setGameMode(GameMode.valueOf(__args[0]));
						for(int i = 0; i < gamemode.size(); i++)
						{
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', gamemode.get(i).replace("{>}", "»").replace("{MODE}", player.getGameMode().toString().toLowerCase())));
						}
					}
				}
			}
		}
		else
		{
			Player player = (Player) __sender;
			for(int i = 0; i < error.size(); i++)
			{
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', error.get(i).replace("{>}", "»").replace("{ERROR}", "You do not have permission to use this command!")));
			}
		}
		return true;
	}
}
