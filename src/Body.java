
public class Body {
	
	double myXPos;
	double myYPos;
	double myXVel;
	double myYVel;
	double myMass;
	String myFileName;



public Body(double xp, double yp, double xv, double yv, double mass, String filename){
		myXPos = xp;
		myYPos = yp;	
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
}

public Body(Body p){
		myXPos = p.myXPos;
		myYPos = p.myYPos;
		myXVel = p.myXVel;
		myYVel = p.myYVel;
		myMass = p.myMass;
		myFileName = p.myFileName;
}

public double calcDistance(Body b){
	double bodyx = myXPos - b.myXPos;
    double bodyy = myYPos - b.myYPos;
    return Math.sqrt(bodyx*bodyx + bodyy*bodyy);
}

public double calcForceExertedBy(Body body){
	double r = calcDistance(body);
	double G = (6.67* Math.pow(10, -11));
	double force = (G * body.myMass * myMass )/ (r*r);
	return force;
}

public double calcForceExertedByX(Body p){  
	double forceX = calcForceExertedBy(body) * (p.myXPos - myXPos) / calcDistance(body);
	if (calcDistance(body) == 0){
		return 0;
	 }
	 return forceX;
}

public double calcForceExertedByY(Body p){  
	double forceY = calcForceExertedBy(body) * (p.myYPos - myYPos) / calcDistance(body);
	if (calcDistance(body) == 0){
		return 0;
	 }
	return forceY;
}
public double calcNetForceExertedByX(Body[] bodies){ 
	double sum = 0;
	for (int x=0; x < bodies.length; ++x){
		if (! body.equals(this)) {
		    sum += calcForceExertedByX(body[x]);
		}
	}
	return sum;
}
public double calcNetForceExertedByY(Body[] bodies){ 
	double sum = 0;
	for (int y=0; y < bodies.length; ++y){
		if (! bodies.equals(this)) {
		    sum += calcForceExertedByY(bodies[y]);
		}

	}
	return sum;	
}

public void update(double seconds, double xforce, double yforce){  
	double accelX = xforce/myMass;
	double accelY = yforce/myMass;
	myXVel += seconds * xforce / myMass;
	myYVel += seconds * yforce / myMass;
	myXPos += seconds * myXVel;
	myYPos += seconds * myYVel;
}
public void draw(){  
	StdDraw.picture(myXPos, myYPos, "images/" + myFileName);
}
}
	