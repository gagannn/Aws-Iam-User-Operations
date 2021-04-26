package com.iam.user.operations;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.NoSuchEntityException;
import com.amazonaws.services.identitymanagement.model.UpdateAccessKeyRequest;
import com.amazonaws.services.identitymanagement.model.UpdateAccessKeyResult;

public class UpdateIAMAccessKey {
	public static void main(String[] args) {
		try {

			String username = "java-user-updated";
			String access_id = "AKIATG7TGEXJZ7UCZ2XV";
			String status = "Active"; // Inactive

			final AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder.standard()
					.withCredentials(
							new AWSStaticCredentialsProvider(new BasicAWSCredentials("access-key", "secret-key")))
					.withRegion(Regions.EU_CENTRAL_1).build();

			UpdateAccessKeyRequest request = new UpdateAccessKeyRequest().withAccessKeyId(access_id)
					.withUserName(username).withStatus(status);

			UpdateAccessKeyResult response = iam.updateAccessKey(request);
			
			System.out.printf("Successfully updated status of access key %s to " + "status %s for user %s", access_id,
					status, username);
		} catch (NoSuchEntityException e) {
			System.out.println(e);
		}
	}

}
