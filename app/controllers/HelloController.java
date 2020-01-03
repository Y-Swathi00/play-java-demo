package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Singleton
public class HelloController extends Controller {

    HashMap< String,String> hm =new HashMap< String,String>();

    public Result doSomethingFancy(){
        return  ok("Naseem Shaik");
    }

    public Result helloUser(String name){
        String message="Hello,"+ name;
        return ok(message);
    }

    public Result helloUserWithDetails(){
        JsonNode requestJson= request().body().asJson();
        String firstName=null;
        String lastName=null;
        if(requestJson.has("first_name")){
            firstName=requestJson.get("first_name").asText();
        }
        if(requestJson.has("last_name")){
            lastName=requestJson.get("last_name").asText();
        }
        if(firstName!=null && lastName!=null)
        {
            String message="Hello, "+ firstName+" " +lastName;
            return ok(message);
        }
        return badRequest("provide first_name and last_name");
    }

    public Result helloUserWithMap(){
        JsonNode requestJson= request().body().asJson();
        String name=null;
        String id=null;
        if(requestJson.has("Name")){
            name=requestJson.get("Name").asText();
        }
        if(requestJson.has("Id")){
            id=requestJson.get("Id").asText();
        }
        if(name!=null && id!=null){
            String msg="User Created , Hello "+ name;
            hm.put(name,id);
            System.out.println(hm);
            return ok(msg);
        }
        return badRequest("provide name and id");
      }
    public Result helloUserWithName(String name){
        String roll=hm.get(name);
        String message="Hello,"+ name+"your id is "+ roll;
        return ok(message);
    }



}
