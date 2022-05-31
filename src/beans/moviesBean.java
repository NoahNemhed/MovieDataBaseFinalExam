package beans;

import helpers.jsonHelper;
import helpers.keyvaluepair;

import java.util.ArrayList;

public class moviesBean {

    private int movieId;
    private int dirId;
    private String movieTitle;
    private int releaseDate;
    private int length;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getDirId() {
        return dirId;
    }

    public void setDirId(int dirId) {
        this.dirId = dirId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String toJson() {
        ArrayList<keyvaluepair> datalist = new ArrayList<keyvaluepair>();
        datalist.add(new keyvaluepair("Title", this.movieTitle));
        datalist.add(new keyvaluepair("ReleaseYear", Integer.toString(this.releaseDate)));
        datalist.add(new keyvaluepair("LengthMinutes", Integer.toString(this.length)));
        return jsonHelper.toJsonObject(datalist);
    }

    @Override
    public String toString() {
        return "moviesBean{" +
                "movieId=" + movieId +
                ", dirId=" + dirId +
                ", movieTitle='" + movieTitle + '\'' +
                ", releaseDate=" + releaseDate +
                ", length=" + length +
                '}';
    }
}
