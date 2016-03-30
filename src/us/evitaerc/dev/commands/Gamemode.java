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
		/* This is where it gets messy
		 * Getting the:
		 * A) Multi-line configuration for the Gamemode message
		 * and
		 * B) Multi-line configuration for the Error message
		*/
		List<String> gamemode = instance.getConfig().getStringList("gamemode");
		List<String> error = instance.getConfig().getStringList("error");
		// If the sender isn't a player...
		if(__sender instanceof Player != true)
		{
			// Preventing memory leaks by sending the console a message.
			// If a command block were to do this returning in a string format could produce memory leaks.
			if(__sender instanceof ColouredConsoleSender || __sender instanceof ConsoleCommandSender)
			{
				for(int i = 0; i < error.size(); i++)
				{
					__sender.sendMessage(ChatColor.translateAlternateColorCodes('&', error.get(i).replace("{>}", "»").replace("{ERROR}", "You must be a player to preform this command!")));
				}
			}
			return true;
		}
		// Otherwise if the player has the permission...
		else if(__sender.hasPermission("evitaerc.gamemode"))
		{
			// Setting up the "player" variable for later use
			Player player = (Player) __sender;
			// ADVENTURE COMMAND
			if(__command.getName().equalsIgnoreCase("gma"))
			{
				// Setting the gamemode and sending the gamemode message
				player.setGameMode(GameMode.ADVENTURE);
				for(int i = 0; i < gamemode.size(); i++)
				{
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', gamemode.get(i).replace("{>}", "»").replace("{MODE}", player.getGameMode().toString().toLowerCase())));
				}
			}
			// CREATIVE COMMAND
			if(__command.getName().equalsIgnoreCase("gmc"))
			{
				// Setting the gamemode and sending the gamemode message
				player.setGameMode(GameMode.CREATIVE);
				for(int i = 0; i < gamemode.size(); i++)
				{
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', gamemode.get(i).replace("{>}", "»").replace("{MODE}", player.getGameMode().toString().toLowerCase())));
				}
			}
			// SURVIVAL COMMAND
			if(__command.getName().equalsIgnoreCase("gms"))
			{
				// Setting the gamemode and sending the gamemode message
				player.setGameMode(GameMode.SURVIVAL);
				for(int i = 0; i < gamemode.size(); i++)
				{
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', gamemode.get(i).replace("{>}", "»").replace("{MODE}", player.getGameMode().toString().toLowerCase())));
				}
			}
			// SPECTATOR COMMAND
			if(__command.getName().equalsIgnoreCase("gmsp"))
			{
				// Setting the gamemode and sending the gamemode message
				player.setGameMode(GameMode.SPECTATOR);
				for(int i = 0; i < gamemode.size(); i++)
				{
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', gamemode.get(i).replace("{>}", "»").replace("{MODE}", player.getGameMode().toString().toLowerCase())));
				}
			}
			// The messiest part of them all...
			if(__command.getName().equalsIgnoreCase("gm") || __command.getName().equalsIgnoreCase("gamemode"))
			{
				// Sending the syntax message if the player doesn't have the correct amount of arguements
				if(__args.length < 1)
				{
					for(int i = 0; i < error.size(); i++)
					{
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', error.get(i).replace("{>}", "»").replace("{ERROR}", "Syntax: /gm [player] <gamemode>")));
					}
				}
				else
				{
					// If the player puts in too many arguements? No one cares.
					if(__args.length >= 2)
					{
						// Checking if they have their player name correct; They could also enter a partial name
						if(Bukkit.getPlayer(__args[0]).isOnline())
						{
							// Error messages with "U GAMMOD NUT CORRUT"
							if(!__args[1].equalsIgnoreCase("adventure") && !__args[1].equalsIgnoreCase("creative") && !__args[1].equalsIgnoreCase("survival") && !__args[1].equalsIgnoreCase("spectator"))
							{
								for(int i = 0; i < error.size(); i++)
								{
									player.sendMessage(ChatColor.translateAlternateColorCodes('&', error.get(i).replace("{>}", "»").replace("{ERROR}", __args[1] + " is not a valid gamemode!")));
								}
							}
							else
							{
								// Setting the specified player's gamemode and sending them a message you did
								player.setGameMode(GameMode.valueOf(__args[1]));
								for(int i = 0; i < gamemode.size(); i++)
								{
									player.sendMessage(ChatColor.translateAlternateColorCodes('&', gamemode.get(i).replace("{>}", "»").replace("{MODE}", player.getGameMode().toString().toLowerCase())));
								}
							}
						}
						else
						{
							// The player in the first arguement isn't online... ERROR
							for(int i = 0; i < error.size(); i++)
							{
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', error.get(i).replace("{>}", "»").replace("{ERROR}", __args[0] + " is not online!")));
							}
						}
					}
					// If there is only one arguement
					else if((__args[0].equalsIgnoreCase("adventure") || __args[0].equalsIgnoreCase("creative") || __args[0].equalsIgnoreCase("survival") || __args[0].equalsIgnoreCase("spectator")) && __args.length == 1)
					{
						// Setting the gamemode and sending the gamemode message
						player.setGameMode(GameMode.valueOf(__args[0]));
						for(int i = 0; i < gamemode.size(); i++)
						{
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', gamemode.get(i).replace("{>}", "»").replace("{MODE}", player.getGameMode().toString().toLowerCase())));
						}
					}
					else
					{
						// Error messages with "U GAMMOD NUT CORRUT"
						for(int i = 0; i < error.size(); i++)
						{
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', error.get(i).replace("{>}", "»").replace("{ERROR}", __args[1] + " is not a valid gamemode!")));
						}
					}
				}
			}
		}
		else
		{
			// No permissions for command
			Player player = (Player) __sender;
			for(int i = 0; i < error.size(); i++)
			{
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', error.get(i).replace("{>}", "»").replace("{ERROR}", "You do not have permission to use this command!")));
			}
		}
		return true;
	}
}
