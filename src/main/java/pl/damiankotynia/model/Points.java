package pl.damiankotynia.model;


import java.io.Serializable;
import java.util.List;

public class Points implements Serializable {
    private List<Point> points;

    public Points(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
