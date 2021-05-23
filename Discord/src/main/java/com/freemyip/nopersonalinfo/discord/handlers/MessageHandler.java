package com.freemyip.nopersonalinfo.discord.handlers;

import com.freemyip.nopersonalinfo.discord.commands.Command;
import com.freemyip.nopersonalinfo.discord.commands.CommandList;
import com.freemyip.nopersonalinfo.discord.utils.Messages;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * The handler for guild messages.
 */
public class MessageHandler extends ListenerAdapter {
    private String prefix;
    private CommandList list;

    /**
     * Constructs a new MessageHandler with the specified prefix to check for and the specified command list.
     * @param prefix The prefix to check messages for commands
     * @param list The command list to use
     */
    public MessageHandler(String prefix, CommandList list){
        this.prefix = prefix;
        this.list = list;
    }
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(!msg.startsWith(prefix)){
            return;
        }
        String first = msg.substring(1, !msg.contains("\n") ? msg.length() : msg.indexOf("\n")).split(" ")[0];
        Optional<Command> get = list.tryGet(first);
        if(get.isEmpty()){
            Messages.sendMessage(event,"Cannot find this command! Sorry!");
            return;
        }
        get.get().action(event);
    }
}

