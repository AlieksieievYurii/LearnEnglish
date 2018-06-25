package CheckingWords;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConvertStringToStandartTest
{
    @Test
    public void ConvertTest()
    {
        String[][] words_traslations = {
                {"bring","Перенести"},
                {"STOP","стоп"},
                {"huRT","дРаТуВаТи"},
                {"Convert","конвертувати"}};

        String[][] res = {
                {"Bring","Перенести"},
                {"Stop","Стоп"},
                {"Hurt","Дратувати"},
                {"Convert","Конвертувати"}};

        for(int i = 0; i < words_traslations.length; i++)
        {
            assertArrayEquals(res[i],ConvertStringToStandart.convertToStandart(words_traslations[i]));
        }
    }
}