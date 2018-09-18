	

/**
 * @author YOUR NAME THE STUDENT IN 201
 * 
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	
	/**
	 * Read the specified file and return the radius
	 * @param fname is name of file that can be open
	 * @return the radius stored in the file
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static double readRadius(String fname) throws FileNotFoundException  {
		Scanner s = new Scanner(new File(fname));
	
		// TODO: read values at beginning of file to
		// find the radius
		int Number = s.nextInt(); // whole number (integer)
		double Radius = s.nextDouble();  // reads the radius
		
		s.close();
		
		// TODO: return radius read
		return Radius;	 // Returns the radius value
	}
	
	/**
	 * Read all data in file, return array of Celestial Bodies
	 * read by creating an array of Body objects from data read.
	 * @param fname is name of file that can be open
	 * @return array of Body objects read
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static Body[] readBodies(String fname) throws FileNotFoundException {
		
			Scanner s = new Scanner(new File(fname));
			
			// TODO: read # bodies, create array, ignore radius
			int nb = s.nextInt(); // # bodies to be read (1-5)
			s.next(); // skips second number
			Body[] nbArray = new Body[nb]; // use Body[] because that is the array of body objects, then named nb from above
			for(int k=0; k < nb; k++) {
				nbArray[k] = new Body(s.nextDouble(), s.nextDouble(), s.nextDouble(), s.nextDouble(), s.nextDouble(), s.next());
				// used new name of array and then inputed with s.nextDouble() for 5 numbers in line and then s.next() goes to new line
				// TODO: read data for each body
				// construct new body object and add to array
				
			}
			
			s.close();
			
			// TODO: return array of body objects read
			return nbArray;
	}
	public static void main(String[] args) throws FileNotFoundException{
		double totalTime = 1000000000.0;
		double dt = 1000000.0;
		
		String fname= "./data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			fname = args[2];
		}	
		
		Body[] bodies = readBodies(fname);
		for(int k = 0; k < bodies.length; k++) {  // reading through the file given and putting it into the main method
		bodies[k].draw(); // This begins to draw the planets in the program
		}
		double radius = readRadius(fname);
		
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
	
		for(double t = 0.0; t < totalTime; t += dt) {
			
			// TODO: create double arrays xforces and yforces
			// to hold forces on each body
			double[] xforces = new double [bodies.length]; // creates new double array for xforces
			double[] yforces = new double [bodies.length];
			
			// TODO: loop over all bodies, calculate
			// net forces and store in xforces and yforces
			for(int k = 0; k < bodies.length; k++) { // loops through bodies 
				xforces[k] = bodies[k].calcNetForceExertedByX(bodies); // stores net force x in xforces 
				yforces[k] = bodies[k].calcNetForceExertedByY(bodies);
			}                                                           // stores net force y in yforces
			// TODO: loop over all bodies and call update
			// with dt and corresponding xforces, yforces values
			for(int k = 0; k < bodies.length; k+=1) {
						bodies[k].update(dt, xforces[k], yforces[k]);
			}
			StdDraw.picture(0,0,"images/starfield.jpg");
			
			// TODO: loop over all bodies and call draw on each one
			for(int k=0; k < bodies.length; k++)
				bodies[k].draw();
			
			StdDraw.show(10);
		}
		
		// prints final values after simulation
		
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              bodies[i].getX(), bodies[i].getY(), 
		                      bodies[i].getXVel(), bodies[i].getYVel(), 
		                      bodies[i].getMass(), bodies[i].getName());	
		}
	}
}
