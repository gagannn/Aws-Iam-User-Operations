package com.iam.user.operations;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.CreateUserRequest;
import com.amazonaws.services.identitymanagement.model.CreateUserResult;
import com.amazonaws.services.identitymanagement.model.EntityAlreadyExistsException;

public class CreatingIAMUser {
	public static void main(String[] args) {
		try {
			String username = "java-user-updated";

			final AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder.standard()
					.withCredentials(
							new AWSStaticCredentialsProvider(new BasicAWSCredentials("access-key", "secret-key")))
					.withRegion(Regions.EU_CENTRAL_1).build();
			CreateUserRequest request = new CreateUserRequest().withUserName(username);

			CreateUserResult response = iam.createUser(request);

			System.out.println("Successfully created user: " + response.getUser().getUserName());
		} catch (EntityAlreadyExistsException e) {
			System.out.println(e);
		}
	}
}
