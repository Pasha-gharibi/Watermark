package de.akelius.watermark.domain;

import de.akelius.watermark.enm.Topic;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("book")
public class Book extends Document {

    @Enumerated(EnumType.STRING)
    private Topic topic;

    public Book() {
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String jsonify() {
        return "{ content : \"book\""
                + ", title : \"" + getTitle()
                + "\" , author : \""
                + getAuthor() + "\" , topic : \""
                + topic + "\"" + '}';    }
}
