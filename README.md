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

### Added feature to create list of values for IntegerArgumentMarshaler
After that I want to add this feature other ArgumentMarshaler classes
I have also written some tests for this function


### Added feature to create list of values for other classes implementing ArgumentMarshaler

First, I am removing StringArrayArgumentMarshaler class because just StringArgumentMarshaler will do same things.
I edited class IntegerArgumentMarshaler, because I don't split argument anymore
After that I added the same logic to other classes
I also created a new test class to test exactly these functions (ArgsValuesListTest) and wrote test for each class
