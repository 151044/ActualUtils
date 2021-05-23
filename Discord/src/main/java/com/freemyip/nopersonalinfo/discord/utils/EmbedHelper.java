package com.freemyip.nopersonalinfo.discord.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for creating embeds.
 */
public class EmbedHelper {
    private static String author = "Default";

    private EmbedHelper(){
        throw new AssertionError();
    }

    /**
     * Sets the author of the embeds created.
     * @param author The author string to set
     */
    public static void setAuthor(String author){
        EmbedHelper.author = author;
    }

    /**
     * Gets the author of the embeds created.
     * @return The author string
     */
    public static String getAuthor() {
        return author;
    }

    /**
     * Creates an embed with the specified description and title.
     * @param desc The description to set
     * @param title The title to set
     * @return The created embed
     */
    public static MessageEmbed getEmbed(String desc, String title){
        return new EmbedBuilder().setAuthor(author).setColor(Color.CYAN).setDescription(desc).setTitle(title).build();
    }

    /**
     * Creates one or more embeds with a given input string.
     * If {@param desc} is too long such that it cannot be sent in one message, it will be split up onto multiple embeds.
     * @param desc The description to send
     * @param title The title to send
     * @return The created embed(s)
     */
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
}
