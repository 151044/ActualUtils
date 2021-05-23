package com.freemyip.nopersonalinfo.discord.utils;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.LoginException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessagesTest {
    static JDA jda;
    static TextChannel botTest;
    static BlockingQueue<GuildMessageReceivedEvent> events = new ArrayBlockingQueue<>(30);
    @BeforeAll
    static void initJDA() throws InterruptedException, LoginException {
        jda = JDABuilder.createDefault(System.getenv("objectToken")).addEventListeners(
                new ListenerAdapter() {
                    @Override
                    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
                        events.offer(event);
                    }
                }
        ).build();
        jda.awaitReady();
        botTest = jda.getTextChannelById(678947657003040781L);
    }

    @Test
    void sendMessage() {
        Messages.sendMessage(botTest, "From getTextChannel test");
        Messages.sendMessage(botTest, "Stupendous Marginals");
        Messages.sendMessage(botTest,"Diminishing nuclear returns");
    }

    @Test
    void sendMessageEvent() throws InterruptedException {
        Messages.sendMessage(botTest, "Initial queue provider:");
        Messages.sendMessage(retrieveMessage(), "Limiting FizzBuzz");
        Messages.sendMessage(retrieveMessage(), "Resplendent Prism");
        Messages.sendMessage(retrieveMessage(),"");
    }

    @Test
    void sendMessageEmbed() {
        Messages.sendMessage(botTest, EmbedHelper.getEmbed("You are about to die","A small announcement:"));
        Messages.sendMessage(botTest, EmbedHelper.getEmbed("For the enemy is not dwarf, nor troll","Who the enemy is"));
        Messages.sendMessage(botTest,EmbedHelper.getEmbed("Ook","Librarian"));
    }

    @Test
    void sendMessageEmbedEvent() throws InterruptedException {
        Messages.sendMessage(retrieveMessage(), EmbedHelper.getEmbed("Never gonna give you up","Mr. Rick Astley's Promise:"));
        Messages.sendMessage(retrieveMessage(), EmbedHelper.getEmbed("Very very frightening","Reactions to a large electric discharge:"));
    }

    @Test
    void getEmote() {

    }

    @Test
    void toUnicode() {
    }

    @Test
    void toUnicodeString() {
    }

    @Test
    void toTime() {
        assertEquals("1:00",Messages.toTime(60000));
        assertEquals("1:00:00",Messages.toTime(3600000));
        assertEquals("0:00",Messages.toTime(0));
        assertEquals("0:59",Messages.toTime(59000));
        assertEquals("1:00:59",Messages.toTime(3659000));
        assertEquals("3:59:59",Messages.toTime(14399000));
    }
    static GuildMessageReceivedEvent retrieveMessage() throws InterruptedException {
        GuildMessageReceivedEvent evt = events.take();
        events.remove(0);
        return evt;
    }
}