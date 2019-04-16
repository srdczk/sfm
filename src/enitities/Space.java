package enitities;

import util.Vector;

import java.util.LinkedList;

/**
 * @Auther: srd-czk
 * @Date: 2019/3/22
 * @Description: enitities
 * @version: 1.0
 *
 * 场景类,包含所有墙
 *
 */
public class Space {
    //左上角
    private Vector begin;
    //右下角
    private Vector end;
    private LinkedList<Wall> walls;

    public Space(){
        walls = new LinkedList<>();
    }
    public Space (LinkedList<Wall> walls) {
        this.walls = walls;
    }

    public void addWall(Wall new_wall){
        walls.add(new_wall);
    }

    public LinkedList<Wall> getWalls() {
        return walls;
    }

    public Vector getBegin() {
        return this.begin;
    }

    public Vector getEnd() {
        return this.end;
    }
}
