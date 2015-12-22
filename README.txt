
-- BRIEF

Hi,

I'am Zeki Gulser and a team member candidate for ThoughtWorks and this is a README of what I've done.

For the assignment, I picked Kiwiland Trains Problem to go with. In this assignment we are asked to find out all possible paths/routes 
between two (distinct) cities of Kiwiland along with the shortest paths among all couples of cities.

-- ASSUMPTIONS

1. Throughout the code I used terms "city", "vertex" or "town" interchangeably. So for 'path' & 'route'.

2. Graph is directed. ( Stated in the assignment as " A directed graph where a node represents a town and an edge represents a route between two towns. ")

3. No edges can be repeated on a route. ( Stated in the assignment as " A given route will never appear more than once ")

4. Starting and ending vertices should not be the same. ( Stated in the assignment as "for a given route, the starting and ending town will not be the same town." )

5. Max edge distance is set to MAX_ARBITRARY_INTEGER_VALUE = 1000000 (as infinity)

6. SUB-PATH is for a given CEBECAEFC

   CEB, CEBE, CAEF etc.

   For example: SUB-PATH starting and ending at C is CAEFC and CEBEC   

-- DESIGN OVERVIEW

First of all, I mainly used Djikstra's approach and modified it a little bit to find out;

 1. all paths between two towns
 2. shortest path between two towns (originally, this is what Djikstra's algorithm offers)
 
I implemented Djikstra's approach as I mentioned but I needed to modify it so that to include
all paths from a pivot city/vertex to all other towns. To do this I inserted a method called addToPathList() into the shortest path algorithm.
This method basically does two things;

 1. Firstly,it finds out all paths from a pivot vertex to the source vertex. And then, it uses those paths to find all possible 
	paths to the source's adjacent vertices/cities. When doing this, the method checks for two things as they've mentioned in the
	problem description as well;
 
	1.1. Newly added city (candidate for "new end/tail" of the currently processing path) should not be the same with the starting city of the currently processing path.
	1.2. Newly added city should not create a path so that there are same cities in a row, on the entire path.
	1.3. Newly added city should not create a route that is doubled.
	
 2. Secondly, it looks for the possibly missed paths from source (which was destination in the item 1) to catch missed paths if any;
 
	This is added since ordering is unpredictable when processing the queue. You might not have the all paths available when  
	trying the first step which will leave your algorithm incomplete. So you have to perform one more checking with this step to ensure
	you take all the paths prior to a vertex into account.
	
	I also did the similar checkings I mentioned above, for the first item.
	
Hope you will find more information about to process as you read the comments.
	   
-- TESTS

I wrote number of tests (as separate test classes)to prove that my code is OK. For the given test input, you should look at testInputsFromTheAssignment class.

-- REMARKS ON TESTS **** 
	
1. There is one thing I need to point out is the input data given in the assignment might be faulty. You see in the problem description, it's 
exactly said that " A given route will never appear more than once, and for a given route, the starting and ending town will not be the same town."
but in the sample input data, we've been asked to test;

				
	7.The number of trips starting at A and ending at C with exactly 4 stops.  In the sample data below,
	there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
	
		I think 2nd sample this violates the "A given route will never appear more than once" rule. It simply produced;
		
			A-C-D-C-D in which you will repeat the path C-D. Additionally, we never know when to stop C-D & D-C infinite path switching. (See ASSUMPTIONS)
			
			Thus I produced 2, instead of 3 paths and that's why this test is failed. You can run it correctly when you modify it to return 2.		
	
	10.The number of different routes from C to C with a distance of less than 30.  In the sample data, 
	the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
	
		I think some of those input values violates the " A given route will never appear more than once " 
		and " the starting and ending town will not be the same town."  sentences.
		
		For example CEBCEBCEBC includes repeated edges and also starting and ending at C. 
		Thus I found SUB-PATHS starting and ending at C. See assumptions for SUB-PATH definition.
	
2. Please see how to provide different inputs in the INSTRUCTIONS section below

-- INSTRUCTIONS

The program is written in java using Eclipse. So you will need the following;

1. To run a java program, you simply need to install jre (java runtime environment) in the first place. You can gather the latest from the following;

http://www.oracle.com/technetwork/java/javase/install-windows-141940.html

2. Eclipse IDE (Not really sure if or not it's broken when you load it to some other IDE like it's workspace or project settings)

3. Since it's required to supply an input file in the assignment instructions, you MUST supply a text file named "input.txt" under the location of your project file.

   Note: This was supplied already with the data provided in the assignment in the format of;
   
		Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
		Start: A
		
		but if you want to change it, like changing pivot/start point, it's available (of course) under the mentioned location.
		
		Please do NOT change the format, but only change letters & numbers.
   
   
   



