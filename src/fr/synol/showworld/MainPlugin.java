package fr.synol.showworld;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MainPlugin extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);
		setWorldName();
		getServer().getConsoleSender().sendMessage("[§1ShowWorld§r] -> §aEnabled !");
	}
	
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("[§1ShowWorld§r] -> §4Disabled !");
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		setWorldName();
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		setWorldName();
	}
	@EventHandler
	public void onChangedWorld(PlayerChangedWorldEvent event) {
		setWorldName();
	}
	
	@EventHandler
	public void onCommandSend(PlayerCommandSendEvent event) {
		setWorldName();
	}
	@EventHandler
	public void onGameModeChange(PlayerGameModeChangeEvent event) {
		Player player = event.getPlayer();
		StringBuilder sb = new StringBuilder();
		if (getConfig().getBoolean("setting.showOp") && player.isOp()) {
			sb.append(noColor(getConfig().getString("setting.opPrefix")));
		}
		World world = player.getWorld();
		String worldColor = "";
		for (String str : getConfig().getConfigurationSection("worlds").getKeys(false)) {
			if (world.equals(Bukkit.getWorld(str))) {
				worldColor = getConfigColor("worlds."+str);
				break;
			}else {
				worldColor = getConfigColor("worlds.other");
			}
		}
		sb.append(worldColor+player.getName()+"§r");
		if (event.getNewGameMode() == GameMode.CREATIVE && getConfig().getBoolean("setting.showCreative")) {
			sb.append(noColor(getConfig().getString("setting.creativeSufix")));
		}
		player.setPlayerListName(sb.toString());
	}
	
	private void setWorldName() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			StringBuilder sb = new StringBuilder();
			if (getConfig().getBoolean("setting.showOp") && player.isOp()) {
				sb.append(noColor(getConfig().getString("setting.opPrefix")));
			}
			World world = player.getWorld();
			String worldColor = "";
			for (String str : getConfig().getConfigurationSection("worlds").getKeys(false)) {
				if (world.equals(Bukkit.getWorld(str))) {
					worldColor = getConfigColor("worlds."+str);
					break;
				}else {
					worldColor = getConfigColor("worlds.other");
				}
			}
			sb.append(worldColor+player.getName()+"§r");
			if (player.getGameMode() == GameMode.CREATIVE && getConfig().getBoolean("setting.showCreative")) {
				sb.append(noColor(getConfig().getString("setting.creativeSufix")));
			}
			player.setPlayerListName(sb.toString());
		}
	}
	
	private String noColor(String msg) {
		String finalMsg;
		if (msg.contains("%noColor%")) {
			finalMsg = msg.replace("%noColor%", "");
		}else {
			finalMsg = msg.replace("&", "§");
		}
		return finalMsg;
	}
	public static String ignoreColor(String msg) {
		return msg.replace("&4", "").replace("&c", "").replace("&6", "").replace("&e", "").replace("&2", "").replace("&a", "").replace("&b", "").replace("&3", "").replace("&1", "").replace("&9", "").replace("&d", "").replace("&5", "").replace("&f", "").replace("&7", "").replace("&8", "").replace("&0", "").replace("&k", "").replace("&l", "").replace("&m", "").replace("&n", "").replace("&o", "").replace("&r", "");
	}
	private String getConfigColor(String labelSection) {
		if (getConfig().getString(labelSection).equalsIgnoreCase("DARK_RED")) {
			return "§4";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("RED")) {
			return "§c";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("GOLD")) {
			return "§6";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("YELLOW")) {
			return "§e";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("DARK_GREEN")) {
			return "§2";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("GREEN")) {
			return "§a";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("AQUA")) {
			return "§b";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("DARK_AQUA")) {
			return "§3";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("BLUE")) {
			return "§1";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("DARK_BLUE")) {
			return "§9";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("PURPLE")) {
			return "§d";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("DARK_PURPLE")) {
			return "§5";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("WHITE")) {
			return "§f";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("GRAY")) {
			return "§7";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("DARK_GRAY")) {
			return "§8";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("BLACK")) {
			return "§0";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("MAGIC")) {
			return "§k";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("BOLD")) {
			return "§l";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("FRONTLINE")) { /// a revoir !!!
			return "§m";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("UNDERLINE")) {
			return "§n";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("ITALIC")) {
			return "§o";
		}else if (getConfig().getString(labelSection).equalsIgnoreCase("RESET")) {
			return "§p";
		}else {
			return "";
		}
	}
}