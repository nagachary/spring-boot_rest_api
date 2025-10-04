package com.spring.rest.service;

import com.spring.rest.model.MyGitHubRequest;
import java.net.URISyntaxException;

public interface MyGitHubService {

  <T> T getRepoCommitDetails(MyGitHubRequest request) throws URISyntaxException;
}
