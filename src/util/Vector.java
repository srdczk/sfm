package util;


/**
 * @Auther: srd-czk
 * @Date: 2019/3/21
 * @Description: util
 * @version: 1.0
 *
 * 向量类--用于力,速度的计算,和位置的计算和表示。
 *
 */
public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {

        this.x = x;
        this.y = y;
    }

    //两点之间距离
    public double distanceTo(Vector vector) {
        return Math.sqrt(Math.pow(x - vector.x, 2) + Math.pow(y - vector.y, 2));
    }

    //向量的模
    public double length() {
        return Math.sqrt(x * x + y * y);
    }
    //归一化
    public Vector normalize (Vector vector){
        double distance = distanceTo(vector);
        double x = this.x - vector.x;
        double y = this.y - vector.y;
        if(distance != 0){
            x = x / distance;
            y = y / distance;
        }
        return new Vector(x, y);
    }

    //两个向量相乘
    public double product(Vector vector){
        return x * vector.x + y * vector.y;
    }

    //向量数字相乘
    public Vector product(double d) {
        return new Vector(x * d, y * d);
    }

    //两个向量相加
    public Vector add(Vector vector) {
        return new Vector(x + vector.x, y + vector.y);
    }

    //两个向量相减去
    public Vector subtract(Vector vector) {
        return new Vector(x - vector.x, y - vector.y);
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}
