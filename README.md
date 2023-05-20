# Super Args

This project is an attempt to modify the class Args from Robert Martin's book "Pure Code".

I downloaded this source code from another git repository.
I ran into some problems when I ran the tests for the first time: some tests failed. 
My first task will be these tests

After the tests, I will change the project. I will record each step for clarity.

**Thanks for your attention**

## Commits

### Edited in order for the tests to work out

I edited the logical StringArrayArgumentMarshaler class, because it has to add to the array every argument passed to the method set
I edited throw exception from StringArrayArgumentMarshaler, DoubleArgumentMarshaler because there was a problem there
I pass errorParameter to constructor ArgsException when necessary
