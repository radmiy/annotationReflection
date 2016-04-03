import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import by.gsu.epamlab.Point;
import by.gsu.epamlab.Point3D;
import by.gsu.epamlab.analyzers.Analyzer;
import by.gsu.epamlab.constants.Constants;

public class Runner {

	public static void main(String[] args) {
		final int x1 = 10;
		final int y1 = 10;
		Point p1 = new Point(x1, y1, Color.RED);
		final int x2 = 20;
		final int y2 = 20;
		Point p2 = new Point(x2, y2, Color.GREEN);
		final int x3 = 10;
		final int y3 = 10;
		Point p3 = new Point(x3, y3, Color.RED);
		final int x4 = 10;
		final int y4 = 10;
		final int z4 = 10;
		Point3D p4 = new Point3D(x4, y4, z4, Color.RED);
		final int x5 = 10;
		final int y5 = 10;
		Point p5 = new Point(x5, y5, new Color(255, 0, 0));
		
		isObjectsEqual(p1, p2, p3, p4, p5);
	}

	private static void isObjectsEqual(Object...objects) {
		final String message = "%s and %s are %s by mark fields\n";
		Analyzer analyzer = new Analyzer();
		Class[] parameterTypes = new Class[]{Object.class, Object.class};
		Method method = null;
		try {
			method = analyzer.getClass().getDeclaredMethod("equalsObjects", parameterTypes);
			for(int numberObject1 = 0; numberObject1 < objects.length - 1; numberObject1++) {
				for(int numberObject2 = numberObject1 + 1; numberObject2 < objects.length; numberObject2++) {
					method.setAccessible(true);
					boolean result = false;
					try {
						result = (Boolean)method.invoke(analyzer, new Object[]{objects[numberObject1], objects[numberObject2]});
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
					System.out.printf(message, objects[numberObject1], objects[numberObject2], result ? Constants.stringEqual : Constants.stringNotEqual);
					System.out.println();
				}
			}
		} catch (NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
		}
	}
}
