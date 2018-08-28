
Part I:  Identifying Sort Algorithms
-------------------------------------------

Histogram 1 is the Quicksort because we can see that it's significantly faster than all the other histogram. However, it is the slowest intially in sorting the array.

Histogram is the Selection Sort because it visits each data and put the largest number at the bottom of the histogram. Then restart at the beginning to find the second largest one in the data and put it next to the largest number and it does this over and over again until the data has all been sorted. Hence, the slowest among them.

Histogram 3 is MergeSort because it divides the data into sublists and merge the sublists into sorted sublists until there is only 1 sublist remaining. This is the second fastest among the histograms.

Histogram 4 is Insertion Sort because at each iteration, it removes one element from the data then finds the location it belongs within the sorted list by comparing if it is smaller or larger than the other element.

Part II:  Timing Sort Algorithms (2 points)
-------------------------------------------

I took the screenshot of the plot. The "FirstPlot" is the plot of select, merge, insert, and quick with 5 being the size of the small array, 175 size of the largest array, and 200 trials. The "SecondPlot" is also the plot of select, merge, insert, and quick, but 1 is the size of the small array, 50 is the size of the larest array with 1000 trials. "ThirdPlot" and "FourthPlot" are pretty much the same thing with "FirstPlot" and "SecondPlot" but with an additional sort which is the algorithm implemented in YourSort.java "best".

PART III:  Building a Better Sort 
-------------------------------------------
Implemented in YourSort.java

if array length is less than 65, then use insertion sort to sort the array
else, use quicksort to sort the array.


