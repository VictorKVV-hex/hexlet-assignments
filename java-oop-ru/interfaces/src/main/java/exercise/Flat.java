package exercise;

public class Flat implements Home {
    double area;
    double balconyArea;
    int floor;
    public Flat (double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }
    @Override
    public double getArea() {
        return area + balconyArea;
    }
    @Override
    public int compareTo(Home another) {
//        return (area < another.getArea()) ? -1 : ((area == another.getArea()) ? 0 : 1);
        return Double.compare((area + balconyArea), another.getArea());
    }

    @Override
    public String toString() {
        return "Квартира площадью " + (area+balconyArea) + " метров на " + floor + " этаже";
    }
}

