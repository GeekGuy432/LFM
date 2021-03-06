package net.camtech.fopmremastered.commands;

import static me.StevenLawson.BukkitTelnet.BukkitTelnet.plugin;
import net.camtech.fopmremastered.FOPMR_Bans;
import net.camtech.fopmremastered.FOPMR_Rank;
import net.camtech.fopmremastered.FOPMR_Rank.Rank;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Command_superdoom extends FOPMR_Command
{
    public Command_superdoom()
    {
        super("superdoom", "/superdoom <player> [reason]", "Super Doom! Super powerful ban a user.", Rank.SENIOR);
    }
    
    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args)
    {
        if (args.length < 1)
        {
            return false;
        }

        String name = args[0];
        Player player = FOPMR_Rank.getPlayer(args[0]);
                if(player.getName().equals("OxLemonxO") | player.getName().equals("DarkHorse108") | player.getName().equals("vj13573")) {
                Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "LFM" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_RED + sender.getName() + " has tried to doom the owners/developers of the owner.");
                return false;
            }
        String reason = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");
    
        if (player == null)
        {
            sender.sendMessage(ChatColor.GRAY + "Player not found!");
            return true;
        }
        
        Bukkit.broadcastMessage(ChatColor.RED + sender.getName() + " - Superdooming " + player.getName() + " to a never-ending oblivion in the deepest burning pits of Hell.");
        Bukkit.broadcastMessage(ChatColor.RED + sender.getName() + " - Starting a huge nope fest over " + player.getName());
        Bukkit.broadcastMessage(ChatColor.AQUA + sender.getName() + " - Chucking " + player.getName() + " left");
        Bukkit.broadcastMessage(ChatColor.AQUA + sender.getName() + " - Chucking " + player.getName() + " right");
        
        // create explosion
        player.getWorld().createExplosion(player.getLocation(), 4F);
        
        // strike lightning
        player.getWorld().strikeLightning(player.getLocation());
        
        // burn player
        player.setFireTicks(10000);
        
        // kill player (if not done already)
        player.setHealth(0.0);
        
        // burn player
        player.setFireTicks(10000);
        
        // shoot the player into the sky
        player.setVelocity(player.getVelocity().clone().add(new Vector(0, 90, 0)));
        
        // shoot the player left + up
        player.setVelocity(player.getVelocity().clone().add(new Vector(0, 90, 90)));
        
        // shoot the player right + up
        player.setVelocity(player.getVelocity().clone().add(new Vector(90, 90, 0)));
        
        
        // scheduler
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                for (Player pl : Bukkit.getOnlinePlayers())
                {
                    
                    // plays the sound 100 times
                    for (int i = 0; i < 100; i++)
                    {
                        pl.playSound(pl.getLocation(), Sound.AMBIENCE_THUNDER, 2, 2);
                    } 
                }

                Bukkit.broadcastMessage(ChatColor.AQUA + sender.getName() + " - Throwing " + player.getName() + " off the planet!");
                
                // create explosion
                player.getWorld().createExplosion(player.getLocation(), 4F);
                
                // strike lightning
                player.getWorld().strikeLightning(player.getLocation());
                
                // shoot the player into the sky
                player.setVelocity(player.getVelocity().clone().add(new Vector(0, 90, 0)));
                Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " shall be sent to mars!");
            }
        }.runTaskLater(plugin, 2L * 20L);
        
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                
                // player machat
                player.chat("No!");
                player.chat("Please " + sender.getName() + "! Don't do your final superdoom power!");
                
                // sender machat
                Player p = (Player) sender;
                p.chat("It's too late, good day sir.");
                
                Bukkit.broadcastMessage(ChatColor.DARK_RED + sender.getName() + " - Dumping " + player.getName() + " into a deep pit full of fire!");
                
                // burn player
                player.setFireTicks(10000);
                
                // ban player
                String finalreason = reason + "  §4PS: FUCKOFF, AND GET YOUR MOTHERFUCKING SHIT TOGETHER!";
                FOPMR_Bans.HandleLemonBan(player, finalreason, sender.getName());
                
            }
        }.runTaskLater(plugin, 4L * 20L);
                
        return true;
    }
}
