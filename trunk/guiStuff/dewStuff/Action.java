package guiStuff.dewStuff;

import java.awt.Image;

public abstract class Action implements Comparable<Object> {
	public abstract String getName();
	public abstract String getDesc();
	public abstract Image getImage();
	
	public abstract void execute();
	
	public abstract int compareTo(Object o);
}
