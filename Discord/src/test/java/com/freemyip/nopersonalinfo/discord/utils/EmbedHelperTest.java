package com.freemyip.nopersonalinfo.discord.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    void getEmbed() {

    }

    @Test
    void getLongEmbed() {
    }
}