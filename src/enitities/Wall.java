package enitities;

import util.Vector;
/**
 * @Auther: srd-czk
 * @Date: 2019/3/21
 * @Description: enitities
 * @version: 1.0
 *
 * 墙类----两个点组成的直线
 *
 */
public class Wall {
    private Vector begin;
    private Vector end;

    public Wall(Vector begin, Vector end) {
        this.begin = begin;
        this.end = end;
    }


    //计算交点
    public Vector crossPoint(Vector vector) {
        //直线方程y = a * x + b
        if (begin.getX() != end.getX()) {
            //计算斜率
            double a_wall = (end.getY() - begin.getY()) / (end.getX() - begin.getX());
            double b_wall = begin.getY() - a_wall * begin.getX();
            if (a_wall != 0) {
                double a = (-1) / a_wall;
                double b = vector.getY() - a * vector.getX();
                //交点
                return new Vector((b - b_wall) / (a_wall - a), a_wall * ((b - b_wall) / (a_wall - a))+ b_wall);
            } else {
                //横线
                return new Vector(vector.getX(), begin.getY());
            }
        } else {
            //竖线
            return new Vector(begin.getX(), vector.getY());
        }
    }

    //判断点是否在线段的范围内
    public boolean isIn(Vector point, double r) {
        Vector cross = crossPoint(point);
        Vector v = end.normalize(begin);
        Vector c = v.product(r / 2.0);
        Vector begin_ = begin.subtract(c);
        Vector end_ = end.add(c);
        return cross.getX() >= Math.min(begin_.getX(), end_.getX())
                && cross.getX() <= Math.max(begin_.getX(), end_.getX())
                && cross.getY() >= Math.min(begin_.getY(), end_.getY())
                && cross.getY() <= Math.max(begin_.getY(), end_.getY());
    }
    //点到线的距离
    public double distanceTo(Vector vector) {
        Vector point = crossPoint(vector);
        return point.distanceTo(vector);
    }



    public Vector getBegin() {
        return begin;
    }

    public void setBegin(Vector begin) {
        this.begin = begin;
    }

    public Vector getEnd() {
        return end;
    }

    public void setEnd(Vector end) {
        this.end = end;
    }
}
