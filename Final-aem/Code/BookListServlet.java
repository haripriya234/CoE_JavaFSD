package com.myTraining.core.servlets;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/training/searchpages")
public class BookListServlet extends SlingAllMethodsServlet {

    @Reference
    private QueryBuilder queryBuilder;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();
        JSONArray resultsArray = new JSONArray();

        Session session = request.getResourceResolver().adaptTo(Session.class);
        if (session == null) {
            try {
                jsonResponse.put("error", "JCR Session is null");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            response.getWriter().write(jsonResponse.toString());
            return;
        }

        try {
            // Query Predicate Map
            Map<String, String> predicateMap = new HashMap<>();
            predicateMap.put("path", "/content/myTraining/books"); // Search under this path
            predicateMap.put("type", "cq:Page"); // Search only cq:Page nodes
            predicateMap.put("p.limit", "-1"); // Fetch all results

            Query query = queryBuilder.createQuery(PredicateGroup.create(predicateMap), session);
            SearchResult searchResult = query.getResult();

            List<Hit> hits = searchResult.getHits();
            for (Hit hit : hits) {
                JSONObject pageObject = new JSONObject();
                pageObject.put("path", hit.getPath());

                try {
                    // Fetch `jcr:title` from `jcr:content`
                    String title = hit.getProperties().get("jcr:title", "");
                    pageObject.put("title", title);

                    // Fetch `jcr:description` from `jcr:content`
                    String description = hit.getProperties().get("jcr:description", "");
                    pageObject.put("description", description);
                } catch (RepositoryException e) {
                    pageObject.put("title", "N/A"); // Default title if not found
                    pageObject.put("description", "N/A"); // Default description if not found
                }

                resultsArray.put(pageObject);
            }

            jsonResponse.put("results", resultsArray);
        } catch (Exception e) {
            try {
                jsonResponse.put("error", "Search failed: " + e.getMessage());
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }
        }

        response.getWriter().write(jsonResponse.toString());
    }
}
