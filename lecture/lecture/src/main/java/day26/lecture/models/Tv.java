package day26.lecture.models;

import java.util.List;

import org.bson.Document;

import day26.lecture.services.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tv {

    private int id;
    private String name;
    private String url;
    private String summary;
    private Number rating;

    public static Tv create(Document doc) {
        Tv tv = new Tv();

        tv.setId(doc.getInteger(Constants.FIELD_ID));
        tv.setName(doc.getString(Constants.FIELD_NAME));
        tv.setUrl(doc.getString(Constants.FIELD_URL));
        tv.setSummary(doc.getString(Constants.FIELD_SUMMARY));
        tv.setRating(doc.getEmbedded(List.of("rating", "average"),Number.class));

        //OR

        // Document d = (Document) doc.get("rating.average");
        // tv.setRating(d.getDouble("average").floatValue());

        return tv;
    }
    
}
