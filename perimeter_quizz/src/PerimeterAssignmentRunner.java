import edu.duke.*;
import java.io.File;
import java.util.Iterator;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count=0;
        for(Point p:s.getPoints())
            count+=1;
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double average=0,total=0;


        Iterator<Point> q= s.getPoints().iterator();

        Point point1=(Point)q.next();
        Point x=point1;
        Point previousPoint=point1;
           while(q.hasNext()){
               point1=previousPoint;
               Point p2=(Point)q.next();
                previousPoint=p2;
                total+=point1.distance(p2);

           }
           total+=previousPoint.distance(x);


        return total/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double max=0;
        Iterator<Point> q= s.getPoints().iterator();

        Point point1=(Point)q.next();
        Point intial_point=point1;
        Point previousPoint=point1;
        while(q.hasNext()){
            point1=previousPoint;
            Point point2=(Point)q.next();
            previousPoint=point2;
            if(point1.distance(point2)>max)
                max=point1.distance(point2);

        }
        if(previousPoint.distance(intial_point)>max)
            max=previousPoint.distance(intial_point);

        return max;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double max=0;
        for(Point p:s.getPoints()){
            if(p.getX()>max)
                max=p.getX();
        }
        return max;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double max=0;
        DirectoryResource dr= new DirectoryResource();
        for(File f:dr.selectedFiles()){
            FileResource fr =new FileResource(f);
            Shape s= new Shape(fr);
            if(getPerimeter(s)>max)
                max=getPerimeter(s);

        }
        return max;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;
        // replace this code
        double max=0;
        DirectoryResource dr= new DirectoryResource();
        for(File f:dr.selectedFiles()){
            FileResource fr =new FileResource(f);
            Shape s= new Shape(fr);
            if(getPerimeter(s)>max){
                max=getPerimeter(s);
                temp=f;
            }

        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource("datatest1.txt");
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numOfPoints=getNumPoints(s);
        double largestSide=getLargestSide(s);
        double largestX=getLargestX(s);
        double averageLength=getAverageLength(s);
        System.out.println("Number of points ="+numOfPoints);
        System.out.println("perimeter = " + length);

        System.out.println("Average length of all sides = " +averageLength);
        System.out.println("Largest side = "+largestSide);
        System.out.println("Largest x of all points = "+largestX);
    }

    public void testPerimeterMultipleFiles() {
        System.out.println("Largest perimeter = "+getLargestPerimeterMultipleFiles());
        // Put code here
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("File with Lagest perimeter = "+ getFileWithLargestPerimeter());
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
