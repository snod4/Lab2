import java.awt.Color;

public class Runner {

	static Turtle t;

	//Exercise 2a
	/*
	 * Recursive function that adds n and every nonnegative integer below n
	 * n is the starting integer value
	 * n must be greater than or equal to zero
	 * */
	public static int Add(int n) {
		if(n <= 1)
			return 1;
		return  n + Add(n-1);
	}
	
	//Exercise 2b
	/*
	 * Recursive function that prints out seqNum values of Fibonacci sequence starting from num
	 * prevNum is the previous integer before num in the sequence
	 * seqNum is the number of calculations to perform
	 * num is the first integer in the Fibonacci sequence to be printed
	 * */
	public static int Fibonacci(int prevNum, int num, int seqNum) {
		if(seqNum == 0) {
			return num;
		}
		System.out.print(num + " ");
		return Fibonacci(num, num+prevNum, seqNum-1);
	}
	
	//Exercise 2c
	/*
	 * Recursive method that draws a square, inward spiral
	 * length, a double, is the starting length of the line to be drawn for a 
	 * given side of the square
	 * minLength, a double, is the minimum length that length is recursively allowed to reach
	 * */
	public static void DrawSquareSpiral(double length, double minLength) {
		if(length <= minLength) {
			return;
		}
		t.forward(length);
		t.right(90);
		DrawSquareSpiral(length*.9, minLength);
	}
	
	//Exercise 2d
	/*
	 * Recursive method that draws an inward spiral
	 * length, a double, is the starting length of the line to be drawn for a 
	 * given side of the square
	 * minLength, a double, is the minimum length that length is recursively allowed to reach
	 * angle, a double, is the angle to rotate the turtle after each line is drawn
	 * scaleFactor, a double, is the scaleFactor for the length of each line in the spiral
	 * */
	public static void DrawSpiral(double length, double angle, double scaleFactor, double minLength) {
		if(length <= minLength) {
			return;
		}
		t.forward(length);
		t.right(angle);
		DrawSpiral(length*scaleFactor, angle, scaleFactor, minLength);
		
	}
	
	//Exercise 2e
	/*
	 * Recursive method that draws an inward spiral
	 * length, a double, is the starting length of the line to be drawn for a 
	 * given side of the square
	 * minLength, a double, is the minimum length that length is recursively allowed to reach
	 * angle, a double, is the angle to rotate the turtle after each line is drawn
	 * scaleFactor, a double, is the scaleFactor for the length of each line in the spiral
	 * colorGen, a SimpleRandom object, is the object that allows the turtle to change colors randomly with each line drawn
	 * */
	public static void DrawColorSpiral(SimpleRandom colorGen, double length, double angle, double scaleFactor, double minLength) {
		if(length <= minLength) {
			t.penColor(Color.BLACK);
			return;
		}
		
		Color c = new Color(colorGen.nextInt(0, 255),colorGen.nextInt(0, 255),colorGen.nextInt(0, 255));
		t.penColor(c);
		t.forward(length);
		t.right(angle);
		DrawColorSpiral(colorGen,length*scaleFactor, angle, scaleFactor, minLength);
		
	}
	
	//Exercise 2f
	/*
	 * Recursive method that draws weird stuff utilizing DrawColorSpiral
	 * cycles, an integer, is the number of times the method is recursively called
	 * rand, a SimpleRandom object, is passed to DrawColorSpiral
	 * */
	public static void DrawWeirdStuff(int cycles, SimpleRandom rand) {
		if(cycles <= 0) {
			return;
		}
		t.forward(rand.nextInt(8, 259));
		DrawColorSpiral(rand, rand.nextInt(0, 1000), rand.nextInt(0, 360), .9, rand.nextInt(5, 100));
		DrawWeirdStuff(cycles-1, rand);
	}
	
	//Exercise 3a
	/*
	 * Recursive method that draws a Koch Curve
	 * length, a double, is the length of the curve
	 * levels, an integer, represents the detail in the curve
	 * level, an integer, keeps track of the current level in the recursion
	 * */
	public static void KochCurve(double length, int levels, int level) {
		if(level <= 0) {
			t.forward(length/(Math.pow(3, levels)));
			return;
		}
		KochCurve(length, levels, level-1);
		t.left(60);
		KochCurve(length, levels, level-1);
		t.right(120);
		KochCurve(length, levels, level-1);
		t.left(60);
		KochCurve(length, levels, level-1);
		
		
	}
	
	//Exercise 3b
	/*
	 * Recursive method that draws a Koch snowflake
	 * length, a double, is the length of the curve
	 * levels, an integer, represents the detail in the curve
	 * */
	public static void KochSnowFlake(double length, int levels){
		KochCurve(length, levels, levels);
		t.right(120);
		KochCurve(length, levels, levels);
		t.right(120);
		KochCurve(length, levels, levels);
	}
	
	//Exercise 3c
	/*
	 * Recursive method that draws a Koch snowflake and attempts to center it around x,y
	 * length, a double, is the length of the curve
	 * levels, an integer, represents the detail in the curve
	 * x, an integer, represents the x position of the center of the snowflake
	 * y, an integer, represents the y position of the center of the snowflake
	 * c, a Color object, allows the turtle's pen color to be customizable
	 * */
	public static void KochSnowFlakeParamaterized(double length, int levels, int x, int y, Color c){
	t.penColor(c);
	double turtleX = (double)x - length/2;
	double turtleY = y + Math.tan(30/180*Math.PI)*length/2;
	t.up();
	t.setPosition(turtleX, turtleY);
	t.down();
		KochCurve(length, levels, levels);
		t.right(120);
		KochCurve(length, levels, levels);
		t.right(120);
		KochCurve(length, levels, levels);
	}
	public static void main(String[] args) {
		//System.out.println(Add(154));
		Fibonacci(1, 1, 13);
		
		SimpleRandom colorGenerator = new SimpleRandom(12);
		t = new Turtle();
		t.speed(0);
	//	DrawSquareSpiral(1000, 5);
	//	DrawSpiral(200, 121, 0.95, 15);
		
		//DrawColorSpiral(colorGenerator, 200, 72, .97, 10);
		//DrawWeirdStuff(8, colorGenerator);
	//	KochCurve(1000, 2, 2);
	//	KochSnowFlake(1000, 5);
		
		KochSnowFlakeParamaterized(100, 2, 0, 0, Color.RED);
	}

}
