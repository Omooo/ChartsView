package top.omooo.admin.chartsdemo.bean;

/**
 * Created by Omooo on 2018/1/28.
 */

public class RadarMapBean {
    private int count;
    private String[] names;
    private int[] values;

    public RadarMapBean(int count, String[] names, int[] values) {
        this.count = count;
        this.names = names;
        this.values = values;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }
}
