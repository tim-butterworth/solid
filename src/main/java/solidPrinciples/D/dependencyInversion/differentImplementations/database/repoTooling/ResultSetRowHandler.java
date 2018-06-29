package solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling;

import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.converters.NoOpConverter;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.entityAnnotations.DatabaseTypeConverter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class ResultSetRowHandler<T> {
    public T apply(
            InstanceProvider<T> instanceProvider,
            Map<String, Method> columnMethodMap,
            ResultSet resultSet
    ) {
        T instance = instanceProvider.getInstance();
        Converter noOpConverter = new NoOpConverter();

        columnMethodMap.entrySet()
                .iterator()
                .forEachRemaining((entry) -> {
                    String column = entry.getKey();
                    Method setter = entry.getValue();

                    Converter databaseTypeConverter = Optional.ofNullable(setter.getAnnotation(DatabaseTypeConverter.class))
                            .map(DatabaseTypeConverter::value)
                            .map(this::getConverterInstance)
                            .orElse(noOpConverter);

                    invokeSetter(resultSet, instance, column, setter, databaseTypeConverter);

                });
        return instance;
    }

    private Converter getConverterInstance(Class<? extends Converter> converterClass) {
        try {
            Constructor<Converter> constructor = (Constructor<Converter>) converterClass.getConstructor();
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void invokeSetter(ResultSet resultSet, T instance, String column, Method setter, Converter databaseTypeConverter) {
        try {
            Object object = resultSet.getObject(column);
            setter.invoke(instance, databaseTypeConverter.apply(object));
        } catch (IllegalAccessException | InvocationTargetException | SQLException e) {
            e.printStackTrace();
        }
    }
}