package us.evitaerc.dev.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import us.evitaerc.dev.Evitaerc;

public class Message implements CommandExecutor
{
	Evitaerc instance = Evitaerc.instance;
	///////////////////////
	//      Message      //
	///////////////////////	

	public boolean onCommand(CommandSender __sender, Command __command, String __label, String[] __args)
	{
		List<String> error = instance.getConfig().getStringList("error");
		if(__args.length < 2)
		{
			for(int i = 0; i < error.size(); i++)
			{
				__sender.sendMessage(ChatColor.translateAlternateColorCodes('&', error.get(i).replace("{>}", "»").replace("{ERROR}", "Syntax: /msg <player> <message>")));
			}
		}
		else
		{
			if(Bukkit.getPlayer(__args[0]).isOnline())
			{
				String sender = null;
				if(__sender instanceof Player == false)
				{
					sender = "Console";
				}
				else
				{
					Player player = (Player) __sender;
					sender = player.getDisplayName();
				}
				int length = __args.length;
				String message = "";
				for(int i = 1; i < length; i++)
				{
					message = message + " " + __args[i];
				}
				
				List<String> msg = instance.getConfig().getStringList("msg");
				List<String> msgSend = instance.getConfig().getStringList("msgSend");
				
				Player reciever = Bukkit.getPlayer(__args[0]);
				
				for(int i = 0; i < error.size(); i++)
				{
					reciever.sendMessage(ChatColor.translateAlternateColorCodes('&', msg.get(i).replace("{>}", "»").replace("{NAME}", sender).replace("{MESSAGE}", message)));
				}
				for(int i = 0; i < error.size(); i++)
				{
					__sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msgSend.get(i).replace("{>}", "»").replace("{NAME}", reciever.getDisplayName()).replace("{MESSAGE}", message)));
				}
			}
			else
			{
				for(int i = 0; i < error.size(); i++)
				{
					__sender.sendMessage(ChatColor.translateAlternateColorCodes('&', error.get(i).replace("{>}", "»").replace("{ERROR}", __args[0] + " is not Online!")));
				}
			}
		}
		return true;
	}
}
