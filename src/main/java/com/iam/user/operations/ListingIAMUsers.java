package com.iam.user.operations;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.ListUsersRequest;
import com.amazonaws.services.identitymanagement.model.ListUsersResult;
import com.amazonaws.services.identitymanagement.model.User;

public class ListingIAMUsers {
	public static void main(String[] args) {

		final AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder.standard()
				.withCredentials(
						new AWSStaticCredentialsProvider(new BasicAWSCredentials("access-key", "secret-key")))
				.withRegion(Regions.EU_CENTRAL_1).build();

		boolean done = false;
		ListUsersRequest request = new ListUsersRequest();

		while (!done) {
			ListUsersResult response = iam.listUsers(request);

			for (User user : response.getUsers()) {
				System.out.format("Retrieved user %s\n", user.getUserName());
			}

			request.setMarker(response.getMarker());

			if (!response.getIsTruncated()) {
				done = true;
			}
		}
	}
}
