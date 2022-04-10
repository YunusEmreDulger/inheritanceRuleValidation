package com.emredulger.achitecturalstandards;

import com.emredulger.exception.EntityInheritanceViolationException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;

public class InheritanceCheckerForEntity {

    private static final String BASE_ENTITY_NAME = "com.emredulger.entity.BaseEntity";
    private static final String DIRECTORY_NAME = "com.emredulger.entity";
    private static final String INHERITANCE_VIOLATION_MESSAGE = "The classes under 'entity' package must extend the BaseEntity.class";
    private static final String NO_INHERITANCE_VIOLATION_MESSAGE = " passed the inheritance control, because it extends the " + BASE_ENTITY_NAME;

    public static void checkInheritanceStandart() {

        Set<? extends Class> classes = findClassesInPackage(DIRECTORY_NAME);
        classes = classes.stream().filter(x -> !x.getName().equals(BASE_ENTITY_NAME)).collect(Collectors.toSet());

        classes.forEach(x -> {
            if (!BASE_ENTITY_NAME.equals(x.getSuperclass().getName())) {
                throw new EntityInheritanceViolationException(INHERITANCE_VIOLATION_MESSAGE, x.getName(), BASE_ENTITY_NAME);
            } else {
                System.out.println(x.getName() + NO_INHERITANCE_VIOLATION_MESSAGE);
            }
        });
        classes.forEach(x -> System.out.println(x.toString()));
        System.out.println("lets get it oooonnn");
    }

    private static Set<? extends Class> findClassesInPackage(String directoryName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(directoryName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Set<? extends Class> classes = reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClassFor(line, directoryName))
                .collect(Collectors.toSet());
        return classes;

    }

    private static Class getClassFor(String className, String directoryName) {
        try {
            return Class.forName(directoryName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            System.out.println("There is no class with this name under entity package.");
            e.printStackTrace();
        }
        return null;
    }
}
