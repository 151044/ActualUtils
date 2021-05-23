package com.freemyip.nopersonalinfo.discord.utils;

import net.dv8tion.jda.api.entities.MessageEmbed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmbedHelperTest {

    @BeforeEach
    void resetAuthor(){
        EmbedHelper.setAuthor("Object extends Object");
    }

    @Test
    void getAuthor() {
        assertEquals("Object extends Object",EmbedHelper.getAuthor());
        EmbedHelper.setAuthor("Eric");
        assertEquals("Eric",EmbedHelper.getAuthor());
        EmbedHelper.setAuthor("!@#$%^&*()_+<>?:{}:|");
        assertEquals("!@#$%^&*()_+<>?:{}:|",EmbedHelper.getAuthor());
        EmbedHelper.setAuthor("Paginate");
        MessageEmbed embed = EmbedHelper.getEmbed("Paginate","Lorem ipsum dolor sit");
        assertEquals("Paginate",embed.getAuthor().getName());
    }

    @Test
    void getEmbed() {
        MessageEmbed embed = EmbedHelper.getEmbed("Paginate","Lorem ipsum dolor sit");
        assertEquals("Paginate",embed.getDescription());
        assertEquals("Lorem ipsum dolor sit",embed.getTitle());
    }

    @Test
    void getLongEmbed() throws IOException {
        String read = Files.readAllLines(Path.of("src/test/resources/lorem.txt")).stream().collect(Collectors.joining("\n"));
        assertEquals(2, EmbedHelper.getLongEmbed(read,"Paginate").size());
    }
}