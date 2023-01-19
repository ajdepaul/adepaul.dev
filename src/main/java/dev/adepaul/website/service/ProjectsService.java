/*
 * Copyright © 2021 Anthony DePaul
 */
package dev.adepaul.website.service;

import com.google.gson.*;
import dev.adepaul.website.model.ProjectDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class ProjectsService {

    @Autowired
    private Gson gson;

    @Value("classpath:templates/projects/featured.json")
    private Resource featuredProjectsRes;

    public List<ProjectDetails> getFeaturedProjectDetails() throws IOException {
        var featuredProjects = gson.fromJson(new InputStreamReader(featuredProjectsRes.getInputStream()), List.class)
                .stream().map(proj -> "projects/" + proj).toList();

        return getAllProjectDetails().stream()
                .filter(proj -> featuredProjects.contains(proj.getArticleLink()))
                .sorted(Comparator.comparingInt(p -> featuredProjects.indexOf(p.getArticleLink())))
                .toList();
    }

    // TODO look into caching https://www.javatpoint.com/spring-boot-caching
    public List<ProjectDetails> getAllProjectDetails() throws IOException {
        Resource[] projDetailsResources= new PathMatchingResourcePatternResolver()
                .getResources("templates/projects/*/details.json");

        return Arrays.stream(projDetailsResources).map(res -> {

            try {
                return gson.fromJson(new InputStreamReader(res.getInputStream()), ProjectDetails.class);
            } catch (IOException e) {
                throw new RuntimeException(e); // should never happen
            }
        }).toList();
    }
}
