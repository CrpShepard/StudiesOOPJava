package ru.crpshepard.springcourse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            "applicationContext.xml"
        );

        Music music = context.getBean("classicalMusic", Music.class);

        MusicPlayer musicPlayer = new MusicPlayer(music);

        musicPlayer.playMusic();

        Music music2 = context.getBean("rockMusic", Music.class);

        MusicPlayer musicPlayer2 = new MusicPlayer(music2);

        musicPlayer2.playMusic();

        context.close();
    }
}
