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

/**
 * Created by Mitch on 1/5/2017.
 */

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.CommandBlockSource;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;


public class PayRespectsCommand implements CommandExecutor{
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        //Player pays respects
        if(src instanceof Player) {
            Player player = (Player) src;
            Sponge.getServer().getBroadcastChannel().send(Text.of(player.getName() + " pays their respects."));
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
