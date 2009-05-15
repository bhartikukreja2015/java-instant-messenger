package guiStuff.dewStuff;

import java.awt.Image;

public interface Action {
	public String getName();
	public String getDesc();
	public Image getImage();
	
	public void execute();
}
