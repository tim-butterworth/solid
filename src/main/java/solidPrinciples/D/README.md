## Dependency Inversion

### What?

There are a few related notions:

1. Dependency Injections
1. Inversion of Control
1. Plugins
1. Ports and Adapters

There are a few descriptions of Dependency Inversion:

1. High level policy should not depend on low level details. Both should depend on abstractions.
1. The build-time dependencies point in the opposite direction of the runtime flow of control

Dependency Inversion in its fullest form can be a technique for establishing relative importance of different components.
It can be a way of setting up the part of the system from which changes should propagate from, some sort of core should drive changes out to the rest of the system.
The component that defines the contract (owns the interfaces) is the component that can make changes which require the implementor to change.

### When?

At the boundaries of the system, treat the database like a plugin, treat service integrations like plugins, and some would even suggest treating the web or controllers as plugins.
If there is a portion of the system that is more important than the things it depends on, that is a good time to invert dependencies.

### Why?

Flexibility, testability, and the ability to switch out implementations.
Passing in collaborators via constructors provides seams for slicing off parts of the application graph for testing or for injecting mocks.
Protecting an application from external changes.
If an old integration is replaced by a shiny new one, an adapter can be written so that the new integration can feed into the same interface as the old integration.

### How?

Passing in collaborators via a constructors, fields, or setters (constructor injection is strictly the best except for sometimes).
There are a few different techniques depending on the shape of the dependency graph.
A simple shape would be if the dependency is already passed in through the constructor, replace it with an interface.
Put the interface and the class that uses it together in a package perhaps called core, put the implementations elsewhere, and finally, a new class will need to be introduced to wire everything together.
Dependency inversion always requires some form of container that assembles everything, that can be a DI framework or simply a class that news up all the things.
It is a little more tricky but possible to invert a dependency so that a caller has to fulfill a contract defined in core.