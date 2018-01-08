/*
 * Copyright (C) 2017  Skatem
 *
 * This file is part of PayRespects.
 *
 * PayRespects is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PayRespects is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PayRespects.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.skatem.payrespects;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.CommandBlockSource;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PayRespectsCommand implements CommandExecutor{
    private Map<UUID, Instant> playerMap = new HashMap<>();
    private int cooldown;
    public PayRespectsCommand(PayRespects pr) {
        cooldown = pr.getCooldown();
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        //Player pays respects
        if(src instanceof Player) {
            Player player = (Player) src;
            UUID playerUUID = player.getUniqueId();

            if (playerMap.containsKey(playerUUID)) {
                if (Duration.between(playerMap.get(playerUUID), Instant.now()).getSeconds() <= cooldown) {
                    player.sendMessage(Text.of(TextColors.DARK_GRAY, "Command on cooldown!"));
                } else {
                    Sponge.getServer().getBroadcastChannel().send(Text.of(player.getName() + " pays their respects."));
                    playerMap.put(playerUUID, Instant.now());
                }
            } else {
                Sponge.getServer().getBroadcastChannel().send(Text.of(player.getName() + " pays their respects."));
                playerMap.put(playerUUID, Instant.now());
            }

        }
        //Server pays respects
        else if(src instanceof ConsoleSource) {
            Sponge.getServer().getBroadcastChannel().send(Text.of("The Server pays its respects."));
        }
        //Command block pays respects
        else if(src instanceof CommandBlockSource) {
            Sponge.getServer().getBroadcastChannel().send(Text.of("A humble command block pays its respects."));
        }
        return CommandResult.success();
    }
}
