package com.dongnaoedu.dnplayer.common.data.entity;

public class MusicInfo {

    /**
     * musicId : 10
     * musicName : 七里香
     * musicUrl :
     * musicPicUrl : https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.qinyipu.com%2Fd%2Ffile%2Fjita%2Fjitapu%2F2018-04-08%2Fe522f39bb1cfff733287b283cc67bfb3.jpg&refer=http%3A%2F%2Fimg.qinyipu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1623291463&t=254840913f3d573d36837793625a6312
     * musicAlbum : 七里香
     * musicArtistName : 周杰伦
     * createTime : 2021-05-11 02:18:00
     * updateTime : 2021-05-11 02:18:03
     * state : 1
     */

    private String musicId;
    private String musicName;
    private String musicUrl;
    private String musicPicUrl;
    private String musicAlbum;
    private String musicArtistName;
    private String createTime;
    private String updateTime;
    private String state;
    private String musicDesc;

    public String getMusicDesc() {
        return musicDesc;
    }

    public void setMusicDesc(String musicDesc) {
        this.musicDesc = musicDesc;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getMusicPicUrl() {
        return musicPicUrl;
    }

    public void setMusicPicUrl(String musicPicUrl) {
        this.musicPicUrl = musicPicUrl;
    }

    public String getMusicAlbum() {
        return musicAlbum;
    }

    public void setMusicAlbum(String musicAlbum) {
        this.musicAlbum = musicAlbum;
    }

    public String getMusicArtistName() {
        return musicArtistName;
    }

    public void setMusicArtistName(String musicArtistName) {
        this.musicArtistName = musicArtistName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
