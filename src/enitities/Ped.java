package enitities;

import java.util.LinkedList;

import params.Config;
import util.Vector;

import static params.Config.REACH;

/**
 * @Auther: srd-czk
 * @Date: 2019/3/21
 * @Description: enitities
 * @version: 1.0
 *
 * 行人类
 *
 */
public class Ped {
    //目标点
    private Vector target;
    //现在所处位置
    private Vector cur_pos;
    //实际速度
    private Vector cur_vel;
    //方向
    private Vector dir;
    //编号
    private int id;
    //半径
    private double radius;
    //质量
    private double mass;
    //弛豫时间--τ
    private double relax_time;
    //期望速度大小
    private double desired_speed;
    //期望速度
    private Vector disied_vel;
    //行人的排斥参数k
    private double k;
    //行人的摩擦系数
    private double kappa;
    //判断是否到达目标点
    private boolean get_target;
    //计算受到的总力大小
    private double crowd_pressure;
    //保存最近到达的地点
    private LinkedList<Vector> recent_pos;
    //保存所有所有速度以便计算均值
    private LinkedList<Double> actual_speeds;
    //初始化--质量80kg, 初始速度（0，0) , 半径在[0.25, 0.35), 赋给行人id以便于对单个行人可执行画出轨迹等操作
    public Ped(Vector start, Vector target, int id) {
        this.radius = Config.MIN_RADIUS + Math.random() * (Config.MAX_RADIUS - Config.MIN_RADIUS);
        this.mass = Config.MASS;
        this.relax_time = Config.TAU;
        this.desired_speed = Config.DESIRED_VEL;
        this.cur_pos = start;
        this.target = target;
        this.get_target = false;
        this.id = id;
        this.cur_vel = new Vector(0, 0);
        this.dir = target.normalize(this.cur_pos);
        this.disied_vel = dir.product(desired_speed);
        this.k = Config.K;
        this.kappa = Config.KAPPA;
        this.crowd_pressure = 0;
        this.recent_pos = new LinkedList<>();
        this.actual_speeds = new LinkedList<>();
    }

    //判断行人是否到达目标点
    public void checkIfFinished (){
        if(cur_pos.getX() - (REACH) < target.getX() && cur_pos.getX() + (REACH) > target.getX()){
            if(cur_pos.getY() - (REACH) < target.getY() && cur_pos.getY() + (REACH) > target.getY()){
                get_target = true;

                System.out.println(this.id + ".ped: reached target!");
            }
        }
    }


    public Vector calculateForceFrom(Obstacle o) {
        return null;
    }
    public Vector calculateForceFrom(Ped ped) {
        double A = Config.A;
        double B = Config.B;

        if (ped.get_target) {
            return new Vector(0, 0);
        }
        double r = this.radius + ped.radius;
        double d = this.cur_pos.distanceTo(ped.cur_pos);
        //计算社会力,原文 repulsive interaction force Ai  * exp[(rij - dij) / Bi] * nij
        Vector n = this.cur_pos.normalize(ped.cur_pos);
        Vector social_force = n.product(A * Math.exp((r - d) / B));
        //计算接触当r > d 时候产生的body force--原文 a ‘body force’ k * (rij - dij) * nij
        Vector body_force = n.product(this.k * Math.max(0.0, (r - d)));
        //计算产生的'滑动摩擦力'--原文 a ‘sliding friction force’ κ * (rij - dij) * Δvtji * tij
        Vector t = new Vector((-1) * n.getY(), n.getX());
        double delv = (ped.cur_vel.subtract(this.cur_vel)).product(t);
        Vector sliding_friction = t.product(delv * this.kappa * Math.max(0.0, (r - d)));
        return social_force.add(body_force).add(sliding_friction);
    }

    public Vector calculateForceFrom(Wall wall) {
        if (this.get_target) return new Vector(0, 0);
        double A = Config.A;
        double B = Config.B / 10.0;
        if (!wall.isIn(cur_pos, radius)) {
            return new Vector(0, 0);
        }
        double r = this.radius;
        double d = wall.distanceTo(this.cur_pos);
        //与计算人的类似--
        Vector cross_point = wall.crossPoint(this.cur_pos);
        Vector n = cur_pos.normalize(cross_point);
        Vector t = new Vector((-1) * n.getY(), n.getX());
        Vector body_force = n.product(A * Math.exp((r - d) * 20) + this.k * Math.max(0.0, r - d));
        Vector sliding_friction = t.product(Math.max(0.0, r - d) * kappa * cur_vel.product(t));
        return body_force.subtract(sliding_friction);
    }


    public double meanVelocity() {
        double res = 0;
        for (double v : actual_speeds) {
            res += v;
        }
        return res / (double) actual_speeds.size();
    }

    //社会力模型中的第一项
    public Vector updateVelocity() {
        return this.disied_vel.subtract(this.cur_vel).product(mass / relax_time);
    }

    public Vector getTarget() {
        return target;
    }

    public void setTarget(Vector target) {
        this.target = target;
    }

    public Vector getCur_pos() {
        return cur_pos;
    }

    public void setCur_pos(Vector cur_pos) {
        this.cur_pos = cur_pos;
    }

    public Vector getCur_vel() {
        return cur_vel;
    }

    public void setCur_vel(Vector cur_vel) {
        this.cur_vel = cur_vel;
    }

    public Vector getDir() {
        return dir;
    }

    public void setDir(Vector dir) {
        this.dir = dir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getRelax_time() {
        return relax_time;
    }

    public void setRelax_time(double relax_time) {
        this.relax_time = relax_time;
    }

    public double getDesired_speed() {
        return desired_speed;
    }

    public void setDesired_speed(double desired_speed) {
        this.desired_speed = desired_speed;
    }

    public Vector getDisied_vel() {
        return disied_vel;
    }

    public void setDisied_vel(Vector disied_vel) {
        this.disied_vel = disied_vel;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getKappa() {
        return kappa;
    }

    public void setKappa(double kappa) {
        this.kappa = kappa;
    }

    public boolean isGet_target() {
        return get_target;
    }

    public void setGet_target(boolean get_target) {
        this.get_target = get_target;
    }

    public double getCrowd_pressure() {
        return crowd_pressure;
    }

    public void setCrowd_pressure(double crowd_pressure) {
        this.crowd_pressure = crowd_pressure;
    }

    public LinkedList<Vector> getRecent_pos() {
        return recent_pos;
    }

    public void setRecent_pos(LinkedList<Vector> recent_pos) {
        this.recent_pos = recent_pos;
    }

    public LinkedList<Double> getActual_speeds() {
        return actual_speeds;
    }

    public void setActual_speeds(LinkedList<Double> actual_speeds) {
        this.actual_speeds = actual_speeds;
    }
}
