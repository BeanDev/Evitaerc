package us.evitaerc.dev.cmds;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_8_R3.command.ColouredConsoleSender;
import org.bukkit.entity.Player;

import us.evitaerc.dev.Evitaerc;
import us.evitaerc.dev.utils.apis.API;

public class Gamemode implements CommandExecutor
{
	Evitaerc plugin = Evitaerc.plugin;
	///////////////////////
	//      Gamemode     //
	///////////////////////
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		/* This is where it gets messy
		 * Getting the:
		 * A) Multi-line configuration for the Gamemode message
		 * and
		 * B) Multi-line configuration for the Error message
		*/
		List<String> gamemode = API.getConfig(API.getConfigFile("formats.yml")).getStringList("gamemode");
		List<String> error = API.getConfig(API.getConfigFile("formats.yml")).getStringList("error");
		// If the sender isn't a player...
		if(sender instanceof Player != true)
		{
			// Preventing memory leaks by sending the console a message.
			// If a command block were to do this returning in a string format could produce memory leaks.
			if(sender instanceof ColouredConsoleSender || sender instanceof ConsoleCommandSender)
			{
				API.sendConsoleListMsg(API.formatListMsg(error, "{ERROR}", "You must be a player to preform this command!"), (ConsoleCommandSender) sender);
			}
			return true;
		}
		// Otherwise if the player has the permission...
		else if(sender.hasPermission("evitaerc.gamemode"))
		{
			// Setting up the "player" variable for later use
			Player player = (Player) sender;
			// ADVENTURE COMMAND
			if(command.getName().equalsIgnoreCase("gma"))
			{
				// Setting the gamemode and sending the gamemode message
				player.setGameMode(GameMode.ADVENTURE);
				API.sendPlayerListMsg(API.formatListMsg(gamemode, "{MODE}", player.getGameMode().toString().toLowerCase()), player);
			}
			// CREATIVE COMMAND
			if(command.getName().equalsIgnoreCase("gmc"))
			{
				// Setting the gamemode and sending the gamemode message
				player.setGameMode(GameMode.CREATIVE);
				API.sendPlayerListMsg(API.formatListMsg(gamemode, "{MODE}", player.getGameMode().toString().toLowerCase()), player);
			}
			// SURVIVAL COMMAND
			if(command.getName().equalsIgnoreCase("gms"))
			{
				// Setting the gamemode and sending the gamemode message
				player.setGameMode(GameMode.SURVIVAL);
				API.sendPlayerListMsg(API.formatListMsg(gamemode, "{MODE}", player.getGameMode().toString().toLowerCase()), player);
			}
			// SPECTATOR COMMAND
			if(command.getName().equalsIgnoreCase("gmsp"))
			{
				// Setting the gamemode and sending the gamemode message
				player.setGameMode(GameMode.SPECTATOR);
				API.sendPlayerListMsg(API.formatListMsg(gamemode, "{MODE}", player.getGameMode().toString().toLowerCase()), player);
			}
			// The messiest part of them all...
			if(command.getName().equalsIgnoreCase("gm") || command.getName().equalsIgnoreCase("gamemode"))
			{
				// Sending the syntax message if the player doesn't have the correct amount of arguments
				if(args.length < 1)
				{
					API.sendPlayerListMsg(API.formatListMsg(error, "{ERROR}", "Syntax: /gm [player] <gamemode>"), player);
				}
				else
				{
					// If the player puts in too many arguments? No one cares.
					if(args.length >= 2)
					{
						// Checking if they have their player name correct; They could also enter a partial name
						if(Bukkit.getPlayer(args[0]).isOnline())
						{
							// Error messages with "U GAMMOD NUT CORRUT"
							if(!args[1].equalsIgnoreCase("adventure") && !args[1].equalsIgnoreCase("creative") && !args[1].equalsIgnoreCase("survival") && !args[1].equalsIgnoreCase("spectator"))
							{
								API.sendPlayerListMsg(API.formatListMsg(error, "{ERROR}", args[1] + " is not a valid gamemode!"), player);
							}
							else
							{
								// Setting the specified player's gamemode and sending them a message you did
								player.setGameMode(GameMode.valueOf(args[1]));
								API.sendPlayerListMsg(API.formatListMsg(gamemode, "{MODE}", player.getGameMode().toString().toLowerCase()), player);
							}
						}
						else
						{
							// The player in the first argument isn't online... ERROR
							API.sendPlayerListMsg(API.formatListMsg(error, "{ERROR}", args[0] + " is not online!"), player);
						}
					}
					// If there is only one argument
					else if((args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("spectator")) && args.length == 1)
					{
						// Setting the gamemode and sending the gamemode message
						player.setGameMode(GameMode.valueOf(args[0]));
						API.sendPlayerListMsg(API.formatListMsg(gamemode, "{MODE}", player.getGameMode().toString().toLowerCase()), player);
					}
					else
					{
						// Error messages with "U GAMMOD NUT CORRUT"
						API.sendPlayerListMsg(API.formatListMsg(error, "{ERROR}", args[1] + " is not a valid gamemode!"), player);
					}
				}
			}
		}
		else
		{
			// No permissions for command
			Player player = (Player) sender;
			API.sendPlayerListMsg(API.formatListMsg(error, "{ERROR}", "You do not have permission to use this command!"), player);
		}
		return true;
	}
}
