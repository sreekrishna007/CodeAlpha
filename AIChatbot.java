import java.util.*;

public class AIChatbot {
  private static final Map<String,String>predefinedResponses=new HashMap<>();
 
  public AIChatbot(){
    predefinedResponses.put("Hi","Hello! How can I assist you today?");
    predefinedResponses.put("Hello","Hi there! How can I help?");
    predefinedResponses.put("How are you","I'm just a bot,but I'm doing great!How about you?");
    predefinedResponses.put("Your name","I'm an AI chatbot created in Java.");
    predefinedResponses.put("What can you do","I can answer simple questions and chat with you!");
    predefinedResponses.put("Give me some motivation","You are stronger than you think and capable of more than you imagine.");
    predefinedResponses.put("Bye","Goodbye!Have a great day!");
    predefinedResponses.put("Thanks","You're welcome!Feel free to ask anything.");
  }

  public String getResponse(String userInput){
    userInput=userInput.toLowerCase().trim();

   for(String key: predefinedResponses.keySet()){
    if(userInput.equalsIgnoreCase(key.trim())){
       return predefinedResponses.get(key);
    }
   }

    if(userInput.contains("weather")){
       return "I can't predict the weather,but you can check a weather website!";
    }else if(userInput.contains("help")){
      return "Sure!How can I assist you?";
    }else if(userInput.contains("joke")){
      return getRandomJoke();
    }

    return "I'm not sure about that.Can you ask something else?";
  }

  private String getRandomJoke(){
    String[]jokes={"Why don't scientists trust atoms?Because they make up everything!",
                   "Parallel lines have so much in common. It's a shame they'll never meet."};
    return jokes[new Random().nextInt(jokes.length)];
  }
  
  public static void main(String[]args){
    Scanner scanner=new Scanner(System.in);
    AIChatbot chatbot=new AIChatbot();

    System.out.println("AI Chatbot:Hello! Type 'bye' to exit.");

    while(true){
      System.out.print("You:");
      String userInput=scanner.nextLine();

      if(userInput.equalsIgnoreCase("bye")){
         System.out.println("AI Chatbot:"+chatbot.getResponse(userInput));
         break;
      }

      System.out.println("AI Chatbot:"+chatbot.getResponse(userInput));
    }

    scanner.close();
   }
}
 
