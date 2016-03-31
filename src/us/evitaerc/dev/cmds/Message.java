package us.evitaerc.dev.cmds;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_8_R3.command.ColouredConsoleSender;
import org.bukkit.entity.Player;

import us.evitaerc.dev.Evitaerc;
import us.evitaerc.dev.utils.apis.API;

public class Message implements CommandExecutor
{
	Evitaerc plugin = Evitaerc.plugin;
	///////////////////////
	//      Message      //
	///////////////////////	

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		List<String> error = API.getConfig(API.getConfigFile("formats.yml")).getStringList("error");
		if(!(sender instanceof Player))
		{
			// Preventing memory leaks by sending the console a message.
			// If a command block were to do this returning in a string format could produce memory leaks.
			if(sender instanceof ColouredConsoleSender || sender instanceof ConsoleCommandSender)
			{
				API.sendConsoleListMsg(API.formatListMsg(error, "{ERROR}", "You must be a player to preform this command!"), (ConsoleCommandSender) sender);
			}
			return true;
		}
		if(args.length < 2)
		{
			API.sendPlayerListMsg(API.formatListMsg(error, "{ERROR}", "Syntax: /msg <player> <message>"), (Player) sender);
		}
		else
		{
			if(Bukkit.getPlayer(args[0]).isOnline())
			{
				String from = null;
				Player player = null;
				
				player = (Player) sender;
				from = player.getDisplayName();
				
				int length = args.length;
				String message = "";
				for(int i = 1; i < length; i++)
				{
					message = message + " " + args[i];
				}
				
				List<String> msgTo = API.getConfig(API.getConfigFile("formats.yml")).getStringList("msgTo");
				List<String> msgFrom = API.getConfig(API.getConfigFile("formats.yml")).getStringList("msgFrom");
				
				Player to = Bukkit.getPlayer(args[0]);
				
				API.sendPlayerListMsg(API.formatListMsg(API.formatListMsg(msgTo, "{MESSAGE}", message), "{NAME}", to.getDisplayName()), player);
				API.sendPlayerListMsg(API.formatListMsg(API.formatListMsg(msgFrom, "{MESSAGE}", message), "{NAME}", from), to);
			}
			else
			{
				API.sendPlayerListMsg(API.formatListMsg(error, "{ERROR}", args[0] + " is not Online!"), (Player) sender);
			}
		}
		return true;
	}
}
