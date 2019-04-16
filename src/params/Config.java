package params;

/**
 * @Auther: srd-czk
 * @Date: 2019/3/21
 * @Description: params
 * @version: 1.0
 */
public class Config {
    //控制停止
    public static boolean ISGOING = false;
    //控制场景
    public static int VERSION = 1;
    //期望速度,单位m / s
    public static double DESIRED_VEL = 7.6;
    //摩擦系数单位kg / (m * s)
    public static final double KAPPA = 240000;
    //单位 kg  / (s * s)
    public static final double K = 120000;
    //单位N
    public static final double A = 2000;
    //单位m
    public static final double B = 0.08;
    //弛豫时间,单位s
    public static final double TAU = 0.5;
    public static final double REACH = 0.36;
    public static final double MASS = 80;
    public static final double MAX_RADIUS = 0.35;
    public static final double MIN_RADIUS = 0.25;
    public static final double SCALE = 25;
    public static final double DELT = 0.05;
    public static final double Width = 0;
    public static final double Height = 0;
    public static final double MAX_FORCE = 500;
    public static final double MAX_SPEED = 1.8;
    public static final double RANDOM_MAX = 100;
    public static final double REACH_WALL = 2;
}
