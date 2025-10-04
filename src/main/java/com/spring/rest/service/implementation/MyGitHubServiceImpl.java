package com.spring.rest.service.implementation;

import com.spring.rest.configuration.BeansConfiguration;
import com.spring.rest.model.MyGitHubRequest;
import com.spring.rest.service.MyGitHubService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Import(BeansConfiguration.class)
public class MyGitHubServiceImpl implements MyGitHubService {
  Logger logger = LoggerFactory.getLogger(MyGitHubServiceImpl.class);

  @Qualifier("REST_TEMPLATE")
  private final RestTemplate restTemplate;

  @Autowired
  public MyGitHubServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public ResponseEntity<List> getRepoCommitDetails(MyGitHubRequest request)
      throws URISyntaxException {
    logger.info("getRepoCommitDetails in GitHubServiceImpl");
    HashMap<Object, Object> map = new HashMap<>();

    map.put("Authorization", request.authToken());
    map.put("X-GitHub-Api-Version", request.gitHubApiVer());
    map.put("Accept", "application/vnd.github+json");

    String url =
        "http://api.github.com/repos/" + request.owner() + "/" + request.repo() + "/commits";
    return restTemplate.exchange(new URI(url), HttpMethod.GET, new HttpEntity<>(map), List.class);
  }
}
