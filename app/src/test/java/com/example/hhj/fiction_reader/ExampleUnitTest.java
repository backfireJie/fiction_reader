package com.example.hhj.fiction_reader;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hhj.fiction_reader.web_data.HtmlService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testHtml() throws Exception {
        String url = "http://www.wcsfa.com/wlkhds.php?sort=sort_order&page=2";
        String src = HtmlService.getHtml(url);

        System.out.println(src);

    }
}