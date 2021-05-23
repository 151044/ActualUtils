package com.freemyip.nopersonalinfo.discord.commands;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Commands {
    private Commands(){
        throw new AssertionError();
    }
    private static Map<String,Command> commandMap;
    private static Map<List<String>,String> aliasMap;
    static{
        commandMap = new HashMap<>();
        aliasMap = commandMap.values().stream().collect(Collectors.toMap(cmd -> cmd.alias(),cmd -> cmd.callName()));
    }
    public static Optional<Command> tryGet(String byName){
        if(commandMap.containsKey(byName)){
            return Optional.of(commandMap.get(byName));
        }
        if(aliasMap.keySet().stream().flatMap(list -> list.stream()).anyMatch(str -> str.equals(byName))){
            return Optional.of(commandMap.get(aliasMap.entrySet().stream().filter(ent -> ent.getKey().contains(byName)).findFirst().map(ent -> ent.getValue()).get()));
        }else{
            return Optional.empty();
        }
    }
    public static void sendMessage(GuildMessageReceivedEvent evt, String msg){
        sendMessage(evt.getChannel(),msg);
    }
    public static void sendMessage(TextChannel text, String msg){
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
    public static void sendMessage(TextChannel text, MessageEmbed toSend){
        text.sendMessage(toSend).queue();
    }
    public static void sendMessage(GuildMessageReceivedEvent evt, MessageEmbed toSend){
        sendMessage(evt.getChannel(),toSend);
    }
    public static Optional<Emote> getEmote(String name, Guild retrieve){
        return retrieve.getEmotesByName(name,true).stream().findFirst();
    }
    public static String toUnicode(char c){
        if (Character.isDigit(c)) {
            return "U+00" + Long.toHexString((int) c);
        }else {
            return "U+1F1" + Long.toHexString(((int) c) - 97 + 0xE6);
        }
    }
    public static List<String> toUnicode(String split){
        return split.toLowerCase().chars().mapToObj(i -> toUnicode((char) i)).collect(Collectors.toList());
    }
}
