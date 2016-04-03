package by.gsu.epamlab.analyzers;

import java.lang.reflect.Field;

import by.gsu.epamlab.annotations.Equal;
import by.gsu.epamlab.annotations.Equal.CompareBy;
import by.gsu.epamlab.constants.Constants;

public class Analyzer {
	@SuppressWarnings("unused")
	private boolean equalsObjects(Object obj1, Object obj2) {
		final String message = "Field %s=%s of object1 %s field %s=%s of object2 by %s\n";
		final String notEqual = "The objects is not equals";
		
		if(obj1 == null) {
			return false;
		}
		
		if(obj2 == null) {
			return false;
		}
		
		Field[] fieldsObj1 = getFields(obj1);
		Field[] fieldsObj2 = getFields(obj2);
		
		boolean result = true;
		int countFields = 0;
		
		for(Field fieldObj1 : fieldsObj1) {
			if(fieldObj1.isAnnotationPresent(Equal.class)) {
				for(Field fieldObj2 : fieldsObj2) {
					if(fieldObj2.isAnnotationPresent(Equal.class)) {
						countFields++;
						Equal equalObj1 = fieldObj1.getAnnotation(Equal.class);
						Equal equalObj2 = fieldObj2.getAnnotation(Equal.class);
						CompareBy typeObj1 = equalObj1.compareBy(); 
						CompareBy typeObj2 = equalObj2.compareBy();
						if(typeObj1 == typeObj2 && 
								fieldObj1.getName().equals(fieldObj2.getName())) {
							try {
								fieldObj1.setAccessible(true);
								fieldObj2.setAccessible(true);
								Object valueObj1 = fieldObj1.get(obj1);
								Object valueObj2 = fieldObj2.get(obj2);
								if(valueObj1 == null || valueObj2 == null) {
									System.out.println(notEqual);
									return false;
								}else{
									boolean equalField = typeObj1 == CompareBy.REFERENCE && valueObj1 == valueObj2 ||
											typeObj1 == CompareBy.VALUE && valueObj1.equals(valueObj2);
									System.out.printf(message, "'" + fieldObj1.getName() + "'", valueObj1,  equalField ? Constants.stringEqual : Constants.stringNotEqual, "'" + fieldObj2.getName() + "'", valueObj2, typeObj1);
									if(!equalField) {
										return false;
									}
								}
								
							} catch (IllegalArgumentException | IllegalAccessException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		if(result && countFields == 0) {
			return false;
		}
		return result;
	}
	
	private Field[] getFields(Object obj) {
		final int BEGIN_POS = 0; 
		Field[] fieldsObj = obj.getClass().getDeclaredFields();
		Class<?> myClass = obj.getClass();
		while(myClass.getSuperclass() != null) {
			myClass = myClass.getSuperclass();
			int lenObj = fieldsObj.length;
			Field[] fieldsSuper = myClass.getDeclaredFields();
			int lenSuper = fieldsSuper.length;
			Field[] concatFields = new Field[lenObj + lenSuper];
			System.arraycopy(fieldsObj, BEGIN_POS, concatFields, BEGIN_POS, lenObj);
			System.arraycopy(fieldsSuper, BEGIN_POS, concatFields, lenObj, lenSuper);
			fieldsObj = concatFields;
			
		}
		
		return fieldsObj;
	}
}
