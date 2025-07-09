/*
 * Copyright © 2021 Anthony DePaul
 */
package dev.adepaul.website.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
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
        final var projectObjs = gson.fromJson(
                new InputStreamReader(featuredProjectsRes.getInputStream()),
                new TypeToken<List<String>>() {}.getType()
        );
        @SuppressWarnings("unchecked") final var projects = (List<String>) projectObjs;
        final var featuredProjects = projects.stream().map(proj -> "projects/" + proj).toList();

        return getAllProjectDetails().stream()
                .filter(proj -> featuredProjects.contains(proj.getArticleLink()))
                .sorted(Comparator.comparingInt(p -> featuredProjects.indexOf(p.getArticleLink())))
                .toList();
    }

    public List<ProjectDetails> getAllProjectDetails() throws IOException {
        Resource[] projDetailsResources = new PathMatchingResourcePatternResolver()
                .getResources("templates/projects/*/details.json");

        return Arrays.stream(projDetailsResources).map(res -> {
                    try (final var reader = new InputStreamReader(res.getInputStream())) {
                        return gson.fromJson(reader, ProjectDetails.class);
                    } catch (IOException e) {
                        throw new RuntimeException("Error reading project details from resource: " + res.getFilename(), e);
                    } catch (JsonParseException e) {
                        throw new JsonParseException("Error parsing JSON from resource: " + res.getFilename(), e);
                    }
                })
                .filter(ProjectDetails::isPublished)
                .toList();
    }
}
