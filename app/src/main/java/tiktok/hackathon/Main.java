package tiktok.hackathon;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Objects;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import tiktok.hackathon.model.Item;
import tiktok.hackathon.repository.ItemRepository;

@SpringBootApplication
@EnableMongoRepositories
public class Main implements CommandLineRunner {
  @Autowired private ItemRepository repository;

  public static void main(String[] args) {
    System.out.println("Running...");
    SpringApplication.run(Main.class);
  }

  @Override
  public void run(String... args) throws Exception {
//    System.out.println("-------------CREATE GROCERY ITEMS-------------------------------\n");
//
//    createGroceryItems();
//
//    System.out.println("\n----------------SHOW ALL GROCERY ITEMS---------------------------\n");
//
//    showAllGroceryItems();
//
//    System.out.println("\n--------------GET ITEM BY NAME-----------------------------------\n");
//
//    getGroceryItemByName("Whole Wheat Biscuit");
//
//    System.out.println("\n-----------GET ITEMS BY CATEGORY---------------------------------\n");
//
//    getItemsByCategory("millets");
//
//    System.out.println("\n-----------UPDATE CATEGORY NAME OF SNACKS CATEGORY----------------\n");
//
//    updateCategoryName("snacks");
//
//    System.out.println("\n----------DELETE A GROCERY ITEM----------------------------------\n");
//
//    deleteGroceryItem("Kodo Millet");
//
//    System.out.println("\n------------FINAL COUNT OF GROCERY ITEMS-------------------------\n");
//
//    findCountOfGroceryItems();
//
//    System.out.println("\n-------------------THANK YOU---------------------------");
  }

  private void createGroceryItems() {
    System.out.println("Data creation started...");
    repository.save(new Item("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
    repository.save(new Item("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
    repository.save(new Item("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
    repository.save(new Item("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
    repository.save(new Item("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));
    System.out.println("Data creation complete...");
  }

  private void showAllGroceryItems() {
    repository.findAll().forEach(item -> System.out.println(getItemDetails(item)));
  }

  private void getGroceryItemByName(String name) {
    System.out.println("Getting item by name: " + name);
    Item item = repository.findItemByName(name);
    System.out.println(getItemDetails(item));
  }

  private void getItemsByCategory(String category) {
    System.out.println("Getting items for the category " + category);
    List<Item> list = repository.findAll(category);

    list.forEach(
        item ->
            System.out.println("Name: " + item.getName() + ", Quantity: " + item.getQuantity()));
  }

  private void findCountOfGroceryItems() {
    long count = repository.count();
    System.out.println("Number of documents in the collection: " + count);
  }

  private void deleteGroceryItem(String id) {
    repository.deleteById(id);
    System.out.println("Item with id " + id + " deleted...");
  }

  private String getItemDetails(Item item) {
    System.out.println(
        "Item Name: "
            + item.getName()
            + ", \nQuantity: "
            + item.getQuantity()
            + ", \nItem Category: "
            + item.getCategory());

    return "";
  }

  private void updateCategoryName(String category) {
    String newCategory = "munchies";

    List<Item> list = repository.findAll(category);

    list.forEach(
        item -> {
          item.setCategory(newCategory);
        });

    // Save all the items in database
    List<Item> itemsUpdated = repository.saveAll(list);

    if (itemsUpdated != null)
      System.out.println("Successfully updated " + itemsUpdated.size() + " items.");
  }
}
