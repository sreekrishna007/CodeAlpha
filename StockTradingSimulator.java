import java.util.*;

class Stock{
  String symbol;
  double price;

  public Stock(String symbol,double price){
    this.symbol=symbol;
    this.price=price;
  }

  public void updatePrice(){
    this.price+=(Math.random()-0.5)*10;

    if(this.price<1)this.price=1;
  }
}

class Portfolio{
  Map<String,Integer>holdings=new HashMap<>();
  double balance;

  public Portfolio(double balance){
    this.balance=balance;
  }
  
  public void buyStock(Stock stock,int quantity){
    double cost=stock.price*quantity;
    if(cost>balance){
       System.out.println("Insufficient funds.");
       return;
    }
    holdings.put(stock.symbol,holdings.getOrDefault(stock.symbol,0)+quantity);
    balance-=cost;
    System.out.println("Bought"+quantity+"shares of"+stock.symbol);
  }

  public void sellStock(Stock stock,int quantity){
    if(!holdings.containsKey(stock.symbol)||holdings.get(stock.symbol)<quantity){
      System.out.println("Not enough shares to sell.");
      return;
  }
  holdings.put(stock.symbol,holdings.get(stock.symbol)-quantity);
  balance+=stock.price*quantity;
  if(holdings.get(stock.symbol)==0)holdings.remove(stock.symbol);
  System.out.println("Sold"+quantity+"shares of"+stock.symbol);
 }
  public void displayPortfolio(Map<String,Stock>market){
    System.out.println("\nYour Portfolio:");
    for(String symbol:holdings.keySet()){
      System.out.println(symbol+":"+holdings.get(symbol)+"shares@$"+market.get(symbol).price);
    }
    System.out.println("Balance:$"+balance);
   }
  }

  public class StockTradingSimulator{
    public static void main(String[]args){
      Scanner scanner=new Scanner(System.in);
      Map<String,Stock>market=new HashMap<>();
      market.put("AAPL",new Stock("AAPL",150));
      market.put("GOOGL",new Stock("GOOGL",2800));
      market.put("TSLA",new Stock("TSLA",700));

      Portfolio portfolio=new Portfolio(5000);

      while(true){

        for(Stock stock:market.values()){
            stock.updatePrice();
        }

        System.out.println("\nMarket Prices:");
        for(Stock stock:market.values()){
            System.out.println(stock.symbol+":$"+stock.price);
        }

        System.out.println("\n1.Buy Stock");
        System.out.println("2.Sell Stock");
        System.out.println("3.View Portfolio");
        System.out.println("4.Exit");
        System.out.print("Choose an option:");

        int choice=scanner.nextInt();
        if(choice==4)break;
     
        switch(choice){
          case 1:
            System.out.print("Enter stock symbol to buy:");
            scanner.nextLine();
            String buySymbol=scanner.nextLine().trim().toUpperCase();
            System.out.print("Enter quantity:");
            int buyQuantity=scanner.nextInt();
            if(market.containsKey(buySymbol)){
               portfolio.buyStock(market.get(buySymbol),buyQuantity);
             }else{
               System.out.println("Stock not found.");
             }
             break;

         case 2:
           System.out.print("Enter stock symbol to sell:");
           scanner.nextLine();
           String sellSymbol=scanner.nextLine().trim().toUpperCase();
           System.out.print("Enter quantity:");
           int sellQuantity=scanner.nextInt();
           if(market.containsKey(sellSymbol)){
              portfolio.sellStock(market.get(sellSymbol),sellQuantity);
           }else{
              System.out.println("Stock not found.");
           }
           break;

        case 3:
          portfolio.displayPortfolio(market);
          break;

        default:
          System.out.println("Invalid choice.");
       }
     }

     System.out.println("Exiting...Final balance:$"+portfolio.balance);
     scanner.close();
    }
}
