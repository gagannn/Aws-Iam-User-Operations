package com.iam.user.operations;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.DeleteAccessKeyRequest;
import com.amazonaws.services.identitymanagement.model.DeleteAccessKeyResult;
import com.amazonaws.services.identitymanagement.model.NoSuchEntityException;

public class DeleteIAMAccessKey {
	public static void main(String[] args) {
		try {
			String username = "java-user-updated";
			String access_key = "AKIATG7TGEXJZ7UCZ2XV";

			final AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder.standard()
					.withCredentials(
							new AWSStaticCredentialsProvider(new BasicAWSCredentials("access-key", "secret-key")))
					.withRegion(Regions.EU_CENTRAL_1).build();

			DeleteAccessKeyRequest request = new DeleteAccessKeyRequest().withAccessKeyId(access_key)
					.withUserName(username);

			DeleteAccessKeyResult response = iam.deleteAccessKey(request);

			System.out.println("Successfully deleted access key " + access_key + " from user " + username);
		} catch (NoSuchEntityException e) {
			System.out.println(e);
		}
	}
}
