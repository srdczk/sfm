package enitities;

import util.Vector;

/**
 * @Auther: srd-czk
 * @Date: 2019/3/28
 * @Description: enitities
 * @version: 1.0
 * 圆形障碍物类,位置和半径--
 *
 */
public class Obstacle {
    private Vector pos;
    private double r;
    public Obstacle(double x, double y, double r) {
        this.pos = new Vector(x, y);
        this.r = r;
    }
    public Obstacle(Vector v, double r) {
        this.pos = v;
        this.r = r;
    }

}
