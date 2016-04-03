package by.gsu.epamlab;

import java.awt.Color;

public class Point3D extends Point{
	private int z;

	public Point3D(int x, int y, int z, Color color) {
		super(x, y, color);
		this.z = z;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public String fieldsToString() {
		return super.fieldsToString() + ", z=" + z;
	}
}
