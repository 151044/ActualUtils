package com.freemyip.nopersonalinfo.discord.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EmbedHelper {
    private static String author = "Default";

    private EmbedHelper(){
        throw new AssertionError();
    }
    public static void setAuthor(String author){
        EmbedHelper.author = author;
    }

    public static String getAuthor() {
        return author;
    }

    public static MessageEmbed getEmbed(String desc, String title){
        return new EmbedBuilder().setAuthor(author).setColor(Color.CYAN).setDescription(desc).setTitle(title).build();
    }
    public static List<MessageEmbed> getLongEmbed(String desc,String title){
        List<MessageEmbed> ret = new ArrayList<>();
        boolean isFirst = true;
        while(desc.length() > 1980){
            String copy = desc.substring(0,desc.lastIndexOf("\n",1960));
            desc = desc.substring(desc.lastIndexOf("\n",1960) + 1);
            ret.add(new EmbedBuilder().setAuthor(author).setColor(Color.CYAN).setDescription(copy).setTitle(isFirst ? title : title + "(Continued)").build());
            if(isFirst){
                isFirst = false;
            }
        }
        ret.add(new EmbedBuilder().setAuthor(author).setColor(Color.CYAN).setDescription(desc).setTitle(isFirst ? title : title + "(Continued)").build());
        return ret;
    }
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
