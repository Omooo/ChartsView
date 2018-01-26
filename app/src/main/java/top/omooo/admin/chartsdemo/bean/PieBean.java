package top.omooo.admin.chartsdemo.bean;

/**
 * Created by Omooo on 2018/1/25.
 */

public class PieBean {
    private String name;
    private int data;
    private int color = 0;
    private float angle = 0;
    private float percentage = 0;

    public PieBean(String name, int data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
