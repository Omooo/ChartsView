package top.omooo.admin.chartsdemo.bean;

/**
 * Created by Omooo on 2018/2/5.
 */

import java.util.List;

/**
 * 歌词文件信息
 */
public class LyricInfoBean {
    private String songArtist;
    private String songTitle;
    private String songAlbum;
    private String songContent;
    private long startTime;

    public LyricInfoBean(String songArtist, String songTitle, String songAlbum, String songContent, long startTime) {
        this.songArtist = songArtist;
        this.songTitle = songTitle;
        this.songAlbum = songAlbum;
        this.songContent = songContent;
        this.startTime = startTime;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(String songAlbum) {
        this.songAlbum = songAlbum;
    }

    public String getSongContent() {
        return songContent;
    }

    public void setSongContent(String songContent) {
        this.songContent = songContent;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
