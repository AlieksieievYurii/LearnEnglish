package CheckingWords;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckWordTest
{
    @Test
    public void testCheckingWord()
    {
       String[] words = {"bring","Broke"," ","    ","1231232","rest","1$#%$#","figure out","перенести","сламать "};
       int[] errors = {0,0,1,1,1,0,1,1,0,1};

        for(int i = 0; i < words.length; i++)
        {
            assertEquals(errors[i],CheckWord.checkText(words[i]));
        }
    }

}