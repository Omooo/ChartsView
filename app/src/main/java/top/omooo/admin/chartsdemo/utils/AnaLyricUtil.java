package top.omooo.admin.chartsdemo.utils;

/**
 * Created by Omooo on 2018/2/5.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import top.omooo.admin.chartsdemo.bean.LyricInfoBean;

/**
 * 解析歌词
 */
public class AnaLyricUtil {
    private List<LyricInfoBean> mInfoBeans;
    private String title = "", artist = "", album = "";

    private List<LyricInfoBean> getLyricResource(InputStream inputStream, String charsetName) {
        if (inputStream != null) {
            mInfoBeans = new ArrayList<>();
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charsetName);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = null;
                try {
                    while ((line = reader.readLine()) != null) {
                        analyzeLyric(mInfoBeans, line);
                    }
                    reader.close();
                    inputStream.close();
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return mInfoBeans;
    }

    private void analyzeLyric(List<LyricInfoBean> beans, String line) {
        int index = line.lastIndexOf("]");

        //标题
        if (line != null && line.startsWith("[ti:")) {
            title = line.substring(4, index).trim();
            return;
        }

        //歌手
        if (line != null && line.startsWith("[ar:")) {
            artist = line.substring(4, index).trim();
            return;
        }

        //专辑
        if (line != null && line.startsWith("[al:")) {
            album = line.substring(4, index).trim();
            return;
        }

        //歌词
        if (line != null && index == 9 && line.trim().length() > 10) {
            String content = line.substring(10, line.length());
            long minute = Long.parseLong(line.substring(1, 3));
            long second = Long.parseLong(line.substring(4, 6));
            long millisecond = Long.parseLong(line.substring(7, 9));
            long startTime = millisecond + second * 1000 + minute * 60 * 1000;
            beans.add(new LyricInfoBean(artist, title, album, content, startTime));
        }
    }
}
