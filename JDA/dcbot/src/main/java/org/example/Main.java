package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class Main {
    public static void main(String[] args) {
        JDA builder = JDABuilder.createDefault("token").build();

        builder.getPresence().setActivity(Activity.playing("with JDA!"));

    }
}