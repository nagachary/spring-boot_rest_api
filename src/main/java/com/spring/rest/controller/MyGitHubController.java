package com.spring.rest.controller;

import static org.springframework.util.StringUtils.hasLength;

import com.spring.rest.exception.ErrorResponse;
import com.spring.rest.exception.GitHubServiceException;
import com.spring.rest.model.MyGitHubRequest;
import com.spring.rest.service.MyGitHubService;
import java.net.URISyntaxException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/github")
public class MyGitHubController {
  Logger logger = LoggerFactory.getLogger(MyGitHubController.class);

  @Autowired private final MyGitHubService gitHubService;

  public MyGitHubController(MyGitHubService gitHubService) {
    this.gitHubService = gitHubService;
  }

  @GetMapping("/{owner}/{repoName}/commits")
  public ResponseEntity<List> readGitHubDetails(
      @PathVariable("owner") String owner,
      @PathVariable("repoName") String repoName,
      @RequestHeader("Authorization") String authKey,
      @RequestHeader("X-GitHub-Api-Version") String gitHubApiVersion)
      throws GitHubServiceException {
    logger.info("readGitHubDetails in MyGitHubController:");
    if (!hasLength(owner)
        || !hasLength(repoName)
        || !hasLength(authKey)
        || !hasLength(gitHubApiVersion)) {
      throw new GitHubServiceException(
          new ErrorResponse("Bad Data", HttpStatus.BAD_REQUEST.toString()));
    }

    ResponseEntity<List> responseEntity = null;
    try {
      responseEntity =
          gitHubService.<ResponseEntity<List>>getRepoCommitDetails(
              new MyGitHubRequest(owner, repoName, authKey, gitHubApiVersion));
    } catch (URISyntaxException e) {
      throw new GitHubServiceException(
          new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()));
    }

    if (null != responseEntity && responseEntity.getStatusCode().is2xxSuccessful()) {
      logger.info("response :{}", responseEntity.getBody());
    } else {
      logger.info("API call failed");
    }
    return responseEntity;
  }
}
