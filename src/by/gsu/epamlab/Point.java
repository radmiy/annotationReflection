package by.gsu.epamlab;

import java.awt.Color;

import by.gsu.epamlab.annotations.Equal;
import by.gsu.epamlab.annotations.Equal.CompareBy;

public class Point {
	@Equal
	private int x;
	private int y;
	@Equal(compareBy = CompareBy.REFERENCE)
	private Color color;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Point() {
		this(0, 0, new Color(0, 0, 0));
	}
	
	public Point(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public String fieldsToString() {
		return "x=" + x + ", y=" + y;
	} 
	
	@Override
	public String toString() {
		return "Point [" + fieldsToString() + ", color=" + color + "]";
	}
}
