package top.omooo.admin.chartsdemo.bean;

/**
 * Created by SSC on 2018/6/14.
 */

public class BillBean {
    private boolean isChick;
    private int iconId;
    private String text;

    public BillBean(boolean isChick, int iconId, String text) {
        this.isChick = isChick;
        this.iconId = iconId;
        this.text = text;
    }

    public boolean isChick() {
        return isChick;
    }

    public void setChick(boolean chick) {
        isChick = chick;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
