/**
 * EZTRAVEL CONFIDENTIAL
 * @Package:  eztravel.controller
 * @FileName: SampleController.java
 * 
 * <pre>
 *  Copyright 2013-2014 The ezTravel Co., Ltd. all rights reserved.
 *
 *  NOTICE:  All information contained herein is, and remains
 *  the property of ezTravel Co., Ltd. and its suppliers,
 *  if any.  The intellectual and technical concepts contained
 *  herein are proprietary to ezTravel Co., Ltd. and its suppliers
 *  and may be covered by TAIWAN and Foreign Patents, patents in 
 *  process, and are protected by trade secret or copyright law.
 *  Dissemination of this information or reproduction of this material
 *  is strictly forbidden unless prior written permission is obtained
 *  from ezTravel Co., Ltd.
 *  </pre>
 */
package com.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.pojo.LineMessage;
import com.repository.LineMessageRepository;
import com.repository.LineUserInfoRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Demo", description = "Demo API")
@RequestMapping("/rest/demo")
@RestController
public class DemoController {
  private final Logger log = LoggerFactory.getLogger(DemoController.class);

  @Autowired
  private LineMessagingClient lineMessagingClient;
  
  @Value("${line.bot.channel-token}")
  private String channelAccessToken;
  
  @Autowired
  private LineUserInfoRepository lineUserInfoRepository;
  
  @Autowired
  private LineMessageRepository lineMessageRepository;

  @Autowired
  private MongoTemplate mongoTemplate;
  
  /**
   * 
   * @param firstName
   * @param lastName
   * @return
   */
  @Operation(summary = "test", description = "test api")
  @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"), @ApiResponse(responseCode = "401", description = "Nto Found") })
  @GetMapping(value = "/getControllerCheck", produces = "application/json")
  public String getControllerCheck(@RequestParam(value = "firstName", defaultValue = "test") String firstName, @RequestParam(value = "lastName", defaultValue = "123") String lastName) {
    log.info("Route checked");
    return "route check, greeting ".concat(firstName).concat(" ").concat(lastName);
  }


  @Operation(summary = "send message to line", description = "send message to line")
  @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"), @ApiResponse(responseCode = "401", description = "Nto Found") })
  @GetMapping(value = "/sendMessage2Line", produces = "application/json")
  public String sendMessage2Line(@RequestParam(value = "someoneUserId") String someoneUserId, @RequestParam(value = "messageText") String messageText) {
//    final LineMessagingClient client = LineMessagingClient.builder(channelAccessToken).build();

    final TextMessage textMessage = new TextMessage(messageText);
    final PushMessage pushMessage = new PushMessage(someoneUserId, textMessage);

    BotApiResponse botApiResponse = null;
    try {
      botApiResponse = lineMessagingClient.pushMessage(pushMessage).get();
    } catch (InterruptedException | ExecutionException e) {
      log.error(e.getMessage(), e);
    }

    log.info("botApiResponse : {}", botApiResponse);
    return "sendMessage2Line ok";
  }
  
  @Operation(summary = "get message", description = "get message by user Id from db")
  @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"), @ApiResponse(responseCode = "401", description = "Nto Found") })
  @GetMapping(value = "/getMessage", produces = "application/json")
  public List<LineMessage> getMessage(@RequestParam(value = "userId") String userId) {
    List<LineMessage> results = null;
    // mongoTemplate
//    Query query = new Query();
//    query.addCriteria(Criteria.where("userId").is(userId));
//    results = mongoTemplate.find(query, LineMessage.class);
    
    // Repository
    results = lineMessageRepository.findByUserId(userId);
    
//    LineUserInfo userInfo = lineUserInfoRepository.findById(userId).get();
    
    return results;
  }
}
