package de.ghac.heightclear;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class HeightClear extends JavaPlugin implements Listener{
    @Override
    public void onDisable() {

        System.out.println("[" + this.getName()+"] Plugin deaktiviert!");

    }
    
    @Override
    public void onEnable() {
        System.out.println("[" + this.getName()+"] Plugin by ghac!");
        System.out.println("[" + this.getName()+"] Plugin aktiviert!");
        getServer().getPluginManager().registerEvents(this, this);
        getConfig().addDefault("main.low", 10);
        getConfig().addDefault("main.high", 100);
        getConfig().addDefault("worlds.world", "world");
        getConfig().options().copyDefaults(true);
        this.saveConfig();
}
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args){
    Player p = (Player) sender;
    p.sendMessage(ChatColor.GOLD + "[" + this.getName()+"]" + ChatColor.DARK_GREEN + " HeightClear Plugin by ghac. 2014.");
    return true;  
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerMove(final PlayerMoveEvent e){
        Player p = e.getPlayer();

        if((p.getLocation().getBlockY() > getConfig().getInt("main.high") || p.getLocation().getBlockY() < getConfig().getInt("main.low"))&& p.getLocation().getWorld().getName().equalsIgnoreCase(getConfig().getString("worlds.world"))){
            clearInv(p);
        }
    }
    
    public void clearInv(Player p){
        PlayerInventory inv = p.getInventory();
        inv.clear();
    }
}
