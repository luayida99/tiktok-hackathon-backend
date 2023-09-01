package tiktok.hackathon.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tiktok.hackathon.model.Item;

public interface ItemRepository extends MongoRepository<Item, String> {

  @Query("{name:'?0'}")
  Item findItemByName(String name);

  @Query(value = "{category:'?0'}", fields = "{'name' : 1, 'quantity' : 1}")
  List<Item> findAll(String category);

  public long count();
}
