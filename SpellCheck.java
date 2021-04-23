import java.util.Scanner;
import java.lang.Math;
import java.util.HashMap;
public class SpellCheck
{
    static final int alphabet_size = 26;       //as we are considering only lowerCase letters   
    static int points = 0;      //to keep track of points
    static Scanner sc = new Scanner(System.in);    //Scanner class for inputs
    static HashMap<String, Boolean> visitedWord = new HashMap<String, Boolean>();
    static int numberOfAttempts = 0;
    static int won = 0;

    static class TrieNode  //trie Node
    {
        TrieNode[] children = new TrieNode[alphabet_size];
        boolean isEndOfWord;
        TrieNode()   //trie node constructor
        {
            isEndOfWord = false;
            for(int i=0;i<alphabet_size;i++)
            {
                children[i] = null;          //intializing all null
            } 
        }
    };


    static TrieNode root;

    static void insert(String key)
    {
        int level,len = key.length(),index; //level will keep track of index of each letter of the word and index will keep track of the children index
        TrieNode temp = root;
        for(level=0;level<len;level++)
        {
            index = key.charAt(level) - 'a'; //storing ascii of the letter
            if(temp.children[index]==null)
            {
                temp.children[index] = new TrieNode();
            }
            temp = temp.children[index];
        }
        temp.isEndOfWord = true;   //now the last letter of the word will be a leaf node
    }


    static void easy()
    {
        System.out.print("\n-->  ");
        String key = sc.next().toLowerCase();
        if(searchWord(key))
        {
            if(visitedWord.get(key)==null)
            {
                visitedWord.put(key,true);
                points+=100;
                System.out.println("You got it!\nPoints  :  "+points);
                if(points>=300)
                {
                    System.out.println("-----------------------------------------------------------------\n\nCongratulations!! You have advanced to the medium level\n-----------------------------------------------------------------\n");
                    mediumLevel();
                    return;
                }
                easy();
            }
            else
            {
                System.out.println("\n!!This word has already been formed, Try again\n");
                easy();
            }
        }
        else
        {
            System.out.println("\nOops! Try again");
            numberOfAttempts++;
            if(numberOfAttempts>=2)
            {
                System.out.println("\n\nYOU'VE LOST THE GAME (⌣́_⌣̀)");
                return;
            }
            easy();
        }   
    }


    static void mediumLevel()
    {
        randomWords(2);
    }
    static void hardLevel()
    {
        randomWords(3);
    }


    static void medium()
    {
        System.out.print("\n-->  ");
        String key = sc.next().toLowerCase();
        if(searchWord(key))
        {
            if(visitedWord.get(key)==null)
            {
                visitedWord.put(key,true);
                points+=100;
                System.out.println("You got it!\nPoints  :  "+points);
                if(points>=600)
                {
                    System.out.println("-----------------------------------------------------------------\n\nCongratulations!! You have advanced to the Hard level\n-----------------------------------------------------------------");
                    hardLevel();
                    return;
                }
                medium();
            }
            else
            {
                System.out.println("!!This word has already been formed, Try again\n");
                medium();
            }
        }
        else
        {
            System.out.println("Oops! Try again");
            numberOfAttempts++;
            if(numberOfAttempts>=3)
            {
                System.out.println("YOU'VE LOST THE GAME (⌣́_⌣̀)");
                return;
            }
            medium();
        }   
    }

   
    static void hard()
    {
        if(won == 1)
            {return;}
        else 
        {
            System.out.print("\n-->  ");
            String key = sc.next().toLowerCase();
            if(searchWord(key))
            {
                if(visitedWord.get(key)==null)
                {
                    visitedWord.put(key,true);
                    points+=100;
                    System.out.println("You got it!\nPoints  :  "+points);
                    if(points>=1000)
                    {
                        won = 1;
                        System.out.println("Congratulations!! YOU HAVE WON (ノ・∀・)ノ"+won);
                    }
                    else
                    {
                        hard();
                    }
                }
                else
                {
                    System.out.println("!!This word has already been formed, Try again\n");
                    hard();
                }
                
            }
            else
            {
                System.out.println("Oops! Try again");
                numberOfAttempts++;
                if(numberOfAttempts>=3)
                {
                    System.out.println("YOU'VE LOST THE GAME (⌣́_⌣̀)");
                    return;
                }
                hard();
            }
        }
           
    }
    

    static boolean searchWord(String key)
    {
        int level,len = key.length(), index;
        TrieNode temp = root;
        for(level = 0;level<len;level++)
        {
            index = key.charAt(level) - 'a';  //ascii key
            if(temp.children[index] == null)
                return false;
            temp = temp.children[index];
        }
        return (temp!=null && temp.isEndOfWord);
    }
    static void randomWords(int level)
    {
        int randomWord;
        //if easy pass 1
        if(level==1)
        {
            String wordsChoice[] = {"e t a", "a f r i" , "a t c", "x b o t", "l y d a"};
            randomWord = (int)(Math.random()*5);
            System.out.println(wordsChoice[randomWord]+"  : These are your letters, lets see if how many you can find! ");
            easy();
        }
        else if(level==2)
        {
            String wordsChoice[] = {"v d e s a t", "d o c e" , "d l o u c", "n b o c a", "e r i t"};
            randomWord = (int)(Math.random()*5);
            System.out.println(wordsChoice[randomWord]+"  : These are your letters, lets see if how many you can find! ");
            medium();
        }
        else if(level==3)
        {
            String wordsChoice[] = {"h c s a e", "l g e a e" , "c o l e l e g", "r p g o r m a", "r n t e e i d"};
            randomWord = (int)(Math.random()*5);
            System.out.println(wordsChoice[randomWord]+"  : These are your letters, lets see if how many you can find! ");
            hard();
            
        }
    }
    public static void main(String[] args)    //driver code
    {
        System.out.println("---------------------Welcome Player!---------------------\n");
        char c;
        root = new TrieNode();
        String words[] = {"sad","save","saved","vase","sat","date","set","cold","cloud","loud","duo","could","ludo", "eagle","gel","leg","lag",
                          "age","eel","algae","glee","lea","alee","tea","eat","ate","at","box","bot","ox","air","far","fair","lady","day","lay",
                          "tire","tier","rite","it","iter","tie","college","cello","log","ego","cell","geo","clog","cole","ogle","loge","program",
                          "armor","map","ramp","prom","mop","gram","roam","rap","roar","rite","tire","tier","chase","each","ache","ace","sea",
                          "case","ash","she","has","aces","cash","code","coed","eco","ode","doe","deco","rented","entire","tender","insert",
                          "enter","tired","diner","dirt","rent","deer","teen","tree","edit","need","nerd","cab","ban","can","bacon","an","cob",
                          "cat","act"};
        for(int i=0;i<words.length;i++)
        {
            insert(words[i]);      //creating a trie
        }
        do
        {
            visitedWord.clear();    //clearing the hash map
            System.out.println("Choose a level to start with!\n1.EASY\n2.MEDIUM\n3.HARD");
            int choice = sc.nextInt();
            won = 0;
            switch(choice)
            {
                case 1: points = 0;
                        numberOfAttempts = 0;
                        randomWords(1);
                        break;
                case 2: points = 0;
                        numberOfAttempts = 0;
                        randomWords(2);
                        break;
                case 3: points = 0;
                        numberOfAttempts = 0;
                        randomWords(3);
                        break;
            }
            System.out.println("\n\nPress Y to continue playing");
            c = sc.next().charAt(0);
        }while(c=='Y'||c=='y');
        System.out.println("Thank you for playing!"); 
    }
}


