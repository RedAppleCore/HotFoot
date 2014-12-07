package com.jam2400.hotfoot.threads;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.jam2400.hotfoot.handlers.Game;
import com.jam2400.hotfoot.utils.ChatUtils;
import com.jam2400.hotfoot.utils.GameState;
import com.jam2400.hotfoot.utils.PlayerUtils;

public class Countdown implements Runnable {
private static int timeUntilStart;
    
    @Override
    public void run() {
        while (true) {
            if (GameState.isState(GameState.IN_LOBBY)) {
            
                if (Game.canStart() == true) {
                    ChatUtils.broadcast("Minimum players reached! Countdown starting!");
                    timeUntilStart = 20;
                    for (; timeUntilStart >= 0; timeUntilStart--) {
                        
                    	PlayerUtils.setAllLevel(timeUntilStart);
                    	
                    	if(!Game.canStart()){
                            ChatUtils.broadcast("The countdown stopped because there weren't enough players.");
                            break;
                        }
                        
                        if (timeUntilStart == 0) {
                            Game.start();
                            GameState.setState(GameState.IN_GAME);
                            break;
                        }
                        if (timeUntilStart % 10 == 0 || timeUntilStart < 10) {
                            ChatUtils.broadcast("There are " + ChatColor.YELLOW + timeUntilStart + ChatColor.WHITE + 
                            		" seconds until the game starts!");
                        }

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Bukkit.shutdown();
                        }
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Bukkit.shutdown();
                }
            }
        }
    }
}
