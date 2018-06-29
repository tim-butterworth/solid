package solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.entityAnnotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DatabaseTable {
    String value();
}
