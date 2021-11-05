package edu.umb.cs680.hw06;

public interface State {
	DVDPlayer dvdPlayer = DVDPlayer.getInstance();
	void playButtonPushed();
    void openCloseButtonPushed();
    void stopButtonPushed();
}
