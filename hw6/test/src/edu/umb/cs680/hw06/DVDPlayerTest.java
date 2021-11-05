package edu.umb.cs680.hw06;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class DVDPlayerTest {
	@Test
    public void verifyDVDPlayer() {
	DVDPlayer dvdPlayer = DVDPlayer.getInstance();
	State expected = null;
	expected = DrawerClosedNotPlaying.getInstance();
	assertEquals(expected, dvdPlayer.getState());
	
	dvdPlayer.openCloseButtonPushed();
	expected = DrawerOpen.getInstance();
	assertSame(expected, dvdPlayer.getState());
	
	dvdPlayer.playButtonPushed();
	expected = DrawerClosedPlaying.getInstance();
	assertSame(expected, dvdPlayer.getState());

	dvdPlayer.stopButtonPushed();
    expected = DrawerClosedNotPlaying.getInstance();
    assertEquals(expected, dvdPlayer.getState());
	
	}
	
    @Test
    public void DrawerOpenWhenOpenCloseButtonPushedWhilePlaying() {
        DVDPlayer dvdPlayer = DVDPlayer.getInstance();
        dvdPlayer.playButtonPushed();
        dvdPlayer.openCloseButtonPushed();
        State expected = DrawerOpen.getInstance();
        assertEquals(expected, dvdPlayer.getState());
    }

    @Test
    public void DrawerClosedNotPlayingWhenStopButtonPushedWhilePlaying() {
        DVDPlayer dvdPlayer = DVDPlayer.getInstance();
        dvdPlayer.playButtonPushed();
        dvdPlayer.stopButtonPushed();
        State expected = DrawerClosedNotPlaying.getInstance();
        assertEquals(expected, dvdPlayer.getState());
    }
}
