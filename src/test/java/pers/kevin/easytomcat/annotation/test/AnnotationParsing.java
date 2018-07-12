package pers.kevin.easytomcat.annotation.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationParsing {

	
	public static void main(String[] args) {
		 try {
			 
			 for (Method method : AnnotationParsing.class
				        .getClassLoader()
				        .loadClass(("pers.kevin.easytomcat.annotation.test.AnnotationExample"))
				        .getMethods()) {//得到类中的所有方法，遍历每个方法
				  if (method.isAnnotationPresent(pers.kevin.easytomcat.annotation.test.MethodInfo.class)) {//判断该方法的注解是MethodInfo
			            try {
			        // iterates all the annotations available in the method
			                for (Annotation anno : method.getDeclaredAnnotations()) {
			                	
			                    System.out.println("Annotation in Method "+ method + " : " + anno);
			                }
			                	MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);
			                	System.out.println(methodAnno.author());
			                	if (methodAnno.revision() == 1) {
			                		System.out.println("Method with revision no 1 = "+ method);
			                    }
			 
			            } catch (Throwable ex) {
			                    ex.printStackTrace();
			                 }
			        }
				      
				    }
			 
		 } catch (SecurityException | ClassNotFoundException e) {
			           e.printStackTrace();
		}
			    
	}
}
