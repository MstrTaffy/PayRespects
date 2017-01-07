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

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

@Plugin(
        id = "payrespects",
        name = "PayRespects",
        version = "1.0.2",
        description = "Type /f to pay respects when a player dies.",
        authors = {
                "skatem"
        }
)
public class PayRespects {
    @Inject
    private Logger logger;

    //build command
    @Listener
    public void  OnGameInit(GameInitializationEvent event) {
        CommandSpec PayRespectsCommand = CommandSpec.builder()
                .description(Text.of("Pay Respects Command"))
                .permission("payrespects.commands.pr")
                .executor(new PayRespectsCommand())
                .build();
        //executes when player types '/f' or '/PayRespects'
        Sponge.getCommandManager().register(this, PayRespectsCommand, "f", "PayRespects");
    }
}