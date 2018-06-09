package D.dependencyInversion;

import D.dependencyInversion.dependencies.DataSourceDirectDependency;
import D.dependencyInversion.dependencies.DirectDependencyTwo;

public class NotInverted {

    private final DataSourceDirectDependency dataSourceDirectDependency;
    private final DirectDependencyTwo directDependencyTwo;

    public NotInverted() {
        dataSourceDirectDependency = new DataSourceDirectDependency();
        directDependencyTwo = new DirectDependencyTwo();
    }

    public String doStuff() {
        return dataSourceDirectDependency.doStuff() + " " + directDependencyTwo.doStuff();
    }
}
