package com.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pojo.LineMessage;

public interface LineMessageRepository extends MongoRepository<LineMessage, String> {
  
//  @Query("{name:'?0'}")
//  GroceryItem findItemByName(String name);
//  
//  @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
//  List<GroceryItem> findAll(String category);
  public List<LineMessage> findByUserId(String userId);
  
  public long count();

}
