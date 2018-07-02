## Introduction

There is a journey that I think most developers follow regarding their primary development concern.

1. Getting a program to compile, or getting the framework application to start.
1. Correctness of the program, the feature works but the code tends to be a bit messy and lacking in design.
1. Maintainability, the feature works but the code should be flexible and understandable so that new features can be added.

SOLID is a useful set of heuristics and notions which can be applied when the primary concern becomes maintainability.
These steps of primary concern also map to the Red Green Refactor cycle in TDD, Red - may not compile, Green - correctness, Refactor - make the code maintainable and improve design.
SOLID should be considered during the Refactor step.

## SOLID

stands for:

[Single Responsibility](src/main/java/solidPrinciples/S/README.md)

[Open (for extension) Closed (for modification)](src/main/java/solidPrinciples/O/README.md)

[Liskov Substitution](src/main/java/solidPrinciples/L/README.md)

[Interface Segregation](src/main/java/solidPrinciples/I/README.md)

[Dependency Inversion](src/main/java/solidPrinciples/D/README.md)

Credit for the acronym goes to Uncle Bob Martin who is not my uncle or probably yours either, but he is a big name in the land of Agile and particularly the subset which is Code Craftsmanship.
He has a bunch of books and has endorsed a bunch of books, he has a ton of stuff out on youtube and blogs.
Even though Uncle Bob Martin is a big name and has contributed alot, we still need to be critical, there is room for a good bit more science in our field and it should never be good enough to say “This is a good idea because some big name person said so”.
