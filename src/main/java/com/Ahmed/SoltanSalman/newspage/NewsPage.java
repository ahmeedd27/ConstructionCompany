package com.Ahmed.SoltanSalman.newspage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "NewsPage")
public class NewsPage {
    private ObjectId _id; // that is so important for the NewsService functionality because when you retrieve it
    // to update the array of news in the newsPage it the object of NewsPage must have id because it is being
    // updated if mongo does not finding any id will create new document with the new updates
    private Header header;
    private List<NewP> news;
}
