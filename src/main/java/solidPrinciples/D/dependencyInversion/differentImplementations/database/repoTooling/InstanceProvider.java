package solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class InstanceProvider<T> {

    private final Class<T> tClass;

    public InstanceProvider(Class<T> tClass) {
        this.tClass = tClass;
    }

    public T getInstance() {
        try {
            Constructor<T> constructor = tClass.getConstructor();

            return constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
