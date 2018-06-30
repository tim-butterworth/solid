## Single Responsibility

What?  This notion that software components should have no more than one responsibility. A responsibility can be defined as a reason to change, each component should have one reason to change.  The notion of one responsibility and one reason to change are both fairly vague and they don’t have to be precise, we can disagree on the size of a responsibility and a reason for change.  For example, there could be a repository that is responsible for peristing some fancy entity.  I like super small responsibilities so I say, hey that has two responsibilities, reading and writing that fancy entity.  You may say, no, that is one responsibility, persisting.  We can both be right, explicitly thinking about what belongs and what does not belong to a component and being intentional about it is more important than the size of the component you end up with (but I was right, we should have separate read and write models).

When? This can be appropriate to apply when it comes time to refactor, decompose a component into a few independent components can be easier to modify.  There are two different refactor times to think about, there is the initial red green refactor step and it is during the refactor step when single responsibility should be applied and then there is the someone is coming back to the code to refactor it and that is when single responsibility can pay off.  There can be a case for not applying single responsibility to code that somehow you know will never change, but even then, I would argue that the single responsibility principle can be leveraged to simplify and make the code easier to write.

Why?

How?  Single responsibility is kind of a dial that can be turned up or turned down.  There is certainly a risk to very aggressively applying single responsibility too soon.  The components might be the wrong shape, the abstractions might not fit the problem space very well and you will see changes cascade through multiple components.  I think it can make sense to make responsibilities smaller and tighter as time passes, the second or third time you pass through a certain bit of code you will have a better understanding of the usefulness and stableness of certain shapes of responsibility distribution.

There are some adjacent ideas, decomposing code into independent (orthogonal) pieces.

Single Responsibility is a useful notion if it leads to deliberate decisions.  Mindfulness/deliberate decisions about boundaries and what belongs is the real value of this notion.  There are prescriptions for classes or methods not to exceed a certain size, but that sort of one size fits all approach is not particularly useful.

If we don’t make deliberate choices about boundaries and decompositions, our languages and frameworks will make them for us.
