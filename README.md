# nanoTime

Introduction:
For this experiment, I timed the Sorting Algorithms using the nanoTime() function which returns the current value of the most precise available system timer, in nanoseconds. The way I did this was to set a variable of type long to System.nanoTime() function before calling the sorting algorithm. After the sorting algorithm was called I set another variable of type long to System.nanoTime(). Afterwards, I subtracted the 1st variable to the 2nd variable which gave me the running time. Finally, I converted that to milliseconds by diving it by 1,000,000 and printing out the result using the System.print.ln() function.

Merge Sort:
To find an estimation for the merge sort constant, what I did was use the formula: time = cnlgn. I applied this to all my values and most of the answers were in between 6 and 7.

Linear Sort:
To find and estimation for the linear sort constant, I used the formula: time = cn^2. I applied this to all my values and most of the answers were in between 1 and 2.

Optimal Solution: 
I found the optimal solution to be around the 800,000 size array. That’s where the merge sort starts getting better time than the linear sort as you can see in the ‘close-up’ chart. I’ve also included the chart where I included array size of up to 8,000,000.

