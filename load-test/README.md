# load-test

This is an attempt to execute an endless loop and therefore put a heavy load on the CPU.

Each loop the square root of number (type double) is calculated.

Run:

$ java -jar load-test-1.0.jar


Or with arguments:

$ java -jar load-test-1.0.jar -d 5 -m

where:

-d - delay in each loop (must be followed by a number)
n - delay in milliseconds

-m - put objects in HashMap until OutOfMemoryException occurs




