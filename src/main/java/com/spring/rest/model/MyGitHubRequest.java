package com.spring.rest.model;

public record MyGitHubRequest(String owner, String repo, String authToken, String gitHubApiVer) {}
