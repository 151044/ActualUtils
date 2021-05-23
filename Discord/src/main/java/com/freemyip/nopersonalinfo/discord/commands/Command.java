package com.freemyip.nopersonalinfo.discord.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public interface Command {
    void action(GuildMessageReceivedEvent evt);
    List<String> alias();
    String callName();
}
