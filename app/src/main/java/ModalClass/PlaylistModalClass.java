package ModalClass;

public class PlaylistModalClass {

    private String title,id,imageurl,duration,artist;

    public PlaylistModalClass(){


    }

    public PlaylistModalClass(String title, String id, String imageurl, String duration, String artist) {
        this.title = title;
        this.id = id;
        this.imageurl = imageurl;
        this.duration = duration;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
