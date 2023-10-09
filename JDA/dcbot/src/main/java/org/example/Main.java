package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main {
    public static void main(String[] args) {
        JDA builder = JDABuilder.createDefault("token").build();

        builder.setActivity(Activity.playing("with JDA!"));

    }
}