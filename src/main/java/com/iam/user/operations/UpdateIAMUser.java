package com.iam.user.operations;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.NoSuchEntityException;
import com.amazonaws.services.identitymanagement.model.UpdateUserRequest;
import com.amazonaws.services.identitymanagement.model.UpdateUserResult;

public class UpdateIAMUser {
	public static void main(String[] args) {
		try {
			String curName = "java-user";
			String newName = "java-user-updated";

			final AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder.standard()
					.withCredentials(
							new AWSStaticCredentialsProvider(new BasicAWSCredentials("access-key", "secret-key")))
					.withRegion(Regions.EU_CENTRAL_1).build();
			UpdateUserRequest request = new UpdateUserRequest().withUserName(curName).withNewUserName(newName);

			UpdateUserResult response = iam.updateUser(request);

			System.out.printf("Successfully updated user to username %s", newName);
		} catch (NoSuchEntityException e) {
			System.out.println(e);
		}
	}
}
