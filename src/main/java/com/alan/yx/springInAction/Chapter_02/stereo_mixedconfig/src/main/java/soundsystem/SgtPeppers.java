package com.alan.yx.springInAction.Chapter_02.stereo_mixedconfig.src.main.java.soundsystem;

public class SgtPeppers implements CompactDisc {

  private String title = "Sgt. Pepper's Lonely Hearts Club Band";  
  private String artist = "The Beatles";

  @Override
  public void play() {
    System.out.println("Playing " + title + " by " + artist);
  }

}
