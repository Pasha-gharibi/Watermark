package de.akelius.watermark.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("journal")
public class Journal extends Document {

    @Override
    public String jsonify() {
        return "{ content : \"journal\""
                + ", title : \"" + getTitle()
                + "\" , author : \"" + getAuthor() + "}";    }
}
