package gui;

import enitities.Ped;
import enitities.Space;
import enitities.Wall;
import initialization.Initialization;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import params.Config;
import util.Vector;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import static params.Config.SCALE;

/**
 * @Auther: srd-czk
 * @Date: 2019/3/22
 * @Description: gui
 * @version: 1.0
 */
public class Scene implements Initializable {
    private int allSum;
    private File file;
    private PrintWriter printWriter;
    private int timesteps;
    //画布
    @FXML
    private Canvas canvas;
    @FXML
    private Button start;
    //输出速度信息
    @FXML
    private Button print;
    //选择场景
    @FXML
    private ComboBox<String> sceneType;
    //画笔
    private GraphicsContext graphicsContext;
    private Timeline timelineLoop;
    //初始化
    private Initialization initialization;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //场景设置
        sceneType.getItems().addAll("One group, one direction, no wall", "Two groups, opposite direction, no wall", "Two groups, opposite direction, wall", "Classic bottleneck flow", "Escape route with a wider area", "Single exit room");
        sceneType.getSelectionModel().select("Two groups, opposite direction, no wall");
        initialization = new Initialization();
        timesteps = 0;
        allSum = 0;
        try {
            printWriter = new PrintWriter(new FileWriter(new File("f:\\czk\\sfm\\lib\\ped.txt"), true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawAllScene();
    }

    public void drawAllScene() {
        graphicsContext = canvas.getGraphicsContext2D();
        canvas.setScaleY(-1);
        canvas.setScaleX(1);
        loopInit();
    }

    private void loopInit() {
        Duration duration = Duration.millis(10);
        KeyFrame frame = getNextKeyFrame(duration);
        timelineLoop = new Timeline();
        timelineLoop.setCycleCount(Animation.INDEFINITE);
        timelineLoop.getKeyFrames().add(frame);
        timelineLoop.play();
        timelineLoop.pause();
    }


    private KeyFrame getNextKeyFrame(Duration duration) {
        KeyFrame keyFrame = new KeyFrame(duration, e -> {
            removeAll();
            drawSpace(initialization.getSpace());
            try {
                pedsUpdate(initialization.getPeds(), initialization.getSpace().getWalls());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            drawPeds(initialization.getPeds());
        });
        return keyFrame;
    }

    private void removeAll() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    public void onStart() {
        allSum = 0;
        timesteps = 0;
        if (!Config.ISGOING) {

            switch (sceneType.getSelectionModel().getSelectedItem()){
                case "One group, one direction, no wall":
                    Config.VERSION = 1;
                    break;
                case "Two groups, opposite direction, no wall":
                    Config.VERSION = 2;
                    break;
                case "Two groups, opposite direction, wall":
                    Config.VERSION = 3;
                    break;
                case "Classic bottleneck flow":
                    Config.VERSION = 4;
                    break;
                case "Escape route with a wider area":
                    Config.VERSION = 5;
                    break;
                case "Single exit room":
                    Config.VERSION = 6;
                    break;
                default:
                    break;
            }
            initialization = new Initialization();
            timelineLoop.play();
            Config.ISGOING = true;
            printWriter.println("场景"+ Config.VERSION + ":" + "期望速度 " + Config.DESIRED_VEL + " m/s");
            printWriter.flush();
        } else {
            Config.ISGOING = false;
            timelineLoop.pause();
        }
    }

    public void onPrint() {
        for (Ped ped : initialization.getPeds()) {
            System.out.println(ped.getId() + "号人员 平均速度" + ped.meanVelocity());
        }
    }

    private void pedsUpdate(LinkedList<Ped> peds, LinkedList<Wall> walls) throws IOException {
        for (Ped ped : peds) {
            if (ped.isGet_target()) continue;
            Vector target = ped.getTarget();
            Vector cur_pos = ped.getCur_pos();
            Vector dir = target.normalize(cur_pos);
            ped.setDir(dir);
            ped.setDisied_vel(dir.product(ped.getDesired_speed()));
            Vector change_velocity_force = ped.updateVelocity();

            Vector fromPed = new Vector(0, 0);
            Vector fromWall = new Vector(0, 0);

            for (Ped ped1 : peds) {
                if (ped1 == ped) continue;
                fromPed = fromPed.add(ped.calculateForceFrom(ped1));
            }

            for (Wall wall : walls) {
                fromWall = fromWall.add(ped.calculateForceFrom(wall));
            }
            Vector sum_force = change_velocity_force.add(fromPed).add(fromWall);
            Vector acceleration = sum_force.product(1 / ped.getMass());
            Vector new_vel = ped.getCur_vel().add(acceleration.product(Config.DELT));
            if(new_vel.length() >= Config.MAX_SPEED){
                new_vel = new_vel.normalize(new Vector(0,0));
                new_vel = new_vel.product(Config.MAX_SPEED);
            }
            if (new_vel.length() <= 0.01) {
                new_vel = new Vector(Math.random() * 0.5, Math.random() * 1);
            }
            ped.setCur_vel(new_vel);
            cur_pos = cur_pos.add(new_vel.product(Config.DELT));
            ped.setCur_pos(cur_pos);
            ped.checkIfFinished();
            if (ped.isGet_target()) {
                allSum++;
//                printWriter.println("Person" + ped.getId() + " get target at " + timesteps * Config.DELT + " s");
                printWriter.flush();
            }
            ped.getActual_speeds().add(new_vel.length());
            ped.setCrowd_pressure(fromPed.length());
        }
        if (allSum == peds.size()) {
            printWriter.println("Finished at desired_speed " + " " + Config.DESIRED_VEL + " m/s sum time: " + timesteps * Config.DELT + " s");
            printWriter.flush();
            printWriter.println();
            printWriter.flush();
            Config.DESIRED_VEL += 0.1;
            printWriter = new PrintWriter(new FileWriter(new File("f:\\czk\\sfm\\lib\\ped.txt"), true));
            onStart();
            onStart();
        }
        timesteps++;
    }

    private void drawPeds(LinkedList<Ped> peds) {
        for (Ped ped : peds) {
            if (ped.isGet_target()) {
                continue;
            }
            double pressure = Math.min(Config.MAX_FORCE, ped.getCrowd_pressure());

            Color pedColor = new Color(0 + (pressure / Config.MAX_FORCE), 1  -(pressure / Config.MAX_FORCE), 0, 1);
            graphicsContext.setFill(pedColor);
            graphicsContext.fillOval(Config.Width + ped.getCur_pos().getX() * SCALE - ped.getRadius() * SCALE, Config.Height + ped.getCur_pos().getY() * SCALE- ped.getRadius() * SCALE,  ped.getRadius() * 2 * SCALE, ped.getRadius() * 2 * SCALE);

            graphicsContext.setFill(new Color(0, 0, 0, 1));
            graphicsContext.fillOval(Config.Width + ped.getTarget().getX() * SCALE - 2.5, Config.Height + ped.getTarget().getY() * SCALE - 2.5, 5, 5);

        }
    }
    private void drawSpace (Space space){
        drawWalls(space.getWalls());
    }

    private void drawWalls (LinkedList<Wall> walls){
        for(Wall wall : walls){
            Vector begin = wall.getBegin();
            Vector end = wall.getEnd();
            graphicsContext.strokeLine(Config.Width + begin.getX() * SCALE, Config.Height + begin.getY() * SCALE, Config.Width + end.getX() * SCALE, Config.Height + end.getY() * SCALE);
        }
    }
}
