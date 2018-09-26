package tech.niocoders.com.jokegenerator;

import java.util.HashMap;
import java.util.Random;

public class jokeClass {
    public HashMap<Integer,String> jokesData;
    //lets construct a constructor and set data to hashmap
    public jokeClass()
    {//all jokes were extracted from http://jokes.cc.com/funny-fat/jq7mjv/a-j--jamal--good-place-to-eat
      jokesData =  new HashMap<>();
      jokesData.put(0,"I can't believe I made it anywhere creatively, though, because I was raised by two loving and supportive parents. Nothing squashes creativity more than unconditional love and support from a functional household. If you have kids, sh*t on their dreams a little bit.");
      jokesData.put(1,"A bear walks into a bar and says to the bartender, \"I'll have a pint of beer and a.......... packet of peanuts.\"\n" +
              "\n" +
              "The bartender asks, \"Why the big pause?\"");
      jokesData.put(2,"A man and his pet giraffe walk into a bar and start drinking. As the night goes on, they get drunk, and the giraffe finally passes out. The man decides to go home.\n" +
              "\n" +
              "As he's leaving, the man is approached by the barkeeper who says, \"Hey, you're not gonna leave that lyin' here, are ya?\"\n" +
              "\n" +
              "\"Hmph,\" says the man. \"That's not a lion -- it's a giraffe.\"");
      jokesData.put(3,"You ever accidentally go up to a real big fat person, and you accidentally ask them for a good place to eat? And they look at you and say they don't know. And you're looking at them, like, 'You do know. I bet if I follow you for an hour, we gonna be eatin'. '");
    }
    public String tellAJoke(){
        //lets construct a random joke
       Random ran = new Random();
        return jokesData.get(ran.nextInt(jokesData.size()-1 + 0));
    }

}
