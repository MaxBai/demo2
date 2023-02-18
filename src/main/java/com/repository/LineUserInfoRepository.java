package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pojo.LineUserInfo;

public interface LineUserInfoRepository extends MongoRepository<LineUserInfo, String> {
  
//  @Query("{name:'?0'}")
//  GroceryItem findItemByName(String name);
//  
//  @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
//  List<GroceryItem> findAll(String category);
  
  public long count();

}
