## Open (to extension) Closed (to modification)

### What?

Opened to extension of functionality, closed to modification of existing code.
We can extend the functionality of our system without modifying our existing code.
This is more of a description of two opposing forces that we should balance in some way.
There is the pressure for increased flexibility vs the pressure to have structure.
The more flexibility that is added the less structure there is.
Followed to the extreme, for a maximally flexible system, all information is passed in, essentially, one is left with essentially a programming language.
On the other extreme a maximally inflexible system has no information passed in and every change requires a source code change.
For something like a framework, general flexibility should be on the higher side.
For an application that is highly in tune with its problem space, there will be much lower general flexibility and instead there should be some domain specific flexibility.

### When?

At refactor time (Red Green Refactor).

### Why?

The more of a system that has to change to accommodate extensions to functionality the larger the opportunity for bugs and breakages.
It is therefore desirable to be able to make enhancements without disturbing existing, working code.

### How?

Increasing extensibility without needing to make source code changes involves externalizing information.
This can be done in multiple ways, a few common ways are:

1. Externalized configuration
1. Spring like component scan mechanism
1. Registration of

The ideal is to have as much flexibility as needed to make changes easy and no more.
An overly flexible system is probably also an overly abstracted, and underspecified, it has very little useful to say about its domain and it can be very challenging to know just what such a system does.
The balance of structure and flexibility is something that should be discovered.
As changes are difficult to make, refactor towards flexibility in that particular dimension of change can be helpful.
