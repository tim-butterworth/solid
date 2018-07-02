package solidPrinciples.I.InterfaceSegregation.externalExamples.proxies;

import solidPrinciples.I.InterfaceSegregation.externalClassWeCanNotChange.TooBigClass;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InterfaceSegregationHandler implements InvocationHandler {

    private final TooBigClass tooBigClass;

    public InterfaceSegregationHandler(TooBigClass tooBigClass) {
        this.tooBigClass = tooBigClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?>[] parameterTypes = method.getParameterTypes();

        Method instanceMethod = tooBigClass.getClass().getDeclaredMethod(method.getName(), parameterTypes);

        return instanceMethod.invoke(tooBigClass, args);
    }
}
