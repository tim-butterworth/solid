## Single Responsibility

### What?

This is the notion that software components should have no more than one responsibility.
A responsibility can be defined as a reason to change, each component should have one reason to change.
The idea of a responsibility or a reason to change is pretty subjective, it is directly related to the level of abstraction with which we want to look at a component.
For example, if there is a repository that is responsible for reading and writing a fancy entity, does it have two responsibilities, reading and writing, or does it have one responsibility, wrapping the database?
Either way of looking at responsibilities can be valid.
We can disagree about the size of a responsibility or a reason to change, the size we pick is much less important than deliberately choosing boundaries for the responsibilities in our systems.
Putting effort into picking our boundaries will result in code with greater coherence and flexibility then we would otherwise have.
There are prescriptions for classes or methods not to exceed a certain size, but that sort of one size fits all approach is not particularly useful.
If we don’t make deliberate choices about boundaries and decompositions, our languages and frameworks will make them for us.

### When?

At refactor time (Red Green Refactor).
When our tests tell us to, tests with complex setups or an exponential explosion of test cases can be an indication that responsibilities need to be split out.
When our components are getting too big or difficult to understand, splitting out responsibilities is an immensely powerful way of increasing code clarity.
When our code is hard to change, hard to change code can be an indication that responsibilities need to be split out, code being easy to change is the most effective measure that the boundaries have been chosen well.

### Why?

For clarity, code is read more times then it is written (or so they say).
Having easy to understand code drastically reduces maintenance time and therefore maintenance cost.
Even if the code is not changed, being able to quickly identify "this is not the code you are looking for" is super helpful.
For changeability, if the responsibility boundaries make sense the code will be easy to change, there will rarely be changes that cascade throughout the code base.

### How?

Single responsibility is kind of a dial that can be turned up or turned down.
Not too soon, there can be a risk to very aggressively applying single responsibility too soon.
The components might be the wrong shape, the abstractions might not fit the problem space very well, changes will cascade through multiple components.
Make responsibilities smaller and tighter as time passes, the second or third pass through a certain bit of code will yield better understanding of the usefulness and stableness of certain shapes of responsibility distribution.

There are different ways to slice the responsibilities, they can be sliced by technical role (controllers, repositories, ect), they can be sliced by domain meaning.
It can makes sense to slice both ways, separate domain responsibilities and technical responsibilities.
