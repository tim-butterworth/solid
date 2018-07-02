## Interface Segregation

### What?

The notion that clients should only see the methods they actually depend on.
This also extends to subclasses not having to implement methods they don't need or use.
It can be seen as a form of the adapter pattern.

### When?

Interface Segregation is a common technique when integrating with a dependency that exposes more methods and information than needed.
Such a dependency can be wrapped with a class that exposes only the subset of functionality needed.
Interface Segregation can be useful in any context when a user of a component only needs a limited subset of the functionality.
When a large class needs to be refactored into a set of smaller classes.

### Why?

For clarity, intentionality can be obscured when there are extra methods hanging off here and there particularly when there are seemingly related methods then it can be difficult to understand if the correct one was used or if a mistake was made.
To avoid mistakes around using the wrong method.
To make refactoring the large class possible, Interface Segregation can be used to free up the class targeted for refactoring from direct dependencies.
To reduce coupling across a system, the tighter the bounds applied to parameters passed into methods or objects, the easier it is to refactor, passing too much information around a system is a sure way to increase dependencies and reduce flexibility.

### How?

If it is to wrap a class coming from an external dependency, writing a wrapper class that calls through to the limited methods it exposes is a way to go.
There is some Java reflection trickery that could be used to cut down on the tedium of writing all the passthrough functions.
If it is a class owned by the system, having it implement multiple, segregated interface works well.