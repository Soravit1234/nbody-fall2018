	

/**
 * @author Soravit Kitsiriboon
 * 
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {

	public static void main(String[] args) { 
		double T = 2000000.0;
		double dt = 25000.0;
		String pfile = "data/planets.txt";
		if (args.length > 2) {
			T = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}
		double radius = readRadius(pfile);
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");

		Body[] bodys = readBodies(pfile); 
		for (int i = 0; i < bodys.length; i++) { //reads file and draws bodys
			bodys[i].draw();
		}
		double time = 0.0;
		while (time <= T) {
			double[] xForces = new double[bodys.length];
			double[] yForces = new double[bodys.length];
			for (int i = 0; i < bodys.length; i++) {
				xForces[i] = bodys[i].calcNetForceExertedByX(bodys);
				yForces[i] = bodys[i].calcNetForceExertedByY(bodys);
			}
			for (int i = 0; i < bodys.length; i++) {
				bodys[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.setScale(-radius, radius); 
			StdDraw.picture(0, 0, "images/starfield.jpg"); 
			for (int i = 0; i < bodys.length; i++) {
				bodys[i].draw(); 
			}
			StdDraw.show(10); 
			time += dt;
		}

		System.out.printf("%d\n", bodys.length);  
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodys.length; i++) {
			System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", bodys[i].myXPos, bodys[i].myYPos,
					bodys[i].myXVel, bodys[i].myYVel, bodys[i].myMass, bodys[i].myFileName);
		}
	}

	public static double readRadius(String fname) { 
		File file = new File(fname);
		Scanner s = null;
		try {
			s = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.nextDouble();
		return s.nextDouble();

	}

	public static Body[] readBodies(String fname) { 
		File file = new File(fname);
		Scanner s = null;
		try {
			s = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int arrayNum = s.nextInt();
		Body[] bodyArray = new Body[arrayNum];
		s.nextDouble();
		for (int i = 0; i < arrayNum; i++) {
			bodyArray[i] = new Body(s.nextDouble(), s.nextDouble(), s.nextDouble(), s.nextDouble(), s.nextDouble(),
					s.next());
		}

		return bodyArray;
	}

}