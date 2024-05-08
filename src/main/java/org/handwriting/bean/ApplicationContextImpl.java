package org.handwriting.bean;

import org.handwriting.anno.Bean;
import org.handwriting.anno.DI;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

/**
 * @author Linson
 */
public class ApplicationContextImpl implements ApplicationContext {
    private final Map<Class<?>, Object> beans = new HashMap<>();

    public ApplicationContextImpl() {
    }

    ;

    public ApplicationContextImpl(String packageName) throws IOException, IllegalAccessException {
        List<String> classNamesList = findClassNames(packageName);

        for (String className : classNamesList) {
            try {
                Class<?> clazz = Class.forName(className);

                if (!clazz.isInterface() && !clazz.isAnnotation() && !clazz.isEnum() && !clazz.isPrimitive()) {
                    Object instance = clazz.getDeclaredConstructor().newInstance();

                    if (clazz.isAnnotationPresent(Bean.class)) {
                        Class<?>[] interfaces = clazz.getInterfaces();
                        if (interfaces.length > 0) {
                            beans.put(interfaces[0], instance);
                        } else {
                            beans.put(clazz, instance);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        dependencyInjection();
    }

    @Override
    public Object getBean(Class<?> clazz) {
        return beans.get(clazz);
    }

    private void dependencyInjection() throws IllegalAccessException {
        for (Map.Entry<Class<?>, Object> entry : beans.entrySet()) {

            System.err.println("getKey:" + entry.getKey());
            System.err.println("getValue:" + entry.getValue());

        }
        for (Object bean : beans.values()) {
            Class<?> clazz = bean.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(DI.class)) {
                    field.setAccessible(true);
                    field.set(bean, getBean(field.getType()));
                }
            }
        }
    }

    private List<String> findClassNames(String packageName) throws IOException {
        String packagePath = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(packagePath);

        List<String> classNamesList = new ArrayList<>();

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            traverseFiles(new File(resource.getFile()), packageName, classNamesList);
        }

        return classNamesList;
    }

    private void traverseFiles(File file, String packageName, List<String> classNamesList) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File subFile : files) {
                    traverseFiles(subFile, packageName, classNamesList);
                }
            }
        } else {
            String filePath = file.getPath();
            if (filePath.endsWith(".class")) {
                String className = filePath.substring(filePath.indexOf(packageName.replace('.', '\\')) + packageName.length() + 1);
                className = className.replace("\\", ".");

                if (className.endsWith(".class")) {
                    className = className.substring(0, className.length() - 6);
                }

                classNamesList.add(packageName + "." + className);
            }
        }
    }
}
