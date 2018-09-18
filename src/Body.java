
public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	public Body(double xp, double yp, double xv, double yv, double mass, String filename) { //lists parameters
		myXPos = xp;  // Defining within the constructor
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}

	public Body(Body b) {  // Copying into new constructor (so you know how to make objects out of other objects)
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myFileName= b.myFileName;
	}

	public double getX() { //Methods have to be public and get values 
		return myXPos;
	}

	public double getY() {
		return myYPos;
	}

	public double getXVel() {
		return myXVel;
	}

	public double getYVel() {
		return myYVel;
	}

	public double getMass() {
		return myMass;
	}

	public String getName() {
		return myFileName;
	}
	public double calcDistance(Body b) {
		double dx = myXPos - b.myXPos; // taking the differences between x positions
		double dy = myYPos - b.myYPos; // taking the difference between y positions 
		return Math.sqrt((dx *dx + dy*dy));
  }
	 public double calcForceExertedBy(Body p) { // (Body p) copies into a new constructor
		double G = 6.67e-11;
		double m1 = myMass;
		double m2 = p.myMass; // copies in a new mass
		double distance = calcDistance(p); // (p) uses new constructor -- thats why you don't use b in this case 
	    return (G * m1 * m2) / (distance * distance);
	 }
	 public double calcForceExertedByX(Body p) {
		 double F = calcForceExertedBy(p);
		 double dx = -1*(myXPos - p.myXPos); 
		 double distance = calcDistance(p);
		 return (F * dx) / distance;
	 }
	 public double calcForceExertedByY(Body p) {
		 double F = calcForceExertedBy(p);
		 double dy = -1*(myYPos - p.myYPos);
		 double distance = calcDistance(p);
		 return (F * dy) / distance;
		 }
	 public double calcNetForceExertedByX(Body[] bodies) {
		 double sum = 0;
		 for(Body b : bodies) { //iterates through bodies b equals element
			 if(! b.equals(this)) { // Body cannot equal itself -- checking if b equals this
				 sum += this.calcForceExertedByX(b) ; // sums forces between home body and foreign body
				 }
			 }
		 return sum;
	 }
	 public double calcNetForceExertedByY(Body[] bodies) {
		 double sum = 0;
		 for(Body b : bodies) { // iterates through the bodies - b is individual element
			 if(! b.equals(this)) { // this equals home body and b equals foreign body, need both for force between
				 sum += this.calcForceExertedByY(b); // sums forces between home and foreign body
			 }
		 }
	 return sum;
	 }
	 public void update(double deltaT, double xforces, double yforces) {
		 double Fx = xforces;
		 double Fy = yforces;
		 double Ax =  Fx / myMass;
		 double Ay = Fy / myMass; 
		 double nvx = myXVel + deltaT * Ax; // new vx
		 double nvy = myYVel + deltaT * Ay; // new vy
		 double nx = myXPos + deltaT * nvx; // new x value
		 double ny = myYPos + deltaT * nvy; // new y value
		 // name new instance variables
		  myXPos = nx;
		  myYPos = ny;
		  myXVel = nvx;
		  myYVel = nvy;
	 }
	 public void draw() {
		 StdDraw.picture(myXPos, myYPos, "images/" + myFileName);
		 
	 }
}
