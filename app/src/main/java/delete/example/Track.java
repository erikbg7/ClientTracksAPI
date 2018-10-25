package delete.example;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Track {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("singer")
    @Expose
    private String singer;
    @SerializedName("title")
    @Expose
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
