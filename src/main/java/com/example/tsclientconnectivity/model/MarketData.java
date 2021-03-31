package com.example.tsclientconnectivity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarketData {
    @JsonProperty("LAST_TRADED_PRICE")
    public double lAST_TRADED_PRICE;
    @JsonProperty("BID_PRICE")
    public double bidPRICE;
    @JsonProperty("SELL_LIMIT")
    public int sELL_LIMIT;
    @JsonProperty("MAX_PRICE_SHIFT")
    public int mAX_PRICE_SHIFT;
    @JsonProperty("TICKER")
    public String tICKER;
    @JsonProperty("ASK_PRICE")
    public double aSK_PRICE;
    @JsonProperty("BUY_LIMIT")
    public int bUY_LIMIT;
}


//    @GetMapping()
//    public String market(){
//        RestTemplate restTemplate=new RestTemplate();
//        String link="https://exchange.matraining.com/md";
//        ResponseEntity<MarketData[]> responseEntity=restTemplate.getForEntity(link,MarketData[].class);
//        MarketData[] userArray = responseEntity.getBody();
//        //assert userArray != null;
//        for (MarketData user :userArray) {
//            System.out.println(user.tICKER);
//        }
//        return "";
//    }
