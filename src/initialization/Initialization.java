package initialization;

import enitities.Ped;
import enitities.Space;
import enitities.Wall;
import javafx.fxml.Initializable;
import params.Config;
import util.Processing;
import util.Vector;

import java.util.LinkedList;

import static util.Processing.cosθ;
import static util.Processing.sinθ;

/**
 * @Auther: srd-czk
 * @Date: 2019/3/22
 * @Description: initialization
 * @version: 1.0
 */
public class Initialization {
    private LinkedList<Ped> peds;
    private Space space;
    public Initialization() {
        this.peds = new LinkedList<>();
        this.space = new Space();
        switch (Config.VERSION) {
            case 1:
                peds.add(new Ped(new Vector(300 / Config.SCALE, 300 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 1));
                peds.add(new Ped(new Vector(380 / Config.SCALE, 310 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 2));
                peds.add(new Ped(new Vector(320 / Config.SCALE, 310 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 3));
                peds.add(new Ped(new Vector(300 / Config.SCALE, 310 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 4));
                peds.add(new Ped(new Vector(350 / Config.SCALE, 320 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 5));
                peds.add(new Ped(new Vector(410 / Config.SCALE, 280 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 6));
                peds.add(new Ped(new Vector(310 / Config.SCALE, 220 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 7));
                peds.add(new Ped(new Vector(420 / Config.SCALE, 320 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 8));
                peds.add(new Ped(new Vector(320 / Config.SCALE, 290 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 9));
                peds.add(new Ped(new Vector(295 / Config.SCALE, 315 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 10));
                peds.add(new Ped(new Vector(345 / Config.SCALE, 315 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 11));
                peds.add(new Ped(new Vector(370 / Config.SCALE, 315 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 12));
                peds.add(new Ped(new Vector(325 / Config.SCALE, 400 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 13));
                peds.add(new Ped(new Vector(375 / Config.SCALE, 300 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 14));
                peds.add(new Ped(new Vector(285 / Config.SCALE, 345 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 15));
                break;
            case 2:
                peds.add(new Ped(new Vector(300 / Config.SCALE, 300 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 1));
                peds.add(new Ped(new Vector(310 / Config.SCALE, 310 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 2));
                peds.add(new Ped(new Vector(300 / Config.SCALE, 320 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 3));
                peds.add(new Ped(new Vector(301 / Config.SCALE, 320 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 4));
                peds.add(new Ped(new Vector(311 / Config.SCALE, 320 / Config.SCALE), new Vector(500 / Config.SCALE, 500 / Config.SCALE), 5));
                peds.add(new Ped(new Vector(500 / Config.SCALE, 500 / Config.SCALE), new Vector(300 / Config.SCALE, 300 / Config.SCALE), 6));
                peds.add(new Ped(new Vector(511 / Config.SCALE, 501 / Config.SCALE), new Vector(300 / Config.SCALE, 300 / Config.SCALE), 7));
                peds.add(new Ped(new Vector(502 / Config.SCALE, 512 / Config.SCALE), new Vector(300 / Config.SCALE, 300 / Config.SCALE), 8));
                peds.add(new Ped(new Vector(502 / Config.SCALE, 502 / Config.SCALE), new Vector(300 / Config.SCALE, 300 / Config.SCALE), 9));
                peds.add(new Ped(new Vector(512 / Config.SCALE, 502 / Config.SCALE), new Vector(300 / Config.SCALE, 300 / Config.SCALE), 10));
                break;
            case 3:
                peds.add(new Ped(new Vector(353.0/ Config.SCALE, 394.0/ Config.SCALE), new Vector(525/ Config.SCALE, 245/ Config.SCALE),1));
                peds.add(new Ped(new Vector(424.0/ Config.SCALE, 407.0/ Config.SCALE), new Vector(525/ Config.SCALE, 245/ Config.SCALE),2));
                peds.add(new Ped(new Vector(373.0/ Config.SCALE, 369.0/ Config.SCALE), new Vector(525/ Config.SCALE, 245/ Config.SCALE),3));
                peds.add(new Ped(new Vector(408.0/ Config.SCALE, 383.0/ Config.SCALE), new Vector(525/ Config.SCALE, 245/ Config.SCALE),4));
                peds.add(new Ped(new Vector(453.0/ Config.SCALE, 382.0/ Config.SCALE), new Vector(525/ Config.SCALE, 245/ Config.SCALE),5));
                peds.add(new Ped(new Vector(485.0/ Config.SCALE, 376.0/ Config.SCALE), new Vector(525/ Config.SCALE, 245/ Config.SCALE),6));
                peds.add(new Ped(new Vector(444.0/ Config.SCALE, 344.0/ Config.SCALE), new Vector(525/ Config.SCALE, 245/ Config.SCALE),7));
                peds.add(new Ped(new Vector(406.0/ Config.SCALE, 325.0/ Config.SCALE), new Vector(525/ Config.SCALE, 245/ Config.SCALE),8));

                peds.add(new Ped(new Vector(485.0/ Config.SCALE, 277.0/ Config.SCALE), new Vector(370/ Config.SCALE, 385/ Config.SCALE),11));
                peds.add(new Ped(new Vector(520.0/ Config.SCALE, 323.0/ Config.SCALE), new Vector(370/ Config.SCALE, 385/ Config.SCALE),12));
                peds.add(new Ped(new Vector(492.0/ Config.SCALE, 250.0/ Config.SCALE), new Vector(370/ Config.SCALE, 385/ Config.SCALE),13));
                peds.add(new Ped(new Vector(449.0/ Config.SCALE, 223.0/ Config.SCALE), new Vector(370/ Config.SCALE, 385/ Config.SCALE),14));
                peds.add(new Ped(new Vector(523.0/ Config.SCALE, 217.0/ Config.SCALE), new Vector(370/ Config.SCALE, 385/ Config.SCALE),15));
                peds.add(new Ped(new Vector(588.0/ Config.SCALE, 269.0/ Config.SCALE), new Vector(370/ Config.SCALE, 385/ Config.SCALE),16));
                peds.add(new Ped(new Vector(564.0/ Config.SCALE, 234.0/ Config.SCALE), new Vector(370/ Config.SCALE, 385/ Config.SCALE),17));
                peds.add(new Ped(new Vector(545.0/ Config.SCALE, 269.0/ Config.SCALE), new Vector(370/ Config.SCALE, 385/ Config.SCALE),18));
                space.addWall(new Wall(new Vector(259 / Config.SCALE, 182 / Config.SCALE), new Vector(422 / Config.SCALE, 289 / Config.SCALE)));
                space.addWall(new Wall(new Vector(489 / Config.SCALE, 339 / Config.SCALE), new Vector(684 / Config.SCALE, 491 / Config.SCALE)));
                break;
            case 4:

                //将点顺时针旋转后的坐标--
                for (int i = 1; i <= 200; ++i) {
                    double tx = 21;
                    double ty = 12.5;
                    double x = Math.random() * 14.3 + 5.35;
                    double y = Math.random() * 14.3 + 5.35;
                    double r = Math.random() * 0.1 + 0.25;
                    if (i == 1) {
                        peds.add(new Ped(new Vector(x, y), new Vector(tx, ty), i));
                    } else {
                        boolean pd = true;
                        while (pd) {
                            pd = false;
                            for (Ped ped : peds) {
                                if (ped.getRadius() + r > ped.getCur_pos().distanceTo(new Vector(x, y))) {
                                    pd = true;
                                    x = Math.random() * 14.3 + 5.35;
                                    y = Math.random() * 14.3 + 5.35;
                                    r = Math.random() * 0.1 + 0.25;
                                    break;
                                }
                            }
                        }
                        Ped ped = new Ped(new Vector(x, y), new Vector(tx, ty), i);
                        ped.setRadius(r);
                        peds.add(ped);
                    }
                }
                space.addWall(new Wall(new Vector(5, 5), new Vector(5, 20)));
                space.addWall(new Wall(new Vector(5, 20), new Vector(20, 20)));
                space.addWall(new Wall(new Vector(20, 20), new Vector(20, 13)));
                space.addWall(new Wall(new Vector(20, 12), new Vector(20, 5)));
                space.addWall(new Wall(new Vector(20, 5), new Vector(5, 5)));
                space.addWall(new Wall(new Vector(20, 12), new Vector(20.5, 12)));
                space.addWall(new Wall(new Vector(20, 13), new Vector(20.5, 13)));
                break;
            case 5:
                space.addWall(new Wall(new Vector(5, 15), new Vector(11.5, 15)));
                space.addWall(new Wall(new Vector(5, 18), new Vector(11.5, 18)));
                space.addWall(new Wall(new Vector(13.5, 15), new Vector(20, 15)));
                space.addWall(new Wall(new Vector(13.5, 18), new Vector(20, 18)));
                space.addWall(new Wall(new Vector(11.5, 18), new Vector(12.5, 19)));
                space.addWall(new Wall(new Vector(12.5, 19), new Vector(13.5, 18)));
                space.addWall(new Wall(new Vector(11.5, 15), new Vector(12.5, 14)));
                space.addWall(new Wall(new Vector(12.5, 14), new Vector(13.5, 15)));
                for (int i = 0; i < 4; ++i) {
                    for (int j = 0; j < 9; ++j) {
                        peds.add(new Ped(new Vector(0.35 + j * 0.7 + 5, 0.35 + i * 0.7 + 15), new Vector(30, 16.5), i * 4 + j));
                    }
                }
                break;
            case 6:
                space.addWall(new Wall(new Vector(10, 10), new Vector(10, 15)));
                space.addWall(new Wall(new Vector(10, 15), new Vector(20, 15)));
                space.addWall(new Wall(new Vector(10, 10), new Vector(20, 10)));
                space.addWall(new Wall(new Vector(20, 10), new Vector(20, 11.75)));
                space.addWall(new Wall(new Vector(20, 15), new Vector(20, 13.25)));
                for (int i = 1; i <= 40; ++i) {
                    double tx = 21;
                    double ty = 12.5;
                    double x = Math.random() * 9.3 + 10.35;
                    double y = Math.random() * 4.3 + 10.35;
                    double r = Math.random() * 0.1 + 0.25;
                    if (i == 1) {
                        peds.add(new Ped(new Vector(x, y), new Vector(tx, ty), i));
                    } else {
                        boolean pd = true;
                        while (pd) {
                            pd = false;
                            for (Ped ped : peds) {
                                if (ped.getRadius() + r > ped.getCur_pos().distanceTo(new Vector(x, y))) {
                                    pd = true;
                                    x = Math.random() * 9.3 + 10.35;
                                    y = Math.random() * 4.3 + 10.35;
                                    r = Math.random() * 0.1 + 0.25;
                                    break;
                                }
                            }
                        }
                        Ped ped = new Ped(new Vector(x, y), new Vector(tx, ty), i);
                        ped.setRadius(r);
                        peds.add(ped);
                    }
                }
                break;
            default:
                break;
        }
    }

    public LinkedList<Ped> getPeds() {
        return peds;
    }

    public void setPeds(LinkedList<Ped> peds) {
        this.peds = peds;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }
}
