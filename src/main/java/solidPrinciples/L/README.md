## Liskov Substitution

### What?

Liskov Substitution is about not doing weird things or more specifically, to follow Liskov Substitution a subclass should be substitutable for its superclass everywhere in a program and the program should still be “correct” for certain meanings of correct.  This might be more clear with some examples of violations.  Java is an amazing technological feat but it also has a bunch of mistakes and thanks to its success and focus on backwards compatibility many of those mistakes will never go away and we can learn from them.  We don’t have to look any further than core Java for violations of Liskov Substitution.
Liskov Substitution is about `Behavioral Subtyping`, this is stronger than Subtyping from type theory, it is also undecidable in general so no programming languages attempt to enforce it.

### When?

Always if possible, implement subclasses to adhere to the "contract" of the parent class.

### Why?

For clarity, for bug prevention, and for the ability to better reason about code.
If Liskov Substitution is always followed there are whole classes of issues that go away.
Unexpected null pointers, unexpected exceptions, and other general unexpected bad behavior.

### How?

Mistakes in Liskov Substitution can be subtle.
In order to know one has a valid subclass it is essential to understand the contract of the parent class.
Because the programming languages can not be expected to enforce correctness of Liskov Substitution, in practice, apart from trivial examples, a "contract test" mechanism can be used.
The "contract test" should cover the contract that any subclass should fulfill in order to be considered Liskov Substitutable.
