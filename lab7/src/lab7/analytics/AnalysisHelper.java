/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7.analytics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lab7.entities.Comment;
import lab7.entities.Post;
import lab7.entities.User;

/**
 *
 * @author harshalneelkamal
 */
public class AnalysisHelper {
    // find user with Most Likes
    // TODO
    public void userWithMostLikes(){
        System.out.println("---------------------------User With Most Likes--------------------------");
        Map<Integer,Integer> userLikesCount =  new HashMap<>();
        Map<Integer, User> users = DataStore.getInstance().getUsers();
                
        for(User user : users.values()){
            for(Comment c : user.getComments()){
                int likes =0;
                if(userLikesCount.containsKey(user.getId())){
                    likes = userLikesCount.get(user.getId());
                }
                likes += c.getLikes();
                userLikesCount.put(user.getId(),likes);
            }
        }
        int max=0;
        int maxId=0;
        for(int id: userLikesCount.keySet()){
            if(userLikesCount.get(id)>max){
                max = userLikesCount.get(id);
                maxId = id;
                
            }
        }
        
        System.out.println("User with most likes: "+max+"\n"+users.get(maxId));
        System.out.println("");
    }
    
    // find 5 comments which have the most likes
    public void getFiveMostLikedComments(){
        System.out.println("---------------------------Five Most Liked Comments--------------------------");
        Map<Integer,Comment> comments = DataStore.getInstance().getComments();
        List<Comment> commentList = new ArrayList<>(comments.values());
        
        Collections.sort(commentList,new Comparator<Comment>(){
            @Override
            public int compare(Comment c1, Comment c2) {
                return c2.getLikes() - c1.getLikes();
            }
        
        });
        
        System.out.println("5 most liked Comments: ");

        for( int i =0 ; i<commentList.size()&&i<5;i++){  
            System.out.println(commentList.get(i));
        }
        
        System.out.println("");
    }
    
    //find average Number of likes per comment
    public void getAverageNumberOfLikesPerComment(){
        
        System.out.println("---------------------------Average Number of Likes Per Comment--------------------------");
        float average=0;
        int likes=0;
        int commentCount=0;
        Map<Integer,Comment> comments = DataStore.getInstance().getComments();
        List<Comment> commentList = new ArrayList<>(comments.values());
        
        for(Comment c : commentList){       
            likes=likes+c.getLikes();
            commentCount++;
        }
        average=(float)likes/commentCount;
        System.out.println("Average Number of Likes Per Comment : "+average);
        System.out.println("");
    } 
    
    //find post with most liked comments
    public void getPostWithMostLikedComments(){
        System.out.println("---------------------------Posts with Most Liked Comments--------------------------");
        Map<Integer,Comment> comments = DataStore.getInstance().getComments();
        List<Comment> commentList = new ArrayList<>(comments.values());
        
        Collections.sort(commentList,new Comparator<Comment>(){
            @Override
            public int compare(Comment c1, Comment c2) {
                return c2.getLikes() - c1.getLikes();
            }
        });
        
        System.out.println("Post with most liked comments "+commentList.get(0).getLikes()+" is: "+commentList.get(0).getPostId()); 
        System.out.println("");
    }
    
    //find the post with most number of comments
    public void getPostWithMostComments(){
        
    System.out.println("---------------------------Posts with Most Comments--------------------------");
    Map<Integer,Post> posts = DataStore.getInstance().getPosts();
    Map<Integer,Integer> countMap =  new HashMap<>();
    int count=0;
        
    for(Post p : posts.values()){
         count = p.getComments().size();
         countMap.put(p.getPostId(), count);
         countMap.put(p.getPostId(), p.getComments().size());       
    }   
//    for(Map.Entry<Integer, Integer> en : countMap.entrySet()) { 
//            System.out.println("Key = " + en.getKey() +  
//                          ", Value = " + en.getValue()); 
//        } 
    int maxCommentCount=0;
    for( Integer i: countMap.values()){
        if(i>maxCommentCount){
            maxCommentCount=i;
        }
    }
    for(Map.Entry<Integer, Integer> en : countMap.entrySet()) { 
        if(en.getValue()==maxCommentCount){    
        System.out.println("Post with most comments : PostID "+en.getKey()+" with "+en.getValue()+" comments");
        }
    } 
    System.out.println("");
}
    
    //find the top five inactive users based on Post
    public void getTopFiveInactiveUsersBasedOnPost(){
        System.out.println("---------------------------TOP 5 Inactive Users Based on Total Posts--------------------------");
        Map<Integer,Integer> userPostCount = new HashMap<>();
        Map<Integer,Post> posts = DataStore.getInstance().getPosts();
        
        for(Post p : posts.values()){
            if(userPostCount.containsKey(p.getUserId())){
                int countPost = userPostCount.get(p.getUserId());
                userPostCount.put(p.getUserId(), countPost+1);
            }else{
                userPostCount.put(p.getUserId(), 1);
            }
        }
        Map<Integer,Integer> sortedUserPostCount = sortByComparator(userPostCount, true);
        int i=0;
        for(Map.Entry<Integer, Integer> en : sortedUserPostCount.entrySet()) { 
            if(i==5){
            break;
            }else{
             System.out.println("User = " + en.getKey()+" with Posts: "+en.getValue());
             i++;
            }
         }
        
        System.out.println("");
    }
    
    //find top Five inactive users based on total comments
    public void getTop5InactiveUsersBasedOnTotalComments(){
          
        System.out.println("---------------------------TOP 5 Inactive Users Based on Total Comments--------------------------");
         Map<Integer,Integer> totalComments =  new HashMap<>();
         Map<Integer, User> users = DataStore.getInstance().getUsers();
         int count=0;
         
         Map<Integer,Integer> countMap =  new HashMap<>();
    
         for(User user : users.values()){
         count = user.getComments().size();
         countMap.put(user.getId(), count);
         countMap.put(user.getId(), user.getComments().size());            
    }
//         for(Map.Entry<Integer, Integer> en : countMap.entrySet()) { 
//            System.out.println("Key = " + en.getKey() +  
//                          ", Value = " + en.getValue()); 
//        }               
        Map<Integer, Integer> sortedMapDesc = sortByComparator(countMap, true);
//          for(Map.Entry<Integer, Integer> en : sortedMapDesc.entrySet()) { 
//            System.out.println("Key = " + en.getKey() +  
//                          ", Value = " + en.getValue()); 
//        }        
         int i =0;
         
         System.out.println("Top 5 Inactive Users based on Total Comments");
         
         for(Map.Entry<Integer, Integer> en : sortedMapDesc.entrySet()) { 
            if(i==5){
            break;
            }else{
             System.out.println("User = " + en.getKey()+" with comments: "+en.getValue());
             i++;
            }
         }
         System.out.println(""); 
    }

        //Find the top five inactive users overall based on the sum of likes, comments and posts
        public void getTopFiveInactiveUsersOverall(){
            
            System.out.println("---------------------------TOP 5 Inactive Users Overall(Sum of comments posts and likes)--------------------------");
        Map<Integer,Integer> userAllCount = new HashMap<>();
        Map<Integer,Post> posts = DataStore.getInstance().getPosts();
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        
        //Post Count
        for(Post p : posts.values()){
            if(userAllCount.containsKey(p.getUserId())){
                int countPost = userAllCount.get(p.getUserId());
                userAllCount.put(p.getUserId(), countPost+1);
            }else{
                userAllCount.put(p.getUserId(), 1);
            }
        }
        
        //Comments and likes Count
        for(Comment c : comments.values()){
            int userID = c.getUserId();
            if(userAllCount.containsKey(userID)){
                int countPost = userAllCount.get(userID);
                userAllCount.put(userID,countPost + 1 + c.getLikes());
            }else{
                userAllCount.put(userID, 1 + c.getLikes());
            }
        }
        
        Map<Integer,Integer> sortedUserAllCount = sortByComparator(userAllCount,true);
        System.out.println("Top 5 inactive users overall (Sum of comments, posts and likes)");
        int i = 1;
        for (Entry<Integer,Integer> entry : sortedUserAllCount.entrySet()){
            if(i<=5){
                System.out.println("User Id = " + entry.getKey() + ", Overall Count = " + entry.getValue());
                i++;
            }else{
            break;
        }        
        }  
        
        System.out.println("");
    } 
    
    //find the top five proactive users overall based on the sum of comments,likes and posts
     public void getTop5ProactiveUsersOverall(){
         
        System.out.println("---------------------------TOP 5 Proactive Users Overall(Sum of comments posts and likes)--------------------------");
        
         Map<Integer,Integer> countMap =  new HashMap<>();
         Map<Integer, User> users = DataStore.getInstance().getUsers();
         Map<Integer, Comment> comments = DataStore.getInstance().getComments();
         Map<Integer,Post> posts = DataStore.getInstance().getPosts();
         
         int count=0;
        
         
         for(User user : users.values()){
     
         count = user.getComments().size();
        
       
         countMap.put(user.getId(), count);
         countMap.put(user.getId(), user.getComments().size());  
          
    }
         
           
               for(Comment c : comments.values()){
                  if(countMap.containsKey(c.getUserId())){
                     int likesCount = countMap.get(c.getUserId());
                     likesCount=likesCount+c.getLikes();
                     countMap.put(c.getUserId(),likesCount);
                  }
                    
                else{
                      countMap.put(c.getUserId(),c.getLikes());
                      
                  }
               
            }
               
               
                for(Post p : posts.values()){
                  if(countMap.containsKey(p.getUserId())){
                     int PostCount = countMap.get(p.getUserId());
                     PostCount=PostCount+1;
                     countMap.put(p.getUserId(),PostCount);
                  }
                    
                else{
                      countMap.put(p.getUserId(),1);
                      
                  }
               
            }
           
                
                Map<Integer, Integer> sortedMapDesc = sortByComparator(countMap, false);
           
        int i=0;
        for(Map.Entry<Integer, Integer> en : sortedMapDesc.entrySet()) { 
            if(i==5){
            break;
            }else{
             System.out.println("User = " + en.getKey()+" with Overall Count: "+en.getValue());
             i++;
            }
         }
         
         
         
         
       System.out.println(""); 
    }
       
     
     //method for sorting hashMap
    private static Map<Integer, Integer> sortByComparator(Map<Integer, Integer> unsortMap, final boolean order)
    {
        List<Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<Integer, Integer>>()
        {
            public int compare(Entry<Integer, Integer> o1,
                    Entry<Integer, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        for (Entry<Integer, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }


        return sortedMap;
    }
    
}



    
