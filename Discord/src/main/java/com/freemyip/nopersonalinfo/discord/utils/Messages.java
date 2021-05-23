package com.freemyip.nopersonalinfo.discord.utils;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A utility class for sending messages.
 */
public class Messages {
    /**
     * Queues a message to be sent.
     * This is a convenience method for {@link Messages#sendMessage(TextChannel, String)}.
     * @param evt The message received event, from which a text channel can be derived
     * @param msg The message to send
     */
    public static void sendMessage(GuildMessageReceivedEvent evt, String msg){
        sendMessage(evt.getChannel(),msg);
    }

    /**
     * Queues a message to be sent.
     * @param text The text channel to send to
     * @param msg The message to send
     */
    public static void sendMessage(TextChannel text, String msg){
        if(msg.isBlank()){
            return;
        }
        if(msg.length() > 1990){
            boolean isCode = false, isFirst = true;
            if(msg.endsWith("```")){
                isCode = true;
            }
            String op = msg;
            while(op.length() > 1980){
                StringBuilder out = new StringBuilder(op.substring(0,op.lastIndexOf("\n",1960)));
                if(isFirst){
                    isFirst = false;
                }else{
                    if(isCode) {
                        out = out.insert(0, "```java\n");
                    }
                }
                if(isCode){
                    text.sendMessage(out.append("```").toString()).queue();
                }
                op = op.substring(op.lastIndexOf("\n",1960) + 1);
            }
            text.sendMessage((isCode ? "```java\n" : "") + op).queue();
        }else {
            text.sendMessage(msg).queue();
        }
    }

    /**
     * Queues an embed to be sent.
     * @param text The message received event, from which a text channel can be derived
     * @param toSend The embed to send
     */
    public static void sendMessage(TextChannel text, MessageEmbed toSend){
        text.sendMessage(toSend).queue();
    }

    /**
     * Queues an embed to be sent.
     * This is a convenience method for {@link Messages#sendMessage(TextChannel, MessageEmbed)}.
     * @param evt The message received event, from which a text channel can be derived
     * @param toSend The embed to send
     */
    public static void sendMessage(GuildMessageReceivedEvent evt, MessageEmbed toSend){
        sendMessage(evt.getChannel(),toSend);
    }

    /**
     * Gets a emotes by its name for the specified guild.
     * @param name The name of the emote to retrieve
     * @param retrieve The guild to get the emote for
     * @return An optional containing the emote if it can be found, or an empty optional otherwise
     */
    public static Optional<Emote> getEmote(String name, Guild retrieve){
        return retrieve.getEmotesByName(name,true).stream().findFirst();
    }

    /**
     * Converts numbers and digits only to a suitable unicode for use in Discord.
     * @param c The character to convert
     * @return The unicode String
     */
    public static String toUnicode(char c){
        if (Character.isDigit(c)) {
            return "U+00" + Long.toHexString((int) c);
        }else {
            return "U+1F1" + Long.toHexString(((int) c) - 97 + 0xE6);
        }
    }

    /**
     * Converts numbers and digits only to a suitable unicode for use in Discord.
     * @param split The string to convert
     * @return The unicode String
     */
    public static List<String> toUnicode(String split){
        return split.toLowerCase().chars().mapToObj(i -> toUnicode((char) i)).collect(Collectors.toList());
    }

    /**
     * Converts a length of time, in milliseconds, to a string in the format of hh:mm:ss.
     * @param length The time period to use
     * @return The formatted time string
     */
    public static String toTime(long length){
        long seconds = length / 1000;
        long min = seconds / 60;
        long hour = min / 60;
        String sec = seconds % 60 < 10 ? "0" + seconds % 60 : seconds % 60 + "";
        String mins = min % 60 < 10 ? "0" + min % 60 : min % 60 + "";
        if(hour == 0){
            return min + ":" + sec;
        }else{
            return hour + ":" + mins + ":" + sec;
        }
    }
}
