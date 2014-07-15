package pl.website.bcsn.customipbanmessage;

import java.util.GregorianCalendar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class Listeners implements Listener{
	
	public CIPBM instance;
	public GregorianCalendar gc;
	
	public void init(){
		instance = CIPBM.instance;
		gc = new GregorianCalendar();
	}
	
	@EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
	public void onServerListPing(ServerListPingEvent slpe){
		String msg = CIPBM.MOTD_FOR_BANNED_IP;
		msg = msg.replaceAll("@ip", slpe.getAddress().getHostAddress());
		msg = cmsg(msg);
		
		
		if(Bukkit.getIPBans().contains(slpe.getAddress().getHostAddress())){
			slpe.setMotd(msg);
			slpe.setMaxPlayers(-1);
		}
		
	}
	

	public String cmsg(String in){
		return ChatColor.translateAlternateColorCodes('&', in);
	}
}
