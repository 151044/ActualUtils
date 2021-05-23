package com.freemyip.nopersonalinfo.discord.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

/**
 * The interface to represent a command which can be triggered by a message in Discord.
 */
public interface Command {
    /**
     * The function that is called on a message sent by anyone in a guild.
     * @param evt The message received event
     */
    void action(GuildMessageReceivedEvent evt);

    /**
     * Returns a list of aliases with which this command can be called by a message.
     * @return The list of aliases
     */
    List<String> alias();

    /**
     * Gets the name with which this command can be invoked by a message.
     * @return The call name of this command
     */
    String callName();
}
