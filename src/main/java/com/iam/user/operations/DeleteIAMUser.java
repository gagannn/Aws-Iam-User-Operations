package com.iam.user.operations;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.DeleteConflictException;
import com.amazonaws.services.identitymanagement.model.DeleteUserRequest;
import com.amazonaws.services.identitymanagement.model.NoSuchEntityException;

public class DeleteIAMUser {
	public static void main(String[] args) {

		String username = "java-user-updated";

		final AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder.standard()
				.withCredentials(
						new AWSStaticCredentialsProvider(new BasicAWSCredentials("access-key", "secret-key")))
				.withRegion(Regions.EU_CENTRAL_1).build();

		DeleteUserRequest request = new DeleteUserRequest().withUserName(username);

		try {
			iam.deleteUser(request);
			System.out.println("Successfully deleted IAM user " + username);
		} catch (DeleteConflictException e) {
			System.out.println("Unable to delete user. Verify user is not" + " associated with any resources");
			throw e;
		}
		catch (NoSuchEntityException e) {
			System.out.println(e);
		}

	}
}
